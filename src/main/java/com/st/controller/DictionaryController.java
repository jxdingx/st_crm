package com.st.controller;

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
import com.st.model.DictionaryModel;
import com.st.service.impl.DictionaryService;

@Controller
@RequestMapping("/dictionary")
public class DictionaryController {
	@Autowired
	private DictionaryService<DictionaryModel> dictionaryService;

	/**
	 * 查询字典表数据，带有分页
	 * 
	 * @param dModel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getdictionaryPage.do", method = RequestMethod.POST)
	public void getdictionaryPage(DictionaryModel dModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<DictionaryModel> dicList = dictionaryService.selectModel(dModel);
		DictionaryModel dictemp = new DictionaryModel();
		dictemp.setPcode("0");
		List<DictionaryModel> dictempList = dictionaryService.selectAll(dictemp);
		Map<String, String> mapsta = new HashMap<>();
		for (DictionaryModel d : dictempList) {
			mapsta.put(d.getCode(), d.getName());
		}
		for (DictionaryModel d : dicList) {
			d.setPcodeName(mapsta.get(d.getPcode()));
		}
		Map<String, Object> map = new HashMap<>();
		map.put("total", dModel.getPager().getRowCount());
		map.put("rows", dicList);
		HtmlUtil.writerJson(response, map);
	}

	/**
	 * 根据id查询字典表数据详情
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getdictionarydetils.do")
	public void getdictionarydetils(String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DictionaryModel dModel = dictionaryService.selectId(Integer.valueOf(id));
		HtmlUtil.writerJson(response, dModel);
	}

	/**
	 * 根据id判断更新字典表或增加字典表数据
	 * 
	 * @param dModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updatedictionary.do", method = RequestMethod.POST)
	@ResponseBody
	public String updatequesqa(DictionaryModel dModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (dModel.getId() == null) {
			dictionaryService.insert(dModel);
			return "insert";
		}
		dictionaryService.updateActive(dModel);
		updateConstant();
		return "update";
	}

	/**
	 * 得到所有状态的上级
	 * 
	 * @param dModel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getpcode.do", method = RequestMethod.POST)
	public void getpcode(DictionaryModel dModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DictionaryModel dictemp = new DictionaryModel();
		dictemp.setPcode("0");
		List<DictionaryModel> dictempList = dictionaryService.selectAll(dictemp);
		HtmlUtil.writerJson(response, dictempList);
	}

	/**
	 * 修改字典表后更新数据
	 * 
	 * @throws Exception
	 */
	public void updateConstant() throws Exception {
		getDictionaryData(Constant.STU_CHECK, Constant.CHECK_MAP);
		getDictionaryData(Constant.STU_LEAVE, Constant.LEAVE_MAP);
		getDictionaryData(Constant.STU_HOME, Constant.HOME_GRADE);
		getDictionaryData(Constant.STU_HOME_TYPE, Constant.HOME_TYPE);
		getDictionaryData(Constant.HOME_LOCK, Constant.LOCK_MAP);
		getDictionaryData(Constant.STU_PRO, Constant.PRO_MAP);
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
}
