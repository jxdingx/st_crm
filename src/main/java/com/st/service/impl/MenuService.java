package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.MenuMapper;

@Service("menuService")
public class MenuService<T> extends BaseService<T> {

	@Autowired
	private MenuMapper<T> menuMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return menuMapper;
	}

}
