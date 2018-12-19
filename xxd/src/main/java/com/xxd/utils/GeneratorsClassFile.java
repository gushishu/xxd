package com.xxd.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xxd.models.XxdBuyOrderDetails;
import com.xxd.services.impls.XxdBuyOrderDetailsSI;

/**
 * 自动生成固定格式的class文件
 * @author Liang
 * @version 1.0
 */

public class GeneratorsClassFile {
	
	/**
	 * 自动生成固定service接口
	 * @param name
	 * @throws IOException
	 */
	public static void generatorS(String name) throws IOException {
		File file = new File("D:/work/myEclipse1/workspace1/xxd/src/main/java/com/xxd/services/"+name+"S.java");
		if(!file.exists()) file.createNewFile();
		PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
		//定义package
		pw.println("package com.xxd.services;");
		pw.println();
		//导包
		pw.println("import java.util.ArrayList;");
		pw.println();
		pw.println("import com.xxd.models."+name+";");
		pw.println();
		//开始编写类
		pw.println("public interface "+name+"S {");
		pw.println();
		//增加抽象方法
		pw.println("	public Integer insert("+name+" model);");
		pw.println();
		//删除抽象方法
		pw.println("	public Integer deleteByPrimaryKey(Integer primaryKey);");
		pw.println();
		//修改抽象方法
		pw.println("	public Integer updateByPrimaryKeySelective("+name+" model);");
		pw.println();
		//查询方法
		pw.println("	public "+name+" selectByPrimaryKey(Integer primaryKey);");
		pw.println();
		pw.println("	public ArrayList<"+name+"> selectAll();");
		//结束
		pw.println();
		pw.println("}");
		pw.close();
	}
	
	/**
	 * 自动生成固定的service实现类
	 * @param name
	 * @throws IOException
	 */
	public static void generatorSI(String name) throws IOException {
		File file = new File("D:/work/myEclipse1/workspace1/xxd/src/main/java/com/xxd/services/impls/"+name+"SI.java");
		if(!file.exists()) file.createNewFile();
		PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
		//定义package
		pw.println("package com.xxd.services.impls;");
		pw.println();
		//导包
		pw.println("import java.util.ArrayList;");
		pw.println();
		pw.println("import org.springframework.beans.factory.annotation.Autowired;");
		pw.println("import org.springframework.stereotype.Service;");
		pw.println();
		pw.println("import com.xxd.mappers."+name+"Mapper;");
		pw.println("import com.xxd.models."+name+";");
		pw.println("import com.xxd.services."+name+"S;");
		pw.println();
		//注解service
		pw.println("@Service");
		//开始编写类
		pw.println("public class "+name+"SI implements "+name+"S{");
		pw.println();
		//注入mapper
		pw.println("	@Autowired");
		pw.println("	private "+name+"Mapper mapper;");
		pw.println();
		//增加实现方法
		pw.println("	@Override");
		pw.println("	public Integer insert("+name+" model) {");
		pw.println("		return mapper.insert(model);");
		pw.println("	}");
		pw.println();
		//删除实现方法
		pw.println("	@Override");
		pw.println("	public Integer deleteByPrimaryKey(Integer primaryKey) {");
		pw.println("		return mapper.deleteByPrimaryKey(primaryKey);");
		pw.println("	}");
		pw.println();
		//修改实现方法
		pw.println("	@Override");
		pw.println("	public Integer updateByPrimaryKeySelective("+name+" model) {");
		pw.println("		return mapper.updateByPrimaryKeySelective(model);");
		pw.println("	}");
		pw.println();
		//查询实现方法
		pw.println("	@Override");
		pw.println("	public "+name+" selectByPrimaryKey(Integer primaryKey) {");
		pw.println("		return mapper.selectByPrimaryKey(primaryKey);");
		pw.println("	}");
		pw.println();
		pw.println("	@Override");
		pw.println("	public ArrayList<"+name+"> selectAll() {");
		pw.println("		return mapper.selectAll();");
		pw.println("	}");
		//结束
		pw.println();
		pw.println("}");
		pw.close();
	}
	
