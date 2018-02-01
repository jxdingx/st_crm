package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.CheckinMapper;

@Service("checkInService")
public class CheckInService<T> extends BaseService<T> {
	@Autowired
	private CheckinMapper<T> checkInMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return checkInMapper;
	}

	public T selectUser(Object obj) {
		return checkInMapper.selectUser(obj);
	}

}
