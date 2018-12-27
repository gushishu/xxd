package com.xxd.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xxd.utils.ProperU;
import com.xxd.annotations.AdminLogin;
import com.xxd.models.*;
import com.xxd.services.impls.*;
import com.xxd.utils.Constans;
import com.xxd.utils.ImgU;
import com.xxd.utils.U;

/**
 * 处理后台的请求
 * @author Liang
 * @version 1.0
 */

@Controller
@RequestMapping("/admin")
public class XxdAdminC {

	@Autowired
	private XxdUserSI userService;
	
	@Autowired
	private XxdPowerSI powerService;
	
//	@Autowired
//	private XxdIntegrationSI integrationService;
	
	@Autowired
	private XxdIntegrationFreezeSI integrationFreezeService;
	
	@Autowired
	private XxdIntegrationTransferSI integrationTransferService;
	
	@Autowired
	private XxdGoodsSI goodsService;
	
	@Autowired
	private XxdGoodsGreightSI goodsGreightService;
	
	@Autowired
	private XxdPictureHandleSI pictureHandleService;
	
	@Autowired
	private XxdPictureHandleSI pictureHandleServiceImp;
	
	/**
	 * 登录页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/login.html")
	public String loginHtml() {
		return "/admin/login";
	}
	
	/**
	 * 首页欢迎页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/welcome.html")
	public String welcomeHtml() {
		return "/admin/welcome";
	}
	
	
	/**
	 * 销总管理页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/user_leader.html")
	public ModelAndView userLeaderHtml(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user_leader");
		//获取用户中的销总信息(判断当前用户的级别，根据级别来确定调用方法)
		//用户基本信息
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		ArrayList<XxdUsers> leaderUserInfo = null;
		//判断是否是总管理员
		if(userInfo.getType() == 0) {
			leaderUserInfo = userService.select(Constans.LEADERUSER);
			mav.addObject("isAdmin", true);
		}else {
			leaderUserInfo = userService.selects(Constans.LEADERUSER, userInfo.getUid());
			mav.addObject("isAdmin", false);
		}
		mav.addObject("leaderUserInfo", leaderUserInfo);
		return mav;
	}
	
	/**
	 * 会员管理页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/user_member.html")
	public ModelAndView userMemberHtml(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user_member");
		//获取用户中的会员信息(判断当前用户的级别，根据级别来确定调用方法)
		//用户基本信息
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		ArrayList<XxdUsers> memberUserInfo = null;
		//判断是否是总管理员
		if(userInfo.getType() == 0) {
			memberUserInfo = userService.select(Constans.MEMBERUSER);
			mav.addObject("isAdmin", true);
		}else {
			memberUserInfo = userService.selects(Constans.MEMBERUSER, userInfo.getUid());
			mav.addObject("isAdmin", false);
		}
		mav.addObject("memberUserInfo", memberUserInfo);
		return mav;
	}
	
	/**
	 * 管理员管理页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/user_manager.html")
	public ModelAndView userManagerHtml(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user_manager");
		ArrayList<XxdUser> managerUserInfo = userService.selectByUserType(Constans.MANAGERUSER);
		mav.addObject("managerUserInfo", managerUserInfo);
		return mav;
	}

	
	/**
	 * 首页显示
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/index.html")
	public ModelAndView indexHtml(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/index");
		//权限列表-页面(一次限制)
		ArrayList<XxdPower> powerList = (ArrayList<XxdPower>) request.getSession().getAttribute("adminUserPowerList");
		//提取关于页面的权限
		//ArrayList<XxdPower> powerListPage = new ArrayList<XxdPower>();
		//将权限概要提取生成新的数组
		ArrayList<String> powerListPage = new ArrayList<String>();
		for(XxdPower pl : powerList) {
			if(pl.getType() == 1) {
				powerListPage.add(pl.getUri());
			}
		}
		mav.addObject("powerListPage", powerListPage);
		//用户基本信息
		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
		userInfo.setTypeStr(Constans.USERTYPE[userInfo.getType()]);
		mav.addObject("userInfo", userInfo);
		return mav;
	}
	
	@AdminLogin
	@RequestMapping("/integration_transfer.html")
	public ModelAndView integrationTransferHtml() {
		ModelAndView mav = new ModelAndView();
		//将所有用户都显示到划拨用户选择上面
		ArrayList<XxdUser> userInfo = userService.selectAll();
		mav.addObject("userInfo", userInfo);
		return mav;
	}
	
	@AdminLogin
	@RequestMapping("/platform_profits.html")
	public ModelAndView platformProfitsHtml() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/platform_profits");
		//默认显示一次当前利润
		BigDecimal buyCarProfit = U.checkNullBigDecimal(buyOrderDetailsService.selectBuyCarProfit());
		BigDecimal upCarProfit = U.checkNullBigDecimal(buyOrderDetailsService.selectUpCarProfit());
		mav.addObject("profit", buyCarProfit.add(upCarProfit));
		return mav;
	}
	
	/**
	 * 登录请求
	 * @param username
	 * @param password
	 * @param request
	 * @return 
	 */
	@AdminLogin
	@PostMapping("/logins")
	@ResponseBody
	public HashMap<String, Object> login(String username, String password, HttpServletRequest request) {
		XxdUser userModel = userService.adminLogin(username);
		if(null == userModel) {
			return Constans.returnCon(-2, "用户不存在");
		}
		if(U.verify(password, userModel.getSalt(), userModel.getPassword())) {
			//用户登录成功，加载权限列表
			userModel.setPassword("");
			request.getSession().setAttribute("adminUserPowerList", powerService.selectUserPowers(userModel.getUid()));
			
			request.getSession().setAttribute("adminUserInfo", userModel);
			//设置后台登录会话的登录时长
			request.getSession().setMaxInactiveInterval(0);
			U.logLogin(userModel.getUid() + "  " + request.getRemoteAddr() + "  登录");
			return Constans.returnCon(1, null);
		}else {
			return Constans.returnCon(-1, "密码错误");
		}
	}
	
