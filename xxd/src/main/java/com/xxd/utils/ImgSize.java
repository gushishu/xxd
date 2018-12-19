package com.xxd.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class ImgSize {

	 //所有文件路径
    public static List<String> getPath(File file,List<String> resultFileName) {
        File[] files = file.listFiles();
        if(files==null)return resultFileName;// 判断目录下是不是空的
        for (File f : files) {
            if(f.isDirectory()){// 判断是否文件夹
                resultFileName.add(f.getPath());
                getPath(f,resultFileName);// 调用自身,查找子目录
            }else
                resultFileName.add(f.getPath());
        }
        return resultFileName;
    }
    
    /**
     * @param srcPath  源图片路径
     * @param desPath  修改大小后图片路径 
     * @param scaleSize 图片的修改比例，目标宽度
     */  
    public static void resizeImage(String srcPath, String desPath,int scaleSize) throws IOException {  

        File srcFile = new File(srcPath);  
        Image srcImg = ImageIO.read(srcFile);  
        BufferedImage bi = null;
        try {
        bi = ImageIO.read(srcFile);
        } catch (Exception e) {
        e.printStackTrace();
        }
        float width = bi.getWidth(); // 像素
        float height = bi.getHeight(); // 像素
        float scale=width/scaleSize;
        BufferedImage buffImg = null;  
        buffImg = new BufferedImage(scaleSize, (int)(height/scale), BufferedImage.TYPE_INT_RGB); 
        //使用TYPE_INT_RGB修改的图片会变色 
        buffImg.getGraphics().drawImage(  
                srcImg.getScaledInstance(scaleSize, (int)(height/scale), Image.SCALE_SMOOTH), 0,  
                0, null);  

        ImageIO.write(buffImg, "JPEG", new File(desPath));
    }  
    
    
}
