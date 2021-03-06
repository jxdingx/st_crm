package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.AssessmentMapper;

@Service("assessmentService")
public class AssessmentService<T> extends BaseService<T> {

	@Autowired
	private AssessmentMapper<T> assessmentMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return assessmentMapper;
	}

}