	/**
	 * 管理员登出
	 * @param request
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/loginOut")
	public void loginOut(HttpServletRequest request, HttpServletResponse response) {
		U.logLogin(((XxdUser)request.getSession().getAttribute("adminUserInfo")).getUid() + "  " + request.getRemoteAddr() + "  登出");
		request.getSession().removeAttribute("adminUserPowerList");
		request.getSession().removeAttribute("adminUserInfo");
		//展示无权限对应页面板块
		try {
			response.sendRedirect("/admin/login.html");
		} catch (IOException e) {
			U.exceptionLog(e, null);
		}
	}
	
	/**
	 * 添加总页面（适用于总管理员方便操作）
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/user_add.html")
	public ModelAndView userAddHtml() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user_add");
		String y = U.getLeaderRandom();
		for(;;) {
			if(userService.selectUserByUsername(y) == null) {
				break;
			}
			y = U.getLeaderRandom();
		}
		mav.addObject("username", y);
		return mav;
	}
	
	/**
	 * 修改总页面（适用于总管理员方便操作）
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/user_edit.html")
	public String userEditHtml() {
		return "/admin/user_edit";
	}
	
	/**
	 * 销总添加页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/user_leader_add.html")
	public ModelAndView userLeaderAddHtml() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user_leader_add");
		String y = U.getLeaderRandom();
		for(;;) {
			if(userService.selectUserByUsername(y) == null) {
				break;
			}
			y = U.getLeaderRandom();
		}
		mav.addObject("username", y);
		return mav;
	}
	
	/**
	 * 销总修改页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/user_leader_edit.html")
	public ModelAndView userLeaderEditHtml(Integer uid) {
		XxdUser userInfo = userService.selectByPrimaryKey(uid);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user_leader_edit");
		mav.addObject("userInfo", userInfo);
		return mav;
	}
	
	/**
	 * 会员添加页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/user_member_add.html")
	public ModelAndView userMemberAddHtml() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user_member_add");
		String y = U.getLeaderRandom();
		for(;;) {
			if(userService.selectUserByUsername(y) == null) {
				break;
			}
			y = U.getLeaderRandom();
		}
		mav.addObject("username", y);
		return mav;
	}
	
	/**
	 * 会员修改页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/user_member_edit.html")
	public ModelAndView userMemberEditHtml(Integer uid) {
		XxdUser userInfo = userService.selectByPrimaryKey(uid);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user_member_edit");
		mav.addObject("userInfo", userInfo);
		return mav;
	}
	
	/**
	 * 管理员添加页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/user_manager_add.html")
	public ModelAndView userManagerAddHtml() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user_manager_add");
		String y = U.getLeaderRandom();
		for(;;) {
			if(userService.selectUserByUsername(y) == null) {
				break;
			}
			y = U.getLeaderRandom();
		}
		mav.addObject("username", y);
		return mav;
	}
	
	/**
	 * 管理员修改页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/user_manager_edit.html")
	public ModelAndView userManagerEditHtml(Integer uid) {
		XxdUser userInfo = userService.selectByPrimaryKey(uid);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/user_manager_edit");
		mav.addObject("userInfo", userInfo);
		return mav;
	}
	
//	/**
//	 * 积分管理显示页面
//	 * @return
//	 */
//	@AdminLogin
//	@RequestMapping("/integration.html")
//	public ModelAndView integrationHtml(HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/admin/integration");
//		ArrayList<XxdIntegration> models = null;
//		//获取用户中的管理员信息(判断当前用户的级别，根据级别来确定调用方法)
//		//用户基本信息
//		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
//		//判断是否是总管理员
//		if(userInfo.getType() == 0) {
//			mav.addObject("isAdmin", true);
//			models = integrationService.selectAll();
//		}else {
//			mav.addObject("isAdmin", false);
//			models = integrationService.selectAllParentId(userInfo.getUid());
//		}
//		mav.addObject("integration", models);
//		return mav;
//	}
	
