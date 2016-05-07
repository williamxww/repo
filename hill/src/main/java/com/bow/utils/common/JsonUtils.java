/**  
 * @FileName: JsonUtils.java 
 * @Package com.bow.utils.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.utils.common;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bow.model.common.EasyUiTreeNode;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName: JsonUtils
 * @Description: 处理所有Json事务
 * @author ViVi
 * @date 2015年9月13日 下午6:10:48
 */

public class JsonUtils {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * 
     * @Description: 将列表转为easyui可以识别的tree
     * @param nodeList
     * @return
     * @throws JsonProcessingException
     */
    public static String toEasyUiTree(List<EasyUiTreeNode> nodeList) throws JsonProcessingException {
        List<EasyUiTreeNode> roots = getRoots(nodeList);
        ObjectMapper objectMapper = new ObjectMapper();
        // NULL 不参与序列化
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        return objectMapper.writeValueAsString(roots);
    }

    private static List<EasyUiTreeNode> getRoots(List<EasyUiTreeNode> nodeList) {
        List<EasyUiTreeNode> roots = new ArrayList<EasyUiTreeNode>();
        for (EasyUiTreeNode node : nodeList) {
            if (isRoot(node, nodeList)) {
                roots.add(node);
            }
        }

        // 循环给每个root设置children 并且递归调用
        nodeList.removeAll(roots);
        for (EasyUiTreeNode root : roots) {
            buildChildren(root, nodeList);
        }
        return roots;
    }

    /**
     * 
     * @Description: 从待选集合remaining中找root的子节点，找到就将子节点放到children属性中
     * 
     *               并将parent的state置为closed
     * @param root
     * @param descendant
     * @return
     */
    private static void buildChildren(EasyUiTreeNode parent, List<EasyUiTreeNode> remaining) {
        for (EasyUiTreeNode node : remaining) {
            // 发现子节点就放到children,接着就找子节点的子节点
            if (parent.getId().equals(node.getPid())) {
                if (parent.getChildren() == null) {
                    List<EasyUiTreeNode> children = new ArrayList<EasyUiTreeNode>();
                    children.add(node);
                    parent.setChildren(children);
                } else {
                    parent.getChildren().add(node);
                }
                buildChildren(node, remaining);

                parent.setState("closed");// 有子节点时，先不完全展开
            }
        }
    }


    /**
     * 
     * @Description: 在nodeList中不存在given父节点则given就是根节点
     * @param given
     * @param nodeList
     * @return
     */
    private static boolean isRoot(EasyUiTreeNode given, List<EasyUiTreeNode> nodeList) {
        for (EasyUiTreeNode node : nodeList) {
            if (given.getPid().equals(node.getId())) {
                return false;
            }
        }
        logger.debug("找到root{}", given);
        return true;
    }

    public static String toJson(Object param) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        // NULL 不参与序列化
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        return objectMapper.writeValueAsString(param);
    }

}
