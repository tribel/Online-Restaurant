package com.tribel.jpa.dao;

import java.util.List;

import com.tribel.jpa.entity.Role;
import com.tribel.jpa.entity.Users;

public interface UsersDao {
	public void registration(Users user);

	public Users autorize(Users user);
	
	public void editUser(Users users);

	public void createStaffAccount(Users staff, int roleId);

	public void editStaffRights(int userId, int roleId);
	
	public void setActiveUser(int userId);
	public void setDeactiveUser(int userId);
	
	public List<Users> getStaffList();
	
	public Users findById(int id);
	
	public List<Role> getAllRoles();

	public Users setRoleNewUser(Users user);
	
	public List<String> getLosginRequest();
	
}
