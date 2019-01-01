package com.xhMall.common.util;

import com.xhMall.baseEnum.SessionKey;
import com.xhMall.common.constant.Constant;
import com.xhMall.controller.UserController;
import com.xhMall.db.entity.base.BaseForm;
import com.xhMall.exception.DBException;
import com.xhMall.exception.PrjException;
import com.xhMall.global.GlobalContext;
import com.xhMall.global.GlobalManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/4,22:26
 */
public class LogUtil {

    private static final String DEFAULT_USER_ID = "****************";
    private static final String DEFAULT_PAGE_ID = "****************";
    private static final Logger loggerAll = LoggerFactory.getLogger("ff");


    public static void doSkip(){
        /**
         * 暂不处理
         */
    }

    /**
     * trace级别
     * @param msgId
     */
    public static void trace(String msgId) {
        loggerAll.trace(getLogInfo(msgId));
    }

    public static void trace(String msgId,String msgPram) {
        String[] msgPrams = {msgPram};
        loggerAll.trace(getLogInfo(msgId,msgPrams));
    }


    public static void trace(String msgId,String[] msgPrams) {
        loggerAll.trace(getLogInfo(msgId,msgPrams));
    }

    public static void trace(Exception ex) {
        loggerAll.trace(getLogInfo(null,null,ex.getMessage()),ex);
    }


    public static void trace(String msgId,Throwable t) {
        loggerAll.trace(getLogInfo(msgId,null,null),t);
    }



    public static void debug(String msgId) {
        loggerAll.debug(getLogInfo(msgId));
    }


    public static void debug(String msgId,String msgPram) {
        String[] msgParms = {msgPram};
        loggerAll.debug(getLogInfo(msgId,msgParms));
    }

    public static void debug(String msgId,String[] msgPrams){
        loggerAll.debug(getLogInfo(msgId,msgPrams));
    }


    public static void debug(Exception e) {
        loggerAll.debug(getLogInfo(null,null,e.getMessage()),e);
    }


    public static void debug(String msgId,Throwable t) {
        loggerAll.debug(getLogInfo(msgId,null,null),t);
    }


    public static void info(String msgId){
        loggerAll.info(getLogInfo(msgId));
    }

    public static void info(String msgId,String msgPram){
        String[] msgPrams = {msgPram};
        loggerAll.info(getLogInfo(msgId,msgPrams));
    }

    public static void info(String msgId,String[] msgPrams){
        loggerAll.info(getLogInfo(msgId,msgPrams));
    }

    public static void info(Exception e) {
        loggerAll.info(getLogInfo(null,null,e.getMessage()),e);
    }

    public static void info(String msgId,Throwable t) {
        loggerAll.info(getLogInfo(msgId,null,null),t);
    }

    public static void warn(String msgId){
        loggerAll.warn(getLogInfo(msgId));
    }

    public static void warn(String msgId,String msgPram){
        String[] msgPrams = {msgPram};
        loggerAll.warn(getLogInfo(msgId,msgPrams));
    }

    public static void warn(String msgId,String[] msgPrams){
        loggerAll.warn(getLogInfo(msgId,msgPrams));
    }

    public static void warn(Exception e){
        loggerAll.warn(getLogInfo(null,null,e.getMessage()),e);
    }

    public static void warn(String msgId,Throwable t) {
        loggerAll.warn(getLogInfo(msgId,null,null),t);
    }



    public static void error(String msgId){
        loggerAll.error(getLogInfo(msgId));
    }

    public static void error(String msgId,String msgPram){
        String[] msgPrams = {msgPram};
        loggerAll.error(getLogInfo(msgId,msgPrams));
    }

    public static void error(String msgId,String[] msgPrams){
        loggerAll.error(getLogInfo(msgId,msgPrams));
    }

    public static void error(Exception e){
        loggerAll.error(getLogInfo(null,null,e.getMessage()),e);
    }

    public static void error(String msgId,Throwable t) {
        loggerAll.error(getLogInfo(msgId,null,null),t);
    }

    /**
     * sql日志信息打印
     * @param sql
     * @param args
     */
    public static void writeSQLLog(String sql,Object[] args) {

    }

    /**
     * sql执行异常日志信息打印
     * @param errInfo
     * @param operation
     */
    public static void writeSQLErrorLog(String errInfo,String operation){

    }


    private static String getLogInfo(String msgId) {
        return getLogInfo(msgId,null,null);
    }

    private static String getLogInfo(String msgId,String[] params) {
        return getLogInfo(msgId,params,null);
    }

