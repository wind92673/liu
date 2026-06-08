package edu.nuc.light_system.utils;


import edu.nuc.light_system.entity.constant.Constants;
import org.apache.commons.lang3.RandomStringUtils;

public class StringTools {

    public static String getUserId(String phone) {
        return "user" + phone +getRandomNumber(Constants.LENGTH_3);
    }

    public static String getRandomNumber(Integer count) {
        return RandomStringUtils.random(count, false,true);
    }

    public static String getRandomString(Integer count) {
        return RandomStringUtils.random(count, false,true);
    }

    /**
     * 判断字符串是否为空
     * 为空则返回true，否则返回false
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null == str || "".equals(str) || "null".equals(str) || "\u0000".equals(str)) {
            return true;
        } else if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    public static boolean isNumber(String str){
        String checkNumber = "^[0-9]+$";
        if (null == str){
            return false;
        }
        return str.matches(checkNumber);
    }


}
