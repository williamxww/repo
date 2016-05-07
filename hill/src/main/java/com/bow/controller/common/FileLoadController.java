/**  
 * @FileName: FileLoadController.java 
 * @Package com.bow.controller.common 
 * all rights reserved by Hill team
 * @version v1.3  
 */ 
package com.bow.controller.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: FileLoadController
 * @Description: 处理上传下载 ---还没测试
 * @author ViVi
 * @date 2015年9月10日 下午8:43:46
 */

@Controller
public class FileLoadController {

    private static final Logger logger = LoggerFactory.getLogger(FileLoadController.class);

    @RequestMapping("/toFileList.do")
    public String toFileList() {
        return "common/fileList";
    }

    @RequestMapping("/toUploadFile.do")
    public String toUploadFile() {
        return "common/uploadFile";
    }

    @RequestMapping(value = "/upload.do", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request, HttpServletResponse response) throws Exception {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");
        String realFileName = file.getOriginalFilename();
        // 获取路径
        File directory = getDirectory(request);
        logger.info("上传文件{}到{}", realFileName, directory.getAbsolutePath());
        File uploadFile = new File(directory.getAbsolutePath() + realFileName);
        FileCopyUtils.copy(file.getBytes(), uploadFile);
        Collection<File> files = FileUtils.listFiles(directory, null, true);
        request.setAttribute("files", files);
        return "fileList";
    }

    @RequestMapping("/download.do")
    public ModelAndView download(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        String fileName = request.getParameter("fileName");
        logger.info("下载文件{}", fileName);
        File ctxPath = getDirectory(request);
        String downLoadPath = ctxPath.getAbsolutePath() + fileName;
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }

    @RequestMapping(value = "/upload4", method = RequestMethod.POST)
    public ModelAndView fileUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        File directory = getDirectory(request);
        String fileName = null;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            // 上传文件名
            // System.out.println("key: " + entity.getKey());
            MultipartFile mf = entity.getValue();
            fileName = mf.getOriginalFilename();
            File uploadFile = new File(directory + fileName);
            FileCopyUtils.copy(mf.getBytes(), uploadFile);
        }
        Collection<File> files = FileUtils.listFiles(directory, null, true);
        request.setAttribute("files", files);
        return new ModelAndView("success");
    }

    private File getDirectory(HttpServletRequest request) {
        String ctxPath = request.getSession().getServletContext().getRealPath("/") + "documents";
        // 创建文件
        File dirPath = new File(ctxPath);
        if (!dirPath.exists()) {
            dirPath.mkdir();
        }
        return dirPath;
    }

}
