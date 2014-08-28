package com.kobaco.smartad.dao;

import java.util.List;

import com.kobaco.smartad.model.Params;

public interface CommonDao<T> {

	public static final String namespace = "com.kobaco.smartad";
	
	public List<T> list(T t);
	public T info(T t);
	public T insert(T t);
	public int update(T t);
	public int delete(T t);
	public int count(T t);
	public List<T> list(T t, Params param);
	public T info(T t, Params param);
	public int update(T t, Params param);
	public int delete(T t, Params param);
	public int count(T t, Params param);
	
}
