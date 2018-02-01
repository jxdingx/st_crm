package com.st.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.core.session.HtmlUtil;
import com.st.model.QuestionAnswerModel;
import com.st.model.UserModel;
import com.st.service.impl.QuestionAnswerService;
import com.st.service.impl.UserService;

@Controller
@RequestMapping("/questionanswer")
public class QuestionAnswerController {
	@Autowired
	private QuestionAnswerService<QuestionAnswerModel> questionAnswerService;
	@Autowired
	private UserService<UserModel> userService;

	/**
	 * 根据问卷id，班级classId跳转到问卷回答详情
	 * 
	 * @param id
	 * @param classId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toquestionanswer.do")
	public ModelAndView tohomesta(String id, String classId, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView m = new ModelAndView();
		m.addObject("id", id);
		m.addObject("classId", classId);
		m.setViewName("adminQuesSta");
		return m;
	}

	/**
	 * 根据问卷id，班级classId得到问卷回答详情
	 * 
	 * @param classId
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getquestionanswer.do", method = RequestMethod.POST)
	public void getquestionanswer(String classId, String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserModel uModel = new UserModel();
		uModel.setClassId(Integer.valueOf(classId));
		uModel.setRoleId(1);
		List<UserModel> userList = userService.selectAll(uModel);
		for (UserModel u : userList) {
			QuestionAnswerModel qModel = new QuestionAnswerModel();
			qModel.setUserId(u.getId());
			qModel.setCreatQuestionId(Integer.valueOf(id));
			List<QuestionAnswerModel> quesansstaList = questionAnswerService.selectAll(qModel);
			if (!quesansstaList.isEmpty()) {
				QuestionAnswerModel quesansstatemp = quesansstaList.get(0);
				String[] ans = quesansstatemp.getAnswer().split(",");
				int length = ans.length;
				if (length == 7) {
					quesansstatemp.setAnswer1(ans[0]);
					quesansstatemp.setAnswer2(ans[1]);
					quesansstatemp.setAnswer3(ans[2]);
					quesansstatemp.setAnswer4(ans[3]);
					quesansstatemp.setAnswer5(ans[4]);
					quesansstatemp.setAnswer6(ans[5]);
					quesansstatemp.setAnswer7(ans[6]);
				}
				u.setQuestionAnswerModel(quesansstatemp);
			}
		}
		HtmlUtil.writerJson(response, userList);
	}

	/**
	 * 根据id得到问卷的回答详情
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getquesanswer.do", method = RequestMethod.POST)
	public void getquesanswer(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
		QuestionAnswerModel qModel = new QuestionAnswerModel();
		qModel.setUserId(usertemp.getId());
		qModel.setCreatQuestionId(Integer.valueOf(id));
		List<QuestionAnswerModel> quesanswerList = questionAnswerService.selectModel(qModel);
		if (!quesanswerList.isEmpty()) {
			QuestionAnswerModel qModeltemp = quesanswerList.get(0);
			String[] ans = qModeltemp.getAnswer().split(",");
			int length = ans.length;
			if (length == 7) {
				qModeltemp.setAnswer1(ans[0]);
				qModeltemp.setAnswer2(ans[1]);
				qModeltemp.setAnswer3(ans[2]);
				qModeltemp.setAnswer4(ans[3]);
				qModeltemp.setAnswer5(ans[4]);
				qModeltemp.setAnswer6(ans[5]);
				qModeltemp.setAnswer7(ans[6]);
			}
		}
		HtmlUtil.writerJson(response, quesanswerList);
	}

	/**
	 * 根据id判断增加问卷回答或修改问卷回答
	 * 
	 * @param qModel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatequesanswer.do", method = RequestMethod.POST)
	public void updatequesanswer(QuestionAnswerModel qModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int id = qModel.getId();
		if (id == 0) {
			UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
			qModel.setUserId(usertemp.getId());
			questionAnswerService.insert(qModel);
			return;
		}
		questionAnswerService.updateActive(qModel);
	}
}
