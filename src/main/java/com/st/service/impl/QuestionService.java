package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.QuestionMapper;

@Service("questionService")
public class QuestionService<T> extends BaseService<T> {
	@Autowired
	private QuestionMapper<T> questionMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return questionMapper;
	}

}
