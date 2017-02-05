package cn.digirun.component.item.service.util.commons;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUitl {

	public static String getPinyiin(String resource){
		
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();  
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		String result = "";
		char [] stringArr = resource.toCharArray();
		try {
			for(char str : stringArr){
				if(null != PinyinHelper.toHanyuPinyinStringArray(str, format)){
					result += PinyinHelper.toHanyuPinyinStringArray(str, format)[0];
				}
				
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
