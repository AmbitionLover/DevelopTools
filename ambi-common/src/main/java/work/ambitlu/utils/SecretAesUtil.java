package work.ambitlu.utils;

import work.ambitlu.core.domian.AES;

/**
 * 企业密钥加密解密工具类
 *
 * @author Ambi 赵帅
 * @date 2021-04-25 17:35
 */
public class SecretAesUtil {

	//加密基础密钥
	private static final String secretBaseKey="QYS!@#";

	/**
	 * accessSecret加密算法
	 * @param token 加密密钥secretBaseKey+token
	 * @param secret 需要加密的
	 * @return
	 * @throws Exception
	 */
	public static String secretEncrypt(String token,String secret) throws Exception{
		String key = secretBaseKey+token;
		return AES.getInstance().encrypt(secret, key);
	}

	/**
	 * accessSecret解密算法
	 * @param token 加密密钥secretBaseKey+token
	 * @param decryptSecret 需要解密的密文
	 * @return
	 * @throws Exception
	 */
	public static String secretDecrypt(String token,String decryptSecret) throws Exception{
		String key = secretBaseKey+token;
		return AES.getInstance().decrypt(decryptSecret, key);
	}
}
