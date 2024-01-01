package com.example.demo.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadFileController {
	/*
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile[] files) {
	    for (MultipartFile file : files) {
	        try {
	            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	            Document doc = dBuilder.parse(file.getInputStream());
	            doc.getDocumentElement().normalize();
	            // do something with the XML content
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return "uploadSuccess";
	}
	*/
	
	@PostMapping("/uploadMultipleFiles")
	public String uploadMultipleFiles(@RequestParam("file") MultipartFile[] files) {

	    String uploadedFileName = Arrays.stream(files).map(MultipartFile::getOriginalFilename)
	            .filter(fileName -> !StringUtils.isEmpty(fileName)).collect(Collectors.joining(" , "));
	    if (StringUtils.isEmpty(uploadedFileName)) {
	        return "redirect:/";
	    }
	    return "redirect:/";
	}

}
