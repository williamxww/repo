package com.bow.controller.demo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bow.model.vo.TreeGridNode;
import com.bow.utils.common.WebUtils;

/**
 *
 * @author acer
 * @version C10 2016年5月30日
 * @since SDP V300R003C10
 */
@Controller
@RequestMapping("/TreeGrid")
public class TreeGridDemo
{
    private static final Logger logger = LoggerFactory.getLogger(TreeGridDemo.class);
    
    private static final String SPLITTER = "-";
    
    @RequestMapping("/getData")
    public void getData(HttpServletRequest request, HttpServletResponse response)
    {
        String parentName = request.getParameter("name");
        parentName = parentName == null ? "" : parentName;
        logger.debug("parentName " + parentName);
        if (!StringUtils.isEmpty(parentName))
        {
            parentName += SPLITTER;
        }
        List<TreeGridNode> list = new ArrayList<TreeGridNode>();
        for (int i = 0; i < 2; i++)
        {
            TreeGridNode node = new TreeGridNode();
            node.setId(String.valueOf(i));
            node.setName(parentName + i);
            if (i % 2 == 0)
            {
                node.setLeaf(true);
            }
            list.add(node);
        }
        
        WebUtils.writeJsonToPage(response, list);
    }

}
