package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.CreateQuesMapper;

@Service("createQuesService")
public class CreateQuesService<T> extends BaseService<T> {

	@Autowired
	private CreateQuesMapper<T> createQuesMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return createQuesMapper;
	}

}
