package com.tribel.jpa.entity;

import java.sql.Date;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String telephone;
	private java.sql.Date birthday;
	private String address;
	
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role;
	
	//private int roleId;
	private String login;
	private String password;
	private String active;
	
	@OneToMany(cascade= CascadeType.PERSIST,mappedBy="user")
	private Collection<Orders> orders;
	
	public Users() {
		this.active = "active";
	}
	
	public Users(String name, String email, String telephone,
			Date birthday, String address, int roleId, String login,
			String password) {

		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.birthday = birthday;
		this.address = address;
		//this.roleId = roleId;
		this.login = login;
		this.password = password;
		this.active = "active";
	}
	
	public Users(String name, String email, String telephone,
			Date birthday, String address,String login,
			String password) {

		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.birthday = birthday;
		this.address = address;
		//this.roleId = 1;
		this.login = login;
		this.password = password;
		this.active = "active";
	}
	
	public Users(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public Collection<Orders> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Orders> orders) {
		this.orders = orders;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public java.sql.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

/*	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}*/

	public String getLogin() {
		return login;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", email=" + email
				+ ", telephone=" + telephone + ", birthday=" + birthday
				+ ", address=" + address + ", roleId=" + role + ", login="
				+ login + ", password=" + password + "]";
	}
	
	
}

