package dev.dinesh.emlbs.test;

public class Hello {
	private String name="testNAme";
	public Hello() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String greet() {
		return "Hello "+getName();
	}
}
