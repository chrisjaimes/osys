package models;

import java.util.ArrayList;

public class Restaurant {

	private final long id;
	private String name, address, description;
	private ArrayList<String> keywords;
	private ArrayList<Menu> menus;
	
	public Restaurant(long id, String name, String address, String description, ArrayList<String> keywords, ArrayList<Menu> menus) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.description = description;
		this.keywords = keywords;
		this.menus = menus;
	}
	
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
	
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	
	public ArrayList<Menu> getMenus() {
		return menus;
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
	
	public void setKeywords(ArrayList<String> keywords) {
		this.keywords = keywords;
	}
}
