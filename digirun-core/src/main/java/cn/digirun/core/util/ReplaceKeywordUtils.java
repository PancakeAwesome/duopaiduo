package cn.digirun.core.util;

import org.springframework.util.StringUtils;

/** 
 * @ClassName: ReplaceKeywordUtils 
 * @Description: 关键字替换工具类
 * @author 管东海
 *  
 */
public class ReplaceKeywordUtils {

	private ReplaceKeywordUtils() {
	}

	/**
	 * @author 管东海
	 * @param keyword 关键字
	 * @param prefix 保留前几位
	 * @param suffix 保留后几位
	 * @return 例: replace("13913912345",3,3) -> 139*****345
	 */
	public static String replace(String keyword, int prefix, int suffix) {
		if (StringUtils.isEmpty(keyword))
			return "";

		String prefixStr = keyword.substring(0, prefix);
		String suffixStr = keyword.substring(keyword.length() - suffix);
		String point = "";

		int loop = keyword.length() - prefixStr.length() - suffixStr.length();

		for (int i = 0; i < loop; i++)
			point += "*";
		return prefixStr + point + suffixStr;
	}
}
