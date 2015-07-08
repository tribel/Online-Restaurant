package com.tribel.jpa.entity;

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
public class Orders {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private java.sql.Timestamp dateTime;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private Users user;
	//private int userId;
	
	private String status;
	
	@OneToMany(cascade= CascadeType.PERSIST,mappedBy="order")
	private Collection<OrderDishes> orderDishes;
	
	
	public Collection<OrderDishes> getOrderDishes() {
		return orderDishes;
	}

	public void setOrderDishes(Collection<OrderDishes> orderDishes) {
		this.orderDishes = orderDishes;
	}

	public Orders() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public java.sql.Timestamp getDateTime() {
		return dateTime;
	}

	public void setDateTime(java.sql.Timestamp dateTime) {
		this.dateTime = dateTime;
	}

/*	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}*/

	public String getStatus() {
		return status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Orders [id=" + id + ", dateTime=" + dateTime + ", userId="
				+ user + "]";
	}
	
	
}

