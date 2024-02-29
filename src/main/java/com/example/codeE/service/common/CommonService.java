package com.example.codeE.service.common;

import java.util.List;
import java.util.Map;

public interface CommonService<T> {
    T createOne(T entity);
    T getById(String id);
    List<T> getAll();
    //T updateById(String id, Map<String, Object> update);
    boolean deleteById(String id);
}
