package com.xhMall.db.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/17,21:40
 */
public class TreeNode {

    private String id; //要显示的子节点的id
    private String text;//要显示的子节点Text
    private String iconCls;//节点图标
    private String parentId; // 父节点id
    private List<TreeNode> children; //子节点list

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public List <TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List <TreeNode> children) {
        this.children = children;
    }

    public TreeNode(){}

    public TreeNode(String id,String text,String iconCls,String parentId,List<TreeNode> children) {
        super();
        this.id = id;
        this.text = text;
        this.iconCls = iconCls;
        this.parentId = parentId;
        this.children = children;
    }

    public void addChild(TreeNode node) {
        if (this.children == null) {
            children = new ArrayList <TreeNode>();
            children.add(node);
        } else {
            children.add(node);
        }
    }
}
