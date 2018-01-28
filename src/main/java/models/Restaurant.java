package models;

import java.sql.Array;
import java.util.ArrayList;

public class Restaurant implements Comparable<Restaurant>{

	private long id;
	private String name, address, description;
	private String[] keywords;
	private long bmenu_id, lmenu_id, dmenu_id;
	
	public Restaurant(long id, String name, String address, String description, String[] keywords, long bmenu_id, long lmenu_id, long dmenu_id) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;

		this.keywords = keywords;
		
		this.bmenu_id = bmenu_id;
		this.lmenu_id = lmenu_id;
		this.dmenu_id = dmenu_id;
	}
	
	public Restaurant() {}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String[] getKeywords() {
		return keywords;
	}
	
//	public BreakfastMenu getBreakfastMenu() {
//		return bmenu;
//	}
//	
//	public LunchMenu getLunchMenu() {
//		return lmenu;
//	}
//	
//	public DinnerMenu getDinnerMenu() {
//		return dmenu;
//	}

	public long getBreakfastMenuId() {
		return bmenu_id;
	}
	
	public long getLunchMenuId() {
		return lmenu_id;
	}
	
	public long getDinnerMenuId() {
		return dmenu_id;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	public int compareTo(Restaurant restaurant) {
		return this.toString().toLowerCase().compareTo(restaurant.toString().toLowerCase());
	}
}
