package com.demo.microservices.common.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CommonDaoImpl implements CommonDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(String id, Object value) throws Exception {
		int result = 0;

		try {
			result = sqlSession.insert(id, value);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		} finally {
		}
		return result;
	}

	@Override
	public int update(String id, Object value) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update(id, value);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}

		return result;
	}

	@Override
	public int update(String id, Map<String, Object> map) throws Exception {
		int result = 0;

		try {
			result = sqlSession.update(id, map);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}

		return result;
	}

	@Override
	public int delete(String id, Map<String, Object> map) throws Exception {
		int result = 0;

		try {
			result = sqlSession.delete(id, map);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}

		return result;
	}

	@Override
	public int delete(String id, Object value) throws Exception {
		int result = 0;

		try {
			result = sqlSession.delete(id, value);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}

		return result;
	}

	@Override
	public int deleteAll(String id) throws Exception {
		int result = 0;

		try {
			result = sqlSession.delete(id);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}

		return result;
	}

	@Override
	public <T> List<T> selectList(String id, Map<String, Object> map) throws Exception {
		List<T> list = null;
		try {
			list = sqlSession.selectList(id, map);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}
		return list;
	}

	@Override
	public <T> List<T> selectList(String id, Object value) throws Exception {
		List<T> list = null;
		try {
			list = sqlSession.selectList(id, value);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}
		return list;
	}

	@Override
	public <T> List<T> selectList(String id) throws Exception {
		List<T> list = null;
		try {
			list = sqlSession.selectList(id);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}
		return list;
	}

	@Override
	public <T> T selectOne(String id) throws Exception {
		try {
			return sqlSession.selectOne(id);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}
	}

	@Override
	public <T> T selectOne(String id, Object value) throws Exception {
		try {
			return sqlSession.selectOne(id, value);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}
	}

	@Override
	public <T> T selectOne(String id, Map<String, Object> map) throws Exception {
		try {
			return sqlSession.selectOne(id, map);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}
	}

	/**
	 * 프로시져 : INSERT, UPDATE, DELETE
	 */
	@Override
	public void updateProcedure(String id, Object value) throws Exception {
		try {
			sqlSession.update(id, value);
		} catch (Exception e) {
			log.error("[ERROR]", e);

			throw e;
		}

	}

	/**
	 * SELECT : OUT 파라미터가 SYS_REFCURSOR 이외의 하나의 레코드(INTEGER 등)
	 */
	@Override
	public <T> Map<String, T> selectOneProcedureMap(String id, Map<String, T> map) throws Exception {
		try {
			// select procedure 결과는 map로 리턴한다.
			sqlSession.selectOne(id, map);
			
			return map;
		} catch (Exception e) {
			log.error("[ERROR]", e);
			
			throw e;
		}
	}

	/**
	 * SELECT : OUT 파라미터가 SYS_REFCURSOR(하나 또는 하나 이상의 레코드)
	 */
	@Override
	public <T> Map<String, T> selectListProcedureMap(String id, Map<String, T> map) throws Exception{
		try {
			// select procedure 결과는 map로 리턴한다.
			sqlSession.selectList(id, map);
			
			return map;
		} catch (Exception e) {
			log.error("[ERROR]", e);
			
			throw e;
		}
	}

}
