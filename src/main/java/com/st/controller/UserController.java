package com.st.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.st.model.ClassModel;
import com.st.model.DictionaryModel;
import com.st.model.MenuModel;
import com.st.model.RolePermModel;
import com.st.model.UserModel;
import com.st.service.impl.ClassService;
import com.st.service.impl.DictionaryService;
import com.st.service.impl.MenuService;
import com.st.service.impl.RolePermService;
import com.st.service.impl.UserService;
import com.st.util.ExcelExporter;
import com.core.session.HtmlUtil;
import com.core.tool.Constant;
import com.core.tool.ExcelUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService<UserModel> userService;
	@Autowired
	private DictionaryService<DictionaryModel> dictionaryService;
	@Autowired
	private ClassService<ClassModel> classService;
	@Autowired
	private MenuService<MenuModel> menuService;
	@Autowired
	private RolePermService<RolePermModel> rolePermService;

	/**
	 * 用户登录验证
	 * 
	 * @param user
	 * @param code
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	@ResponseBody
	public String login(UserModel user, String code, HttpServletRequest request) throws Exception {
		String codetemp = (String) request.getSession().getAttribute(Constant.SYS_SESSION_VALIDATECODE);
		if (!codetemp.equalsIgnoreCase(code)) {
			return "errcode";
		}
		List<UserModel> userList = userService.selectAll(user);
		if (userList != null && !userList.isEmpty()) {
			UserModel usertemp = userList.get(0);
			request.getSession().setAttribute("user", usertemp);
			getDictionaryData(Constant.STU_CHECK, Constant.CHECK_MAP); // 得到字典表数据
			getDictionaryData(Constant.STU_LEAVE, Constant.LEAVE_MAP);
			getDictionaryData(Constant.STU_HOME, Constant.HOME_GRADE);
			getDictionaryData(Constant.STU_HOME_TYPE, Constant.HOME_TYPE);
			getDictionaryData(Constant.HOME_LOCK, Constant.LOCK_MAP);
			getDictionaryData(Constant.STU_PRO, Constant.PRO_MAP);
			rolePrem(usertemp);
			return "ok";
		}
		return "fail";

	}

	public void getDictionaryData(String pcode, Map<String, String> map) throws Exception {
		DictionaryModel dmModel = new DictionaryModel();
		dmModel.setPcode(pcode);
		map.clear();
		List<DictionaryModel> dicList = dictionaryService.selectAll(dmModel);
		for (DictionaryModel d : dicList) {
			map.put(d.getCode(), d.getName());
		}

	}

	public void rolePrem(UserModel uModel) throws Exception {
		Constant.CLA_List.clear();
		Integer roleId = uModel.getRoleId();
		if (roleId == 3) {
			List<ClassModel> classList = classService.selectAll(new ClassModel());
			Constant.CLA_List.addAll(classList);
		} else {
			Constant.CLA_List.add(classService.selectId(uModel.getClassId()));
		}
	}

	/**
	 * 修改用户密码
	 * 
	 * @param uModel
	 * @param newpassword
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatepassword.do", method = RequestMethod.POST)
	@ResponseBody
	public String updatepassword(UserModel uModel, String newpassword, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = uModel.getId();
		String password = userService.selectId(id).getPassword();
		if (!password.equals(uModel.getPassword())) {
			return "err";
		}
		uModel.setPassword(newpassword);
		userService.updateActive(uModel);
		return "ok";
	}

	/**
	 * 查询所有用户
	 * 
	 * @param uModel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectUser.do", method = RequestMethod.POST)
	public void selectUser(UserModel uModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<UserModel> userList = userService.selectAll(uModel);
		HtmlUtil.writerJson(response, userList);
	}

	/**
	 * 根据id得到用户详情
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getuser.do")
	public void getuser(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserModel usertemp = userService.selectId(Integer.valueOf(id));
		HtmlUtil.writerJson(response, usertemp);
	}

	/**
	 * 用户登出
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/exit.do")
	@ResponseBody
	public ModelAndView exit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().removeAttribute("user");

		ModelAndView m = new ModelAndView();
		m.setViewName("login");
		return m;
	}

	/**
	 * 根据id增加用户或修改用户
	 * 
	 * @param uModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateuser.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateuser(UserModel uModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer id = uModel.getId();
		if (id == null) {
			if (uModel.getRoleId() != 1) {
				uModel.setLoginType(2);
			} else {
				uModel.setLoginType(1);
			}
			userService.insert(uModel);
			return "insert";
		} else {
			userService.updateActive(uModel);
		}
		return "update";
	}

	/**
	 * 用户批量增加excel下载
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/exceldown.do")
	@ResponseBody
	public void exceldown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OutputStream out = null;
		out = response.getOutputStream();
		String fileName = "学生添加.xls";// 文件名
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
		String[] columnNames = { "账号", "密码", "姓名", "电话", "地址" };
		String[] methodNames = { "getUserName", "getPassword", "getTrueName", "getPhone", "getAddress" };
		List<UserModel> userList = new ArrayList<>();
		Workbook excel = ExcelExporter.export2Excel("题头", "脚注", "sheet1", columnNames, methodNames, userList);
		excel.write(out);
		out.flush();
		out.close();
	}

	/**
	 * 用户批量增加excel上传
	 * 
	 * @param excelclassId
	 * @param excelfile
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/upexcelfile.do", method = RequestMethod.POST)
	@ResponseBody
	public String upexcelfile(String excelclassId,
			@RequestParam(value = "excelfile", required = false) MultipartFile excelfile, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<UserModel> list = new ExcelUtils().showExcel4(excelfile, "Sheet1");
			if (!list.isEmpty()) {
				for (UserModel u : list) {
					u.setLoginType(1);
					u.setRoleId(1);
					u.setClassId(Integer.valueOf(excelclassId));
					userService.insert(u);
				}
				return "ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return "fail";

	}

	/**
	 * 首页用户对应菜单
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getusermenu.do", method = RequestMethod.POST)
	public void getusermenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
		RolePermModel rModel = new RolePermModel();
		rModel.setRoleId(usertemp.getRoleId());
		List<RolePermModel> rolepermList = rolePermService.selectAll(rModel);
		Map<String, Object> map = new HashMap<>();
		List<MenuModel> menuListOne = new ArrayList<>();
		List<MenuModel> menuListTwo = new ArrayList<>();
		for (RolePermModel r : rolepermList) {
			MenuModel menu = menuService.selectId(r.getMenuId());
			if (Constant.MENU_TYPE_ONE.equals(menu.getMenuType())) {
				menuListOne.add(menu);
			} else {
				menuListTwo.add(menu);
			}
		}
		map.put("one", menuListOne);
		map.put("two", menuListTwo);
		HtmlUtil.writerJson(response, map);
	}

	/**
	 * 得到用户的班级详情
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getusersta.do", method = RequestMethod.POST)
	public void getusersta(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserModel uModel = userService.selectId(Integer.valueOf(id));
		ClassModel userclass = classService.selectId(uModel.getClassId());
		userclass.setTeacherName(userService.selectId(userclass.getTeacherId()).getTrueName());
		uModel.setClassModel(userclass);
		HtmlUtil.writerJson(response, uModel);
	}

}
