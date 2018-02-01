package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.ClassMapper;

@Service("classService")
public class ClassService<T> extends BaseService<T> {

	@Autowired
	private ClassMapper<T> classMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return classMapper;
	}

}
