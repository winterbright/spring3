package com.fsj.spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fsj.spring.dao.IUserDao;
import com.fsj.spring.model.TUser;
import com.fsj.spring.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	
	private IUserDao userDao;
	
	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public void addUser(TUser user) throws Exception {
		userDao.save(user);
	}

	public void deleteUser(int id) throws Exception {
		userDao.delete(getUserById(id));
	}

	public TUser getUserById(int id) throws Exception {
		return userDao.findById(id);
	}
	
	public TUser getUserByName(String name) throws Exception {
		List list = userDao.findByName(name);
		return list==null || list.size() == 0 ? null : (TUser)list.get(0);
	}

	public List<TUser> getUserList() throws Exception {
		return userDao.findAll();
	}

	public void updateUser(TUser user) throws Exception {
		userDao.merge(user);
	}

}
