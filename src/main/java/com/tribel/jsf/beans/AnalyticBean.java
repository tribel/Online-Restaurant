package com.tribel.jsf.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.springframework.context.annotation.Scope;

import com.tribel.jpa.entity.ClientReport;
import com.tribel.jpa.entity.Menu;
import com.tribel.jpa.service.MenuService;
import com.tribel.jpa.service.OrdersService;

@Named
@Scope("session")
public class AnalyticBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private OrdersService ordersService;
	
	@Inject
	private MenuService menuService;
	
	private LineChartModel dateModel = new LineChartModel();
	private PieChartModel pieModel1 = new PieChartModel();
	
	private List<ClientReport> clientReports;
	private int ordersCount;
	private double totalSum;
	private ArrayList<ClientReport> tableTotalReport = new ArrayList<ClientReport>();
	
	private Date startTime;
	private Date endTime;
	
	private String category ;
	private List<Menu> dishes;
	private Map<String, String> categories = new HashMap<String, String>();
	
	public AnalyticBean() {
	}

	public ArrayList<ClientReport> getTableTotalReport() {
		return tableTotalReport;
	}

	public void setTableTotalReport(ArrayList<ClientReport> tableTotalReport) {
		this.tableTotalReport = tableTotalReport;
	}

	public PieChartModel getPieModel1() {
		return pieModel1;
	}

	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public List<Menu> getDishes() {
		return dishes;
	}

	public void setDishes(List<Menu> dishes) {
		this.dishes = dishes;
	}

	public Map<String, String> getCategories() {
		return categories;
	}

	public void setCategories(Map<String, String> categories) {
		this.categories = categories;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public LineChartModel getDateModel() {
		return dateModel;
	}

	public void setDateModel(LineChartModel dateModel) {
		this.dateModel = dateModel;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getOrdersCount() {
		return ordersCount;
	}

	public void setOrdersCount(int ordersCount) {
		this.ordersCount = ordersCount;
	}

	public double getTotalSum() {
		return totalSum;
	}

	public void setTotalSum(double totalSum) {
		this.totalSum = totalSum;
	}

	public OrdersService getOrdersService() {
		return ordersService;
	}

	public void setOrdersService(OrdersService ordersService) {
		this.ordersService = ordersService;
	}

	public List<ClientReport> getClientReports() {
		return clientReports;
	}

	public void setClientReports(List<ClientReport> clientReports) {
		this.clientReports = clientReports;
	}
	
	public String calculateReport() {
		//LocalDateTime dateTime = LocalDateTime.now(); 		
		//startTime = Timestamp.valueOf(dateTime.minusDays(10));
		//endTime = Timestamp.valueOf(dateTime);
		long time1 = startTime.getTime();
		long time2 = endTime.getTime();
		clientReports = ordersService.getClientReport(new Timestamp(time1), new Timestamp(time2));
		ordersCount = clientReports.size();
		int[] idOFOrders = new int[ordersCount];
		double daySumNumber = 0.0; 
		ArrayList<Double> daySumList = new ArrayList<>();
		
		System.out.println(clientReports);
			totalSum = 0.0;
		for(ClientReport c: clientReports) {
			totalSum += c.getTotalSum();
		}
		for(int i=0; i < clientReports.size(); i++) {
			idOFOrders[i] = clientReports.get(i).getOrderCount();
		}
		
		
		LinkedHashMap<LocalDate, Integer> map = new LinkedHashMap<>();
		Integer value = 0;
		LocalDate dateTo = null;
		for(int i =0; i < clientReports.size(); i++) {
			Timestamp tmpTime = ordersService.find(idOFOrders[i]).getDateTime();
			LocalDate localDate  = tmpTime.toLocalDateTime().toLocalDate();
			if ( localDate.equals(dateTo)) {
				value++; daySumNumber+= clientReports.get(i).getTotalSum();
			} else {			
				value = 1;
			}
			
			if (i != 0) {
				map.put(localDate, value); daySumList.add(daySumNumber); daySumNumber = 0.0;
			}	
			dateTo = localDate;	
		}
		
		dateModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("series1");
		int ii=0;
		for(Map.Entry<LocalDate, Integer> entry: map.entrySet()) {
			series1.set(entry.getKey().toString(), entry.getValue());
			ClientReport tmpRep = new ClientReport();
			tmpRep.setDayDate(entry.getKey());
			tmpRep.setOrderCount(entry.getValue());
			tmpRep.setTotalSum(daySumList.get(ii));
			ii++;
			
			tableTotalReport.add(tmpRep);
		}

		dateModel.addSeries(series1);
		
		dateModel.setTitle("Zoom for Details");
        dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("Values");
        DateAxis axis = new DateAxis("Dates");
        axis.setTickAngle(-50);
        axis.setTickFormat("%b %#d, %y");
         
        dateModel.getAxes().put(AxisType.X, axis);
		
        return "analyticDetailTable";
	}
	
	public void getCategoryReport() {
		ClientReport report = ordersService.getClientReportByCategory(null, null, category);
		ordersCount = (int)report.getDishCount();
		totalSum = report.getTotalSum();
	}

	
	public void getAllMenu() {
		dishes = menuService.getFoodListAll();
		for(Menu m: dishes) {
			categories.put(m.getCategory(), m.getCategory());
		}

	}
	
	public void getGrfikCategoryReport() {
		List<Menu> tmpDish = menuService.getFoodListByCategory(category);
		pieModel1 = new PieChartModel();
		
		for(Menu m: tmpDish) {
			long tmpNumber = ordersService.getClientReportByDishes(m.getName());
			pieModel1.set(m.getName(), tmpNumber);
		}
		
		pieModel1.setTitle("");
        pieModel1.setLegendPosition("w");
		
	}
	
	public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                        "Item Index: " + event.getItemIndex() + ", Series Index:" + event.getSeriesIndex());
         
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
}

