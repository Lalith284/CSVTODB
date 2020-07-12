package com.example.csvdemo2;

import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;

//@Entity
//@Table(name="sales")
public class Sales {
	
	public Sales() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Sales [orderId=" + orderId + ", region=" + region + ", country=" + country + ", itemType=" + itemType
				+ ", salesChannel=" + salesChannel + ", orderPriority=" + orderPriority + ", orderDate=" + orderDate
				+ ", shipDate=" + shipDate + ", unitsSold=" + unitsSold + ", unitPrice=" + unitPrice + ", unitCost="
				+ unitCost + ", totalRevenue=" + totalRevenue + ", totalCost=" + totalCost + ", totalProfit="
				+ totalProfit + "]";
	}

	public Integer getOrderId() {
		return orderId;
	}





	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}





	public String getRegion() {
		return region;
	}





	public void setRegion(String region) {
		this.region = region;
	}





	public String getCountry() {
		return country;
	}





	public void setCountry(String country) {
		this.country = country;
	}





	public String getItemType() {
		return itemType;
	}





	public void setItemType(String itemType) {
		this.itemType = itemType;
	}





	public String getSalesChannel() {
		return salesChannel;
	}





	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}





	public String getOrderPriority() {
		return orderPriority;
	}





	public void setOrderPriority(String orderPriority) {
		this.orderPriority = orderPriority;
	}





	public Date getOrderDate() {
		return orderDate;
	}





	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}





	public Date getShipDate() {
		return shipDate;
	}





	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}





	public Integer getUnitsSold() {
		return unitsSold;
	}





	public void setUnitsSold(Integer unitsSold) {
		this.unitsSold = unitsSold;
	}





	public Float getUnitPrice() {
		return unitPrice;
	}





	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}





	public Float getUnitCost() {
		return unitCost;
	}





	public void setUnitCost(Float unitCost) {
		this.unitCost = unitCost;
	}





	public Float getTotalRevenue() {
		return totalRevenue;
	}





	public void setTotalRevenue(Float totalRevenue) {
		this.totalRevenue = totalRevenue;
	}





	public Float getTotalCost() {
		return totalCost;
	}





	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}





	public Float getTotalProfit() {
		return totalProfit;
	}





	public void setTotalProfit(Float totalProfit) {
		this.totalProfit = totalProfit;
	}

//	@Id
//	@GeneratedValue()
//	@Column(name = "orderid")
	Integer orderId;


//	@Column(name = "region")
	String region;
	
//	@Column(name = "country")
	String country; 
	
//	@Column(name = "itemtype")
	String itemType;
	
//	@Column(name = "saleschannel")
	String salesChannel;
	
//	@Column(name = "orderpriority")
	String orderPriority;
	
//	@Column(name = "orderdate")
	Date orderDate;
	
//	@Column(name = "shipdate")	
	Date shipDate;
	
//	@Column(name = "unitssold")
	Integer unitsSold;
	
//	@Column(name = "unitprice")
	Float unitPrice;
	
//	@Column(name = "unitcost")
	Float unitCost;
	
//	@Column(name = "totalrevenue")
	Float totalRevenue;
	
//	@Column(name = "totalcost")
	Float totalCost;
	
//	@Column(name = "totalprofit")
	Float totalProfit;
	
	
	
	
	
	public Sales(Integer orderId, String region, String country, String itemType, String salesChannel,
			String orderPriority, Date orderDate, Date shipDate, Integer unitsSold, Float unitPrice, Float unitCost,
			Float totalRevenue, Float totalCost, Float totalProfit) {
		super();
		this.orderId = orderId;
		this.region = region;
		this.country = country;
		this.itemType = itemType;
		this.salesChannel = salesChannel;
		this.orderPriority = orderPriority;
		this.orderDate = orderDate;
		this.shipDate = shipDate;
		this.unitsSold = unitsSold;
		this.unitPrice = unitPrice;
		this.unitCost = unitCost;
		this.totalRevenue = totalRevenue;
		this.totalCost = totalCost;
		this.totalProfit = totalProfit;
	}
	

}
