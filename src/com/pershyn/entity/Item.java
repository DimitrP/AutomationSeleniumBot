package com.pershyn.entity;

public class Item {
	
	private String itemName;
	private String itemId;
	private Integer price;
	private String url;

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Item(){}

	public Item(String itemName, String itemId, Integer price, String url) {
		this.itemName = itemName;
		this.itemId = itemId;
		this.price = price;
		this.url = url;
	}

//	public Item(String itemName, String itemId, Integer price) {
//        this.itemName = itemName;
//        this.itemId = itemId;
//        this.price = price;
//    }

    public String getItemName() {
		return itemName;
	}
	public String getItemId() {
		return itemId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public void setPrice(String price) {
		this.price = Integer.parseInt(price);
	}



	@Override
	public String toString() {
		return "Item :: itemName= " + this.itemName + "\nitemId =  " + this.itemId + "\nitemPrice =  " + this.price +"\nItemUrl=  " + this.url+"\n";
	}

}
