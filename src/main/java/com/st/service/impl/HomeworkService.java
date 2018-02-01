package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.HomeworkMapper;

@Service("homeworkService")
public class HomeworkService<T> extends BaseService<T> {
	@Autowired
	private HomeworkMapper<T> homeworkMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return homeworkMapper;
	}

}
