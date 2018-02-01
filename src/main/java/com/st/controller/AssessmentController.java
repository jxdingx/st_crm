package com.st.controller;

import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.session.HtmlUtil;
import com.st.model.AssessmentModel;
import com.st.model.ClassModel;
import com.st.model.UserModel;
import com.st.service.impl.AssessmentService;
import com.st.service.impl.ClassService;
import com.st.service.impl.UserService;
import com.st.util.ExcelExporter;

@Controller
@RequestMapping("assessment")
public class AssessmentController {

	@Autowired
	private AssessmentService<AssessmentModel> assessmentService;
	@Autowired
	private UserService<UserModel> userService;
	@Autowired
	private ClassService<ClassModel> classService;

	/**
	 * 查询该班级classId该时间time的月度考核
	 * 
	 * @param time
	 * @param classId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAssessment.do", method = RequestMethod.POST)
	public void getAssessment(String time, String classId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserModel usertemp = new UserModel();
		usertemp.setClassId(Integer.valueOf(classId));
		usertemp.setRoleId(1);
		List<UserModel> userList = userService.selectAll(usertemp);
		for (UserModel u : userList) {
			AssessmentModel aModel = new AssessmentModel();
			aModel.setUserId(u.getId());
			aModel.setTime(time);
			List<AssessmentModel> assessListtemp = assessmentService.selectAll(aModel);
			if (!assessListtemp.isEmpty()) {
				u.setAssessmentModel(assessListtemp.get(0));
			}
		}
		HtmlUtil.writerJson(response, userList);
	}

	/**
	 * 根据id得到该月度考核的详情
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getassessStu.do")
	public void getassessStu(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AssessmentModel aModel = assessmentService.selectId(Integer.valueOf(id));
		HtmlUtil.writerJson(response, aModel);
	}

	/**
	 * 根据id更新月度考核
	 * 
	 * @param aModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatestu.do", method = RequestMethod.POST)
	@ResponseBody
	public String updatestu(AssessmentModel aModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int id = aModel.getId();
		if (id == 0) {
			assessmentService.insert(aModel);
			return "insert";
		}
		assessmentService.updateActive(aModel);
		return "update";
	}

	/**
	 * 月度考核excel下载
	 * 
	 * @param time
	 * @param classId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/assessmentDown.do")
	@ResponseBody
	public void assessmentDown(String time, String classId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<AssessmentModel> assessList = new ArrayList<>();
		UserModel usertemp = new UserModel();
		usertemp.setClassId(Integer.valueOf(classId));
		String className = classService.selectId(Integer.valueOf(classId)).getClassName();
		List<UserModel> userList = userService.selectAll(usertemp);
		for (UserModel u : userList) {
			AssessmentModel aModel = new AssessmentModel();
			aModel.setUserId(u.getId());
			aModel.setTime(time);
			List<AssessmentModel> assessListtemp = assessmentService.selectAll(aModel);
			if (!assessListtemp.isEmpty()) {
				assessList.add(assessListtemp.get(0));
			}
		}
		OutputStream out = null;
		out = response.getOutputStream();
		String fileName = time + "月度-" + className + "班-考核.xls";// 文件名
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
		String[] columnNames = { "姓名", "月度", "排名", "总分", "考勤分", "作业分", "项目分", "平时分", "备注" };
		String[] methodNames = { "getUserName", "getTime", "getRanking", "getScore", "getCheckScore",
				"getHomeworkScore", "getProjectScore", "getDailyScore", "getRemark" };
		Workbook excel = ExcelExporter.export2Excel("题头", "脚注", "sheet1", columnNames, methodNames, assessList);
		excel.write(out);
		out.flush();
		out.close();
	}

}
