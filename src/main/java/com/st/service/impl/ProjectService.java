package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.ProjectMapper;
@Service("projectService")
public class ProjectService<T>  extends BaseService<T>{

	@Autowired
	private ProjectMapper<T> projectMapper;
	
	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return projectMapper;
	}

}
