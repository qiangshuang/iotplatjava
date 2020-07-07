package com.ipincloud.iotbj.utils;

import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.pkcs.RSAPrivateKeyStructure;
import java.security.spec.RSAPrivateKeySpec;

public class RsaUtils {

	/** 
	 * RSA私钥解密
	 *  
	 * @param str 
	 *            加密字符串
	 * @param privateKey 
	 *            私钥 
	 * @return 字符串
	 * @throws Exception 
	 *             解密过程中的异常信息 
	 */  
	public static String decrypt(String str, String privateKey) throws Exception{
		
		//64位解码加密后的字符串
		byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8")); 
		//base64编码的私钥
		byte[] decoded = Base64.decodeBase64(privateKey);  

		RSAPrivateKeyStructure asn1PrivKey = new RSAPrivateKeyStructure((ASN1Sequence) ASN1Sequence.fromByteArray(decoded));
        RSAPrivateKeySpec rsaPrivKeySpec = new RSAPrivateKeySpec(asn1PrivKey.getModulus(), asn1PrivKey.getPrivateExponent());
        KeyFactory keyFactory=  KeyFactory.getInstance("RSA");
        RSAPrivateKey priKey= (RSAPrivateKey)keyFactory.generatePrivate(rsaPrivKeySpec);

		//RSA解密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		return  new String(cipher.doFinal(inputByte));
			

	} 
}