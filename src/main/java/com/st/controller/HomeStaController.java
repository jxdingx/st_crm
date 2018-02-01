package com.st.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.core.session.HtmlUtil;
import com.core.tool.Constant;
import com.st.model.DictionaryModel;
import com.st.model.HomeStaModel;
import com.st.model.HomeworkModel;
import com.st.model.UserModel;
import com.st.service.impl.DictionaryService;
import com.st.service.impl.HomeStaService;
import com.st.service.impl.HomeworkService;
import com.st.service.impl.UserService;

@Controller
@RequestMapping("/homesta")
public class HomeStaController {

	@Autowired
	private HomeStaService<HomeStaModel> homeStaService;
	@Autowired
	private HomeworkService<HomeworkModel> homeworkService;
	@Autowired
	private DictionaryService<DictionaryModel> dictionaryService;
	@Autowired
	private UserService<UserModel> userService;

	/**
	 * 查询学生的作业与作业详情
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selecthomework.do", method = RequestMethod.POST)
	public void selecthomework(HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
		HomeworkModel hometemp = new HomeworkModel();
		hometemp.setClassId(usertemp.getClassId());
		List<HomeworkModel> homeList = homeworkService.selectAll(hometemp);
		for (HomeworkModel h : homeList) {
			HomeStaModel hModel = new HomeStaModel();
			hModel.setHomeworkId(h.getId());
			hModel.setUserId(usertemp.getId());
			List<HomeStaModel> homestaList = homeStaService.selectAll(hModel);
			if (!homestaList.isEmpty()) {
				HomeStaModel homestatemp = homestaList.get(0);
				String statusname = Constant.HOME_GRADE.get(homestatemp.getOneselfGrade()); // 作业评级
				homestatemp.setOneselfGradeName(statusname);
				h.setHomeStaModel(homestaList.get(0));
			}
		}
		HtmlUtil.writerJson(response, homeList);
	}

	/**
	 * 查询该班级classId该时间time下的所有作业
	 * 
	 * @param classId
	 * @param time
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectallstu.do", method = RequestMethod.POST)
	public void selectallstu(String classId, String time, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HomeworkModel hometemp = new HomeworkModel();
		hometemp.setClassId(Integer.valueOf(classId));
		hometemp.setTime(time);
		List<HomeworkModel> homeList = homeworkService.selectAll(hometemp);
		for (HomeworkModel h : homeList) {
			h.setTypeName(Constant.HOME_TYPE.get(h.getType()));
		}
		HtmlUtil.writerJson(response, homeList);
	}

	/**
	 * 查询该班级classId下所有学生的作业详情
	 * 
	 * @param classId
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectclasshomework.do", method = RequestMethod.POST)
	public void selectclasshomework(String classId, String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserModel uModel = new UserModel();
		uModel.setClassId(Integer.valueOf(classId));
		uModel.setRoleId(1);
		List<UserModel> userList = userService.selectAll(uModel);
		for (UserModel u : userList) {
			HomeStaModel hModel = new HomeStaModel();
			hModel.setUserId(u.getId());
			hModel.setHomeworkId(Integer.valueOf(id));
			List<HomeStaModel> homestaList = homeStaService.selectAll(hModel);
			if (!homestaList.isEmpty()) {
				HomeStaModel homestatemp = homestaList.get(0);
				String oneselfGradeName = Constant.HOME_GRADE.get(homestatemp.getOneselfGrade());
				String teacherGradeName = Constant.HOME_GRADE.get(homestatemp.getTeacherGrade());// 作业评级
				String statusName = Constant.LOCK_MAP.get(homestatemp.getStatus()); // 是否锁定状态
				homestatemp.setStatusName(statusName);
				homestatemp.setOneselfGradeName(oneselfGradeName);
				homestatemp.setTeacherGradeName(teacherGradeName);
				u.setHomeStaModel(homestatemp);
			}
		}
		HtmlUtil.writerJson(response, userList);
	}

	/**
	 * 更新选择的学生该题的作业评分情况，若不存在则增加数据
	 * 
	 * @param checkuserId
	 * @param hModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatehomeworkstu.do", method = RequestMethod.POST)
	@ResponseBody
	public String updatehomeworkstu(String checkuserId, HomeStaModel hModel, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] userId = checkuserId.split(",");
		int length = userId.length;
		for (int i = 0; i < length; i++) {
			HomeStaModel hModeltemp = new HomeStaModel();
			hModeltemp.setUserId(Integer.valueOf(userId[i]));
			hModeltemp.setHomeworkId(hModel.getHomeworkId());
			List<HomeStaModel> homestaList = homeStaService.selectAll(hModeltemp);
			if (homestaList.isEmpty()) {
				hModel.setUserId(Integer.valueOf(userId[i]));
				hModel.setStatus("2"); // 设为已解锁状态
				homeStaService.insert(hModel);
			} else {
				HomeStaModel temp = homestaList.get(0);
				hModel.setId(temp.getId());
				homeStaService.updateActive(hModel);
			}
		}
		return "ok";
	}

	/**
	 * 根据作业详情id查询该作业详情
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/gethomestadetils.do")
	public void gethomestadetils(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HomeStaModel homestatemp = homeStaService.selectId(Integer.valueOf(id));
		String statusname = Constant.HOME_GRADE.get(homestatemp.getOneselfGrade());
		homestatemp.setOneselfGradeName(statusname);
		HtmlUtil.writerJson(response, homestatemp);
	}

	/**
	 * 得到作业等级评价标准名称
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getgradename.do", method = RequestMethod.POST)
	public void getgradename(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// #1-未做放弃 2-差距较大 3-一般状况 4-较好完成 5-全部完成 6-扩展掌握
		DictionaryModel dModel = new DictionaryModel();
		dModel.setPcode(Constant.STU_HOME);
		List<DictionaryModel> dicList = dictionaryService.selectAll(dModel);
		HtmlUtil.writerJson(response, dicList);
	}

	/**
	 * 得到作业的类型名称
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/gethomeworktype.do", method = RequestMethod.POST)
	public void gethomeworkttype(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DictionaryModel dModel = new DictionaryModel();
		dModel.setPcode(Constant.STU_HOME_TYPE);
		List<DictionaryModel> dicList = dictionaryService.selectAll(dModel);
		HtmlUtil.writerJson(response, dicList);
	}

	/**
	 * 根据id查询作业
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/gethomework.do")
	public void gethomework(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HomeworkModel hometemp = homeworkService.selectId(Integer.valueOf(id));
		HtmlUtil.writerJson(response, hometemp);
	}

	/**
	 * 根据id查询学生作业详情
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/gethomeworksta.do")
	public void gethomeworksta(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HomeStaModel homestatemp = homeStaService.selectId(Integer.valueOf(id));
		HtmlUtil.writerJson(response, homestatemp);
	}

	/**
	 * 查询所有作业的
	 * 
	 * @param hModel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/gethometitle.do", method = RequestMethod.POST)
	public void gethometitle(HomeworkModel hModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<HomeworkModel> hometempList = homeworkService.selectAll(hModel);
		HtmlUtil.writerJson(response, hometempList);
	}

	/**
	 * 更新作业
	 * 
	 * @param hModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatehomework.do", method = RequestMethod.POST)
	@ResponseBody
	public String updatehomework(HomeworkModel hModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		homeworkService.updateActive(hModel);
		return "update";
	}

	/**
	 * 增加作业
	 * 
	 * @param hModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/inserthomework.do", method = RequestMethod.POST)
	@ResponseBody
	public String inserthomework(HomeworkModel hModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
		hModel.setId(null);
		hModel.setTeacherId(usertemp.getId());
		homeworkService.insert(hModel);
		return "insert";
	}

	/**
	 * 跳转到该班级classId该作业id学生的回答情况页面
	 * 
	 * @param id
	 * @param classId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tohomesta.do")
	public ModelAndView tohomesta(String id, String classId, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView m = new ModelAndView();
		m.addObject("id", id);
		m.addObject("classId", classId);
		m.setViewName("adminHomeSta");
		return m;
	}

	/**
	 * 修改该学生作业id锁定状态
	 * 
	 * @param id
	 * @param status
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/lockhomesta.do", method = RequestMethod.POST)
	@ResponseBody
	public String lockhomesta(String id, String status, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HomeStaModel hModel = new HomeStaModel();
		hModel.setId(Integer.valueOf(id));
		String str = "";
		if ("1".equals(status)) {
			hModel.setStatus("2");
			str = "lock";
		} else if ("2".equals(status)) {
			hModel.setStatus("1");
			str = "unlock";
		}
		homeStaService.updateActive(hModel);
		return str;
	}

}
