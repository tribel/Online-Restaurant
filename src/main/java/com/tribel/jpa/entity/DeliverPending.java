package com.tribel.jpa.entity;

import java.sql.Timestamp;

public class DeliverPending {
	private Timestamp time;
	private String address;
	private int orderId;

	public DeliverPending() {
	}

	public DeliverPending(Timestamp time, String address, int orderId) {
		this.time = time;
		this.address = address;
		this.orderId = orderId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "DeliverPending [time=" + time + ", address=" + address
				+ ", orderId=" + orderId + "]";
	}

}