	/**
	 * 积分记录显示页面
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/integration_report.html")
	public ModelAndView integrationReportHtml(XxdIntegration xxdIntegration) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/integration_report");
		mav.addObject("integration", xxdIntegration);
		//查询用户的冻结积分记录
		ArrayList<XxdIntegrationFreeze> integrationFreeze = integrationFreezeService.selectByUid(xxdIntegration.getUid());
		for(int i = 0;i < integrationFreeze.size();i ++) {
			integrationFreeze.get(i).setFreeTypeCon(Constans.FREEZETYPE[integrationFreeze.get(i).getFreezeType()]);
		}
		mav.addObject("integrationFreeze" , integrationFreeze);
		//查询用户的划拨积分记录
		ArrayList<XxdIntegrationTransfer> integrationTransfer = integrationTransferService.selectByUid(xxdIntegration.getUid());
		for(int i = 0;i < integrationTransfer.size();i ++) {
			integrationTransfer.get(i).setTransferTypeCon(Constans.TRANSFERTYPE[integrationTransfer.get(i).getTransferType()]);
		}
		mav.addObject("integrationTransfer" , integrationTransfer);
		return mav;
	}
	
//	/**
//	 * 用户积分信息添加页面（适用于总管理员）
//	 * @return
//	 */
//	@AdminLogin
//	@RequestMapping("/integration_add.html")
//	public String integrationAddHtml() {
//		return "/admin/integration_add";
//	}
//	
//	/**
//	 * 销总积分信息添加页面
//	 * @return
//	 */
//	@AdminLogin
//	@RequestMapping("/integration_add_leader.html")
//	public String integrationAddLeaderHtml() {
//		return "/admin/integration_add_leader";
//	}
//	
//	/**
//	 * 会员积分信息添加页面
//	 * @return
//	 */
//	@AdminLogin
//	@RequestMapping("/integration_add_member.html")
//	public ModelAndView integrationAddMemberHtml(HttpServletRequest request) {
//		//获取当前的管理者id
//		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/admin/integration_add_member");
//		mav.addObject("uid", userInfo.getUid());
//		return mav;
//	}
//	
//	/**
//	 * 销总会员积分修改显示页面
//	 * @return
//	 */
//	@AdminLogin
//	@RequestMapping("/integration_edits.html")
//	public ModelAndView integrationEditsHtml(Integer id, Integer uid, HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		//获取用户中的管理员信息(判断当前用户的级别，根据级别来确定调用方法)
//		//用户基本信息
//		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
//		//查询用户类型
//		XxdUser userModel = userService.selectByPrimaryKey(uid);
//		XxdIntegration model = null;
//		if(userInfo.getType() == 0) {
//			model = integrationService.selectByPrimaryKey(id);
//		}else {
//			if(userModel.getType() == 2) {
//				model = integrationService.selectByPrimaryKeyParentId(id, userInfo.getUid());
//			}else {
//				model = integrationService.selectByPrimaryKeyParentsId(id, userInfo.getUid());
//			}
//		}
//		mav.setViewName("/admin/integration_edits");
//		mav.addObject("integration", model);
//		return mav;
//	}
//	
//	/**
//	 * 用户积分修改显示页面（适用于总管理员）
//	 * @return
//	 */
//	@AdminLogin
//	@RequestMapping("/integration_edit.html")
//	public String integrationEditHtml(Integer id, HttpServletRequest request) {
//		return "/admin/integration_edit";
//	}
//	
//	/**
//	 * 积分冻结记录显示页面
//	 * @return
//	 */
//	@AdminLogin
//	@RequestMapping("/integration_freeze.html")
//	public ModelAndView integrationFreezeHtml(Integer id, HttpServletRequest request) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/admin/integration_freeze");
//		XxdIntegrationFreeze model = null;
//		//获取用户中的管理员信息(判断当前用户的级别，根据级别来确定调用方法)
//		//用户基本信息
//		XxdUser userInfo = (XxdUser) request.getSession().getAttribute("adminUserInfo");
//		//判断是否是总管理员
//		if(userInfo.getType() == 0) {
//			model = integrationFreezeService.selectByPrimaryKey(id);
//		}else {
//			model = integrationFreezeService.selectByPrimaryKeyParentId(id, userInfo.getUid());
//		}
//		mav.addObject("integration_freeze", model);
//		return mav;
//	}
//	
//	/**
//	 * 积分冻结添加页面
//	 * @return
//	 */
//	@AdminLogin
//	@RequestMapping("/integration_freeze_add.html")
//	public ModelAndView integrationFreezeAddHtml(Integer uid) {
//		ModelAndView mav = new ModelAndView();
//		mav.setViewName("/admin/integration_freeze_add");
//		mav.addObject("uid", uid);
//		return mav;
//	}
	
	/**
	 * 商品页面信息
	 * @param uid
	 * @param show_type
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/goods.html")
	public ModelAndView goodsHtml(Integer uid, Integer show_type) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/goods");
		ArrayList<XxdGoods> models = goodsService.selectByUid(uid);
		ArrayList<XxdGoods> gResult = new ArrayList<XxdGoods>();
		for(XxdGoods model : models) {
			if(model.getGroundingSta()+0 == show_type) {
				gResult.add(model);
			}else if(model.getGroundingSta()+0 == show_type) {
				gResult.add(model);
			}
		}
		mav.addObject("goods", show_type == 3 ? models : gResult);
		mav.addObject("uid", uid);
		return mav;
	}
	
	
	/**
	 * 显示图片
	 * @param id
	 * @param dir
	 * @return
	 */
	@AdminLogin
	@RequestMapping("/goods_img.html")
	public ModelAndView goodsImgHtml(Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/goods_img");
		//由于刷新,因此需要重新查询当前内容
		XxdGoods goods = goodsService.selectByPrimaryKey(id);
		String dir = goods.getImgFontDir();
		String[] dirNum = dir.split("/");
		Integer imgNum = Integer.parseInt(dirNum[2]);
		ArrayList<String[]> imgPath = new ArrayList<String[]>();
		for(int i = 1;i <= imgNum;i ++) {
			imgPath.add(new String[] {ProperU.read(Constans.PROSOURCE, "host") + Constans.IMGHANDLER + "/goods/" + dirNum[0] + "/" + dirNum[1] + "/" + i + ".jpg", i+""});
		}
		
		dir = goods.getShowImgDir();
		dirNum = dir.split("/");
		imgNum = Integer.parseInt(dirNum[2]);
		ArrayList<String[]> imgPath1 = new ArrayList<String[]>();
		for(int i = 1;i <= imgNum;i ++) {
			imgPath1.add(new String[] {ProperU.read(Constans.PROSOURCE, "host") + Constans.IMGHANDLER + "/goods/" + dirNum[0] + "/" + dirNum[1] + "/" + i + ".jpg", i+""});
		}
		
		mav.addObject("imgPath", imgPath);
		mav.addObject("imgPath1", imgPath1);
		mav.addObject("id", id);
		mav.addObject("saveDir", dirNum[0]);
		return mav;
	}
	
	@AdminLogin
	@RequestMapping("/goods_img_add.html")
	public ModelAndView goodsImgAddHtml(Integer id, String saveDir, Integer type) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/goods_img_add");
		mav.addObject("id", id);
		mav.addObject("saveDir", saveDir);
		mav.addObject("type", type);
		mav.addObject("postUrl", ProperU.read(Constans.PROSOURCE, "host"));
		return mav;
	}
	
	@AdminLogin
	@RequestMapping(value = "/goods_add.html")
	public ModelAndView goodsAddHtml(Integer uid, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/goods_add");
		mav.addObject("uid", uid);
		mav.addObject("type", Constans.GOODS);
		String time = U.md5Hex(System.currentTimeMillis()+"");
		String nowTime = time.substring(time.length()/2);
		mav.addObject("saveDir", nowTime);
		return mav;
	}
	
