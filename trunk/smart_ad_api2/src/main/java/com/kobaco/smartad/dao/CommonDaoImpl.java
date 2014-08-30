package com.kobaco.smartad.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kobaco.smartad.model.Entity;
import com.kobaco.smartad.model.Params;

@Repository
public class CommonDaoImpl<T extends Entity> implements CommonDao<T>{

	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private SqlSessionTemplate sql;
	
	private String getStatement(T t) {
		return CommonDao.namespace + "." + t.getEntityName();
	}
	
	@Override
	public List<T> list(T t) {
		return sql.selectList(getStatement(t) + ".list", t);
	}

	@Override
	public T info(T t) {
		return sql.selectOne(getStatement(t) + ".info", t);
	}

	@Override
	public T insert(T t) {
		sql.insert( getStatement(t) + ".insert", t);
		return t;
	}

	@Override
	public int update(T t) {
		return sql.update(getStatement(t) + ".update", t);
	}

	@Override
	public int delete(T t) {
		return sql.delete(getStatement(t) + ".delete", t);
	}

	@Override
	public List<T> list(T t, Params param) {
		return sql.selectList(getStatement(t) + ".list" + param.getNamespace(), param.getColumns());
	}

	@Override
	public T info(T t, Params param) {
		return sql.selectOne(getStatement(t) + ".info" + param.getNamespace(), param.getColumns());
	}

	@Override
	public int update(T t, Params param) {
		return sql.update(getStatement(t) + ".update" + param.getNamespace(), param.getColumns());
	}

	@Override
	public int delete(T t, Params param) {
		return sql.delete(getStatement(t) + ".delete" + param.getNamespace(), param.getColumns());
	}

	@Override
	public int count(T t, Params param) {
		return (Integer)sql.selectOne(getStatement(t) + ".count" + param.getNamespace(), param.getColumns());
	}

	@Override
	public int count(T t) {
		return (Integer)sql.selectOne(getStatement(t) + ".count", t) ;
	}

}
