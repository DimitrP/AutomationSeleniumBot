package com.pershyn.service;

import com.pershyn.entity.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ItemService {
	
	public static Item getItemByUrl(String url){
		Item item = new Item();
		
		try {
			Document document =  Jsoup.connect(url).get();
			item.setItemId(getItemIdByUrl(document));
			item.setItemName(getItemNameByUrl(document));
			item.setPrice(getItemPriceByUrl(document));
			item.setUrl(url);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;
	}
	
	
	public static String getItemIdByUrl(Document document){
		return document.getElementById("cerberus-data-metrics").attr("data-asin");
			
	}

	
	public static String getItemNameByUrl(Document document){
		return document.getElementById("productTitle").text();
			
	}
	
	public static String getItemPriceByUrl(Document document){
		String temp;
		temp = document.getElementById("cerberus-data-metrics").attr("data-asin-price").replace(".","");
		
		return temp;
			
	}


	
}