//	public static void main(String[] args) {
////		Integer imgNum = Integer.parseInt(("asd/si2".split("/")[1]).substring(2));
////		//ArrayList<String> imgPath = new ArrayList<String>();
////		for(int i = 1;i <= imgNum;i ++) {
////			System.out.println(ProperU.read(Constans.PROSOURCE, "uploadImgPath") + "goods/" + "asd/si2" + "/" + i + ".jpg");
////		}
//		System.out.println("a,a,a,".substring(0, 5));
//	}
	
	private final ResourceLoader resourceLoader;
	
	@Autowired
    public XxdAdminC(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
	
	/**
	 * 显示单张图片
	 * @param fileName
	 * @return
	 */
	@AdminLogin
	@Cacheable(value="imgSource")
    @RequestMapping("/show")
    public ResponseEntity<Resource> showPhotos(String filePath, HttpServletRequest request){
		//应对安全问题，只能指向本地图片的内容
    	if(!filePath.startsWith(ProperU.read(Constans.PROSOURCE, "host") + Constans.IMGHANDLER)) {
    		//记录本次访问异常
    		U.logAction("本次图片访问异常！请注意！ip：" + request.getRemoteAddr());
    		return null;
    	}
        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            return ResponseEntity.ok(resourceLoader.getResource("file:" + filePath));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
	@AdminLogin
    @RequestMapping("/goods_greight.html")
    public ModelAndView goodsGreightHtml(Integer id) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_greight");
    	mav.addObject("id", id);
    	ArrayList<XxdGoodsGreight> model = goodsGreightService.selectAllByGid(id);
    	mav.addObject("goodsGreight", model);
    	return mav;
    }
    
    @Autowired
    private XxdGoodsPriceSI goodsPriceService;
    
    @AdminLogin
    @RequestMapping("/goods_price.html")
    public ModelAndView goodsPriceHtml(Integer id) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_price");
    	ArrayList<XxdGoodsPrice> result = goodsPriceService.selectAllByGoodsId(id);
    	//转换规格图片
    	//拆分规格信息
    	for(int i = 0;i < result.size();i ++) {
    		result.get(i).setImg(ProperU.read(Constans.PROSOURCE, "host") + Constans.IMGHANDLER + Constans.GOODSIMGDIR + result.get(i).getImg());
    	}
    	mav.addObject("goodsPrice", result);
    	mav.addObject("goods_id", id);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/goods_price_add.html")
    public ModelAndView goodsPriceAddHtml(Integer goodsId) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_price_add");
    	mav.addObject("goods_id", goodsId);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/goods_price_edit.html")
    public ModelAndView goodsPriceEditHtml(XxdGoodsPrice model) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_price_edit");
    	mav.addObject("goodsPrice", model);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/goods_edit.html")
    public ModelAndView goodsEditHtml(Integer id, HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_edit");
    	mav.addObject("goods", goodsService.selectByPrimaryKey(id));
    	return mav;
    }
    
    @Autowired
    private XxdBuyOrderSI buyOrderService;
    
    @AdminLogin
    @PostMapping("/orders")
    @ResponseBody
    public HashMap<String, Object> orders(String time){
    	HashMap<String, Object> con = new HashMap<String, Object>();
    	/*int[] count =  buyOrderService.selectAllBuyOrderTime(time);
    	con.put("count",count);*/
    	
    	return Constans.returnCon(1,con);
    }
    
    @AdminLogin
    @RequestMapping("/order_graph.html")
    public  ModelAndView OrderCount(String time){
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/order_graph");
    	int[] count = buyOrderService.selectAllBuyOrderTime(time);
    	mav.addObject("count",count);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/goods_picture_handle.html")
    public  ModelAndView pictureHandleRecord(){
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_picture_handle");
    	ArrayList<XxdPictureHandle> con =  pictureHandleServiceImp.selectAllRecord();
    	mav.addObject("con", con);
    	return mav;
    }
    
    
    @AdminLogin
    @RequestMapping("/goods_owner_add_edit.html")
    public ModelAndView changeImageSize(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/goods_owner_add_edit");
		ArrayList<XxdPictureHandle> record =  pictureHandleServiceImp.selectTenRecord();
		mav.addObject("uid", 0);
		mav.addObject("type", Constans.GOODSOWNER);
		String time = U.md5Hex(System.currentTimeMillis()+"");
		String nowTime = time.substring(time.length()/2);
		mav.addObject("saveDir", nowTime);
		mav.addObject("record",record);
		return mav;
    }
    
    @AdminLogin
    @RequestMapping("/order_pie.html")
    public  ModelAndView OrderCount2(String time){
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/order_pie");
    	
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/order_buy.html")
    public ModelAndView orderHtml(Integer uid) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/order_buy");
    	ArrayList<XxdBuyOrder> con = buyOrderService.selectAllByUidType(uid, Constans.ORDERBUY);
    	for(int i = 0;i < con.size();i ++) {
    		con.get(i).setStaCon(Constans.ORDERSTA[con.get(i).getSta()]);
    	}
    	mav.addObject("buyOrder", con);
    	mav.addObject("uid", uid);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/order_seller.html")
    public ModelAndView orderSellerHtml(Integer buy_id) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/order_seller");
    	ArrayList<XxdOrder> con = buyOrderService.selectAllsByUidType(buy_id, Constans.ORDERBUY);
    	for(int i = 0;i < con.size();i ++) {
    		String[] dirNum = con.get(i).getShow_img_dir().split("/");
    		con.get(i).setShow_img_dir(ProperU.read(Constans.PROSOURCE, "host") + Constans.IMGHANDLER + "/goods/" + dirNum[0] + "/" + dirNum[1] + "/1.jpg");
    		con.get(i).setStaCon(Constans.ORDERSTA[con.get(i).getSta()]);
    		U.cancelDisAbleStr(con.get(i));
    	}
    	mav.addObject("sellerOrder", con);
    	return mav;
    }
    
    
    @Autowired
    private XxdBuyOrderDetailsSI buyOrderDetailsService;
    
    @AdminLogin
    @RequestMapping("/buy_order_details.html")
    public ModelAndView buyOrderDetailsHtml(Integer order_id, Integer uid) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/buy_order_details");
    	ArrayList<XxdBuyOrderDetails> buyOrderDetails = buyOrderDetailsService.selectAllByOrderId(order_id, uid);
    	for(int i = 0;i < buyOrderDetails.size();i ++) {
    		String[] dirNum = buyOrderDetails.get(i).getShow_img_dir().split("/");
    		buyOrderDetails.get(i).setShow_img_dir(ProperU.read(Constans.PROSOURCE, "host") + Constans.IMGHANDLER + "/goods/" + dirNum[0] + "/" + dirNum[1] + "/1.jpg");
    		buyOrderDetails.get(i).setStaCon(Constans.ORDERDETAILSSTA[buyOrderDetails.get(i).getSta()]);
    		U.cancelDisAbleStr(buyOrderDetails.get(i));
    	}
    	mav.addObject("buyOrderDetails", buyOrderDetails);
    	return mav;
    }
    
    @Autowired
    private XxdUserAddrSI userAddrService;
    
    @AdminLogin
    @RequestMapping("/user_addr.html")
    public ModelAndView userAddrHtml(Integer addr_id) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/user_addr");
    	XxdUserAddr userAddr = userAddrService.selectByPrimaryKey(addr_id);
    	mav.addObject("userAddr", userAddr);
    	return mav;
    }
    
    
    @Autowired
    private XxdGoodsClassSI goodsClassService;
    
    @AdminLogin
    @RequestMapping("/goods_class.html")
    public ModelAndView goodsClassHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_class");
    	ArrayList<XxdGoodsClass> goodsClass = goodsClassService.selectAll();
    	mav.addObject("goodsClass", goodsClass);
    	return mav;
    }
    
    @Autowired
    private XxdGoodsProductPackageSI goodsProductPackageService;
    
    @AdminLogin
    @RequestMapping("/goods_product_package.html")
    public ModelAndView goodsProductPackageHtml() {
    	ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/goods_product_package");
		ArrayList<XxdGoodsProductPackage> goods = goodsProductPackageService.selectAll();
		for(int i = 0;i < goods.size();i ++) {
			goods.get(i).setShowImgDir(ProperU.read(Constans.PROSOURCE, "host") + Constans.IMGHANDLER + Constans.GOODSIMGDIR + goods.get(i).getShowImgDir());
		}
		mav.addObject("goods", goods);
		return mav;
    }
    
    @AdminLogin
    @RequestMapping("/goods_product_package_img.html")
    public ModelAndView goodsProductPackageImgHtml(Integer id, String dir, Integer type) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/goods_product_package_img");
		String[] dirNum = dir.split("/");
		Integer imgNum = Integer.parseInt(dirNum[2]);
		ArrayList<String[]> imgPath = new ArrayList<String[]>();
		for(int i = 1;i <= imgNum;i ++) {
			imgPath.add(new String[] {ProperU.read(Constans.PROSOURCE, "host") + Constans.IMGHANDLER + Constans.GOODSIMGDIR + dirNum[0] + "/" + dirNum[1] + "/" + i + ".jpg", i+""});
		}
		mav.addObject("imgPath", imgPath);
		mav.addObject("id", id);
		mav.addObject("type", type);
		mav.addObject("dir", dir);
		return mav;
    }
    
    @AdminLogin
	@RequestMapping(value = "/goods_product_package_add.html")
	public ModelAndView goodsProductPackageAddHtml(Integer uid, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/goods_product_package_add");
		String time = U.md5Hex(System.currentTimeMillis()+"");
		String nowTime = time.substring(time.length()/2);
		mav.addObject("saveDir", nowTime);
		return mav;
	}
    
    @AdminLogin
    @RequestMapping("/goods_product_package_edit.html")
    public ModelAndView goodsProductPackageEditHtml(Integer id, HttpServletRequest request) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_product_package_edit");
    	XxdGoodsProductPackage goods = goodsProductPackageService.selectByPrimaryKey(id);
    	goods.setImgFontDir(goods.getShowImgDir().split("/")[0]);
    	goods.setShowImgDir(ProperU.read(Constans.PROSOURCE, "host") + Constans.IMGHANDLER + Constans.GOODSIMGDIR + goods.getShowImgDir());
    	mav.addObject("goods", goods);
    	return mav;
    }
    
    @AdminLogin
	@RequestMapping("/goods_owner.html")
	public ModelAndView goodsOwnerHtml() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/goods_owner");
		ArrayList<XxdGoods> models = goodsService.selectAllByType(Short.parseShort("2"));
		mav.addObject("goods", models);
		return mav;
	}
    
    @AdminLogin
	@RequestMapping(value = "/goods_owner_add.html")
	public ModelAndView goodsOwnerAddHtml() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/admin/goods_owner_add");
		mav.addObject("uid", 0);
		mav.addObject("type", Constans.GOODSOWNER);
		String time = U.md5Hex(System.currentTimeMillis()+"");
		String nowTime = time.substring(time.length()/2);
		mav.addObject("saveDir", nowTime);
		return mav;
	}
    
    @AdminLogin
    @RequestMapping("/goods_owner_edit.html")
    public ModelAndView goodsOwnerEditHtml(Integer id) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_owner_edit");
    	mav.addObject("goods", goodsService.selectByPrimaryKey(id));
		mav.addObject("type", Constans.GOODSOWNER);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/order_product_package.html")
    public ModelAndView orderProductPackageHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/order_product_package");
    	ArrayList<XxdBuyOrder> con = buyOrderService.selectAllByType(Short.parseShort("1"));
    	for(int i = 0;i < con.size();i ++) {
    		con.get(i).setStaCon(Constans.ORDERSTA[con.get(i).getSta()]);
    	}
    	mav.addObject("con", con);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/order_product_package_details.html")
    public ModelAndView orderProductPackageDetailsHtml(Integer order_id) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/order_product_package_details");
    	ArrayList<XxdBuyOrderDetails>  con = buyOrderDetailsService.selectAllByOrderId1(order_id);
    	for(int i = 0;i < con.size();i ++) {
    		String[] dirNum = con.get(i).getShow_img_dir().split("/");
    		con.get(i).setShow_img_dir(ProperU.read(Constans.PROSOURCE, "host") + Constans.IMGHANDLER + "/goods/" + dirNum[0] + "/" + dirNum[1] + "/1.jpg");
    		con.get(i).setStaCon(Constans.ORDERDETAILSSTA[con.get(i).getSta()]);
    		U.cancelDisAbleStr(con.get(i));
    	}
    	mav.addObject("orderProductPackageDetails", con);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/order_goods_owner.html")
    public ModelAndView orderGoodsOwnerHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/order_goods_owner");
    	ArrayList<XxdOrder> con = buyOrderService.selectAllsByUidType(0, Constans.ORDERBUY);
    	for(int i = 0;i < con.size();i ++) {
    		String[] dirNum = con.get(i).getShow_img_dir().split("/");
    		con.get(i).setShow_img_dir(ProperU.read(Constans.PROSOURCE, "host") + Constans.IMGHANDLER + "/goods/" + dirNum[0] + "/" + dirNum[1] + "/1.jpg");
    		con.get(i).setStaCon(Constans.ORDERSTA[con.get(i).getSta()]);
    		con.get(i).setOrder_id(Integer.parseInt(con.get(i).getOrder_id() + "" + con.get(i).getId()));
    	}
    	mav.addObject("orderOwner", con);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/power.html")
    public ModelAndView powerHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/power");
    	ArrayList<XxdPower> models = powerService.selectAll();
    	for(int i = 0;i < models.size();i ++) {
    		models.get(i).setTypeCon(Constans.POWERTYPE[models.get(i).getType()]);
    	}
    	mav.addObject("models", models);
    	return mav;
    }

    @AdminLogin
    @RequestMapping("/power_add.html")
    public String powerAddHtml() {
    	return "/admin/power_add";
    }
    
    @AdminLogin
    @RequestMapping("/power_edit.html")
    public ModelAndView powerEditHtml(Integer id) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/power_edit");
    	XxdPower models = powerService.selectByPrimaryKey(id);
    	mav.addObject("models", models);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/declaration.html")
    public ModelAndView declarationHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/declaration");
    	//查询产品包订单基本信息,以及对应的需要升级的会员信息
    	ArrayList<XxdDeclaration> decla = buyOrderService.selectProductPackage();
    	mav.addObject("decla", decla);
    	return mav;
    }
    
    
    @Autowired
    private XxdPowerGroupSI powerGroupService;
    
    @AdminLogin
    @RequestMapping("/power_group.html")
    public ModelAndView powerGroupHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/power_group");
    	ArrayList<XxdPowerGroup> models = powerGroupService.selectAlls();
    	mav.addObject("models", models);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/power_group_add.html")
    public ModelAndView userPowerAddHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/power_group_add");
    	//查询所有权限信息以供选择
    	ArrayList<XxdPower> powerInfo = powerService.selectAll();
    	mav.addObject("powerInfo", powerInfo);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/power_group_edit.html")
    public ModelAndView userPowerEditHtml(Integer id) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/power_group_edit");
    	//查询所有权限信息以供选择
    	ArrayList<XxdPower> powerInfo = powerService.selectAll();
    	//查询当前权限组信息
    	XxdPowerGroup powerGroup = powerGroupService.selectByPrimaryKey(id);
    	String[] pg = powerGroup.getPowers().split(",");
    	//手动判断是否存在
    	for(int i = 0;i < powerInfo.size();i ++) {
    		powerInfo.get(i).setIsChecked(Short.parseShort("0"));
    		for(String p : pg) {
    			if(p.equals(powerInfo.get(i).getId() + "")) {
    				powerInfo.get(i).setIsChecked(Short.parseShort("1"));
    				break;
    			}
    		}
    	}
    	mav.addObject("powerInfo", powerInfo);
    	mav.addObject("powerGroup", powerGroup);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/user_power.html")
    public ModelAndView userPowerHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/user_power");
    	//查询所有的销总,通过修改所属管理员来控制用户权限
    	ArrayList<XxdUser> leaderUserInfo = userService.selectPowerByUserType(Constans.LEADERUSER);
    	mav.addObject("leaderUserInfo", leaderUserInfo);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/user_power_change.html")
    public ModelAndView userPowerChangeHtml(XxdUser userInfo) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/user_power_change");
    	mav.addObject("userInfo" , userInfo);
    	mav.addObject("managerInfo", userService.selectByUserType(Constans.MANAGERUSER));
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/page_power.html")
    public ModelAndView pagePowerHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/page_power");
    	ArrayList<XxdUserPowerGroup> pagePower = userService.selectUserPowerGroup();
    	ArrayList<XxdPower> powerInfo = powerService.selectAll();
    	for(int i = 0;i < pagePower.size();i ++) {
    		if(pagePower.get(i).getPowers() != null) {
    			String[] power = pagePower.get(i).getPowers().split(",");
        		int[] powe = new int[power.length];
        		for(int j = 0;j < power.length;j ++) {
        			powe[j] = Integer.parseInt(power[j]);
        		}
        		String powers = "";
        		for(XxdPower pi : powerInfo) {
        			if(Arrays.contains(powe, pi.getId())) {
        				powers += pi.getOutline() + ",";
        			}
        		}
        		pagePower.get(i).setPowers(powers.substring(0, powers.length()-1));
    		}else {
    			pagePower.get(i).setPowers("");
    		}
    	}
    	mav.addObject("userPowerGroup", pagePower);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/page_power_add.html")
    public ModelAndView pagePowerAddHtml(XxdUser userInfo) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/page_power_add");
    	//显示所有权限,以供用户选择
    	ArrayList<XxdPower> powerInfo = powerService.selectAll();
    	//显示所有权限组信息,以供用户选择
    	ArrayList<XxdPowerGroup> powerGroupInfo = powerGroupService.selectAll();
    	mav.addObject("powerGroupInfo", powerGroupInfo);
    	mav.addObject("powerInfo", powerInfo);
    	mav.addObject("userInfo", userInfo);
    	return mav;
    }
    
    @Autowired
    private XxdUserPowerSI userPowerSI;
    
    @AdminLogin
    @RequestMapping("/page_power_edit.html")
    public ModelAndView pagePowerEditHtml(Integer uid, String username, String name, Integer pgid) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/page_power_edit");
    	//显示所有权限,以供用户选择
    	ArrayList<XxdPower> powerInfo = powerService.selectAll();
    	//查询当前用户权限组信息
    	XxdPowerGroup powerGroup = powerGroupService.selectByPrimaryKey(pgid);
    	//查询出用户权限关系信息
    	XxdUserPower userPower = userPowerSI.selectByUid(uid);
    	String[] pg = (0 != pgid) ? powerGroup.getPowers().split(",") : userPower.getPowers().split(",");
    	//手动判断是否存在
    	for(int i = 0;i < powerInfo.size();i ++) {
    		powerInfo.get(i).setIsChecked(Short.parseShort("0"));
    		for(String p : pg) {
    			if(p.equals(powerInfo.get(i).getId() + "")) {
    				powerInfo.get(i).setIsChecked(Short.parseShort("1"));
    				break;
    			}
    		}
    	}
    	XxdUser userInfo = new XxdUser();
    	userInfo.setUid(uid);
    	userInfo.setUsername(username);
    	userInfo.setName(name);
    	//显示所有权限组信息,以供用户选择
    	ArrayList<XxdPowerGroup> powerGroupInfo = powerGroupService.selectAll();
    	mav.addObject("userPower", userPower);
    	mav.addObject("powerGroupInfo", powerGroupInfo);
    	mav.addObject("userInfo", userInfo);
    	mav.addObject("powerInfo", powerInfo);
    	mav.addObject("pgid", pgid);
    	return mav;
    }
    
    @Autowired
    private XxdSystemSI systemService;
    
    /**
     * 销总利润设置
     * @return
     */
    @AdminLogin
    @RequestMapping("/leader_profits.html")
    public ModelAndView leaderProfitsHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/leader_profits");
    	ArrayList<XxdSystem> leaderProfit = systemService.selectLikeByName("leaderProfit%");
    	ArrayList<XxdLeaderProfits> leaderProfits = new ArrayList<XxdLeaderProfits>();
    	for(XxdSystem lp : leaderProfit) {
    		leaderProfits.add(new XxdLeaderProfits(lp.getId(), lp.getName(), new BigDecimal(lp.getValue()), lp.getDescription()));
    	}
    	mav.addObject("leaderProfits", leaderProfits);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/bank_info.html")
    public ModelAndView bankInfoHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/bank_info");
    	ArrayList<XxdSystem> bankInfo = systemService.selectLikeByName("bank%");
    	mav.addObject("bankInfo", bankInfo);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/system_setting.html")
    public ModelAndView systemSettingHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/system_setting.html");
    	
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/transferInte.html")
    public ModelAndView transferInteHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/transferInte");
    	return mav;
    }
    
    @Autowired
    private XxdGoodsParametricSI goodsParametricService;
    
    /**
     * 商品属性
     * @param goodsId
     * @return
     */
    @AdminLogin
    @RequestMapping("/goods_parametric.html")
    public ModelAndView goodsParametricHtml(Integer goodsId) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_parametric");
    	ArrayList<XxdGoodsParametric> result =  goodsParametricService.selectByGoodsId(goodsId);
    	mav.addObject("goodsParametric", result == null ? new ArrayList<XxdGoodsParametric>() : result);
    	mav.addObject("goodsId", goodsId);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/goods_parametric_add.html")
    public ModelAndView goodsParametricAddHtml(Integer goodsId) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_parametric_add");
    	mav.addObject("goodsId", goodsId);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/goods_parametric_edit.html")
    public ModelAndView goodsParametricEditHtml(Integer id, String name, String value) {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/goods_parametric_edit");
    	mav.addObject("id", id);
    	mav.addObject("name", name);
    	mav.addObject("value", value);
    	return mav;
    }
    
    @AdminLogin
    @RequestMapping("/img_test.html")
    public ModelAndView imgTestHtml() {
    	ModelAndView mav = new ModelAndView();
    	mav.setViewName("/admin/img_test");
    	mav.addObject("uid", 0);
		mav.addObject("type", Constans.GOODSOWNER);
		String time = U.md5Hex(System.currentTimeMillis()+"");
		String nowTime = time.substring(time.length()/2);
		mav.addObject("saveDir", nowTime);
    	return mav;
    }
    
    @AdminLogin
    @PostMapping("/imgTest")
    @ResponseBody
    public HashMap<String, Object> imgTest(HttpServletRequest request, String saveDir){
    	HashMap<String, String> con = new HashMap<String, String>();
    	con.put("file", Constans.GOODSIMGDIR + saveDir);
    	HashMap<String, String> con1 = ImgU.uploadImg1(request, con);
    	if(con1.get("file").startsWith("x")) {
    		//x开头的都是图文详情的图片
    		return Constans.returnCon(2, null);
    	}else {
    		return Constans.returnCon(1, null);
    	}
    }
    
    @AdminLogin
    @PostMapping("/imgChangeHandle")
    @ResponseBody
    public HashMap<String, Object> imgTest1(HttpServletRequest request, String saveDir) throws IOException{
    	HashMap<String, String> con = new HashMap<String, String>();
    	con.put("file", Constans.GOODSIMGDIR + saveDir);
    	con.put("code",saveDir);
    	HashMap<String, String> con1 = ImgU.uploadPictureHandle(request, con);
    	con1.get("saveDir");
    	con1.get("file");
    	XxdPicture model = new XxdPicture();
    	model.setPictureName(con1.get("file"));
    	model.setSaveDir(con1.get("saveDir"));
    	pictureHandleService.insert(model);
    	return Constans.returnCon(1, null);
    }
    
