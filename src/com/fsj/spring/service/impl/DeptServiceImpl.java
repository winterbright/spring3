package com.fsj.spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.IDeptDao;
import com.fsj.spring.model.TDept;
import com.fsj.spring.service.IDeptService;

@Service("deptService")
public class DeptServiceImpl implements IDeptService{

	private IDeptDao deptDao;
	
	public IDeptDao getDeptDao() {
		return deptDao;
	}
	
	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}

	public void addDept(TDept dept) throws Exception {
		deptDao.save(dept);
	}

	public void deleteDept(int id) throws Exception {
		deptDao.delete(getDeptById(id));
	}

	public TDept getDeptById(int id) throws Exception {
		return deptDao.findById(id);
	}

	@SuppressWarnings("unchecked")
	public List<TDept> getDeptList() throws Exception {
		return deptDao.findAll();
	}

	public void updateDept(TDept dept) throws Exception {
		deptDao.merge(dept);
	}

}
