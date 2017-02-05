package cn.digirun.core.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.DigestUtils;

/**
 * @ClassName: PasswordUtils
 * @Description: 密码生成工具类
 * @author 管东海
 * 
 */
public class PasswordUtils {

	private PasswordUtils() {
	}

	/**
	 * 生成扰码
	 * @author 管东海
	 * @param length 扰码长度
	 * @return 例：salt(6) -> 34BglE
	 */
	public static String salt(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	/**
	 * 生成密文
	 * @author 管东海
	 * @param password 明文
	 * @param salt 扰码
	 * @return md5(md5(password)+salt)
	 */
	public static String generator(String password, String salt) {
		return DigestUtils.md5DigestAsHex((DigestUtils.md5DigestAsHex(password.getBytes()) + salt).getBytes());
	}

	/**
	 * 比较
	 * @author 管东海
	 * @param encrptyPassword 密文
	 * @param password 明文
	 * @param salt 扰码
	 * @return true or false
	 */
	public static boolean compare(String encrptyPassword, String password, String salt) {
		return generator(password, salt).equals(encrptyPassword);
	}
}
