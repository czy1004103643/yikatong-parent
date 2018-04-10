/**
 * Project Name:meatball-rest
 * File Name:WXPayDomainSimpleImpl.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月22日下午3:14:18
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils.pay.weixin;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import com.meatball.utils.pay.weixin.IWXPayDomain;
import com.meatball.utils.pay.weixin.WXPayConfig;
import com.meatball.utils.pay.weixin.WXPayConstants;

/**   
 * @Title: WXPayDomainSimpleImpl.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(域名管理实现类，实现主备域名自动切换) 
 * @author jw 
 * @date 2018年3月22日 下午3:14:18 
 * @version V1.0   
 */
public class WXPayDomainSimpleImpl implements IWXPayDomain {
    private WXPayDomainSimpleImpl(){}
    private static class WxpayDomainHolder{
        private static IWXPayDomain holder = new WXPayDomainSimpleImpl();
    }
    public static IWXPayDomain instance(){
        return WxpayDomainHolder.holder;
    }

    public synchronized void report(final String domain, long elapsedTimeMillis, final Exception ex) {
        DomainStatics info = domainData.get(domain);
        if(info == null){
            info = new DomainStatics(domain);
            domainData.put(domain, info);
        }

        if(ex == null){ //success
            if(info.succCount >= 2){    //continue succ, clear error count
                info.connectTimeoutCount = info.dnsErrorCount = info.otherErrorCount = 0;
            }else{
                ++info.succCount;
            }
        }else if(ex instanceof ConnectTimeoutException){
            info.succCount = info.dnsErrorCount = 0;
            ++info.connectTimeoutCount;
        }else if(ex instanceof UnknownHostException){
            info.succCount = 0;
            ++info.dnsErrorCount;
        }else{
            info.succCount = 0;
            ++info.otherErrorCount;
        }
    }

    public synchronized DomainInfo getDomain(final WXPayConfig config) {
        DomainStatics primaryDomain = domainData.get(WXPayConstants.DOMAIN_API);
        if(primaryDomain == null ||
                primaryDomain.isGood()) {
            return new DomainInfo(WXPayConstants.DOMAIN_API, true);
        }

        long now = System.currentTimeMillis();
        if(switchToAlternateDomainTime == 0){   //first switch
            switchToAlternateDomainTime = now;
            return new DomainInfo(WXPayConstants.DOMAIN_API2, false);
        }else if(now - switchToAlternateDomainTime < MIN_SWITCH_PRIMARY_MSEC){
            DomainStatics alternateDomain = domainData.get(WXPayConstants.DOMAIN_API2);
            if(alternateDomain == null ||
                alternateDomain.isGood() ||
                alternateDomain.badCount() < primaryDomain.badCount()){
                return new DomainInfo(WXPayConstants.DOMAIN_API2, false);
            }else{
                return new DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        }else{  //force switch back
            switchToAlternateDomainTime = 0;
            primaryDomain.resetCount();
            DomainStatics alternateDomain = domainData.get(WXPayConstants.DOMAIN_API2);
            if(alternateDomain != null)
                alternateDomain.resetCount();
            return new DomainInfo(WXPayConstants.DOMAIN_API, true);
        }
    }

    static class DomainStatics {
        final String domain;
        int succCount = 0;
        int connectTimeoutCount = 0;
        int dnsErrorCount =0;
        int otherErrorCount = 0;

        DomainStatics(String domain) {
            this.domain = domain;
        }
        void resetCount(){
            succCount = connectTimeoutCount = dnsErrorCount = otherErrorCount = 0;
        }
        boolean isGood(){ return connectTimeoutCount <= 2 && dnsErrorCount <= 2; }
        int badCount(){
            return connectTimeoutCount + dnsErrorCount * 5 + otherErrorCount / 4;
        }
    }
    private final int MIN_SWITCH_PRIMARY_MSEC = 3 * 60 * 1000;  //3 minutes
    private long switchToAlternateDomainTime = 0;
    private Map<String, DomainStatics> domainData = new HashMap<String, DomainStatics>();
}

 