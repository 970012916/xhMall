package com.xhMall.common.constant;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/9,16:09
 */
public final class Constant {

    public static final String SYSTEM_START_BEGIN = "系统启动开始";
    public static final String SYSTEM_START_END = "系统启动结束";
    public static final String SYSTEM_START_FAIL = "系统启动失败（初始化错误）";

    public static final String READ_PROPERTY_BEGIN = "property文件信息读取开始";
    public static final String READ_PROPERTY_END = "property文件信息读取结束";
    public static final String READ_PROPERTY_DESTROY = "property文件信息释放";
    public static final String READ_PROPERTY_FAIL = "property文件信息读取失败";

    public static final String READ_COMMOX_INFO_BEGIN = "下拉列表文件信息读取开始";
    public static final String READ_COMMOX_INFO_END = "下拉列表文件信息读取结束";
    public static final String READ_COMMOX_INFO_DESTROY = "下拉列表文件信息释放";
    public static final String READ_COMMOX_INFO_FAIL = "下拉列表文件信息读取失败";

    public static final String SYSTEM_DESTROY = "系统停止日志信息";

    public static final String READ_XML_INFO_BEGIN = "XML文件信息读取开始";
    public static final String READ_XML_INFO_END = "XML文件信息读取结束";
    public static final String READ_XML_INFO_DESTROY = "XML文件信息释放";
    public static final String READ_XML_INFO_FAIL = "XML文件信息读取失败";

    public static final String POINT = ".";
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    public static final String UNDERLINE = "_";
    public static final String NEGATIVE_MONEY_PREFIX = "-";
    public static final String MONEY_SEPARATION = ",";
    public static final String MONEY_FORMART = "#,###";
    public static final String MONEY_UNIT = "元";
    public static final String SLASH = "/";
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String SINGLE_QUOTES = "'";
    public static final String FULL_WIDTH_SPACE = " ";
    public static final String TWO_FULL_WIDTH_SPACE = "  ";

    public static final String ZERO = "0";
    public static final String TWO_ZERO = "00";
    public static final String SIX_ZERO = "000000";
    public static final String TWO_NINE = "99";
    public static final String SIX_NINE= "999999";


    public static final Integer number_0 = 0;
    public static final Integer number_1 = 1;
    public static final Integer number_2 = 2;
    public static final Integer number_3 = 3;
    public static final Integer number_4 = 4;
    public static final Integer number_5 = 5;
    public static final Integer number_6 = 6;
    public static final Integer number_7 = 7;
    public static final Integer number_8 = 8;
    public static final Integer number_9 = 9;
    public static final Integer number_10 = 10;
    public static final Integer number_12 = 12;
    public static final Integer number_13 = 13;
    public static final Integer number_14 = 14;
    public static final Integer number_16 = 16;
    public static final Integer number_23 = 23;
    public static final Integer number_24 = 24;
    public static final Integer number_59 = 59;

    public static final String FILE_XLS = ".xls";
    public static final String FILE_XLSX = ".xlsx";
    public static final String FILE_CSV = ".csv";

    /**
     * 画面状态
     */
    public static final String PAGE_MODE_VIEW = "VIEW";

    public static final String PAGE_MODE_ADD = "ADD";

    public static final String PAGE_MODE_EDIT = "EDIT";

    public static final String PAGE_MODE_RESULT = "RESULT";

    /**
     * 菜单栏状态
     */
    public static final String MENU_MODE_DISABLED = "DISABLED";
    public static final String MENU_MODE_ACTIVITED = "ACTIVITED";

    /**
     * DB操作
     */
    public static final String DB_OPERATION_INSERT = "插入";
    public static final String DB_OPERATION_DELETE = "删除";
    public static final String DB_OPERATION_UPDATE = "更新";
    public static final String DB_OPERATION_SELECT = "查询";

    /**
     * 复选框选中值
     */
    public static final String TRUE = "1";

    /**
     * 复选框未选中值
     */
    public static final String FALSE = "0";

    /**
     * 布尔值
     */
    public static final String STR_TRUE = "true";
    public static final String STR_FALSE = "false";


    public static final String YEAR = "年";
    public static final String MONTH = "月";
    public static final String DAY = "日";


    public static final Integer YEAR_MIN = 1900;
    public static final Integer YEAR_MAX = 9999;


    public static final String MESSAGE_TYPE_INFO = "INFO";
    public static final String MESSAGE_TYPE_ERROR = "ERROR";

    /**
     * 下拉框首选项空值键值
     */
    public static final String COMBOX_HEADER_KEY = "-1";

    /**
     * 管理员角色code
     */
    public static final String ROLE_TYPE_CODE = "0000";


    public enum LangType {
        zh,
        jp,
        en
    }

    public enum CountryType {
        CN,
        JP,
        EN
    }

    public static enum RESULT_TYPE {
        SUCCESS,
        FAILED,
        WARNING
    }

    public static enum ROLE_TYPE{
        admin
    }

    public enum Order {
        ASC,
        DESC
    }

}
