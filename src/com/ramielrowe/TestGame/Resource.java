package com.ramielrowe.TestGame;

import javax.xml.bind.annotation.XmlAttribute;

public class Resource {

	private String key;
	private String location;
	
	public Resource(){
		
	}
	
	public Resource(String key, String location){
		this.key = key;
		this.location = location;
	}
	
	@XmlAttribute
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@XmlAttribute
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
