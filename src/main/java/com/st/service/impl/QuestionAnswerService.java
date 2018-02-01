package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.QuestionAnswerMapper;

@Service("questionAnswerService")
public class QuestionAnswerService<T> extends BaseService<T> {

	@Autowired
	private QuestionAnswerMapper<T> questionAnswerMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return questionAnswerMapper;
	}

}
