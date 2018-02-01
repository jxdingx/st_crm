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
import com.st.model.CreateQuesModel;
import com.st.model.CreateQuesRelModel;
import com.st.model.UserModel;
import com.st.service.impl.CreateQuesRelService;
import com.st.service.impl.CreateQuesService;

@Controller
@RequestMapping("/question")
public class QusetionController {
	@Autowired
	private CreateQuesService<CreateQuesModel> createQuesService;
	@Autowired
	private CreateQuesRelService<CreateQuesRelModel> createQuesRelService;

	/**
	 * 查询登录用户所在班级的问卷
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectStu.do", method = RequestMethod.POST)
	public void selectStu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CreateQuesModel cModel = new CreateQuesModel();
		UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
		cModel.setClassId(usertemp.getClassId());
		List<CreateQuesModel> CreateQuesList = createQuesService.selectAll(cModel);
		HtmlUtil.writerJson(response, CreateQuesList);
	}

	/**
	 * 根据该班级classId查询问卷
	 * 
	 * @param classId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getclassquestion.do", method = RequestMethod.POST)
	public void getclassquestion(String classId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CreateQuesModel creatquestemp = new CreateQuesModel();
		creatquestemp.setClassId(Integer.valueOf(classId));
		List<CreateQuesModel> creatquesList = createQuesService.selectAll(creatquestemp);
		HtmlUtil.writerJson(response, creatquesList);
	}

	/**
	 * 根据问卷id查询问卷详情
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getquestion.do")
	public void getquestion(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CreateQuesModel createtemp = createQuesService.selectId(Integer.valueOf(id));
		HtmlUtil.writerJson(response, createtemp);
	}

	/**
	 * 根据id判断更新问卷或增加问卷
	 * 
	 * @param cModel
	 * @param strId
	 * @param classId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatequestion.do", method = RequestMethod.POST)
	@ResponseBody
	public String updatequestion(CreateQuesModel cModel, String strId, String classId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Integer id = cModel.getId();
		if (id == null) {
			UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
			cModel.setUserId(usertemp.getId());
			cModel.setClassId(Integer.valueOf(classId));
			createQuesService.insert(cModel);
			CreateQuesModel cModeltemp = new CreateQuesModel();
			cModeltemp.setTime(cModel.getTime());
			cModeltemp.setClassId(Integer.valueOf(classId));
			List<CreateQuesModel> cModeltempList = createQuesService.selectAll(cModeltemp);
			if (!cModeltempList.isEmpty()) {
				int idd = cModeltempList.get(0).getId();
				CreateQuesRelModel quesrel = new CreateQuesRelModel();
				quesrel.setCreatQuesId(idd);
				quesrel.setQuestionQaId(strId);
				createQuesRelService.insert(quesrel);
			}
			return "insert";
		}
		createQuesService.updateActive(cModel);
		return "update";
	}

}
