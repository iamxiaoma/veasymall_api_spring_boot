package com.veasymall.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.mysql.cj.util.StringUtils;
import com.veasymall.api.mapper.UserMapper;
import com.veasymall.api.pojo.User;
import com.veasymall.api.service.UserService;

import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveUser(User user) throws Exception {

		userMapper.insert(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateUser(User user) {
		userMapper.updateByPrimaryKeySelective(user);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteUser(Integer userId) {
		userMapper.deleteByPrimaryKey(userId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public User queryUserById(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> queryUserListPagedJqgrid(User user, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public List<User> queryUserListPaged(User user, Integer page, Integer pageSize) {

		// 开始分页
		PageHelper.startPage(page, pageSize);

		Example example = new Example(User.class);
		Example.Criteria criteria = example.createCriteria();

		if (!StringUtils.isEmptyOrWhitespaceOnly(user.getName())) {
			criteria.andLike("name", "%" + user.getName() + "%");
		}
		example.orderBy("createTime").desc();

		return userMapper.selectByExample(example);
	}

}
