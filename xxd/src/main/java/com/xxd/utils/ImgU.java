package com.xxd.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.xxd.models.XxdPicture;
import com.xxd.services.impls.XxdPictureHandleSI;


/**
 * 图片处理工具类
 * 
 * @author Liang
 * @version 1.0
 */

public class ImgU {
	
	//图片工具类的配置文件
	private static String pro = ProperU.read(Constans.PROSOURCE, "img");
	
	//图片扩展管理
	private static String imgEnds = ProperU.read(pro, "imgEnds");
	
	//图片保存路径基础文件夹
	private static String imgSaveDir = ProperU.read(pro, "imgSaveDir");
	
	//图片大小处理路径文件夹
	private static String imgChangeDir = ProperU.read(pro,"imgChageDir");
	
	//图片大小限制(单位：M)
	private static String maxFileSize = ProperU.read(pro, "maxFileSize");
	
	//总上传数据大小
	private static String maxRequestSize = ProperU.read(pro, "maxRequestSize");

	/**
	 * 图片上传主方法
	 * @param request 
	 * @param userPath 需要手动添加的图片保存路径的一部分
	 * @param con 事先确定好的内容
	 * @return 图片和参数内容
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public static HashMap<String, String> uploadImg(HttpServletRequest request, HashMap<String, String> con){
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        multipartResolver.setMaxUploadSizePerFile(Long.parseLong(maxFileSize));
        multipartResolver.setMaxUploadSize(Long.parseLong(maxRequestSize));
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iterF = multiRequest.getFileNames();
            Enumeration<String> iterP = multiRequest.getParameterNames();
            //循环获取多媒体信息(包括文件和图片)
            while(iterF.hasNext()){
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iterF.next().toString());
                String name = file.getName();
                String fileName = file.getOriginalFilename();
                //String str = file.getContentType();
                String path;
                if(file!=null && !fileName.equals("")){
                	//检测文件后缀
    				if(!checkImg(fileName)) return null;
    				
    				//检测文件大小
    				//if(!checkSize(file)) return null;
                	
//                    //判断是文件还是图片
//                    if(str.split("/")[0].equals("image")){
//                        //拼接图片保存路径
//                        path = ConstantManager.IMGURL + file.getOriginalFilename();
//                        mapImg.put(name, path);
//                    }else{
//                        //拼接文件保存路径
//                        path = ConstantManager.FILEURL + file.getOriginalFilename();
//                        mapFile.put(name, path);
//                    }
                	//判断当前需要上传的文件是否有自定义名称
                	if(!con.get(name).equals("")) {
                		path = imgSaveDir + con.get(name) + ".jpg";
                		new File(path.substring(0, path.lastIndexOf("/"))).mkdirs();
                	}else {
                		path = imgSaveDir + file.getOriginalFilename();
                	}
                	con.put(name, "");
                    //上传
                    try {
						file.transferTo(new File(path));
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }
            //循环获取普通参数信息
            while(iterP.hasMoreElements()){
                //获取每一个参数名称
                String paramName = iterP.nextElement();
                //获取参数对应的值
                String paramValue = multiRequest.getParameter(paramName);
                con.put(paramName, paramValue);
            }
        }
        return con;
	}
	
	public static HashMap<String, String> uploadImg1(HttpServletRequest request, HashMap<String, String> con){
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        multipartResolver.setMaxUploadSizePerFile(Long.parseLong(maxFileSize));
        multipartResolver.setMaxUploadSize(Long.parseLong(maxRequestSize));
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iterF = multiRequest.getFileNames();
            Enumeration<String> iterP = multiRequest.getParameterNames();
            //循环获取多媒体信息(包括文件和图片)
            while(iterF.hasNext()){
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iterF.next().toString());
                String name = file.getName();
                String fileName = file.getOriginalFilename();
                //String str = file.getContentType();
                String path;
                if(file!=null && !fileName.equals("")){
                	//检测文件后缀
    				if(!checkImg(fileName)) return null;
            		path = imgSaveDir + con.get(name) + (file.getOriginalFilename().startsWith("x") ? Constans.GOODSIMGFONTDIR : Constans.GOODSSHOWIMGDIR) + (file.getOriginalFilename().replaceFirst("x", ""));
            		new File(path.substring(0, path.lastIndexOf("/"))).mkdirs();
                	con.put(name, file.getOriginalFilename());
                	
                    //上传
                    try {
						file.transferTo(new File(path));
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }
            //循环获取普通参数信息
            while(iterP.hasMoreElements()){
                //获取每一个参数名称
                String paramName = iterP.nextElement();
                //获取参数对应的值
                String paramValue = multiRequest.getParameter(paramName);
                con.put(paramName, paramValue);
            }
        }
        return con;
	}
	
	
	public static HashMap<String, String> uploadPictureHandle(HttpServletRequest request, HashMap<String, String> con) throws IOException{
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        multipartResolver.setMaxUploadSizePerFile(Long.parseLong(maxFileSize));
        multipartResolver.setMaxUploadSize(Long.parseLong(maxRequestSize));
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iterF = multiRequest.getFileNames();
            Enumeration<String> iterP = multiRequest.getParameterNames();
            //循环获取多媒体信息(包括文件和图片)
            while(iterF.hasNext()){
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iterF.next().toString());
                String name = file.getName();
                String fileName = file.getOriginalFilename();
                
                //String str = file.getContentType();
                String path;
                if(file!=null && !fileName.equals("")){
                	//检测文件后缀
                	if(!checkImg(fileName)) return null;
                	String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
                	Date currentTime = new Date();
            		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
            		path = imgChangeDir + con.get(name) + (file.getOriginalFilename().startsWith("x") ? Constans.GOODSIMGFONTDIR : Constans.GOODSSHOWIMGDIR) + newFileName ;
            		new File(path.substring(0, path.lastIndexOf("/"))).mkdirs();
                	con.put(name, newFileName);
                	con.put("saveDir",con.get("code"));
                	//上传
                    try {
						file.transferTo(new File(path));
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
                    ImgSize.resizeImage(path,path,800);
                }
            }
            //循环获取普通参数信息
            while(iterP.hasMoreElements()){
                //获取每一个参数名称
                String paramName = iterP.nextElement();
                //获取参数对应的值
                String paramValue = multiRequest.getParameter(paramName);
                con.put(paramName, paramValue);
            }
        }
        return con;
	}
	
	public static HashMap<String, String> uploadImgOne(HttpServletRequest request, HashMap<String, String> con){
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        multipartResolver.setMaxUploadSizePerFile(Long.parseLong(maxFileSize));
        multipartResolver.setMaxUploadSize(Long.parseLong(maxRequestSize));
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iterF = multiRequest.getFileNames();
            Enumeration<String> iterP = multiRequest.getParameterNames();
            //循环获取多媒体信息(包括文件和图片)
            while(iterF.hasNext()){
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iterF.next().toString());
                String name = file.getName();
                String fileName = file.getOriginalFilename();
                //String str = file.getContentType();
                String path;
                if(file!=null && !fileName.equals("")){
                	//检测文件后缀
    				if(!checkImg(fileName)) return null;
            		path = imgSaveDir + con.get(name) + "/" + (file.getOriginalFilename());
            		new File(path.substring(0, path.lastIndexOf("/"))).mkdirs();
                	con.put(name, file.getOriginalFilename());
                    //上传
                    try {
						file.transferTo(new File(path));
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
                }
            }
            //循环获取普通参数信息
            while(iterP.hasMoreElements()){
                //获取每一个参数名称
                String paramName = iterP.nextElement();
                //获取参数对应的值
                String paramValue = multiRequest.getParameter(paramName);
                con.put(paramName, paramValue);
            }
        }
        return con;
	}
	
	public static HashMap<String, String> uploadImg2(HttpServletRequest request, HashMap<String, String> con, Integer type){
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        multipartResolver.setMaxUploadSizePerFile(Long.parseLong(maxFileSize));
        multipartResolver.setMaxUploadSize(Long.parseLong(maxRequestSize));
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iterF = multiRequest.getFileNames();
            //循环获取多媒体信息(包括文件和图片)
            while(iterF.hasNext()){
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iterF.next().toString());
                String name = file.getName();
                String fileName = file.getOriginalFilename();
                //String str = file.getContentType();
                String path;
                if(file!=null && !fileName.equals("")){
                	//检测文件后缀
    				if(!checkImg(fileName)) return null;
    				if((type == 1 && file.getOriginalFilename().startsWith("X")) || (type == 2 && !file.getOriginalFilename().startsWith("X"))) {
    					path = imgSaveDir + con.get(name) + (file.getOriginalFilename().replaceFirst("x", ""));
                		new File(path.substring(0, path.lastIndexOf("/"))).mkdirs();
                        //上传
                        try {
    						file.transferTo(new File(path));
    					} catch (IllegalStateException e) {
    						e.printStackTrace();
    					} catch (IOException e) {
    						e.printStackTrace();
    					}
    				}
    				con.put(name, file.getOriginalFilename());
                }
            }
        }
        return con;
	}
	
	public static HashMap<String, String> uploadImgTest(HttpServletRequest request, HashMap<String, String> con){
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        multipartResolver.setMaxUploadSizePerFile(Long.parseLong(maxFileSize));
        multipartResolver.setMaxUploadSize(Long.parseLong(maxRequestSize));
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator<String> iterF = multiRequest.getFileNames();
            Enumeration<String> iterP = multiRequest.getParameterNames();
            //循环获取多媒体信息(包括文件和图片)
            while(iterF.hasNext()){
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iterF.next().toString());
                String name = file.getName();
                String fileName = file.getOriginalFilename();
                //String str = file.getContentType();
                String path = "";
                if(file!=null && !fileName.equals("")){
                	System.out.println("fileName" + fileName);
                	System.out.println("name" + name);
                	System.out.println("path" + path);
                }
            }
            //循环获取普通参数信息
            while(iterP.hasMoreElements()){
                //获取每一个参数名称
                String paramName = iterP.nextElement();
                //获取参数对应的值
                String paramValue = multiRequest.getParameter(paramName);
                System.out.println(paramName + "__" + paramValue);
            }
        }
        return con;
	}
	

	/**
	 * 图片检测后缀格式
	 * @param uploadContentType 文件名称
	 * @return 是否是合法的文件格式
	 */
	public static boolean checkImg(String uploadContentType) {
		for(String end : imgEnds.split("-")) {
			if (uploadContentType.endsWith(end)) return true;
		}
		return false;
	}

