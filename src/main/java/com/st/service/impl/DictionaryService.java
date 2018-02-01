package com.st.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.mapper.BaseMapper;
import com.core.service.BaseService;
import com.st.mapper.DictionaryMapper;

@Service("dictionaryService")
public class DictionaryService<T> extends BaseService<T> {

	@Autowired
	private DictionaryMapper<T> dictionaryMapper;

	@Override
	public BaseMapper<T> getMapper() {
		// TODO Auto-generated method stub
		return dictionaryMapper;
	}

}
