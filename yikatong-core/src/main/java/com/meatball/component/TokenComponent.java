/** 
 * Project Name:meatball-core 
 * File Name:TokenUtil.java 
 * Package Name:com.meatball.utils 
 * Date:2017年10月15日下午8:51:56 
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved. 
 */
package com.meatball.component;

import java.security.Key;
import java.util.Date;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.meatball.component.properties.Secret;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @Title: TokenUtil.java
 * @Package com.meatball.utils
 * @Description: TODO(JWT token加密验证)
 * @author 張翔宇
 * @date 2017年10月15日 下午8:51:56
 * @version V1.0
 */
@Component
public class TokenComponent {
	private static final Logger log = LoggerFactory.getLogger(TokenComponent.class);
	@Resource
	private Secret secret;
	
	/**
	 * @Title: createJWT 
	 * @Description: TODO(创建JWT) 
	 * @param id
	 * @param issuer JWT的签发者，是否使用是可选的
	 * @param subject	JWT所面向的用户，是否使用是可选的
	 * @param ttlMillis
	 * @return String    返回类型
	 */
	public String createJWT(Long id) {

		// 使用JWT签名算法来签署令牌
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		log.info(secret.getKey());
		// 我们将用Secret的秘密签署我们的JWT
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret.getKey());
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		// 设置JWT声明
		JwtBuilder builder = Jwts.builder()
				.setId(id.toString())
				.setIssuedAt(now)
				.setSubject(secret.getSubject())
				.setIssuer(secret.getIssuer())
				.signWith(signatureAlgorithm, signingKey);

		// 设置过期时间
		if (secret.getTtlMillis() >= 0) {
			long expMillis = nowMillis + secret.getTtlMillis();
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		// 构建JWT并将其序列化为一个紧凑、安全的url字符串
		log.info(builder.compact());
		return builder.compact();
	}

	/**
	 * @Title: parseJWT 
	 * @Description: TODO(读取JWT) 
	 * @param jwt
	 * @return String 返回用户ID
	 */
	public long parseJWT(String jwt) {
		// 如果不是一个签名的JWS，这一行将抛出一个异常 (as expected)
		Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret.getKey())).parseClaimsJws(jwt).getBody();
		log.info("ID: " + claims.getId());
		log.info("Subject: " + claims.getSubject());
		log.info("Issuer: " + claims.getIssuer());
		log.info("Expiration: " + claims.getExpiration());
		return Long.parseLong(claims.getId());
	}
}
