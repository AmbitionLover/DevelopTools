package work.ambitlu.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import work.ambitlu.core.domian.AjaxResult;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.function.Function;

/**
 * work.ambitlu.web.controller
 * 加解密接口
 * @author Ambi 赵帅
 * @Month 11
 */
@RestController
@RequestMapping("/de")
public class DEController {

	String BASE_SALT = "abcdefghijklmnopqrstuvwxyz0123456789";
	String BASE_SECRET = "5f6db7ec8325a5e4";
	String ENCRYPT_PREFIX = "{cipher}";
	String SHA256_PREFIX = "{sha256}";

	@GetMapping("/decrypt")
	public AjaxResult decryptC(String date,String salt){
		String result = decrypt(date, salt);
		return AjaxResult.success(result);
	}


	/**
	 * 解密
	 */
	String decrypt(String data,String salt) {
		if (StringUtils.isBlank(data)) {
			return data;
		}
		if (!data.startsWith(ENCRYPT_PREFIX)) {
			return data;
		}
		if (StringUtils.isBlank(salt)) {
			return data;
		}

		try {
			data = data.substring(ENCRYPT_PREFIX.length());
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, SECRETKEY_SUPPLIER.apply(salt));
			byte[] result = cipher.doFinal(Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8)));
			return new String(result, StandardCharsets.UTF_8);
		} catch (Exception e) {
			return data;
		}
	}

	/**
	 * 密钥生成器
	 */
	Function<String, SecretKeySpec> SECRETKEY_SUPPLIER = salt -> {
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			SecretKeySpec baseSecret = new SecretKeySpec(BASE_SECRET.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
			mac.init(baseSecret);
			return new SecretKeySpec(mac.doFinal(salt.getBytes(StandardCharsets.UTF_8)), "AES");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	};


}