	/**
	 * 图片检测大小
	 * @param ft 图片对象
	 * @return 是否超过指定大小
	 */
	public static boolean checkSize(MultipartFile ft) {
		if(ft.getSize() > Integer.parseInt(maxFileSize)*1024*1024) return false;
		return true;
	}
	
	
	
	/**
	 * 自定义生成图片
	 * @throws IOException 
	 */
	public static void draw(String imagePath,String path,String content) throws IOException{
		//读取图片文件，得到BufferedImage对象
		if(!new File(imagePath).exists()) new File(imagePath).createNewFile();
		BufferedImage bimg;
		try {
			bimg = ImageIO.read(new FileInputStream(imagePath));
		
			//得到Graphics2D 对象
			Graphics2D g2d=(Graphics2D)bimg.getGraphics();
			//设置颜色和画笔粗细
			g2d.setColor(Color.BLUE);
			g2d.setStroke(new BasicStroke(3));
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 80));
			
			//绘制图案或文字
			g2d.drawString(content, 150, 468);
			BufferedImage image = ImageIO.read(new FileInputStream("D:/work/imgs/b.png"));
			ImageObserver io = new ImageObserver() {
				
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			//绘制图案或文字
			g2d.drawImage(image, 10, 10, io);
			//保存新图片
			ImageIO.write(bimg, "JPG",new FileOutputStream(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void deleteImg(String filePath) {
		new File(imgSaveDir + filePath).delete();
	}
	
	public static void deleteImgOne(String filePath) {
		new File(filePath).delete();
	}
	
	public static Integer makeImg(String dir) {
		File file = new File(imgSaveDir + dir);
		File[] files = file.listFiles();
		if(null == files) return 0;
		for(int i = 1;i <= files.length;i ++) {
			File f = new File(imgSaveDir + dir + "/" + i+".jpg");
			if(!f.exists()) files[i-1].renameTo(f);
		}
		return files.length;
	}
	
	public static void datadelImg(String path) {
		File file = new File(imgSaveDir + path);
		File[] files = file.listFiles();
		if(null == files) return ;
		for(int i = 0;i < files.length;i ++) {
			files[i].delete();
		}
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(makeImg("\\goods\\asdf\\if"));
		//draw("D:/work/imgs/a.jpg", "D:/work/imgs/c.jpg", "Hello java!");
	}
	
}
