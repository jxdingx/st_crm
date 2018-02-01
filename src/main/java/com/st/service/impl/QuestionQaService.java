package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.QuestionQaMapper;

@Service("questionQaService")
public class QuestionQaService<T> extends BaseService<T> {
	@Autowired
	private QuestionQaMapper<T> questionQaMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return questionQaMapper;
	}

}
