package com.xxd.utils;

import org.apache.commons.codec.binary.Hex;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xxd.models.XxdUser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * 超级工具类
 * 
 * @author Liang
 * @version 1.0
 */

public class U {
	
	/**
	 * 获取销总随机用户名
	 * @return
	 */
	public static String getLeaderRandom() {
		return ((int)((Math.random() * 9 + 1) * 100000)) + "";
	}
	
	// 通过参数拼接json字符串
	// "{\"name\":\"Tom\", \"code\":\"123\"}"
	public static String contactJsonStr(String[] jsonKeys, String[] jsonValues) {
		StringBuffer sb = new StringBuffer("{");
		for (int i = 0; i < jsonKeys.length; i++) {
			sb.append("\"" + jsonKeys[i] + "\":\"" + jsonValues[i] + "\"");
			if (i != jsonKeys.length - 1)
				sb.append(",");
		}
		return sb.append("}").toString();
	}
	
	/**
	 * 生成订单号：生成规则	当前日期+当前用户id+随机的四位数字
	 * @param uid
	 * @return
	 */
	public static String createOrderNo(Integer uid) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
		return sdf.format(date) + uid + randomFour();
	}

	// 生成四位随机数
	public static int randomFour() {
		return (int) ((Math.random() * 9 + 1) * 1000);
	}

	/**
	 * 验盐
	 * @param password
	 * @param md5
	 * @return
	 */
	public static boolean verify(String password, String salt, String passwordSalt) {
		
		return md5Hex(password + salt).equals(passwordSalt);
	}
	
	/**
	 * 获取十六进制字符串形式的MD5摘要
	 * @param src
	 * @return
	 */
	public static String md5Hex(String src) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] bs = md5.digest(src.getBytes());
			return new String(new Hex().encode(bs));
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 操作日志记录
	 * @param con 内容
	 */
	public static void logAction(String con) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			File file = new File(ProperU.read(Constans.PROSOURCE, "actionLogsFile") + new SimpleDateFormat("yyyy_MM_dd_HH").format(date) + ".txt");
			if(!file.exists()) file.createNewFile();
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
			pw.println(sdf.format(date) + "  " + con);
			pw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 操作日志记录
	 * @param con 内容
	 */
	public static void logFreeze(String con) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			File file = new File(ProperU.read(Constans.PROSOURCE, "freezeLogsFile") + new SimpleDateFormat("yyyy_MM_dd_HH").format(date) + ".txt");
			if(!file.exists()) file.createNewFile();
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
			pw.println(sdf.format(date) + "  " + con);
			pw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用户登录登出行为日志记录
	 * @param con 内容
	 */
	public static void logLogin(String con) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			File file = new File(ProperU.read(Constans.PROSOURCE, "loginLogsFile") + new SimpleDateFormat("yyyy_MM_dd_HH").format(date) + ".txt");
			if(!file.exists()) file.createNewFile();
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
			pw.println(sdf.format(date) + "  " + con);
			pw.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 操作异常日志记录
	 * @param con 内容
	 */
	public static void exceptionLog(Exception e, String ip) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			File file = new File(ProperU.read(Constans.PROSOURCE, "exceptionLogsFile") + new SimpleDateFormat("yyyy_MM_dd_HH").format(date) + ".txt");
			if(!file.exists()) file.createNewFile();
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
			pw.println(sdf.format(date) + "____" + ip);
			StringWriter sw = new StringWriter();   
            PrintWriter pw1 = new PrintWriter(sw, true);   
            e.printStackTrace(pw1);   
            pw1.flush();   
            sw.flush();   
			pw.println(sw.toString());
			pw.close();
		}catch(Exception e1) {
			e.printStackTrace();
		}
	}
	
	public static String getNowTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	
	
	public static Integer[] getUid(String uids) {
		String[] str = uids.split(",");
		int strLength = 0;
		if(str[0].equals("") || str[0] == null) strLength ++;
		Integer[] result = new Integer[str.length - strLength];
		for(int i = strLength;i < str.length;i ++) {
			result[i-strLength] = Integer.parseInt(str[i]);
		}
		return result;
	}
	
	/**
	 * 对实体类中的空值字符串进行处理
	 * @param obj 需要处理的实体类对象
	 */
	public static void cancelDisAbleStr(Object obj) {
		Class cla = obj.getClass();
		Method[] methods = cla.getMethods();
		for(Method method : methods) {
			if(method.getName().startsWith("get")) {
				Object fieldValue = null;
				try {
					fieldValue = method.invoke(obj, null);
					String Null = null;
					if(fieldValue instanceof String && fieldValue.toString().equals("")) {
						String getMethodName = method.getName();
						String setMethodName = getMethodName.substring(3);
						Method setMethod = cla.getMethod("set"+setMethodName, String.class);
						setMethod.invoke(obj, Null);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Integer checkNullInt(Integer obj) {
		if(null == obj) {
			return 0;
		}else {
			return obj;
		}
	}
	
	public static BigDecimal checkNullBigDecimal(BigDecimal bd) {
		if(null == bd) {
			return new BigDecimal("0.00");
		}else {
			return bd;
		}
	}
	
	public static String checkNullString(String obj) {
		if(null == obj) {
			return "";
		}else {
			return obj;
		}
	}
	
	public static void main(String[] args) {
		//System.out.println(U.md5Hex("123456" + "1234"));
//		Date date = new Date();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		System.out.println(sdf.format(date));
//		PrintWriter pw = null;
//		try {
//			pw = new PrintWriter(new FileOutputStream(new File("D:/work/logs/a.txt"), true));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		pw.println(sdf.format(date));
//		pw.close();
		
//		ArrayList<Integer> photosNumsAl = new ArrayList<Integer>();
//		String[] str = "3,3,4".split(",");
//		photosNumsAl.add(0, Integer.parseInt(str[0]));
//		photosNumsAl.add(1, Integer.parseInt(str[1]));
//		photosNumsAl.add(2, Integer.parseInt(str[2]));
//		System.out.println(photosNumsAl.get(1));
		
//		XxdUser model = new XxdUser();
//		model.setBankCard("");
//		model.setBankLocation("aaaa");
//		cancelDisAbleStr(model);
//		System.out.println(Integer.parseInt(model.getBankCard()));
//		System.out.println(model.getBankLocation());
//		
		
		
		int width=300;
        int height=300;
        String format="png";
        String contents="http://huoxiaoxiao.com/home/download";
        HashMap map=new HashMap();
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        map.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.M);
        map.put(EncodeHintType.MARGIN, 0);
        try {
            BitMatrix bm = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height);
            Path file=new File("D:/img.png").toPath();
            MatrixToImageWriter.writeToPath(bm, format, file);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
