package com.tribel.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;






import com.tribel.jpa.entity.KitchenPending;
import com.tribel.jpa.entity.OrderDishes;


@Repository
public class OrderDishesDaoImpl implements OrderDishesDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public double getIndividualPrice(int orderId, int dishId) {
		TypedQuery<OrderDishes> query = em.createQuery(
				"SELECT od FROM OrderDishes od WHERE od.orderId = " + orderId
						+ " AND od.dishId = " + dishId, OrderDishes.class);
		return query.getSingleResult().getPrice();
	}

	@Override
	public double getTotalOrderPrice(int orderId) {
		TypedQuery<OrderDishes> query  = em.createQuery("SELECT od FROM OrderDishes od WHERE od.orderId = " + orderId,
														OrderDishes.class);
		List<OrderDishes> list = query.getResultList();
		return list.stream().mapToDouble(OrderDishes::getPrice).sum();
	}

	@Override
	public void setDishAsDone(int orderDishId) {
		TypedQuery<OrderDishes> query = em.createQuery(
				"SELECT od FROM OrderDishes od WHERE od.id = " + orderDishId, OrderDishes.class);
		OrderDishes entity  = query.getSingleResult();
		entity = em.find(OrderDishes.class, entity.getId());
		if (entity != null) {
			entity.setStatus("done");
		}
		
	}

	@Override
	public List<KitchenPending> getPendingList() {
		String txt = "SELECT new com.tribel.jpa.entity.KitchenPending(m.name , o.dateTime, od.id)";
		txt += " FROM Menu m,Orders o, OrderDishes od WHERE m.id = od.dish.id AND o.id = od.order.id AND od.status = null ORDER BY o.dateTime";
		TypedQuery<KitchenPending> query  = em.createQuery(txt,KitchenPending.class);
		return query.getResultList();
	}

	@Override
	public List<Integer> getNotDoneOrder(int odId) {
		TypedQuery<Integer> query = em.createQuery("SELECT od.id FROM OrderDishes od WHERE od.order.id = :odId AND od.status = null ", Integer.class);
		query.setParameter("odId", odId);
		return query.getResultList();
	}

	@Override
	public OrderDishes findById(int id) {
		OrderDishes dishes = em.find(OrderDishes.class, id);
		return dishes;
	}

	
}

