package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.LeaveMapper;

@Service("leaveService")
public class LeaveService<T> extends BaseService<T> {

	@Autowired
	private LeaveMapper<T> leaveMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return leaveMapper;
	}

}
