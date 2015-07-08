package com.tribel.jpa.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tribel.jpa.entity.ClientReport;
import com.tribel.jpa.entity.DeliverPending;
import com.tribel.jpa.entity.Orders;

@Repository
public class OrdersDaoImpl implements OrdersDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Orders order) {
		em.persist(order);
	}

	@Override
	public void setOrderStatus(int orderId, String status) {
		Orders orders = em.find(Orders.class, orderId);
		if (orders != null) {
			orders.setStatus(status);
		}
	}

	@Override
	public List<DeliverPending> getDeliverPendingList() {
		String txt = "SELECT new com.tribel.jpa.entity.DeliverPending(o.dateTime,u.address, o.id) FROM Orders o,Users u ";
		txt += "WHERE o.user.id = u.id AND o.status = 'ready for shipment' ";
		TypedQuery<DeliverPending> query = em.createQuery(txt,
				DeliverPending.class);
		return query.getResultList();
	}

	@Override
	public List<ClientReport> getClientReport(Timestamp start, Timestamp end) {
		String txt = "SELECT new com.tribel.jpa.entity.ClientReport( o.id , SUM(od.price))";
		txt += " FROM Orders o , OrderDishes od WHERE o.id = od.order.id AND o.dateTime BETWEEN ?1 AND ?2  GROUP BY o.id";
		TypedQuery<ClientReport> query = em
				.createQuery(txt, ClientReport.class);
		query.setParameter(1, start);
		query.setParameter(2, end);
		return query.getResultList();
	}

	@Override
	public ClientReport getClientReportByCategory(Timestamp start,
			Timestamp end, String category) {

		String txt = "SELECT new com.tribel.jpa.entity.ClientReport( COUNT(o.id), SUM(od.price))";
		txt += " FROM Orders o,OrderDishes od, Menu m WHERE o.id = od.order.id ";
		txt += " AND m.id = od.dish.id AND m.category = ?3";
		TypedQuery<ClientReport> query = em
				.createQuery(txt, ClientReport.class);
		query.setParameter(3, category);
		return query.getSingleResult();
	}

	@Override
	public Orders find(int id) {
		Orders order = em.find(Orders.class, id);
		return order;
	}

	@Override
	public List<DeliverPending> getDeliverPendingListDelivery() {
		String txt = "SELECT new com.tribel.jpa.entity.DeliverPending(o.dateTime,u.address, o.id) FROM Orders o,Users u ";
		txt += "WHERE o.user.id = u.id AND o.status = 'delivering' ";
		TypedQuery<DeliverPending> query = em.createQuery(txt,
				DeliverPending.class);
		return query.getResultList();
	}

	@Override
	public Long getClientReportByDishes(String cat) {
		TypedQuery<Long> query = em
				.createQuery(
						"SELECT COUNT(od.id) FROM OrderDishes od WHERE od.dish.name = :cat",
						Long.class);
		query.setParameter("cat", cat);
		return query.getSingleResult();
	}

}
