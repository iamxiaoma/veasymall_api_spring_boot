package com.veasymall.api.service;

import java.util.List;

import com.veasymall.api.pojo.User;

public interface UserService {

	public void saveUser(User user) throws Exception;

	public void updateUser(User user);

	public void deleteUser(Integer userId);

	public User queryUserById(Integer userId);

	public List<User> queryUserListPagedJqgrid(User user, Integer page, Integer pageSize);
	
	public List<User> queryUserListPaged(User user, Integer page, Integer pageSize);

}
