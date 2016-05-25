package com.bow.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * <pre>
 * public static void main(String[] args)
 * {
 *     List<String> paths = new ArrayList<String>();
 *     paths.add("a/b/c");
 *     paths.add("a/b/d");
 *     paths.add("e");
 *     System.out.println(JSON.toJSONString(buildTree(paths)));
 * }
 * </pre>
 *
 * @author acer
 * @version C10 2016年5月25日
 * @since SDP V300R003C10
 */
public class TreeGridUtil
{
    private static final String SPLITTER = "/";
    public static List<TreeGridNode> buildTree(List<String> paths)
    {
        List<TreeGridNode> tree = new ArrayList<TreeGridNode>();
        for (String path : paths)
        {
            appendNodeToTree(path, tree);
        }
        return tree;
    }
    
    /**
     * 
     * 将a/b/c放入tree中
     *
     * @author acer
     * @param path
     * @param tree
     */
    private static void appendNodeToTree(String path, List<TreeGridNode> tree)
    {
        int index = path.indexOf(SPLITTER);
        String currentPath = "";
        if (index > 0)
        {
            currentPath = path.substring(0, index);
            String subUrl = path.substring(index + 1);
            List<TreeGridNode> children = addNodeIfAbsent(currentPath, tree);
            appendNodeToTree(subUrl, children);
        }
        else
        {
            currentPath = path;
            addNodeIfAbsent(currentPath, tree);
        }
        
    }
    
    /**
     * 
     * 将a/b/c中a放到树中去，然后返回a节点对应的children，便于下次放b
     *
     * @author acer
     * @param currentPath
     * @param currentTree
     * @return
     */
    private static List<TreeGridNode> addNodeIfAbsent(String currentPath, List<TreeGridNode> currentTree)
    {
        boolean matched = false;
        for (TreeGridNode node : currentTree)
        {
            if (currentPath.equals(node.getName()))
            {
                matched = true;
                return node.getChildren();
            }
        }
        // 当前树中没有找到匹配的就新建一个节点放入其中
        if (!matched)
        {
            TreeGridNode newNode = new TreeGridNode();
            newNode.setName(currentPath);
            newNode.setChildren(new ArrayList<TreeGridNode>());
            currentTree.add(newNode);
            return newNode.getChildren();
        }
        return null;
    }
    
    public static TreeGridNode generateNode(String path)
    {
        if (path.startsWith(SPLITTER))
        {
            path = path.substring(1);
        }
        List<TreeGridNode> nodes = new ArrayList<TreeGridNode>();
        String[] ps = path.split(SPLITTER);
        for (String p : ps)
        {
            TreeGridNode node = new TreeGridNode();
            node.setName(p);
            node.setChildren(new ArrayList<TreeGridNode>());
            nodes.add(node);
        }
        for (int i = nodes.size() - 1; i > 0; i--)
        {
            // 后一个节点放到前一个节点的子节点里面
            nodes.get(i - 1).getChildren().add(nodes.get(i));
        }
        // 将根节点返回
        return nodes.get(0);
    }
    
    public static void main(String[] args)
    {
        List<String> paths = new ArrayList<String>();
        paths.add("a/b/c");
        paths.add("a/b/d");
        paths.add("e");
        System.out.println(JSON.toJSONString(buildTree(paths)));
    }

}
