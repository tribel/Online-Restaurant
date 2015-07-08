package com.tribel.jpa.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tribel.jpa.dao.UsersDao;
import com.tribel.jpa.entity.Role;
import com.tribel.jpa.entity.Users;

@Named
public class UsersServiceImpl implements UsersService{
	
	@Inject
	private UsersDao usersDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void registration(Users user) {
		usersDao.registration(usersDao.setRoleNewUser(user));
	}

	@Override
	public Users autorize(Users user){ 
		return usersDao.autorize(user);
	}

	@Override
	@Transactional
	public void createStaffAccount(Users staff, int roleId) {
		usersDao.createStaffAccount(staff, roleId);
	}

	@Override
	@Transactional
	public void editStaffRights(int userId, int roleId) {
		usersDao.editStaffRights(userId, roleId);
	}

	@Override
	@Transactional
	public void setActiveUser(int userId) {
		usersDao.setActiveUser(userId);
	}

	@Override
	@Transactional
	public void setDeactiveUser(int userId) {
		usersDao.setDeactiveUser(userId);	
	}

	@Override
	public List<Users> getStaffList() {
		return usersDao.getStaffList();
	}

	@Override
	public Users findById(int id) {
		return usersDao.findById(id);
	}

	@Override
	public List<Role> getAllRoles() {
		return usersDao.getAllRoles();
	}

	@Override
	@Transactional
	public void editUser(Users users) {
		usersDao.editUser(users);
	}

	@Override
	public List<String> getLosginRequest() {
		return usersDao.getLosginRequest();
	}



}

