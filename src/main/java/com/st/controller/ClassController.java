package com.st.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.session.HtmlUtil;
import com.st.model.ClassModel;
import com.st.model.UserModel;
import com.st.service.impl.ClassService;
import com.st.service.impl.UserService;

@Controller
@RequestMapping("/class")
public class ClassController {

	@Autowired
	private ClassService<ClassModel> classService;
	@Autowired
	private UserService<UserModel> userService;

	/**
	 * 查询所有班级及班级人数
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectClass.do", method = RequestMethod.POST)
	public void selectClass(HttpServletResponse response) throws Exception {
		List<ClassModel> classList = classService.selectAll(new ClassModel());
		for (ClassModel c : classList) {
			UserModel uModel = new UserModel();
			uModel.setClassId(c.getId());
			c.setStuNum(userService.selectCount(uModel));
		}
		HtmlUtil.writerJson(response, classList);
	}

	/**
	 * 查询角色为讲师的用户
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getteacher.do", method = RequestMethod.POST)
	public void getteacher(HttpServletResponse response) throws Exception {
		UserModel uModel = new UserModel();
		uModel.setRoleId(2);
		HtmlUtil.writerJson(response, userService.selectAll(uModel));
	}

	/**
	 * 根据id查询班级详情
	 * 
	 * @param id
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getclasssta.do")
	public void getclasssta(String id, HttpServletResponse response) throws Exception {
		ClassModel classtemp = classService.selectId(Integer.valueOf(id));
		UserModel teacher = userService.selectId(classtemp.getTeacherId());
		UserModel uModel = new UserModel();
		uModel.setClassId(Integer.valueOf(id));
		classtemp.setStuNum(userService.selectCount(uModel));
		classtemp.setTeacherName(teacher.getTrueName());
		HtmlUtil.writerJson(response, classtemp);
	}

	/**
	 * 根据id判断更新班级或增加班级
	 * 
	 * @param cModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateclass.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateclass(ClassModel cModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer id = cModel.getId();
		if (id == null) {
			classService.insert(cModel);
			return "insert";
		}
		classService.updateActive(cModel);
		return "update";
	}
}
