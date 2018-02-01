package com.st.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.core.session.HtmlUtil;
import com.core.tool.Constant;
import com.st.model.CheckinModel;
import com.st.model.HomeStaModel;
import com.st.model.QuestionAnswerModel;
import com.st.model.UserModel;
import com.st.service.impl.CheckInService;
import com.st.service.impl.HomeStaService;
import com.st.service.impl.QuestionAnswerService;
import com.st.service.impl.UserService;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {
	@Autowired
	private HomeStaService<HomeStaModel> homeStaService;
	@Autowired
	private UserService<UserModel> userService;
	@Autowired
	private CheckInService<CheckinModel> checkInService;
	@Autowired
	private QuestionAnswerService<QuestionAnswerModel> questionAnswerService;

	/**
	 * 根据该班级classId得到该作业homeworkId的分析数据
	 * 
	 * @param classId
	 * @param homeworkId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/gethomeworksta.do", method = RequestMethod.POST)
	public void gethomeworksta(String classId, String homeworkId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserModel usertemp = new UserModel();
		usertemp.setClassId(Integer.valueOf(classId));
		usertemp.setRoleId(1);
		List<UserModel> userList = userService.selectAll(usertemp);
		for (UserModel u : userList) {
			HomeStaModel hModel = new HomeStaModel();
			hModel.setUserId(u.getId());
			hModel.setHomeworkId(Integer.valueOf(homeworkId));
			List<HomeStaModel> homestaList = homeStaService.selectAll(hModel);
			HomeStaModel homeststemp = new HomeStaModel();
			if (!homestaList.isEmpty()) {
				homeststemp = homestaList.get(0);
			}
			u.setHomeStaModel(homeststemp);
		}
		List<Map<String, Object>> list = new ArrayList<>();
		Collection<String> grade = Constant.HOME_GRADE.values();
		for (Object o : grade) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", o.toString());
			map.put("value", 0);
			list.add(map);
		}
		for (UserModel u : userList) {
			String teachergrade = u.getHomeStaModel().getTeacherGrade();
			String gradename = Constant.HOME_GRADE.get(teachergrade);
			for (Map<String, Object> m : list) {
				String gradenametemp = (String) m.get("name");
				if (gradenametemp.equals(gradename)) {
					Integer num = (Integer) m.get("value") + 1;
					m.replace("value", num);
				}
			}
		}
		HtmlUtil.writerJson(response, list);
	}

	/**
	 * 根据该班级classId得到该时间checktime的分析数据
	 * 
	 * @param classId
	 * @param checktime
	 * @param checksta
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getcheckinsta.do", method = RequestMethod.POST)
	public void getcheckinsta(String classId, String checktime, String checksta, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserModel usertemp = new UserModel();
		usertemp.setClassId(Integer.valueOf(classId));
		usertemp.setRoleId(1);
		List<UserModel> userList = userService.selectAll(usertemp);
		for (UserModel u : userList) {
			CheckinModel cModel = new CheckinModel();
			cModel.setUserId(u.getId());
			cModel.setTime(checktime);
			List<CheckinModel> checkinList = checkInService.selectAll(cModel);
			CheckinModel checktemp = new CheckinModel();
			if (!checkinList.isEmpty()) {
				checktemp = checkinList.get(0);
			}
			u.setCheckinModel(checktemp);
		}
		List<Map<String, Object>> list = new ArrayList<>();

		Map<String, Object> map1 = new HashMap<>();
		Map<String, Object> map2 = new HashMap<>();
		Map<String, Object> map3 = new HashMap<>();
		Map<String, Object> map4 = new HashMap<>();
		Map<String, Object> map5 = new HashMap<>();
		Map<String, Object> map6 = new HashMap<>();
		map1.put("name", "早晨");
		map1.put("value", 0);
		map2.put("name", "午饭前");
		map2.put("value", 0);
		map3.put("name", "午饭后");
		map3.put("value", 0);
		map4.put("name", "晚饭前");
		map4.put("value", 0);
		map5.put("name", "晚饭后");
		map5.put("value", 0);
		map6.put("name", "晚自习");
		map6.put("value", 0);

		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		for (UserModel u : userList) {
			String oneCheckin = u.getCheckinModel().getOnecheck();
			String twoCheckin = u.getCheckinModel().getTwocheck();
			String threeCheckin = u.getCheckinModel().getThreecheck();
			String fourCheckin = u.getCheckinModel().getFourcheck();
			String fiveCheckin = u.getCheckinModel().getFivecheck();
			String sixCheckin = u.getCheckinModel().getSixcheck();
			if (checksta.equals(oneCheckin)) {
				Integer num = (Integer) map1.get("value") + 1;
				map1.replace("value", num);
			}
			if (checksta.equals(twoCheckin)) {
				Integer num = (Integer) map2.get("value") + 1;
				map2.replace("value", num);
			}
			if (checksta.equals(threeCheckin)) {
				Integer num = (Integer) map3.get("value") + 1;
				map3.replace("value", num);
			}
			if (checksta.equals(fourCheckin)) {
				Integer num = (Integer) map4.get("value") + 1;
				map4.replace("value", num);
			}
			if (checksta.equals(fiveCheckin)) {
				Integer num = (Integer) map5.get("value") + 1;
				map5.replace("value", num);
			}
			if (checksta.equals(sixCheckin)) {
				Integer num = (Integer) map6.get("value") + 1;
				map6.replace("value", num);
			}
		}
		HtmlUtil.writerJson(response, list);
	}

	/**
	 * 根据该班级classId得到该问卷questionId的分析数据
	 * 
	 * @param classId
	 * @param questionId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/getquestionsta.do", method = RequestMethod.POST)
	public void getquestionsta(String classId, String questionId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<>();
		int[] aasa = { 0, 0, 0, 0, 0, 0, 0 };
		int[] aasb = { 0, 0, 0, 0, 0, 0, 0 };
		int[] aasc = { 0, 0, 0, 0, 0, 0, 0 };
		int[] aasd = { 0, 0, 0, 0, 0, 0, 0 };
		map.put("X", Arrays.asList("第一题", "第二题", "第三题", "第四题", "第五题", "第六题", "第七题"));
		map.put("A", aasa);
		map.put("B", aasb);
		map.put("C", aasc);
		map.put("D", aasd);
		UserModel usertemp = new UserModel();
		usertemp.setClassId(Integer.valueOf(classId));
		usertemp.setRoleId(1);
		List<UserModel> userList = userService.selectAll(usertemp);
		for (UserModel u : userList) {
			QuestionAnswerModel qModel = new QuestionAnswerModel();
			qModel.setUserId(u.getId());
			qModel.setCreatQuestionId(Integer.valueOf(questionId));
			List<QuestionAnswerModel> quesanswerList = questionAnswerService.selectAll(qModel);
			if (!quesanswerList.isEmpty()) {
				QuestionAnswerModel quseanswer = quesanswerList.get(0);
				map = answercount(quseanswer, map);
			}
		}
		HtmlUtil.writerJson(response, map);
	}

	/**
	 * 问卷答案统计
	 * 
	 * @param qModel
	 * @param map
	 * @return
	 */
	public Map<String, Object> answercount(QuestionAnswerModel qModel, Map<String, Object> map) {
		String answer[] = qModel.getAnswer().split(",");
		int length = answer.length;
		for (int i = 0; i < length; i++) {
			if ("A".equals(answer[i])) {
				int[] ans = (int[]) map.get("A");
				ans[i] += 1;
				map.replace("A", ans);
			} else if ("B".equals(answer[i])) {
				int[] ans = (int[]) map.get("B");
				ans[i] += 1;
				map.replace("B", ans);
			} else if ("C".equals(answer[i])) {
				int[] ans = (int[]) map.get("C");
				ans[i] += 1;
				map.replace("C", ans);
			} else if ("D".equals(answer[i])) {
				int[] ans = (int[]) map.get("D");
				ans[i] += 1;
				map.replace("D", ans);
			}
		}

		return map;
	}
}
