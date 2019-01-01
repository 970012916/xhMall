package com.xhMall.global;

import com.xhMall.FileSetting;
import com.xhMall.common.constant.Constant;
import com.xhMall.common.util.LogUtil;
import com.xhMall.db.entity.base.BaseSelectItemEntity;
import com.xhMall.db.entity.base.BaseSelectListEntity;
import com.xhMall.exception.PrjException;
import com.xhMall.global.managerInterface.IGlobalManager;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/25,22:22
 */
public class XMLManager implements IGlobalManager {

    private static List<BaseSelectListEntity> selectOptionList = null;


    @Override
    public void init() throws PrjException {
        String xmlPath = "";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            xmlPath = FileSetting.SelectOptionSettingXml.getPath();
            loadMasterListInfo(xmlPath,db);
        }catch (Exception e) {
            LogUtil.error(Constant.READ_XML_INFO_FAIL + xmlPath,e);
        }
    }

    @Override
    public void destroy() {
        selectOptionList = null;
        LogUtil.debug(Constant.READ_XML_INFO_DESTROY);
    }


    /**
     * 去读文件内容刀全局对象
     * @param xmlPath
     * @param db
     */
    public void loadMasterListInfo(String xmlPath,DocumentBuilder db) {
        try{
            InputStream inputStream = XMLManager.class.getClassLoader().getResourceAsStream(xmlPath);
            LogUtil.debug(Constant.READ_XML_INFO_BEGIN);
            Document doc = db.parse(inputStream);
            NodeList nodeList = doc.getElementsByTagName("type");
            //保存下拉框列表信息的List
            List<BaseSelectListEntity> baseList = new ArrayList <BaseSelectListEntity>();

            Node node = null;
            String typeNodeName = "";
            String key = "";
            String value = "";
            for (int i = 0 ; nodeList != null && i<nodeList.getLength();i++){
                boolean bFound = false;
                node = nodeList.item(i);

                //获取节点name属性，如果没有name属性，则跳过继续处理下个节点
                Node typeNode = node.getAttributes().getNamedItem("name");
                if (typeNode == null) {
                    bFound = false;
                    continue;
                }

                typeNodeName = typeNode.getNodeValue();
                //获取下拉框列表类别名称
                List<BaseSelectItemEntity> entityList = new ArrayList <BaseSelectItemEntity>();
                for (int j = 0 ; j<node.getChildNodes().getLength();j++){
                    Node item = node.getChildNodes().item(j);
                    String nodeName = item.getNodeName();

                    if (!"#text".equals(nodeName)){
                        Node attr = item.getAttributes().getNamedItem("key");
                        if (attr == null ) {
                            continue;
                        }
                        key = attr.getNodeValue();

                        attr = item.getAttributes().getNamedItem("value");
                        if (attr == null ) {
                            continue;
                        }
                        value = attr.getNodeValue();

                        BaseSelectItemEntity entity = new BaseSelectItemEntity();
                        entity.setKey(key);
                        entity.setValue(value);
                        entityList.add(entity);
                        bFound = true;
                    }
                }
                if (bFound) {
                    BaseSelectListEntity entity = new BaseSelectListEntity();
                    entity.setSelectedKey(typeNodeName);
                    entity.setBaseSelectList(entityList);
                    baseList.add(entity);
                }
            }
            XMLManager.selectOptionList = baseList;
            LogUtil.debug(Constant.READ_COMMOX_INFO_END);

        }catch (Exception e) {
            LogUtil.debug(Constant.READ_COMMOX_INFO_FAIL,e);

        }
    }

    /**
     * 获取下拉框列表
     * @param type 下拉框数据类别
     * @return
     */
    public List<BaseSelectItemEntity> geListInfo(String type){
        List<BaseSelectItemEntity> selectedList = null;
        List<BaseSelectListEntity> baseList = XMLManager.selectOptionList;
        if (baseList != null) {
            for (BaseSelectListEntity entity : baseList ) {
                if (type != null && type.equals(entity.getSelectedKey())) {
                    selectedList = entity.getBaseSelectList();
                    break;
                }
            }
        }
        if (selectedList == null ) {
            selectedList = new ArrayList <BaseSelectItemEntity>();
        }
        return selectedList;
    }
}
