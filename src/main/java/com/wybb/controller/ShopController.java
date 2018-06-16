package com.wybb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wybb.util.file.FileUtil;

@RestController
@RequestMapping("/shop")
public class ShopController {
	
	
	
	@PostMapping("/add")
	public String createShop(String shopName,String shopType,HttpServletRequest request){

        System.out.println(shopType);
        //返回json
        return "uploadimg success";
		
	}
//	
//	@PostMapping("/add")
//	public String createShop(MultipartFile file,String shopName,String shopType,HttpServletRequest request){
//		 String contentType = file.getContentType();
//        String fileName = file.getOriginalFilename();
//        System.out.println("fileName-->" + fileName);
//        System.out.println("getContentType-->" + contentType);
////        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
////        System.out.println(filePath);
//        String filePath = "d://imgupload/";
//        try {
//            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
//        } catch (Exception e) {
//            // TODO: handle exception
//        	e.printStackTrace();
//        }
//
//        System.out.println(shopType);
//        //返回json
//        return "uploadimg success";
//		
//	}
}
