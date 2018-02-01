package com.st.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.st.model.CheckinModel;
import com.st.model.DictionaryModel;
import com.st.model.UserModel;
import com.st.service.impl.CheckInService;
import com.st.service.impl.DictionaryService;
import com.st.service.impl.UserService;
import com.core.session.HtmlUtil;
import com.core.tool.Constant;

@Controller()
@RequestMapping("/checkin")
public class CheckinController {

	@Autowired
	private CheckInService<CheckinModel> checkInService;
	@Autowired
	private UserService<UserModel> userService;
	@Autowired
	private DictionaryService<DictionaryModel> dictionaryService;

	/**
	 * 学生根据日期范围time查询一周的考勤
	 * 
	 * @param cModel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectStu.do", method = RequestMethod.POST)
	public void selectStu(CheckinModel cModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
		cModel.setUserId(usertemp.getId());
		String time = cModel.getTime();
		cModel.setTime("");
		String[] timeList = time.split(",");
		cModel.setStartTime(timeList[0]);
		cModel.setEndTime(timeList[1]);
		List<CheckinModel> checkList = checkInService.selectAll(cModel);
		for (CheckinModel c : checkList) {
			statusName(c, Constant.CHECK_MAP); // 得到考勤的状态名
		}
		HtmlUtil.writerJson(response, checkList);
	}

	/**
	 * 根据班级，时间，查询该班级下所有学生该时间下的考勤
	 * 
	 * @param classId
	 * @param time
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectallStu.do", method = RequestMethod.POST)
	public void selectallStu(String classId, String time, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserModel uModel = new UserModel();
		uModel.setClassId(Integer.valueOf(classId));
		uModel.setRoleId(1);
		List<UserModel> userList = userService.selectAll(uModel);
		for (UserModel usertemp : userList) {
			CheckinModel cModeltemp = new CheckinModel();
			cModeltemp.setUserId(Integer.valueOf(usertemp.getId()));
			cModeltemp.setTime(time);
			CheckinModel cModel = checkInService.selectUser(cModeltemp);
			statusName(cModel, Constant.CHECK_MAP);
			usertemp.setCheckinModel(cModel);
		}
		HtmlUtil.writerJson(response, userList);
	}

	/**
	 * 得到考勤的各种状态
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getchecksta.do", method = RequestMethod.POST)
	public void getchecksta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DictionaryModel dModel = new DictionaryModel();
		dModel.setPcode(Constant.STU_CHECK);
		List<DictionaryModel> dicList = dictionaryService.selectAll(dModel);
		HtmlUtil.writerJson(response, dicList);
	}

	/**
	 * 修改选中学生的考勤，若考勤不存在则增加改考勤
	 * 
	 * @param checktime
	 * @param checkuserId
	 * @param cModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateStu.do", method = RequestMethod.POST)
	@ResponseBody
	public String updateStu(String checktime, String checkuserId, CheckinModel cModel, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] userId = checkuserId.split(",");
		int length = userId.length;
		for (int i = 0; i < length; i++) {
			CheckinModel cModeltemp = new CheckinModel();
			cModeltemp.setUserId(Integer.valueOf(userId[i]));
			cModeltemp.setTime(checktime);
			List<CheckinModel> checkinList = checkInService.selectAll(cModeltemp);
			if (checkinList.isEmpty()) {
				cModel.setUserId(Integer.valueOf(userId[i]));
				cModel.setTime(checktime);
				checkInService.insert(cModel);
			} else {
				CheckinModel temp = checkinList.get(0);
				cModel.setId(temp.getId());
				cModel.setUserId(Integer.valueOf(userId[i]));
				checkInService.updateActive(cModel);
			}
		}
		return "ok";
	}

	/**
	 * 通过id得到考勤的详细信息
	 * 
	 * @param cModel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getcheckin.do")
	public void getcheckin(CheckinModel cModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CheckinModel cModeltemp = checkInService.selectId(cModel.getId());
		HtmlUtil.writerJson(response, cModeltemp);
	}

	/**
	 * 根据考勤状态得到考勤状态名称
	 * 
	 * @param cModel
	 * @param map
	 * @return
	 */
	public CheckinModel statusName(CheckinModel cModel, Map<String, String> map) {
		if (cModel != null) {
			cModel.setOnecheckName(map.get(cModel.getOnecheck()));
			cModel.setTwocheckName(map.get(cModel.getTwocheck()));
			cModel.setThreecheckName(map.get(cModel.getThreecheck()));
			cModel.setFourcheckName(map.get(cModel.getFourcheck()));
			cModel.setFivecheckName(map.get(cModel.getFivecheck()));
			cModel.setSixcheckName(map.get(cModel.getSixcheck()));
		}
		return cModel;
	}

	/**
	 * 清空选择的学生cleanuserid该时间checktime的考勤
	 * 
	 * @param checktime
	 * @param cleanuserid
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cleanallstu.do", method = RequestMethod.POST)
	@ResponseBody
	public String cleanallstu(String checktime, String cleanuserid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String[] userId = cleanuserid.split(",");
		int length = userId.length;
		for (int i = 0; i < length; i++) {
			CheckinModel cModeltemp = new CheckinModel();
			cModeltemp.setUserId(Integer.valueOf(userId[i]));
			cModeltemp.setTime(checktime);
			List<CheckinModel> checkinList = checkInService.selectAll(cModeltemp);
			if (!checkinList.isEmpty()) {
				CheckinModel temp = checkinList.get(0);
				cModeltemp.setId(temp.getId());
				checkInService.update(cModeltemp);
			}
		}
		return "ok";
	}

}
