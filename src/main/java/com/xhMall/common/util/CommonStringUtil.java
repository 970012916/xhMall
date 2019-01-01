package com.xhMall.common.util;

import com.xhMall.common.constant.Constant;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonStringUtil {


    public CommonStringUtil(){

    }

    /**
     * 获取字符段长度
     * @param str
     * @return
     */
    public static int getByteLength(String str) {
        if (isNullOrEmpty(str)){
            return 0;
        }
        byte[] len = str.getBytes();
        return  len.length;
    }


    //判断是否为空
    public static boolean isNull(String str) {
        if(null == str) {
            return true;
        }else {
            return  false;
        }
    }

    /**
     * 数值型
     * @param param
     * @return
     */
    public static boolean isNumber(String param) {
        if (isNullOrEmpty(param)){
            return false;
        }

        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(param);
        return isNum.matches();
    }


    /**
     * 前面补零
     * @param param
     * @param len
     * @return
     */
    public static String addPrefixZero(String param,int len) {
        if (isNullOrEmpty(param)) {
            return  "";
        } else {
            if (len <= getByteLength(param)){
                return param;
            }
        }

        String strHead = String.format("0%" + len + "d",0);
        String strPrefixZero = strHead + param;
        String strValue = strPrefixZero.substring(getByteLength(strPrefixZero) - len);
        return strValue;

    }

    //判断是否为空
    public static boolean isEmpty(String str) {
        return str.isEmpty();
    }

    //判断是否为空和null
    public static boolean isNullOrEmpty(String param) {
        if(param == null || param.isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    //判断是否为空和null
    public static boolean isNullOrEmpty(Object param) {
        if(param == null || param.toString().isEmpty()) {
            return true;
        }else {
            return false;
        }
    }

    /**
     * 字符的全半角检查
     * @param param
     * @return
     */
    public static boolean isHalfKaku(String param){
        if (isNullOrEmpty(param)) {
            if (param.length() != getByteLength(param)) {
                return false;
            } else {
                return isHalfKaku2(param);
            }
        }
        return true;
    }


    public static boolean isHalfKaku2(String param){
        if (isNullOrEmpty(param)) {
            if (param.length() != getByteLength(param)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 字符的全半角检查
     * @param param
     * @return
     */
    public static boolean isHalfKaku3(String param){
        if (isNullOrEmpty(param)) {
            Pattern p = Pattern.compile("^[^.-]*$");
            Matcher m = p.matcher(param);
            return m.matches();
        }
        return true;
    }

    /**
     * String to Integer
     * @param value
     * @return
     */
    public static Integer formatStringToInteger(String value) {
        if (isNumber(value)){
            return Integer.valueOf(value);
        } else {
            return null;
        }

    }

    /**
     * Null值空位
     * @param value
     * @return
     */
    public static String converNullToEmpty(String value) {
        if (value == null) {
            return Constant.EMPTY;
        }else {
            return value;
        }
    }


    /**
     * 数值格式化
     * @param obj
     * @return
     */
    public static String formatNumber(Object obj) {
        String numberFormat = "";
        DecimalFormat df = new DecimalFormat("###,###,##0");
        if (!isNullOrEmpty(obj) && isNumber(obj.toString())){
            BigDecimal db = new BigDecimal(obj.toString());
            numberFormat = df.format(db);
        }

        return numberFormat;
    }


    /**
     * 获取UUID
     * @return
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        return uuid;
    }

    /**
     * 前后去半角空格
     * @param param
     * @return
     */
    public static String trimAllSpace(String param){
        if (param != null) {
            param = param.trim();
        }

        return param;
    }

    /**
     * 前后去半角空格
     * @param param
     * @return
     */
    public static Object[] trimAllSpace(Object[] param){
        if (param != null) {
            for (int i=0 ; i<param.length ; i++ ) {
                param[i] = trimAllSpace((String)param[i]);
            }
        }
        return param;
    }


    /**
     * @Author: 零度亲吻gy
     * @Description:去除字符串中头部和尾部所包含的空格（包括:空格(全角，半角)、制表符、换页符等）
     * @Date: 23:31 2018/2/6
     * @param source
     */
    public static String trim(String source){

        if(null!=source && !"".equals(source)){
            return source == null ? source : source.replaceAll("^[\\s　]*|[\\s　]*$", "");
        }
        return source;
    }
    /**
     * @Author: 零度亲吻gy
     * @Description: 去除对象中String类型的字符串首尾的空格（包括:空格(全角，半角)、制表符、换页符等）
     * @Date: 23:04 2018/2/7
     * @param obj
     */
    public static Object trim(Object obj) throws Exception{
       Class clazz = obj.getClass();
        Field[] fields =  clazz.getDeclaredFields();
        for (Field field :fields) {
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(),clazz);
            Method getMethod = propertyDescriptor.getReadMethod();
            String type  = field.getGenericType().toString();
            if(type.equals("class java.lang.String")) {
                String value= (String) getMethod.invoke(obj);
                value = trim(value);
                Method setMethod = propertyDescriptor.getWriteMethod();
                setMethod.invoke(obj,value);
            }
        }
        return obj;
    }

}
