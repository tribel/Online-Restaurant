package com.tribel.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tribel.jpa.entity.Menu;

@Repository
public class MenuDaoImpl implements MenuDao{
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Menu> getFoodListByCategory(String category) {
		TypedQuery<Menu> query = em.createQuery("SELECT m FROM Menu m WHERE m.category = :category",Menu.class);
		query.setParameter("category",category);
		return query.getResultList();
	}

	@Override
	public void addDishToMenu(Menu dish) {
		if(dish.getId() == 0) {
			em.persist(dish);	
		} else {
			em.merge(dish);
		}
	}


	@Override
	public void setActiveDish(int dishId) {
		Menu menu  = em.find(Menu.class, dishId);
		if(menu != null) {
			if (menu.getActive().equals("active")) {
				menu.setActive("nonActive");
			} else {
				menu.setActive("active");
			}
		}
	}

	@Override
	public void deleteDish(int dishId) {
		Menu menu = em.find(Menu.class, dishId);
		if (menu != null) {
			em.remove(menu);
		}
	}

	@Override
	public void editDishName(int dishId, String name,String kitchenMade) {
		Menu menu = em.find(Menu.class, dishId);
		if (menu != null) {
			menu.setName(name);
			menu.setKitchenMade(kitchenMade);
		}
		
	}

	@Override
	public void editDishPrice(int dishId, double price, String kitchenMade) {
		Menu menu = em.find(Menu.class, dishId);
		if(menu != null) {
			menu.setPrice(price);
			menu.setKitchenMade(kitchenMade);
		}
	}

	@Override
	public List<Menu> getMenuFromWebService() {
		TypedQuery<Menu> query = em.createQuery("SELECT m FROM Menu m WHERE m.active = 'active' ",Menu.class); 
		return query.getResultList();
	}

	@Override
	public List<Menu> getFoodListAll() {
		TypedQuery<Menu> query = em.createQuery("SELECT m FROM Menu m",Menu.class);
		return query.getResultList();
	}

	@Override
	public Menu findById(int id) {
		Menu menu = em.find(Menu.class, id);
		return menu;
	}

	@Override
	public void setKitchenMade(int dishId) {
		Menu menu  = em.find(Menu.class, dishId);
		if(menu != null) {
			if (menu.getKitchenMade().equals("kitchenMade")) {
				menu.setActive("nonKitchenMade");
			} else {
				menu.setActive("kitchenMade");
			}
		}	
	}

	@Override
	public boolean isActive(int dishId) {
		Menu menu  = em.find(Menu.class, dishId);
		if(menu != null) {
			if (menu.getActive().equals("active")) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	
}

