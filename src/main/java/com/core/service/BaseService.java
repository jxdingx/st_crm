package com.core.service;

import java.util.List;

import com.core.mapper.BaseMapper;
import com.core.model.BaseModel;

public abstract class BaseService<T> {

    public abstract BaseMapper<T> getMapper();

    public void insert(T t) throws Exception {
        getMapper().insert(t);
    }

    public void delete(Integer... ids) throws Exception {
        if (ids == null || ids.length < 1) {
            return;
        }
        for (Object id : ids) {
            getMapper().delete(id);
        }
    }

    public void deleteModel(T t) throws Exception {
        getMapper().deleteModel(t);
    }

    public void update(T t) throws Exception {
        getMapper().update(t);
    }

    public void updateActive(T t) throws Exception {
        getMapper().updateActive(t);
    }

    public T selectId(Object id) throws Exception {
        return getMapper().selectId(id);
    }

    public int selectCount(T model) throws Exception {
        return getMapper().selectCount(model);
    }

    public List<T> selectModel(T model) throws Exception {
        ((BaseModel) model).getPager().setRowCount(selectCount(model));
        return getMapper().selectModel(model);
    }

    public List<T> selectAll(T t) throws Exception {
        return getMapper().selectAll(t);
    }

}
