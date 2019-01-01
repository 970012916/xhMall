package com.xhMall.db.entity.base;

import com.xhMall.baseEnum.SessionKey;
import com.xhMall.common.util.CommonStringUtil;
import com.xhMall.global.GlobalContext;

import java.io.Serializable;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/9,16:03
 */
public class BaseForm implements Serializable {

    private static final long serialVersionUID = 6191660259679934603L;
    private String pageId;
    private String buttonName;
    private String pageName;
    private String buttonId;
    private String pageMode;
    private String currLang;
    //画面显示用messageId
    private String msgId;


    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public String getPageMode() {
        return pageMode;
    }

    public void setPageMode(String pageMode) {
        this.pageMode = pageMode;
    }

    public String getCurrLang() {
        return currLang;
    }

    public void setCurrLang(String currLang) {
        if (!CommonStringUtil.isNullOrEmpty(GlobalContext.getSessionInfo(SessionKey.CurrLangType.toString()))) {
            this.currLang = GlobalContext.getSessionInfo(SessionKey.CurrLangType.toString()).toString();
        } else {
            this.currLang = "";
        }
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }


    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }




}
