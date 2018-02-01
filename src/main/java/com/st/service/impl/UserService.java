package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.UserMapper;

@Service("userService")
public class UserService<T> extends BaseService<T> {
	@Autowired
	private UserMapper<T> userMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return userMapper;
	}

}