    private static String getLogInfo(String msgId,String[] params,String otherInfo) {
        String strLogInfo = "";
        try {
            StackTraceElement[] stack = Thread.currentThread().getStackTrace();
            StackTraceElement ste = stack[4];
            String threadId = Long.toString(Thread.currentThread().getId());
            String className = ste.getClassName();
            String methodName = ste.getMethodName();
            String userId = DEFAULT_USER_ID;
            if(GlobalContext.getCertificateUserInfo() != null) {
                userId = GlobalContext.getCertificateUserInfo().getUserId();
            }
            String currPageId = DEFAULT_PAGE_ID;
            Object sessionObj = GlobalContext.getSessionInfo(SessionKey.CurrPageId.toString());
            if(null != sessionObj) {
                currPageId = sessionObj.toString();
            }
            String msgInfo = "";
            if(msgId != null) {
                if(GlobalManager.getPropertyManager() != null) {
                    if (params == null) {
                        msgInfo = GlobalContext.getMessage(msgId);
                    }else {
                        msgInfo = GlobalContext.getMessage(msgId,params);
                    }
                }
            }else {
                msgId = "";
            }
            strLogInfo = threadId + Constant.SPACE + className + Constant.POINT + methodName + Constant.SPACE + Constant.NEGATIVE_MONEY_PREFIX;
            if (!userId.equals("")){
                strLogInfo = strLogInfo + Constant.SPACE + userId;
            }
            if (!currPageId.equals("")) {
                strLogInfo = strLogInfo + Constant.SPACE + currPageId;
            }
            if(!msgId.equals("")) {
                strLogInfo = strLogInfo + Constant.SPACE + msgId;
            }
            if (!msgInfo.equals("")) {
                strLogInfo = strLogInfo + Constant.SPACE + msgInfo;
            }
            if (otherInfo != null) {
                strLogInfo = strLogInfo + Constant.SPACE + otherInfo;
            }
        }catch (Exception e) {
            doSkip();
        }
        return  strLogInfo;
    }



    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        BaseForm form = new BaseForm();
        if(args != null && args.length>0) {
            for (Object obj : args) {
                if (obj != null) {
                    String className = obj.getClass().getSimpleName();
                    if (className.equals("BaseForm")) {
                        form = (BaseForm) obj;
                    }
                }
            }
        }
        Object result = null;
        String threadId = Long.toString(Thread.currentThread().getId());
        String className = pjp.getTarget().getClass().getName();
        String methodName = pjp.getSignature().getName();
        String logInfoHead = threadId + Constant.SPACE + className + "." + methodName + Constant.SPACE
            + Constant.NEGATIVE_MONEY_PREFIX ;
        String userId = DEFAULT_USER_ID;

        if(GlobalContext.getCertificateUserInfo() != null) {
           userId = GlobalContext.getCertificateUserInfo().getUserId();
        }
        logInfoHead += Constant.SPACE + userId;
        String pageID = DEFAULT_PAGE_ID;
        if(form != null) {
            pageID = form.getPageId();
        }

        if(!pageID.equals("")) {
            logInfoHead += Constant.SPACE + pageID;
        }

        String btnName = "";
        if(form != null) {
            btnName = form.getButtonName();
        }

        writeOperateStartLog(logInfoHead,pageID,btnName,"");
        try {
            result = pjp.proceed();
        }catch (DataAccessException e) {
            throw new DBException(e);
        }catch (DBException e) {
            throw e;
        }catch (Exception e) {
            if(e.getCause()!= null && e.getCause() instanceof SQLException) {
                throw new DBException(e);
            }else {
                throw new PrjException(e);
            }
        }catch (Throwable t) {
            if(t.getCause()!= null && t.getCause() instanceof SQLException) {
                throw new DBException(t);
            }else {
                throw new PrjException(t);
            }
        }
        if(DEFAULT_USER_ID.equals(userId)) {
            logInfoHead = threadId + Constant.SPACE + className + "." + methodName + Constant.SPACE
                    + Constant.NEGATIVE_MONEY_PREFIX ;
            if(GlobalContext.getCertificateUserInfo() != null) {
                userId = GlobalContext.getCertificateUserInfo().getUserId();
            }
            logInfoHead = logInfoHead + Constant.SPACE + userId;
            if(!pageID.equals("")) {
                logInfoHead += logInfoHead + Constant.SPACE + pageID ;
            }
        }

        String otherInfo = "";
        writeOperateStartLog(logInfoHead,pageID,btnName,otherInfo);
        return result;
    }

    /**
     * 输入进入方法时的日志信息
     * @param logInfoHead 日志头部信息
     * @param pageId 画面ID
     * @param btnName 按钮名称
     * @param otherInfo 其他信息
     */
    private static void writeOperateStartLog(String logInfoHead,String pageId,String btnName, String otherInfo) {
        //String msgInfo = "";
        String msgInfo = GlobalContext.getMessage("LS9001I");
        String logInfo = logInfoHead + Constant.SPACE + "LS9001I" + Constant.SPACE + msgInfo;
        loggerAll.info(logInfo);
    }

    /**
     * 输出推出方法时的日志信息
     * @param logInfoHead
     * @param pageId
     * @param btnName
     * @param otherInfo
     */
    private static void writeOperateEndLog(String logInfoHead,String pageId,String btnName, String otherInfo) {
        //String msgInfo = "";
        String msgInfo = GlobalContext.getMessage("LS9002I");
        String logInfo = logInfoHead + Constant.SPACE + "LS9002I" + Constant.SPACE + msgInfo;
        loggerAll.info(logInfo);
    }

    /**
     * error 级别日志打印
     * @param msgId 消息ID
     */
   /* public static void error(String msgId) {
        loggerAll.error();
    }

    public static void error(Exception ex) {
        loggerAll.error();
    }*/




}
