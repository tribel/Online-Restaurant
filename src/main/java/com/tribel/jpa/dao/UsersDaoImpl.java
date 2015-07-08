package com.tribel.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tribel.jpa.entity.Role;
import com.tribel.jpa.entity.Users;

@Repository
public class UsersDaoImpl implements UsersDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void registration(Users user) {
			em.persist(user);	
	}
	
	@Override
	public Users autorize(Users user) {
		TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.login = :login AND u.password = :password",Users.class);
		query.setParameter("password", user.getPassword());
		query.setParameter("login", user.getLogin());
		return query.getSingleResult();
	}

	
	
	@Override
	public void createStaffAccount(Users staff, int roleId) {
		Role role = em.find(Role.class, roleId);
		staff.setRole(role);
		em.persist(staff);
	}

	@Override
	public void editStaffRights(int userId, int roleId) {
		Users users  = em.find(Users.class, userId);
		Role role = em.find(Role.class, roleId);
		if (users != null) {
			users.setRole(role);
		}
	}

	@Override
	public void setActiveUser(int userId) {
		Users users  = em.find(Users.class, userId);
		if (users != null) {
			users.setActive("active");
		}
	}

	@Override
	public void setDeactiveUser(int userId) {
		Users users  = em.find(Users.class, userId);
		if (users != null) {
			users.setActive("deactive");
		}
	}

	@Override
	public List<Users> getStaffList() {
		TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u WHERE u.role.id <> 1", Users.class);
		return query.getResultList();
	}

	@Override
	public Users findById(int id) {
		Users user = em.find(Users.class, id);
		return user;
	}

	@Override
	public List<Role> getAllRoles() {
		TypedQuery<Role> query = em.createQuery("SELECT r FROM Role r",Role.class);
		return query.getResultList();
	}

	
	@Override
	public Users setRoleNewUser(Users user) {
		Role role = em.find(Role.class, 1);
		user.setRole(role);
		return user;
	}

	@Override
	public void editUser(Users users) {
		em.merge(users);
	}

	@Override
	public List<String> getLosginRequest() {
		TypedQuery<String> query = em.createQuery("SELECT u.login FROM Users u ", String.class);
		return query.getResultList();
	}
	
	
}

