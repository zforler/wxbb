package com.wybb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wybb.entity.Shop;
import com.wybb.service.impl.ShopService;
import com.wybb.util.file.FileUtil;
import com.wybb.util.result.Code;
import com.wybb.util.result.Result;

@RestController
@RequestMapping("/shop")
public class ShopController {
	@Autowired
	private ShopService shopService;
	
	@PostMapping("/add")
	public Result createShop(@RequestBody Shop shop,HttpServletRequest request){
		
		Result result = new Result();
		//检查商铺名称是否重复
		if(shopService.validateShopName(shop.getShopName())){
			 result.setCode(Code.FAILED.getCode());
			 result.setMsg("店铺名称已存在！");
			 return result;
		}
		
		
		long createTime = System.currentTimeMillis();
		long endTime = createTime + 90 * 24 * 60 * 60 * 1000;
		shop.setShopCreateTime(String.valueOf(createTime));
		shop.setShopEndTime(String.valueOf(endTime));
		
		int res = shopService.addShop(shop);
		
	
		if(res == 1){
		   result.setCode(Code.SUCCESS.getCode());
    	   result.setMsg("创建成功！");
		}
		else{
		   result.setCode(Code.FAILED.getCode());
		   result.setMsg("创建失败！");
		}
	  
		//返回json
      	return result;
		
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
