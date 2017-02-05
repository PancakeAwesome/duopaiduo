package com.demo.admin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.demo.common.model.Zxad;
import com.demo.common.model.Zxadmin;
import com.demo.common.model.Zxarticle;
import com.demo.common.model.ZxarticleCategory;
import com.demo.common.model.Zxbanner;
import com.demo.common.model.Zxnavigation;
import com.demo.common.model.Zxscreen;
import com.jfinal.core.Controller;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import com.sun.istack.internal.logging.Logger;

/**
 * adminController 后台管理
 */
public class AdminController extends Controller {

	private final static Logger logger = Logger
			.getLogger(AdminController.class);
	
	public void index() {
		Zxadmin admin=getSessionAttr("admin");
		if(admin != null){
			render("index.jsp");
		}else{
			render("login.jsp");
		}
	}
	
	
	public void login() {
		String name = getPara("userName");
		String pwd = getPara("password");
		if(name!=null && pwd !=null){
			Zxadmin admin = Zxadmin.dao.findLogin(name,pwd);
			if(admin != null){
				setSessionAttr("admin", admin);
				setSessionAttr("error",null);
				render("index.jsp");
			}else{
				setSessionAttr("error", "用户名或密码错误！");
				render("login.jsp");
			}
		}else{
			render("login.jsp");
		}
	}

	// 广告分类
	public void bannerflData() {
//		Integer page = getParaToInt("page");
//		Integer rows = getParaToInt("rows");
		Page<Zxbanner> pages = Zxbanner.dao.bannerflData(1, 20);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("rows", pages.getList());
//		map.put("total", pages.getTotalRow());
		renderJson(pages);
	}

	// 广告
	public void adData() {
		Integer page = getParaToInt("page");
		Integer rows = getParaToInt("rows");
		String adName = getPara("adName");
		Integer isShow = getParaToInt("isShow");
		Integer bannerId = getParaToInt("bannerId");
		Page<Zxad> pages = Zxad.dao.adData(page, rows,adName,bannerId,isShow,1);
		renderJson(pages);
	}
	public void adData1() {
		Integer page = getParaToInt("page");
		Integer rows = getParaToInt("rows");
		String adName = getPara("adName");
		Integer isShow = getParaToInt("isShow");
		Integer bannerId = getParaToInt("bannerId");
		Page<Zxad> pages = Zxad.dao.adData(page, rows,adName,bannerId,isShow,0);
		renderJson(pages);
	}

	public void bannflData() { // 分类下拉加载
		renderJson(Zxbanner.dao.getflData());
	}

	public void saveAd() { // 添加
		renderJson(getModel(Zxad.class, "menu").save());
	}
	// id查询
	public void adupsel(){
		setSessionAttr("ad", Zxad.dao.findById(getParaToInt("adId")));
	}
	
	public void adupsh(){
		setSessionAttr("ad", Zxad.dao.findById(getParaToInt("adId")));
	}
	
	public void adUpdate() { // 修改
		renderJson(getModel(Zxad.class, "menu").update());
	}

	public void adDelete() { // 删除
		renderJson(Zxad.dao.delete(getPara("ids")));
	}

