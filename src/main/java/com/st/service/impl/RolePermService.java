package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.RolePermMapper;

@Service("rolePermService")
public class RolePermService<T> extends BaseService<T> {

	@Autowired
	private RolePermMapper<T> rolePermMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return rolePermMapper;
	}

}
