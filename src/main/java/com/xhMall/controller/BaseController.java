package com.xhMall.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xhMall.baseEnum.SessionKey;
import com.xhMall.common.constant.Constant;
import com.xhMall.common.util.CommonStringUtil;
import com.xhMall.db.entity.UserEntity;
import com.xhMall.dto.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

@Controller
public class BaseController implements Serializable{

    private static final long serialVersionUID = 6860089595632246283L;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession session;

    @Autowired
    private RestTemplate restTemplate;

    private HttpServletResponse response = null;

    /**
     *画面初始化时执行的javaScript方法
     * @return
     */
    private String onloadJsFunc;




    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpSession getSession() {
        return session;
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }


    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getOnloadJsFunc() {
        return onloadJsFunc;
    }

    public void setOnloadJsFunc(String onloadJsFunc) {
        this.onloadJsFunc = onloadJsFunc;
    }

    /**
     * 删除指定Key的Session信息
     * @param arg
     */
    public void clearSession(String arg) {
        if (!CommonStringUtil.isNullOrEmpty(arg)) {
            session.removeAttribute(arg);
        }
    }

    /**
     *
     * @param arg
     * @param obj
     */
    public void setAttributeInSession(String arg,Object obj) {
        session.setAttribute(arg,obj);
    }

    /**
     *
     * @param arg
     */
    public Object getAttributeInSession(String arg) {
        return session.getAttribute(arg);
    }

    /**
     * 设置系统语言类别
     */
    public void setLangType(String langType) {
        if (Constant.LangType.zh.toString().equals(langType)) {
            Locale locale = new Locale(Constant.LangType.zh.toString(),Constant.CountryType.CN.toString());
            setAttributeInSession(SessionKey.CurrLangType.toString(),Constant.LangType.zh);
            setAttributeInSession(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);
        } else if (Constant.LangType.jp.toString().equals(langType)){
            Locale locale = new Locale(Constant.LangType.jp.toString(),Constant.CountryType.JP.toString());
            setAttributeInSession(SessionKey.CurrLangType.toString(),Constant.LangType.jp);
            setAttributeInSession(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);
        } else {
            setAttributeInSession(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, LocaleContextHolder.getLocale());
        }
    }

    /**
     * 获取message信息
     * @param msgKey
     * @return
     */
    public String getMessageInfo(String msgKey) {
        Locale locale = request.getLocale();
        setAttributeInSession(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME,locale);
        RequestContext requestContext = new RequestContext(request);
        return requestContext.getMessage(msgKey);
    }

    /**
     * 使用SpringMvc统一方法模板（RestTemplate Post方法） 调用restful接口
     * @param url
     * @param postData
     * @param responseType
     * @param uriVariables
     * @return
     */
    public BaseResult<?> callWebApiByPost(String url, Map<String,Object> postData, TypeReference<?> responseType, Map<String,?> uriVariables){
        return callWebApi(url,HttpMethod.POST,postData,responseType,uriVariables);
    }

    /**
     * 使用SpringMvc统一方法模板（RestTemplate Get方法） 调用restful接口
     * @param url
     * @param postData
     * @param responseType
     * @param uriVariables
     * @return
     */
    public BaseResult<?> callWebApiByGet(String url, Map<String,Object> postData, TypeReference<?> responseType, Map<String,?> uriVariables){
        return callWebApi(url,HttpMethod.GET,postData,responseType,uriVariables);
    }


    /**
     * 使用SpringMvc统一方法模板（RestTemplate Post方法） 调用restful接口
     * @param url
     * @param postData
     * @param responseType
     * @param uriVariables
     * @return
     */
    public BaseResult<?> callWebApiByPost(String url, Map<String,Object> postData, TypeReference<?> responseType, Object... uriVariables){
        return callWebApi(url,HttpMethod.POST,postData,responseType,uriVariables);
    }


    /**
     * 使用SpringMvc统一方法模板（RestTemplate Get方法） 调用restful接口
     * @param url
     * @param postData
     * @param responseType
     * @param uriVariables
     * @return
     */
    public BaseResult<?> callWebApiByGet(String url, Map<String,Object> postData, TypeReference<?> responseType, Object... uriVariables){
        return callWebApi(url,HttpMethod.GET,postData,responseType,uriVariables);
    }

    /**
     * 使用SpringMvc统一方法模板（RestTemplate Post方法） 调用restful接口
     * @param url
     * @param postData
     * @param responseType
     * @return
     */
    public BaseResult<?> callWebApiByPost(String url, Map<String,Object> postData, TypeReference<?> responseType){
        return callWebApi(url,HttpMethod.POST,postData,responseType);
    }

    /**
     * 使用SpringMvc统一方法模板（RestTemplate Get方法） 调用restful接口
     * @param url
     * @param postData
     * @param responseType
     * @return
     */
    public BaseResult<?> callWebApiByGet(String url, Map<String,Object> postData, TypeReference<?> responseType){
        return callWebApi(url,HttpMethod.GET,postData,responseType);    }

