package com.tribel.jpa.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import com.tribel.jpa.dao.OrdersDao;
import com.tribel.jpa.entity.ClientReport;
import com.tribel.jpa.entity.DeliverPending;
import com.tribel.jpa.entity.Orders;
import com.tribel.jpa.entity.Users;

@Named
public class OrdersServiceImpl implements OrdersService{
	
	@Inject
	private OrdersDao ordersDao;
	
	@Inject
	private UsersService userService;
	

	@Override
	@Transactional
	public void createOrderAutorizedUser(Users user, Orders order) {
		Users autUser = userService.autorize(user);
		if (autUser != null)
			order.setUser(autUser);
		
		ordersDao.save(order);
	}
	
	@Override
	@Transactional
	public void createOrderNonAutorizeUser(Users user, Orders order) {
		userService.registration(user);
		order.setUser(user);
		ordersDao.save(order);
	}

	@Override
	@Transactional
	public void setOrderStatus(int orderId, String status) {
		ordersDao.setOrderStatus(orderId, status);
	}

	@Override
	public List<ClientReport> getClientReport(Timestamp start, Timestamp end) {
		return ordersDao.getClientReport(start, end);
	}

	@Override
	public List<DeliverPending> getDeliverPendingList() {
		return ordersDao.getDeliverPendingList();
	}

	@Override
	public ClientReport getClientReportByCategory(Timestamp start,
			Timestamp end, String category) {

		return ordersDao.getClientReportByCategory(start, end, category);
	}

	@Override
	public Orders find(int id) {
		return ordersDao.find(id);
	}

	@Override
	public List<DeliverPending> getDeliverPendingListDelivery() {
		return ordersDao.getDeliverPendingListDelivery();
	}

	@Override
	public Long getClientReportByDishes(String cat) {
		return ordersDao.getClientReportByDishes(cat);
	}

}

