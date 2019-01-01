package com.xhMall.global;

import com.xhMall.baseEnum.SessionKey;
import com.xhMall.common.util.CommonStringUtil;
import com.xhMall.db.entity.CertificateUserInfo;
import com.xhMall.common.util.LogUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/10,20:48
 */
public class GlobalContext  {

    public GlobalContext(){

    }

    /**
     * 获取当前线程的request请求
     * @return
     */
    public static HttpServletRequest getHttpServletRequest(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                                            .getRequest();
        return request;
    }

    public static RequestContext getRequestContext(){
        HttpServletRequest request = getHttpServletRequest();
        RequestContext requestContext = new RequestContext(request);
        return requestContext;
    }

    public static HttpSession getSession(){
        HttpServletRequest request = getHttpServletRequest();
        HttpSession session = request.getSession();
        return session;
    }

    /**
     * 获取系统物理路径
     * @param path
     * @return
     */
    public static String getRealPath(String path){
        HttpServletRequest request = getHttpServletRequest();
        return  request.getServletContext().getRealPath(path);
    }

    /**
     * 获取相对路径
     * @return
     */
    public static String getContextPath(){
        HttpServletRequest request = getHttpServletRequest();
        return  request.getServletContext().getContextPath();
    }

    public static CertificateUserInfo getCertificateUserInfo(){
        CertificateUserInfo certificateUserInfo = null;
        try {
            certificateUserInfo = (CertificateUserInfo) getSessionInfo(SessionKey.CertificateUserInfo.toString());//.getAttribute(SessionKey.CertificateUserInfo.toString());
        }catch (Exception e) {
            LogUtil.doSkip();
        }
        return  certificateUserInfo;
    }


    /**
     * 获取指定的Session信息
     * @param sessionKey
     * @return
     */
    public static Object getSessionInfo(String sessionKey){
        Object obj = null;
        try {
            HttpServletRequest request = getHttpServletRequest();
            HttpSession session = request.getSession();

            if (null == session) {
                return null;
            }

            obj = session.getAttribute(sessionKey);
        } catch (Exception e) {
            LogUtil.doSkip();
        }

        return obj;
    }


    public static void clearSession(){
        HttpServletRequest request = getHttpServletRequest();
        HttpSession session = request.getSession();
        if (null == session) {
            return;
        }

        SessionKey[] keyList = SessionKey.values();
        for (SessionKey key : keyList) {
            session.removeAttribute(key.toString());
        }
    }

    public static boolean isLogin(){
        CertificateUserInfo certificateUserInfo = getCertificateUserInfo();
        boolean result = false;
        if (null != certificateUserInfo) {
            result = true;
        }
        return result;
    }

    /**
     * 根据messageId获取message信息
     * @param msgId
     * @return
     */
    public static String getMessage(String msgId) {
        String msgInfo = "";
        try {
            if(!CommonStringUtil.isNullOrEmpty(msgId)){
                RequestContext requestContext = GlobalContext.getRequestContext();
                msgInfo = requestContext.getMessage(msgId);
            }
        }catch (Exception e) {
            LogUtil.doSkip();
        }
        return msgInfo;
    }

    public static String getMessage(String msgId,Object[] params) {
        String msgInfo = "";
        try {
            if(!CommonStringUtil.isNullOrEmpty(msgId)){
                RequestContext requestContext = GlobalContext.getRequestContext();
                msgInfo = requestContext.getMessage(msgId);
                msgInfo = MessageFormat.format(msgInfo,params);
            }
        }catch (Exception e) {
            LogUtil.doSkip();
        }
        return msgInfo;
    }

    public static String getMessage(String msgId,String params) {
        String msgInfo = "";
        try {
            if(!CommonStringUtil.isNullOrEmpty(msgId)){
                RequestContext requestContext = GlobalContext.getRequestContext();
                msgInfo = requestContext.getMessage(msgId);
                msgInfo = MessageFormat.format(msgInfo,params);
            }
        }catch (Exception e) {
            LogUtil.doSkip();
        }
        return msgInfo;
    }


}