    /**
     * 使用SpringMvc统一方法模板（RestTemplate） 调用restful接口
     * @param url
     * @param method
     * @param postData
     * @param responseType
     * @return
     */
    public BaseResult<?> callWebApi(String url, HttpMethod method, Map<String,Object> postData, TypeReference<?> responseType){

        BaseResult<?> baseResult = new BaseResult<>();
        String jsonString = JSON.toJSONString(postData);
        HttpHeaders headers = new HttpHeaders();
        //TODO 设置字符编码
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(jsonString,headers);

        //访问rest接口
        ResponseEntity<String> results = restTemplate.exchange(url, method,requestEntity,String.class);

        //将json字符串转换成Java对象
        baseResult = (BaseResult<?>) jsonStringToObject(results.getBody(),responseType);
        return baseResult;
    }


    /**
     * 使用SpringMvc统一方法模板（RestTemplate） 调用restful接口
     * @param url
     * @param method
     * @param postData
     * @param responseType
     * @return
     */
    public BaseResult<?> callWebApi(String url, HttpMethod method, Map<String,Object> postData, TypeReference<?> responseType,Map<String,?> uriVariables){

        BaseResult<?> baseResult = new BaseResult<>();
        String jsonString = JSON.toJSONString(postData);
        HttpHeaders headers = new HttpHeaders();
        //TODO 设置字符编码
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(jsonString,headers);

        //访问rest接口
        ResponseEntity<String> results = restTemplate.exchange(url, method,requestEntity,String.class,uriVariables);

        //将json字符串转换成Java对象
        baseResult = (BaseResult<?>) jsonStringToObject(results.getBody(),responseType);
        return baseResult;
    }


    /**
     * 使用SpringMvc统一方法模板（RestTemplate） 调用restful接口
     * @param url
     * @param method
     * @param postData
     * @param responseType
     * @return
     */
    public BaseResult<?> callWebApi(String url, HttpMethod method, Map<String,Object> postData, TypeReference<?> responseType,Object... uriVariables){

        BaseResult<?> baseResult = new BaseResult<>();
        String jsonString = JSON.toJSONString(postData);
        HttpHeaders headers = new HttpHeaders();
        //TODO 设置字符编码
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(jsonString,headers);

        //访问rest接口
        ResponseEntity<String> results = restTemplate.exchange(url, method,requestEntity,String.class,uriVariables);

        //将json字符串转换成Java对象
        baseResult = (BaseResult<?>) jsonStringToObject(results.getBody(),responseType);
        return baseResult;
    }


    /**
     * json转java对象
     * @param jsonString
     * @param responseType
     * @return
     */
    public Object jsonStringToObject(String jsonString,TypeReference<?> responseType) {
        Object object = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        try {
            object = mapper.readValue(jsonString, responseType);
        }catch (JsonParseException e) {
            e.printStackTrace();
        }catch (JsonMappingException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return  object;
    }


    /**
     * 根据key在josn中取得值
     * @param requestJson
     * @param key
     * @return
     */
    public String getParameter(JSONObject requestJson,String key) {
        return requestJson.get(key).toString();
    }

    /**
     * 根据key在josn中取得值
     * @param requestJson
     * @param key
     * @param typeReference
     * @return
     */
    public Object getParameter(JSONObject requestJson,String key,TypeReference<?> typeReference) {
        String jsonString = JSON.toJSONString(requestJson.get(key));
        return jsonStringToObject(jsonString,typeReference);
    }

    /**
     * 获取UUID
     * @return
     */
    public String getUUID() {
        return CommonStringUtil.getUUID();
    }


    /**
     * @Author: 零度亲吻gy
     * @Description: 存储登录信息
     * @Date: 16:02 2018/3/17
     * @param request，response
     */
    public void setCurrentUserInfoInSession(HttpServletRequest request, HttpServletResponse response, UserEntity userEntity) {
        request.getSession().setAttribute("currentUserInfo",userEntity);
    }

    /**
     * @Author: 零度亲吻gy
     * @Description: 获取登陆信息
     * @Date: 16:03 2018/3/17
     * @param request，response
     */
    public UserEntity getCurrentUserInfoInSession(HttpServletRequest request, HttpServletResponse response) {
        UserEntity currrentUserInfo = (UserEntity) request.getSession().getAttribute("currentUserInfo");
        return currrentUserInfo;
}

    public String getVerifyCodeInSession (HttpServletRequest request, HttpServletResponse response) {
        String verifyCode = (String)request.getSession(false).getAttribute("verifyCode");
        return  verifyCode;
    }

    public void setVerifyCodeInSession (HttpServletRequest request, HttpServletResponse response,String verifyCode) {
        request.getSession(true).setAttribute("verifyCode",verifyCode);
    }
}
