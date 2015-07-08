package com.tribel.jpa.entity;

import java.sql.Timestamp;
import java.time.LocalDate;

public class ClientReport {
	private int orderCount;
	private double totalSum;
	private Timestamp startTime;
	private Timestamp endTime;
	private long dishCount;
	private String name;
	private LocalDate dayDate;

	public ClientReport() {
	}

	public ClientReport(int orderCount, double totalSum ) {
		this.orderCount = orderCount;
		this.totalSum = totalSum;
	}
	
	public ClientReport(long dishCount , double totalSum) {
		this.dishCount = dishCount;
		this.totalSum = totalSum;
	}
	
	public ClientReport(long count) {
		this.dishCount = count;
	}

	public LocalDate getDayDate() {
		return dayDate;
	}

	public void setDayDate(LocalDate dayDate) {
		this.dayDate = dayDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getDishCount() {
		return dishCount;
	}

	public void setDishCount(long dishCount) {
		this.dishCount = dishCount;
	}

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "ClientReport [orderCount=" + orderCount + ", totalSum="
				+ totalSum + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}

}
