/** 
 * Project Name:meatball-admin 
 * File Name:FileUpload.java 
 * Package Name:com.meatball.component 
 * Date:2017年10月12日下午10:19:16 
 * Copyright (c) 2017, zhang.xiangyu@foxmail.com All Rights Reserved. 
 */  
package com.meatball.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.meatball.component.properties.Upload;
import com.meatball.utils.DateUtil;
import com.meatball.vo.FileUploadResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**   
 * @Title: FileUpload.java 
 * @Package com.meatball.component 
 * @Description: TODO(上传) 
 * @author 張翔宇  
 * @date 2017年10月12日 下午10:19:16 
 * @version V1.0   
 */
@Api(tags = "文件上传")
@Controller
public class FileUploadUtil {
	private Logger log = LoggerFactory.getLogger(FileUploadUtil.class);
	@Resource
	private ResourceLoader resourceLoader;
	@Resource
	private Upload upload;
	
	/**
	 * @Title: upload 
	 * @Description: TODO(上传) 
	 * @param file
	 * @param request
	 * @return String    返回类型
	 */
	@ApiOperation(value = "文件上传")
	@RequestMapping("/upload")
	@ResponseBody
	public List<FileUploadResponse> upload(@RequestParam("file") MultipartFile[]  files, HttpServletRequest request) {
		List<FileUploadResponse> list = new ArrayList<FileUploadResponse>();
		// 获取文件存放根路径
		String basePath = upload.getBasePath();
		// 文件存放分支路径
		String location = DateUtil.format(new Date(), "yyyy-MM-dd") + "/";
		// 文件类型
		String contentType = "";
		// 原文件名称
		String fileName = "";
		// 存放名称
		String saveName = "";
		// 图片后缀
		String imgSuffix = "";
		// 判断文件夹是否存在，不存在则
		File targetFile = new File(basePath + location);  
        if(!targetFile.exists()){
        		targetFile.setWritable(true, false);
            targetFile.mkdirs();    
        }
		for(MultipartFile file : files) {
			FileUploadResponse rs = new FileUploadResponse();
			contentType = file.getContentType();
	        fileName = file.getOriginalFilename();
	        imgSuffix = fileName.substring(fileName.lastIndexOf("."));
	        saveName = new Date().getTime() + imgSuffix;
	        try {
	        	// java7中新增特性
	        	// ATOMIC_MOVE	原子性的复制
	        	// COPY_ATTRIBUTES	将源文件的文件属性信息复制到目标文件中
	        	// REPLACE_EXISTING	替换已存在的文件
	        	Files.copy(file.getInputStream(), Paths.get(upload.getBasePath() + location, saveName), StandardCopyOption.REPLACE_EXISTING);
	            rs.setContentType(contentType);
	            rs.setFileName(fileName);
	            rs.setUrl(Base64Util.encodeData(location + saveName) + imgSuffix);
//	            rs.setUrl(location + fileName);
	            rs.setType("success");
	        } catch (Exception e) {
	        	rs.setType("fail");
	        	rs.setMsg("文件上传失败！");
	            log.error("上传文件失败，" + e);
	        }
	        list.add(rs);
		}
        //返回json
		log.info(JSON.toJSONString(list));
        return list;
	}
	
	/**
	 * @Title: getFile 
	 * @Description: TODO(获取图片) 
	 * @param url
	 * @return ResponseEntity<?>    返回类型
	 */
	@GetMapping("/{url:.+}")
	@ResponseBody
	public ResponseEntity<?> getFile(@PathVariable String url) {
		url = url.substring(0, url.lastIndexOf("."));
		try {  
	        return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(upload.getBasePath() + Base64Util.decodeData(url)).toString())); 
//			return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(uploadProperties.getBasePath() + path + filename).toString()));
	    } catch (Exception e) {  
	        return ResponseEntity.notFound().build();  
	    }  
	}
}
  