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
import com.st.model.CreateQuesRelModel;
import com.st.model.QuestionQaModel;
import com.st.service.impl.CreateQuesRelService;
import com.st.service.impl.QuestionQaService;

@Controller
@RequestMapping("/questionQa")
public class QuestionQaController {
	@Autowired
	private QuestionQaService<QuestionQaModel> questionQaService;
	@Autowired
	private CreateQuesRelService<CreateQuesRelModel> createQuesRelService;

	/**
	 * 查询问卷的所有试题
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getquestionqa.do", method = RequestMethod.POST)
	public void getquestionqa(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<QuestionQaModel> quesqaList = questionQaService.selectAll(new QuestionQaModel());
		HtmlUtil.writerJson(response, quesqaList);
	}

	/**
	 * 查询问卷的所有试题，带有分页
	 * 
	 * @param qModel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getquestionqaPage.do", method = RequestMethod.POST)
	public void getquestionqaPage(QuestionQaModel qModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<QuestionQaModel> quesqaList = questionQaService.selectModel(qModel);
		Map<String, Object> map = new HashMap<>();
		map.put("total", qModel.getPager().getRowCount());
		map.put("rows", quesqaList);
		HtmlUtil.writerJson(response, map);
	}

	/**
	 * 根据问卷id得到该问卷的试题
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/gethasquestionqa.do")
	public void gethasquestionqa(String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CreateQuesRelModel cModel = new CreateQuesRelModel();
		if (id == null || "".equals(id)) {
			return;
		}
		cModel.setCreatQuesId(Integer.valueOf(id));
		List<CreateQuesRelModel> quesrelList = createQuesRelService.selectAll(cModel);
		String qaid = null;
		if (!quesrelList.isEmpty()) {
			qaid = quesrelList.get(0).getQuestionQaId();
		}
		List<QuestionQaModel> quesQaList = new ArrayList<>();
		String[] qaidtemp = qaid.split(",");
		int length = qaidtemp.length;
		for (int i = 0; i < length; i++) {
			QuestionQaModel qModel = questionQaService.selectId(Integer.valueOf(qaidtemp[i]));
			quesQaList.add(qModel);
		}
		HtmlUtil.writerJson(response, quesQaList);
	}

	/**
	 * 修改该问卷id的试题
	 * 
	 * @param id
	 * @param strid
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatequesrel.do", method = RequestMethod.POST)
	@ResponseBody
	public String updatequesrel(String id, String strid, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CreateQuesRelModel cModel = new CreateQuesRelModel();
		cModel.setCreatQuesId(Integer.valueOf(id));
		List<CreateQuesRelModel> quesrelList = createQuesRelService.selectAll(cModel);
		if (!quesrelList.isEmpty()) {
			CreateQuesRelModel cModeltemp = new CreateQuesRelModel();
			cModeltemp.setId(quesrelList.get(0).getId());
			cModeltemp.setQuestionQaId(strid);
			createQuesRelService.updateActive(cModeltemp);
		}
		return "ok";
	}

	/**
	 * 查询该试题id的详情
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getquestionqadetils.do")
	public void getquestionqadetils(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		QuestionQaModel qModel = questionQaService.selectId(Integer.valueOf(id));
		HtmlUtil.writerJson(response, qModel);
	}

	/**
	 * 根据id判断增加试题或修改试题
	 * 
	 * @param qModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatequesqa.do", method = RequestMethod.POST)
	@ResponseBody
	public String updatequesqa(QuestionQaModel qModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (qModel.getId() == null) {
			questionQaService.insert(qModel);
			return "insert";
		}
		questionQaService.updateActive(qModel);
		return "update";
	}

}
