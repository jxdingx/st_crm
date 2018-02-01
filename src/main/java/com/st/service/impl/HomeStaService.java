package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.HomeStaMapper;

@Service("homeStaService")
public class HomeStaService<T> extends BaseService<T> {
	@Autowired
	private HomeStaMapper<T> homeStaMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return homeStaMapper;
	}

}
