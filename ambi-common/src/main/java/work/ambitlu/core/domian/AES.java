package work.ambitlu.core.domian;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.UUID;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 * @date 2021-04-25 17:35
 */
public class AES {
	private static final Logger logger = LoggerFactory.getLogger(AES.class);
	public static final String DEFAULT_ENCRYPT = "5f6db7ec8325a5e4";
	private SecretKeySpec secretKey;

	public static AES getInstance() {
		return new AES();
	}

	private AES() {
	}

	public AES(String str) {
		this.setKey(str);
	}

	public SecretKey getSecretKey() {
		return this.secretKey;
	}

	public void setKey(String strKey) {
		try {
			byte[] bk = strKey.getBytes("UTF-8");
			this.secretKey = new SecretKeySpec(bk, "AES");
		} catch (Exception var3) {
			logger.error("Encrypt setKey is exception.", var3);
		}

	}

	public String encryptAES(String str) {
		String encryptStr = null;

		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(1, this.getSecretKey());
			byte[] encryptBytes = cipher.doFinal(str.getBytes("utf-8"));
			if (encryptBytes != null) {
				encryptStr = new String(Base64.getEncoder().encode(encryptBytes), "UTF-8");
			}

			return encryptStr;
		} catch (Exception var5) {
			throw new RuntimeException(var5);
		}
	}

	public byte[] encryptAES(byte[] bytes) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(1, this.getSecretKey());
			return cipher.doFinal(bytes);
		} catch (Exception var3) {
			throw new RuntimeException(var3);
		}
	}

	public String decryptAES(String str) {
		String decryptStr = null;

		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(2, this.getSecretKey());
			byte[] scrBytes = Base64.getDecoder().decode(str);
			byte[] decryptBytes = cipher.doFinal(scrBytes);
			if (decryptBytes != null) {
				decryptStr = new String(decryptBytes, "UTF-8");
			}

			return decryptStr;
		} catch (Exception var6) {
			throw new RuntimeException(var6);
		}
	}

	public byte[] decryptAES(byte[] bytes) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(2, this.getSecretKey());
			return cipher.doFinal(bytes);
		} catch (Exception var3) {
			throw new RuntimeException(var3);
		}
	}

	public String encrypt(String value, String key) throws Exception {
		this.setKey(key);
		return this.encryptAES(value);
	}

	public String decrypt(String value, String key) throws Exception {
		this.setKey(key);
		return this.decryptAES(value);
	}

	public static String generateKey() {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 16);
	}
}
