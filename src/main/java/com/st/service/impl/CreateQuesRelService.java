package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.CreateQuesRelMapper;

@Service("createQuesRelService")
public class CreateQuesRelService<T> extends BaseService<T> {
	@Autowired
	private CreateQuesRelMapper<T> createQuesRelMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return createQuesRelMapper;
	}

}
