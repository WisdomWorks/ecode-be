package com.example.codeE.service.common;

import java.util.List;

public interface CommonService<T, V> {
    T createOne(V entity);
    T getById(String id);
    List<T> getAll();
    //T updateById(String id, Map<String, Object> update);
    boolean deleteById(String id);
}
