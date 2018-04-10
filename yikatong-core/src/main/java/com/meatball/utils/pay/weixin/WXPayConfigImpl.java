/**
 * Project Name:meatball-rest
 * File Name:WXPayConfigImpl.java
 * Package Name:com.meatball.api.ykt.parems
 * Date:2018年3月22日下午3:12:29
 * Copyright (c) 2018, zhang.xiangyu@foxmail.com All Rights Reserved.
*/
package com.meatball.utils.pay.weixin;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

/**   
 * @Title: WXPayConfigImpl.java 
 * @Package com.meatball.api.ykt.parems 
 * @Description: TODO(微信支付配置实现类) 
 * @author jw 
 * @date 2018年3月22日 下午3:12:29 
 * @version V1.0   
 */
public class WXPayConfigImpl extends WXPayConfig{
	private byte[] certData;
    private static WXPayConfigImpl INSTANCE;

    private WXPayConfigImpl() throws Exception{
		// InputStream stream = getClass().getClassLoader().getResourceAsStream("weixin/apiclient_cert.p12");
        //File file = ResourceUtils.getFile("classpath:apiclient_cert.p12");
        InputStream certStream = this.getClass().getClassLoader().getResourceAsStream("weixin/apiclient_cert.p12");//new FileInputStream(file);
        File file = new File("apiclient_cert.p12");
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public static WXPayConfigImpl getInstance() throws Exception{
        if (INSTANCE == null) {
            synchronized (WXPayConfigImpl.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WXPayConfigImpl();
                }
            }
        }
        return INSTANCE;
    }

    public String getAppID() {
        return "wx9852a1dc394adc7d";
    }

    public String getMchID() {
        return "1252626401";
    }

    public String getKey() {
        return "ffkk22lem3nb45vap01oo2m3nbvcd236";
    }

    public InputStream getCertStream() {
        ByteArrayInputStream certBis;
        certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }


    public int getHttpConnectTimeoutMs() {
        return 2000;
    }

    public int getHttpReadTimeoutMs() {
        return 10000;
    }

    IWXPayDomain getWXPayDomain() {
        return WXPayDomainSimpleImpl.instance();
    }

    public String getPrimaryDomain() {
        return "api.mch.weixin.qq.com";
    }

    public String getAlternateDomain() {
        return "api2.mch.weixin.qq.com";
    }

    @Override
    public int getReportWorkerNum() {
        return 1;
    }

    @Override
    public int getReportBatchSize() {
        return 2;
    }
}
