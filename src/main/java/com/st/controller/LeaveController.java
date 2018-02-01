package com.st.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.st.model.LeaveModel;
import com.st.model.UserModel;
import com.st.service.impl.ClassService;
import com.st.service.impl.LeaveService;
import com.st.service.impl.UserService;

@Controller
@RequestMapping("/leave")
public class LeaveController {
	@Autowired
	private LeaveService<LeaveModel> leaveService;
	@Autowired
	private UserService<UserModel> userService;
	@Autowired
	private ClassService<ClassModel> classService;

	/**
	 * 查询登录用户的请假记录
	 * 
	 * @param lModel
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectStu.do", method = RequestMethod.POST)
	public void selectStu(LeaveModel lModel, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
		lModel.setUserId(usertemp.getId());
		List<LeaveModel> leaveList = leaveService.selectAll(lModel);
		statusname(leaveList);
		HtmlUtil.writerJson(response, leaveList);
	}

	/**
	 * 增加登录用户的请假
	 * 
	 * @param lModel
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	@ResponseBody
	public String insert(LeaveModel lModel, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
		lModel.setUserId(usertemp.getId());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		lModel.setTime(sdf.format(new Date()));
		lModel.setStatus("1"); // 新增加的请假初始状态设为1-审批中
		leaveService.insert(lModel);
		return "ok";
	}

	/**
	 * 查询该班级classId该时间time的请假记录
	 * 
	 * @param time
	 * @param classId
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectallStu.do", method = RequestMethod.POST)
	public void selectallStu(String time, String classId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserModel usertemp = new UserModel();
		usertemp.setClassId(Integer.valueOf(classId));
		String className = classService.selectId(Integer.valueOf(classId)).getClassName();
		usertemp.setRoleId(1);
		List<UserModel> userList = userService.selectAll(usertemp);
		List<LeaveModel> leaveList = new ArrayList<>();
		for (UserModel u : userList) {
			LeaveModel lModel = new LeaveModel();
			lModel.setUserId(u.getId());
			lModel.setTime(time);
			List<LeaveModel> leaveListtemp = leaveService.selectAll(lModel);
			if (!leaveListtemp.isEmpty()) {
				for (LeaveModel l : leaveListtemp) {
					l.setClassName(className);
					l.setStatusName(Constant.LEAVE_MAP.get(l.getStatus()));
					leaveList.add(l);
				}
			}
		}
		HtmlUtil.writerJson(response, leaveList);
	}

	/**
	 * 得到请假状态名称
	 * 
	 * @param list
	 */
	public void statusname(List<LeaveModel> list) {
		for (LeaveModel l : list) {
			l.setStatusName(Constant.LEAVE_MAP.get(l.getStatus()));
		}
	}

	/**
	 * 查询所有的请假记录
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectallleave.do", method = RequestMethod.POST)
	public void selectallleave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LeaveModel lModel = new LeaveModel();
		List<LeaveModel> leaveListtemp = leaveService.selectModel(lModel);
		if (!leaveListtemp.isEmpty()) {
			for (LeaveModel l : leaveListtemp) {
				int classId = userService.selectId(l.getUserId()).getClassId();
				String className = classService.selectId(classId).getClassName();
				l.setClassName(className);
				l.setStatusName(Constant.LEAVE_MAP.get(l.getStatus()));
			}
		}
		HtmlUtil.writerJson(response, leaveListtemp);
	}

	/**
	 * 根据权限审批请假
	 * 
	 * @param id
	 * @param value
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/approve.do", method = RequestMethod.POST)
	@ResponseBody
	public String getapprove(String id, String value, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserModel usertemp = (UserModel) request.getSession().getAttribute("user");
		Integer roleId = usertemp.getRoleId();
		LeaveModel lModel = new LeaveModel();
		lModel.setId(Integer.valueOf(id));
		String sta = "1";
		if ("1".equals(value)) {
			if (roleId == 2) {
				sta = "2";
			} else if (roleId == 3) {
				sta = "4";
			}
		} else if ("0".equals(value)) {
			sta = "5";
		}
		lModel.setStatus(sta);
		leaveService.updateActive(lModel);
		return "ok";
	}
}