//    /**
//     * 商品的分布信息
//     */
//    @AdminLogin
//    @PostMapping("/goodsData")
//    public HashMap<String, Object> 
    
    @AdminLogin
    @PostMapping("/imgAdd")
    @ResponseBody
    public HashMap<String, Object> imgAdd(HttpServletRequest request, String saveDir){
    	HashMap<String, String> con = new HashMap<String, String>();
    	con.put("file", Constans.GOODSIMGDIR + saveDir);
    	HashMap<String, String> con1 = ImgU.uploadImg1(request, con);
    	if(con1.get("file").startsWith("x")) {
    		//x开头的都是图文详情的图片
    		return Constans.returnCon(2, null);
    	}else {
    		return Constans.returnCon(1, null);
    	}
    }
    
    @AdminLogin
    @PostMapping("/imgAddOne")
    @ResponseBody
    public HashMap<String, Object> imgAddOne(HttpServletRequest request, String saveDir){
    	HashMap<String, String> con = new HashMap<String, String>();
    	con.put("file", Constans.GOODSIMGDIR + saveDir);
    	HashMap<String, String> con1 = ImgU.uploadImgOne(request, con);
    	return Constans.returnCon(1, saveDir + "/" + con1.get("file"));
    }
    
   
    //以下为测试接口
    @PostMapping("/testSessionAdd")
    @ResponseBody
    public HashMap<String, Object> testSessionAdd(String str, HttpServletRequest request){
    	//request.isRequestedSessionIdValid();
    	request.getSession().setAttribute("str", str);
    	return Constans.returnCon(1, request.getSession().getId());
    }
    
    @PostMapping("/testSessionSearch")
    @ResponseBody
    public HashMap<String, Object> testSessionSearch(HttpServletRequest request){
    	return Constans.returnCon(1, request.getSession().getAttribute("str"));
    }
    
    public static void main(String[] args) {
    	//随机商品
    	String id = "3,5,6,7,8,9,10,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,32,33,34,36,40,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151";
    	String[] idss = id.split(",");
    	ArrayList<Integer> ids = new ArrayList<Integer>();
    	for(int i = 0;i < idss.length;i++) {
    		ids.add(Integer.parseInt(idss[i]));
    	}
    	Collections.shuffle(ids);
    	int size = ids.size() % 2 == 0 ? ids.size() : ids.size() - 1;
    	for(int i = 0;i < size;i +=2) {
    		System.out.println("update xxd_goods set id = 0 where id = "+ids.get(i) + ";");
    		System.out.println("update xxd_goods_price set goods_id = 0 where goods_id = "+ids.get(i) + ";");
    		
    		System.out.println("update xxd_goods set id = "+ids.get(i)+" where id = "+ids.get(i+1) + ";");
    		System.out.println("update xxd_goods_price set goods_id = "+ids.get(i)+" where goods_id = "+ids.get(i+1) + ";");
    		
    		System.out.println("update xxd_goods set id = "+ids.get(i+1)+" where id = 0;");
    		System.out.println("update xxd_goods_price set goods_id = "+ids.get(i+1)+" where goods_id = 0;");
    	}
    	
    	//将订单对应的内容也随之修改
    	//sql="select group_concat(xgp.id),group_concat(xgp.goods_id) fromxz xxd_goods_price xgp,xxd_buy_order_details xbod where xbod.price = xgp.id;"
    	String id1 = "190,151,149,150,148,198,199,63,88,64,199,190,190,4,40,124,151,126,249,239,236,169,124,151,4,245,57,245,151,262,258,106,242,242,242,198,245,193,249,197,290,266,263,106,242,313,249,247,266,263,205,188,184,175,242,266,205,242";
		String goods_id = "32,59,151,151,151,72,72,13,111,13,72,32,32,80,128,17,59,89,147,97,100,78,17,59,80,32,14,32,59,149,98,103,55,55,55,72,32,140,147,63,67,129,19,103,55,91,147,112,129,19,118,79,96,81,55,129,118,55";
		String[] ids1 = id1.split(",");
		String[] goods_ids = goods_id.split(",");
		for(int i = 0;i < ids1.length;i ++) {
			System.out.println("update xxd_buy_order_details set goods_id = " + goods_ids[i] + " where price = " + ids1[i] + ";");
		}
		
		String str1 = "80,116,145,127,128,124,60,20,14,13,135,45,26,111,122,12,62,103,136,58,28,17,54,25,89,73,71,90,68,151,59,65,46,77,16,43,48,47,102,132,146,144,139,27,104,87,78,24,40,10,81,21,75,96,42,120,138,34,79,123,32,76,30,140,63,72,44,57,69,3,61,118,99,148,133,106,56,108,29,33,143,150,113,105,126,64,100,117,83,97,49,22,55,94,86,112,88,147,114,18,107,93,110,130,5,98,82,134,66,149,19,70,9,129,95,7,8,125,115,141,50,85,119,15,23,142,67,53,74,131,137,91,52,6,51,109,84,121,92,36,152,153,154,155,156,157";
		String str2 = "3,5,6,7,8,9,10,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,32,33,34,36,40,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157";
		
    }
    
}
