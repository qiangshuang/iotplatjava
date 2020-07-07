package com.ipincloud.iotbj.sys.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {

	// 过期时间24小时
	private static final long EXPRIE_TIME = 24*60*60*1000;
	
	public static boolean verify(String token, Long userId,String userName) {

		try {
			Algorithm algorithm = Algorithm.HMAC256(userName);
			JWTVerifier verifier = JWT.require(algorithm)
					.withClaim("user_id", userId)
					.build();

			verifier.verify(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static Long getUserId(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("user_id").asLong();
		} catch (JWTDecodeException e) {
			return null;
		}
	}
	
	public static String sign(Long userId,String userName) throws UnsupportedEncodingException {
		Date date = new Date(System.currentTimeMillis()+EXPRIE_TIME);
		Algorithm algorithm = Algorithm.HMAC256(userName);
		// 附带userId信息
		return JWT.create()
				.withClaim("user_id", userId)
				.withExpiresAt(date)
				.sign(algorithm);
	}


}
