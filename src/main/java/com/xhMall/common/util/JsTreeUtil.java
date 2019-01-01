//package com.xhMall.common.util;
//
//import com.xhMall.db.entity.TreeNode;
//import com.xhMall.db.entity.UnitLevelEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by sheting on Administrator
// * DateTime  2018/9/17,22:36
// */
//public class JsTreeUtil {
//
//    public static List<TreeNode> tree(List<TreeNode> nodes, String id) {
//
//        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
//        for (TreeNode treeNode : treeNodes) {
//            TreeNode node = new TreeNode();
//            node.setId(treeNode.getId());
//            node.setText(treeNode.getText());
//            node.setParentId(treeNode.getParentId());
//            String path_code = id + node.getId();
//            if (!CommonStringUtil.isNullOrEmpty(path_code) &&
//                    path_code.trim().equals(node.getParentId()) &&
//                    !CommonStringUtil.isNullOrEmpty(node.getParentId())){
//                treeNode.setChildren(tree(nodes,path_code));
//                treeNodes.add(node);
//            }
//        }
//        return treeNodes;
//    }
//}
