package com.demo.microservices.common.dao;

import java.util.List;
import java.util.Map;

public interface CommonDao {

	public int insert(String id, Object value) throws Exception;

	public int update(String id, Object pData) throws Exception;

	public int update(String id, Map<String, Object> map) throws Exception;

	public int delete(String id, Map<String, Object> map) throws Exception;

	public int delete(String id, Object value) throws Exception;

	public int deleteAll(String id) throws Exception;

	public <T> List<T> selectList(String id, Map<String, Object> map) throws Exception;

	public <T> List<T> selectList(String id, Object value) throws Exception;

	public <T> List<T> selectList(String id) throws Exception;

	public <T> T selectOne(String id) throws Exception;

	public <T> T selectOne(String id, Object value) throws Exception;

	public <T> T selectOne(String id, Map<String, Object> map) throws Exception;

	// INSERT, UPDATE, DELETE 프로시져(IN)
	public void updateProcedure(String id, Object value) throws Exception;

	// SELECT(OUT)
	public <T> Map<String, T> selectOneProcedureMap(String id, Map<String, T> map) throws Exception;

	public <T> Map<String, T> selectListProcedureMap(String id, Map<String, T> map) throws Exception;
}
