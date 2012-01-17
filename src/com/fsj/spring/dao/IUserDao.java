package com.fsj.spring.dao;

import java.util.List;

import com.fsj.spring.model.TUser;

public interface IUserDao {

	// property constants
	public static final String NAME = "name";
	public static final String AGE = "age";
	public static final String DEPT_ID = "deptId";

	public abstract void save(TUser transientInstance);

	public abstract void delete(TUser persistentInstance);

	public abstract TUser findById(java.lang.Integer id);

	public abstract List findByExample(TUser instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByName(Object name);

	public abstract List findByAge(Object age);

	public abstract List findByDeptId(Object deptId);

	public abstract List findAll();

	public abstract TUser merge(TUser detachedInstance);

	public abstract void attachDirty(TUser instance);

	public abstract void attachClean(TUser instance);

}