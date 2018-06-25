package com.wybb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wybb.entity.Goods;
import com.wybb.service.IGoodsService;
import com.wybb.util.file.FileUtil;
import com.wybb.util.result.Code;
import com.wybb.util.result.Result;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	@Autowired
	IGoodsService goodsService;
	
	@PostMapping("/add")
	@ResponseBody
	public Result addGoods(String goodsNum, String goodsName, String goodsPrice, String goodsDesc,String shopId) {
		System.out.println(shopId);
		Goods goods = new Goods();
		goods.setGoodsName(goodsName);
		goods.setGoodsPrice(goodsPrice);
		goods.setGoodsDesc(goodsDesc);
		goods.setGoodsStatus(1);
		goods.setGoodsCreateTime(String.valueOf(System.currentTimeMillis()));
		goods.setGoodsType(0);
		goods.setShopId(Integer.valueOf(shopId));
		
		int res = goodsService.addGoods(goods);
		System.err.println(res);
		Result result = new Result();
		if(res > 0) {
			result.setCode(Code.SUCCESS.getCode());
			result.setMsg(Code.SUCCESS.getMsg());
			result.setData(res);
		}else {
			result.setCode(Code.FAILED.getCode());
			result.setMsg(Code.FAILED.getMsg());	
		}
		
		
		return result;
	}
	
	@PostMapping("/image/upload")
	public String goodsImageUpload(MultipartFile file,HttpServletRequest request){
		 String contentType = file.getContentType();
        String fileName = file.getOriginalFilename();
        System.out.println("fileName-->" + fileName);
        System.out.println("getContentType-->" + contentType);
//        String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
//        System.out.println(filePath);
        String filePath = "d://imgupload/";
        try {
            FileUtil.uploadFile(file.getBytes(), filePath, fileName);
        } catch (Exception e) {
            // TODO: handle exception
        	e.printStackTrace();
        }

        //返回json
        return "uploadimg success";
		
	}
}
