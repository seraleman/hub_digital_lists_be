package com.seraleman.digital_lists_be.services;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface IResponse {

    ResponseEntity<Map<String, Object>> list(List<?> objs);

    ResponseEntity<Map<String, Object>> found(Object obj);

    ResponseEntity<Map<String, Object>> empty();

    ResponseEntity<Map<String, Object>> invalidObject(BindingResult result);

    ResponseEntity<Map<String, Object>> notFound(Object id);

    ResponseEntity<Map<String, Object>> created(Object obj);

    ResponseEntity<Map<String, Object>> notCreated();

    ResponseEntity<Map<String, Object>> updated(Object obj);

    ResponseEntity<Map<String, Object>> notUpdated();

    ResponseEntity<Map<String, Object>> deleted();

    ResponseEntity<Map<String, Object>> errorDataAccess(DataAccessException e);

}