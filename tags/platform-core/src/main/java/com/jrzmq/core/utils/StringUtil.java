package com.jrzmq.core.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.jrzmq.core.constant.PatternConstant;



public class StringUtil extends StringUtils {
    /**
     * 判断是否是手机的正则表达式
     */
    public static final Pattern Pattern_Mobile = Pattern.compile("^" + PatternConstant.PHONE + "$");
    
    public static boolean isEmpty(String str) {
        return (null == str || "".equals(str));
    }
    
    /**
     * 邮箱验证
     * @param email
     * @return wj
     */
    public static boolean isEmail(String email){     
        String str="^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);     
        Matcher m = p.matcher(email);     
        return m.matches();     
    } 
    
    /**
     * 创建num位的随机数字
     * @param num 大于0
     * @return
     */
    public static String createFigure(int num){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < num; i++){
            Random random = new Random();
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
    
    /**
     * 判断是否是手机号码
     * @param str - String
     * @return true or false
     */
    public static boolean isMobile(String str){
        if(str == null || str.length() != 11) {
            return false;
        }
        Matcher m = Pattern_Mobile.matcher(str);
        return m.matches();
    }
    
    /**
     * 根据手机号码和明文密码，生成加密后的密码
     * @param userName 手机号码
     * @param password 密码
     * @return
     */
    public static String createPassword(String cellphone, String password){
       return MD5Util.MD5Encode(MD5Util.MD5Encode(cellphone, "UTF-8").substring(0, 10) + password, "UTF-8");
    }
    
    
    /**
     * 隐藏用户名
     * 保留首位两个字节(注意中文和英文)
     * @param name
     * @return
     */
    public static String hideUserName(String userName) {
        if(null == userName || userName.length() < 2) {
            return "***";
        } else {
            int length = userName.length();
            
            StringBuffer sb = new StringBuffer();
            if (isChinese(userName.charAt(0))) {
                sb.append(userName.charAt(0));
            } else {
                sb.append(userName.charAt(0)).append(userName.charAt(1));
            }
            
            sb.append("***");
            
            if (isChinese(userName.charAt(length - 1))) {
                sb.append(userName.charAt(length - 1));
            } else {
                sb.append(userName.charAt(length - 2)).append(userName.charAt(length - 1));
            }
            
            return sb.toString();
        }
    }
    
    /**
     * 根据Unicode编码完美的判断中文汉字和符号
     * @param c
     * @return
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }
    
    /**
     * 根据usergent获取来源
     * @param userAgent
     * @return
     */
    public static Integer getTerminalByUserAgent(String userAgent){
        Integer terminal = 1;
        if (userAgent.toLowerCase().contains("android")) {
            terminal = 4;
        } else if (userAgent.toLowerCase().contains("ios")) {
            terminal = 3;
        }
        return terminal;
    }

}
