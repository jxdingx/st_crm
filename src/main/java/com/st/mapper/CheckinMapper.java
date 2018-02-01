package com.st.mapper;

import com.core.mapper.BaseMapper;

public interface CheckinMapper<T> extends BaseMapper<T> {
	T selectUser(Object id);
}
