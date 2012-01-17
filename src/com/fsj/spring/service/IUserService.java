package com.fsj.spring.service;

import java.util.List;

import com.fsj.spring.model.TUser;

public interface IUserService {
	void addUser(TUser user) throws Exception;
	void updateUser(TUser user) throws Exception;
	void deleteUser(int id) throws Exception;
	TUser getUserById(int id) throws Exception;
	List<TUser> getUserList() throws Exception;
	TUser getUserByName(String name) throws Exception;
}
