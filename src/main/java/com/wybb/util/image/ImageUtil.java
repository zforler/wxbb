package com.wybb.util.image;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageUtil {
	private final static int resizeW = 32;
	private final static int resizeH = 32;
	private final static int dctW = 8;
	private final static int dctH = 8;
	private static String hashStr = "";
	
	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public static char[] getHash(String imgUrl) throws Exception{
		  Mat src = Imgcodecs.imread(imgUrl);
	      if(src.empty()){  
	          throw new Exception("no file");  
	      }  
	      Mat dst = new Mat();
	      
	      Imgproc.resize(src, dst, new Size(resizeW, resizeH));
	      Imgproc.cvtColor(dst, dst, Imgproc.COLOR_RGB2GRAY);
	  
	      dst.convertTo(dst, CvType.CV_32FC1);
	      Core.dct(dst, dst);
	      
	      double mean = 0.0;
	      double[] temp = new double[resizeW*resizeH];
	      int k = 0;
	      /* 第三步，求取DCT系数均值（左上角8*8区块的DCT系数）*/  
	      for (int i = 0; i < dctW; i++) {  
	          for (int j = 0; j < dctH; j++){  
	              mean += dst.get(i, j)[0];
	              temp[k++] = dst.get(i, j)[0];
	          }  
	      } 
	      
	      int num = dctW*dctH;
	      
	      mean = mean / num;
	      
	      char[] hash = new char[num];
	      
	      /* 第四步，计算哈希值。*/  
	      for (int i =0; i < num; i++){  
	          if (temp[i]>=mean){  
	        	  hash[i]='1';  
	          }else{  
	        	  hash[i]='0';  
	          }
	      }
	      return hash;
	}
	
	public static String getHashStr(String imgUrl) throws Exception{
		char[] hash = getHash(imgUrl);
		 StringBuffer sb = new StringBuffer();
	      String hashStr = "";
	      /* 第四步，计算哈希值。*/  
	      for (int i =0, len = hash.length; i < len; i++){  
	          
	          sb.append(hash[i]);
	          
	          if(sb.length() == 8){
	        	  String t = Integer.toString(Integer.valueOf(sb.toString(), 2),16);
	        	  if(t.length() < 2){
	        		  t += "0";
	        	  }
	        	  hashStr += t;
	        	  sb.setLength(0);
	          }
	      }
		  return hashStr;
	}
	
	public static boolean imageIsSimilar(String imgUrl,String imgUrl1) throws Exception{
		char[] hash1;
		char[] hash2;
		try {
		
			hash1 = ImageUtil.getHash(imgUrl);
			hash2 = ImageUtil.getHash(imgUrl1);	
			
			int difference = 0;  
		    for(int i=0;i<64;i++){  
		         if(hash1[i]!=hash2[i]){
		        	 difference++;
		         }
		    }  
		    System.out.println(difference);
		    if(difference <= 5){
	    	  return true;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean imageIsSimilar(char[] hash1, char[] hash2) throws Exception{
		try {
			int difference = 0;  
		    for(int i=0, len = hash1.length; i < len;i++){  
		         if(hash1[i]!=hash2[i]){
		        	 difference++;
		         }
		    }  
		    System.out.println(difference);
		    if(difference <= 5){
	    	  return true;
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	public static void main(String[] args) {
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		try {
			String url1 = "d://test2.jpg";
			String url2 = "d://test0.jpg";
			System.out.println(ImageUtil.imageIsSimilar(url2,url1));
			String h1 = ImageUtil.getHashStr(url2);
			String h2 = ImageUtil.getHashStr(url1);
			System.out.println(h1);
			System.out.println(h2);
			System.out.println(ImageUtil.imageIsSimilar(h1.toCharArray(), h2.toCharArray()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	 
//	      //Writing the image
//	      Imgcodecs.imwrite("D://zhonglouToGrayScale.jpg", dst);
//	      System.out.println("Converted to Grayscale");
	}
}
