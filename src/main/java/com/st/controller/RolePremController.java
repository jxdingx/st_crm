package com.st.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.session.HtmlUtil;
import com.core.tool.Constant;
import com.st.model.MenuModel;
import com.st.model.RoleModel;
import com.st.model.RolePermModel;
import com.st.service.impl.MenuService;
import com.st.service.impl.RolePermService;
import com.st.service.impl.RoleService;

@Controller
@RequestMapping("/rolePrem")
public class RolePremController {

	@Autowired
	private RoleService<RoleModel> roleService;
	@Autowired
	private MenuService<MenuModel> menuService;
	@Autowired
	private RolePermService<RolePermModel> rolePermService;

	/**
	 * 根据用户权限得到所能查询的班级
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/getclass.do", method = RequestMethod.POST)
	public void getclass(HttpServletResponse response) {
		HtmlUtil.writerJson(response, Constant.CLA_List);
	}

	/**
	 * 查询所有角色
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectRole.do", method = RequestMethod.POST)
	public void selectRole(HttpServletResponse response) throws Exception {
		HtmlUtil.writerJson(response, roleService.selectAll(new RoleModel()));
	}

	/**
	 * 根据id得到角色详情
	 * 
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getrole.do")
	public void getrole(String id, HttpServletResponse response) throws Exception {
		HtmlUtil.writerJson(response, roleService.selectId(Integer.valueOf(id)));
	}

	/**
	 * 根据id判断更新角色或增加角色
	 * 
	 * @param rModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updaterole.do", method = RequestMethod.POST)
	@ResponseBody
	public String updaterole(RoleModel rModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer id = rModel.getId();
		if (id == null) {
			roleService.insert(rModel);
			return "insert";
		}
		roleService.updateActive(rModel);
		return "update";
	}

	/**
	 * 查询所有菜单
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectmenu.do", method = RequestMethod.POST)
	public void selectmenu(HttpServletResponse response) throws Exception {
		MenuModel mModel = new MenuModel();
		mModel.setOrder("menu_type");
		MenuModel mModeltemp = new MenuModel();
		mModeltemp.setMenuType(Constant.MENU_TYPE_ONE);
		List<MenuModel> menuOneList = menuService.selectAll(mModeltemp);
		Map<String, String> map = new HashMap<>();
		for (MenuModel m : menuOneList) {
			map.put(m.getMenuCode(), m.getMenuName());
		}
		List<MenuModel> menuList = menuService.selectModel(mModel);
		for (MenuModel m : menuList) {
			String menutype = m.getMenuType();
			if (Constant.MENU_TYPE_TWO.equals(menutype)) {
				m.setMenuParentName(map.get(m.getMenuParentCode())); // 得到对应菜单类型名称
			}
		}
		HtmlUtil.writerJson(response, menuList);
	}

	/**
	 * 根据id得到菜单详情
	 * 
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getmenu.do")
	public void getmenu(String id, HttpServletResponse response) throws Exception {
		HtmlUtil.writerJson(response, menuService.selectId(Integer.valueOf(id)));
	}

	/**
	 * 根据id判断更新菜单或增加菜单
	 * 
	 * @param mModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatemenu.do", method = RequestMethod.POST)
	@ResponseBody
	public String updatemenu(MenuModel mModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer id = mModel.getId();
		if (id == null) {
			menuService.insert(mModel);
			return "insert";
		}
		menuService.updateActive(mModel);
		return "update";
	}

	/**
	 * 查询一级菜单
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getmenuOne.do", method = RequestMethod.POST)
	public void getmenu(HttpServletResponse response) throws Exception {
		MenuModel mModel = new MenuModel();
		mModel.setMenuType(Constant.MENU_TYPE_ONE);
		HtmlUtil.writerJson(response, menuService.selectAll(mModel));
	}

	/**
	 * 查询角色相对应的菜单
	 * 
	 * @param rModel
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getRolemenu.do", method = RequestMethod.POST)
	public void getRolemenu(RolePermModel rModel, HttpServletResponse response) throws Exception {
		MenuModel mModeltemp = new MenuModel();
		mModeltemp.setMenuType(Constant.MENU_TYPE_ONE);
		List<MenuModel> menuOneList = menuService.selectAll(mModeltemp);
		Map<String, String> map = new HashMap<>();
		for (MenuModel m : menuOneList) {
			map.put(m.getMenuCode(), m.getMenuName());
		}
		List<RolePermModel> rolemenuList = rolePermService.selectAll(rModel);
		List<MenuModel> menuList = new ArrayList<>();
		for (RolePermModel r : rolemenuList) {
			MenuModel menutemp = menuService.selectId(r.getMenuId());
			String menutype = menutemp.getMenuType();
			if (Constant.MENU_TYPE_TWO.equals(menutype)) {
				menutemp.setMenuParentName(map.get(menutemp.getMenuParentCode()));
			}
			menuList.add(menutemp);
		}
		HtmlUtil.writerJson(response, menuList);
	}

	/**
	 * 查询角色相对应的菜单
	 * 
	 * @param rModel
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/gethasRolemenu.do", method = RequestMethod.POST)
	public void gethasRolemenu(RolePermModel rModel, HttpServletResponse response) throws Exception {
		MenuModel mModeltemp = new MenuModel();
		mModeltemp.setMenuType(Constant.MENU_TYPE_ONE);
		List<MenuModel> menuOneList = menuService.selectAll(mModeltemp);
		Map<String, String> map = new HashMap<>();
		for (MenuModel m : menuOneList) {
			map.put(m.getMenuCode(), m.getMenuName());
		}
		List<RolePermModel> rolemenuList = rolePermService.selectAll(rModel);
		List<MenuModel> menuList = new ArrayList<>();
		for (RolePermModel r : rolemenuList) {
			MenuModel menutemp = menuService.selectId(r.getMenuId());
			String menutype = menutemp.getMenuType();
			if (Constant.MENU_TYPE_TWO.equals(menutype)) {
				menutemp.setMenuParentName(map.get(menutemp.getMenuParentCode()));
				menuList.add(menutemp);
			}
		}
		HtmlUtil.writerJson(response, menuList);
	}

	/**
	 * 查询所有菜单
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getallmenu.do", method = RequestMethod.POST)
	public void getallmenu(HttpServletResponse response) throws Exception {
		MenuModel mModeltemp = new MenuModel();
		mModeltemp.setMenuType(Constant.MENU_TYPE_ONE);
		List<MenuModel> menuOneList = menuService.selectAll(mModeltemp);
		Map<String, String> map = new HashMap<>();
		for (MenuModel m : menuOneList) {
			map.put(m.getMenuCode(), m.getMenuName());
		}
		MenuModel mModel2temp = new MenuModel();
		mModel2temp.setMenuType(Constant.MENU_TYPE_TWO);
		List<MenuModel> menuTwoList = menuService.selectModel(mModel2temp);
		for (MenuModel m : menuTwoList) {
			m.setMenuParentName(map.get(m.getMenuParentCode()));
		}
		HtmlUtil.writerJson(response, menuTwoList);
	}

	/**
	 * 根据角色id菜单id判断增加角色的菜单
	 * 
	 * @param checkid
	 * @param roleId
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatehasRolemenu.do", method = RequestMethod.POST)
	public void updatehasRolemenu(String checkid, String roleId, HttpServletResponse response) throws Exception {
		String id[] = checkid.split(",");
		int length = id.length;
		for (int i = 0; i < length; i++) {
			RolePermModel rModel = new RolePermModel();
			rModel.setRoleId(Integer.valueOf(roleId));
			rModel.setMenuId(Integer.valueOf(id[i]));
			if (rolePermService.selectAll(rModel).isEmpty()) {
				parentMenu(roleId, id[i], 1);
				rolePermService.insert(rModel);
			}
		}
		RolePermModel rModeltemp = new RolePermModel();
		rModeltemp.setRoleId(Integer.valueOf(roleId));
		gethasRolemenu(rModeltemp, response);
	}

	/**
	 * 根据角色id菜单id判断删除角色的菜单
	 * 
	 * @param checkid
	 * @param roleId
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/deletehasRolemenu.do", method = RequestMethod.POST)
	public void deletehasRolemenu(String checkid, String roleId, HttpServletResponse response) throws Exception {
		String id[] = checkid.split(",");
		int length = id.length;
		for (int i = 0; i < length; i++) {
			RolePermModel rModel = new RolePermModel();
			rModel.setRoleId(Integer.valueOf(roleId));
			rModel.setMenuId(Integer.valueOf(id[i]));
			List<RolePermModel> rolemenuList = rolePermService.selectAll(rModel);
			if (!rolemenuList.isEmpty()) {
				int idd = rolemenuList.get(0).getId();
				rolePermService.delete(idd);
				parentMenu(roleId, id[i], 2);
			}
		}
		RolePermModel rModeltemp = new RolePermModel();
		rModeltemp.setRoleId(Integer.valueOf(roleId));
		gethasRolemenu(rModeltemp, response);
	}

	public void parentMenu(String roleId, String menuId, Integer action) throws Exception {
		String parentCode = menuService.selectId(Integer.valueOf(menuId)).getMenuParentCode();
		MenuModel mModel = new MenuModel();
		mModel.setMenuCode(parentCode);
		List<MenuModel> menuparentList = menuService.selectAll(mModel);
		if (menuparentList.isEmpty()) {
			return;
		}
		MenuModel rolemenuparent = menuparentList.get(0);
		Integer menuparentId = rolemenuparent.getId();
		RolePermModel rModel = new RolePermModel();
		rModel.setRoleId(Integer.valueOf(roleId));
		rModel.setMenuId(menuparentId);
		List<RolePermModel> rolemenuList = rolePermService.selectAll(rModel);

		if (action == 1) {
			if (rolemenuList.isEmpty()) {
				rolePermService.insert(rModel);
			}
			return;
		}
		if (action == 2) {
			MenuModel mModeltemp = new MenuModel();
			mModeltemp.setMenuParentCode(parentCode);
			List<MenuModel> menuparentListtemp = menuService.selectAll(mModeltemp);
			int count = 0;
			for (MenuModel m : menuparentListtemp) {
				RolePermModel rModeltemp = new RolePermModel();
				rModeltemp.setRoleId(Integer.valueOf(roleId));
				rModeltemp.setMenuId(m.getId());
				count += rolePermService.selectCount(rModeltemp);
			}
			if (count == 0) {
				RolePermModel rModeltemp = new RolePermModel();
				rModeltemp.setRoleId(Integer.valueOf(roleId));
				rModeltemp.setMenuId(rolemenuparent.getId());
				List<RolePermModel> rolemenutempList = rolePermService.selectAll(rModeltemp);
				if (!rolemenutempList.isEmpty()) {
					rolePermService.delete(rolemenutempList.get(0).getId());
				}
			}
			return;
		}

	}
}