	/**
	 * 自定义生成固定controller
	 * @param name
	 * @throws IOException 
	 */
	public static void generatorC(String name) throws IOException {
		File file = new File("D:/work/myEclipse1/workspace1/xxd/src/main/java/com/xxd/controllers/"+name+"C.java");
		if(!file.exists()) file.createNewFile();
		PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
		//定义package
		pw.println("package com.xxd.controllers;");
		pw.println();
		//导包
		pw.println("import java.util.ArrayList;");
		pw.println("import java.util.HashMap;");
		pw.println();
		pw.println("import org.springframework.beans.factory.annotation.Autowired;");
		pw.println("import org.springframework.stereotype.Controller;");
		pw.println("import org.springframework.web.bind.annotation.RequestMapping;");
		pw.println("import org.springframework.web.bind.annotation.ResponseBody;");
		pw.println();
		pw.println("import com.xxd.models."+name+";");
		pw.println("import com.xxd.services.impls."+name+"SI;");
		pw.println("import com.xxd.utils.Constans;");
		pw.println();
		//注解service
		pw.println("@Controller");
		pw.println("@RequestMapping(value = \"/"+name+"\")");
		//开始编写类
		pw.println("public class "+name+"C {");
		pw.println();
		//注入mapper
		pw.println("	@Autowired");
		pw.println("	private "+name+"SI serviceImpl;");
		pw.println();
		//增加
		pw.println("	@RequestMapping(value = \"/insert\")");
		pw.println("	@ResponseBody");
		pw.println("	public HashMap<String, Object> insert("+name+" model){");
		pw.println("		Integer con = serviceImpl.insert(model);");
		pw.println("		return Constans.returnCon(con, null);");
		pw.println("	}");
		pw.println();
		//删除
		pw.println("	@RequestMapping(value = \"/deleteByPrimaryKey\")");
		pw.println("	@ResponseBody");
		pw.println("	public HashMap<String, Object> deleteByPrimaryKey(Integer primaryKey){");
		pw.println("		Integer con = serviceImpl.deleteByPrimaryKey(primaryKey);");
		pw.println("		return Constans.returnCon(con, null);");
		pw.println("	}");
		pw.println();
		//修改
		pw.println("	@RequestMapping(value = \"/updateByPrimaryKeySelective\")");
		pw.println("	@ResponseBody");
		pw.println("	public HashMap<String, Object> updateByPrimaryKeySelective("+name+" model){");
		pw.println("		Integer con = serviceImpl.updateByPrimaryKeySelective(model);");
		pw.println("		return Constans.returnCon(con, null);");
		pw.println("	}");
		pw.println();
		//查询
		pw.println("	@RequestMapping(value = \"/selectByPrimaryKey\")");
		pw.println("	@ResponseBody");
		pw.println("	public HashMap<String, Object> selectByPrimaryKey(Integer primaryKey){");
		pw.println("		"+name+" con = serviceImpl.selectByPrimaryKey(primaryKey);");
		pw.println("		return Constans.returnCon(null == con ? 1 : -1, con);");
		pw.println("	}");
		pw.println();
		pw.println("	@RequestMapping(value = \"/selectAll\")");
		pw.println("	@ResponseBody");
		pw.println("	public HashMap<String, Object> selectAll(){");
		pw.println("		ArrayList<"+name+"> con = serviceImpl.selectAll();");
		pw.println("		return Constans.returnCon(null == con ? 1 : -1, con);");
		pw.println("	}");
		//结束
		pw.println();
		pw.println("}");
		pw.close();
	}
	
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\work\\myEclipse1\\workspace1\\xxd\\src\\main\\java\\com\\xxd\\models");
		String[] strs = file.list();
		//for(String str : strs) {
			//String className = str.replace(".java", "");
			//generatorS(className);
			//generatorSI(className);
			//generatorC(className);
		//}
		generatorS("XxdGoodsParametric");
		generatorSI("XxdGoodsParametric");
		generatorC("XxdGoodsParametric");
	}

}