	// 播客and美图
	public void bokeData() {
		Integer page = getParaToInt("page");
		Integer rows = getParaToInt("rows");
		Integer tpId = getParaToInt("tpId"); // 1播客 2 美图
		Page<Zxscreen> pages = Zxscreen.dao.adData(page, rows, tpId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", pages.getList());
		map.put("total", pages.getTotalRow());
		renderJson(map);
	}

	public void saveBoke() { // 添加
		renderJson(getModel(Zxscreen.class, "menu").save());
	}

	public void bokeUpdate() { // 修改
		renderJson(getModel(Zxscreen.class, "menu").update());
	}

	public void bokeBDelete() { // 删除
		renderJson(Zxscreen.dao.delete(getPara("ids")));
	}

	// 导航
	public void daohangData() {
		Integer page = getParaToInt("page");
		Integer rows = getParaToInt("rows");
		Page<Zxnavigation> pages = Zxnavigation.dao.adData(page, rows,1);
		renderJson(pages);
	}
	public void youqingData() {
		Integer page = getParaToInt("page");
		Integer rows = getParaToInt("rows");
		Page<Zxnavigation> pages = Zxnavigation.dao.adData(page, rows,2);
		renderJson(pages);
	}
	public void saveDaohang() { // 添加
		renderJson(getModel(Zxnavigation.class, "menu").save());
	}

	public void linkup(){
		setSessionAttr("link", Zxnavigation.dao.findById(getParaToInt("lid")));
	}
	public void daohangUpdate() { // 修改
		renderJson(getModel(Zxnavigation.class, "menu").update());
	}

	public void daohangBDelete() { // 删除
		renderJson(Zxnavigation.dao.delete(getPara("ids")));
	}

	// 文章分类
	public void wzfenleiData() {
	
		Page<ZxarticleCategory> pages = ZxarticleCategory.dao.wzfenleiData(
				1, 100);
		
		renderJson(pages);
	}

	public void flsave() {
		renderJson(getModel(ZxarticleCategory.class, "ZxarticleCategory")
				.save());
	}

	public void flupdate() {
		renderJson(getModel(ZxarticleCategory.class, "ZxarticleCategory")
				.update());
	}

	public void delete() {
		renderJson(ZxarticleCategory.dao.delete(getPara("ids")));
	}

	// 文章内容
	public void wzleirongData() {

		String wenztitle = getPara("wenztitle") == null ? "" : getPara("wenztitle");
		String addTime = getPara("addTime")==null?"":getPara("addTime");

		Integer page = getParaToInt("page");
		Integer rows = getParaToInt("rows");
		
		int articleCategoryId = getParaToInt("articleCategoryId") == null ? 0 : getParaToInt("articleCategoryId");
		int isShow = getParaToInt("isShow") == null ? 0 : getParaToInt("isShow");
		Page<Zxarticle> pages = Zxarticle.dao.wzneirongData(page, rows,1,articleCategoryId,isShow,addTime,wenztitle);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("rows", pages.getList());
//		map.put("total", pages.getTotalRow());
		renderJson(pages);
	}
	public void wzleirongData1() {

		String wenztitle = getPara("wenztitle") == null ? "" : getPara("wenztitle");
		String addTime = getPara("addTime") == null ? "" : getPara("addTime");

		Integer page = getParaToInt("page");
		Integer rows = getParaToInt("rows");
		
		int articleCategoryId = getParaToInt("articleCategoryId") == null ? 0 : getParaToInt("articleCategoryId");
		int isShow = getParaToInt("isShow") == null ? 0 : getParaToInt("isShow");
		Page<Zxarticle> pages = Zxarticle.dao.wzneirongData(page, rows,0,articleCategoryId,isShow,addTime,wenztitle);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("rows", pages.getList());
//		map.put("total", pages.getTotalRow());
		renderJson(pages);
	}

	public void saveArticle() {
		renderJson(getModel(Zxarticle.class, "menu").save());
	}

	public void updateWenz(){
		Zxarticle zxarticle = Zxarticle.dao.findById(getParaToInt("articleId"));
		//System.out.println(zxarticle);
		setSessionAttr("article", zxarticle);
		
	}
	
	public void upshwenz(){
		Zxarticle zxarticle = Zxarticle.dao.findById(getParaToInt("articleId"));
		//System.out.println(zxarticle);
		setSessionAttr("article", zxarticle);
	}

	public void articleUpdate() {
		renderJson(getModel(Zxarticle.class, "menu").update());
	}

	public void wzflData() {
		renderJson(ZxarticleCategory.dao.getflData());
	}

	public void wzerjiData() {
		renderJson(Zxarticle.dao.geterjifl(getParaToInt("fid")));
	}

	public void wzdelete() {
		renderJson(Zxarticle.dao.delete(getPara("ids")));
	}

	public void uploadFile() {

	}
	// 上传图片
	@SuppressWarnings("rawtypes")
	public void upload() {
		// 构造一个文件上传处理对象
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		String re = "";
		Iterator items;
		String path = "";
		Map<String, Object> map = new HashMap<String, Object>();

		try {
			// 解析表单中提交的所有文件内容
			items = upload.parseRequest(getRequest()).iterator();
			while (items.hasNext()) {
				FileItem item = (FileItem) items.next();
				if (!item.isFormField()) {
					// 取出上传文件的文件名称
					String name = item.getName();
					// 取得上传文件以后的存储路径
					String fileName = name.substring(
							name.lastIndexOf('\\') + 1, name.length());
					SimpleDateFormat dirFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					String saveDir = PathKit.getWebRootPath() + "/upload/"
							+ dirFormat.format(new Date()) + "/";
					if (!(new File(saveDir).isDirectory())) {
						new File(saveDir).mkdir();
					}
					path = (saveDir + fileName).replace("\\", "/");
					System.out.println(path);
					re = "upload/" + dirFormat.format(new Date()) + "/"
							+ fileName;
					System.out.println(re);
					File uploaderFile = new File(path);
					item.write(uploaderFile);
				}
			}
			map.put("data", true);
			map.put("re", re);
			renderJson(map);
		} catch (Exception e) {
			map.put("data", false);
			map.put("re", null);
			renderJson(map);
		}
	}
}
