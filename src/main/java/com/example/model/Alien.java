package com.example.model;



public class Alien {
  
    private int id;
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String name;
	@Override
	public String toString() {
		return "Alien [id=" + id + ", name=" + name + "]";
	}
  
     
  
}

//}
