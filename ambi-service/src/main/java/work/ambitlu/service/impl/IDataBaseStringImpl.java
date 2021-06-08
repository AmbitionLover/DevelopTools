package work.ambitlu.service.impl;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Service;
import work.ambitlu.service.IDataBaseString;

import javax.annotation.Resource;

/**
 * 一些声明信息
 *
 * @author Ambi 赵帅
 */
@Service
public class IDataBaseStringImpl implements IDataBaseString {


	@Resource(name = "jasyptStringEncryptor")
	private StringEncryptor stringEncryptor;


	@Override
	public String dec(String s) {
		return stringEncryptor.decrypt(s);
	}
}
