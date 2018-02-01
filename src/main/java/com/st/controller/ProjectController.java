package com.st.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.session.HtmlUtil;
import com.core.tool.Constant;
import com.st.model.ClassModel;
import com.st.model.DictionaryModel;
import com.st.model.ProjectModel;
import com.st.model.UserModel;
import com.st.service.impl.ClassService;
import com.st.service.impl.DictionaryService;
import com.st.service.impl.ProjectService;
import com.st.service.impl.UserService;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	private ProjectService<ProjectModel> projectService;
	@Autowired
	private UserService<UserModel> userService;
	@Autowired
	private ClassService<ClassModel> classService;
	@Autowired
	private DictionaryService<DictionaryModel> dictionaryService;

	/**
	 * 查询登录用户的项目
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectStu.do", method = RequestMethod.POST)
	public void selectStu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProjectModel pModel = new ProjectModel();
		UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
		pModel.setUserId(usertemp.getId());
		List<ProjectModel> CreateQuesList = projectService.selectAll(pModel);
		HtmlUtil.writerJson(response, CreateQuesList);
	}

	/**
	 * 查询该班级classId下所有学生的项目
	 * 
	 * @param classId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectallStu.do", method = RequestMethod.POST)
	public void selectallStu(String classId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserModel usertemp = new UserModel();
		usertemp.setClassId(Integer.valueOf(classId));
		String className = classService.selectId(Integer.valueOf(classId)).getClassName();
		usertemp.setRoleId(1);
		List<UserModel> userList = userService.selectAll(usertemp);
		List<ProjectModel> projectList = new ArrayList<>();
		for (UserModel u : userList) {
			ProjectModel pModel = new ProjectModel();
			pModel.setUserId(u.getId());
			List<ProjectModel> projectListtemp = projectService.selectAll(pModel);
			if (!projectListtemp.isEmpty()) {
				for (ProjectModel p : projectListtemp) {
					p.setClassName(className);
					p.setGradeName(Constant.PRO_MAP.get(p.getGrade()));
					projectList.add(p);
				}
			}
		}
		HtmlUtil.writerJson(response, projectList);
	}

	/**
	 * 查询所有学生项目
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectallpro.do", method = RequestMethod.POST)
	public void selectallpro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ProjectModel> projectListtemp = projectService.selectAll(new ProjectModel());
		for (ProjectModel p : projectListtemp) {
			int classId = userService.selectId(p.getUserId()).getClassId();
			String className = classService.selectId(classId).getClassName();
			p.setGradeName(Constant.PRO_MAP.get(p.getGrade()));
			p.setClassName(className);
		}
		HtmlUtil.writerJson(response, projectListtemp);
	}

	/**
	 * 得到项目评级名称
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getgradename.do", method = RequestMethod.POST)
	public void getgradename(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DictionaryModel dModel = new DictionaryModel();
		dModel.setPcode(Constant.STU_PRO);
		List<DictionaryModel> dicList = dictionaryService.selectAll(dModel);
		HtmlUtil.writerJson(response, dicList);
	}

	/**
	 * 得到该项目id的详情
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getproject.do")
	public void getproject(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ProjectModel projecttemp = projectService.selectId(Integer.valueOf(id));
		HtmlUtil.writerJson(response, projecttemp);
	}

	/**
	 * 更新该项目pModel
	 * 
	 * @param pModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatestu.do", method = RequestMethod.POST)
	@ResponseBody
	public String updatestu(ProjectModel pModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer id = pModel.getId();
		if (id == null) {
			UserModel uModel = (UserModel) request.getSession().getAttribute("user");
			pModel.setUserId(uModel.getId());
			projectService.insert(pModel);
			return "insert";
		}
		projectService.updateActive(pModel);
		return "update";
	}

}
