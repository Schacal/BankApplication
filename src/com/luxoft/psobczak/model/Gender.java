package com.luxoft.psobczak.model;

public enum Gender {
    MALE("Mr."), FEMALE("Ms."), UNKNOWN;

	private String greeting;

	private Gender(String greeting) {
		this.greeting = greeting;
	}
	
	private Gender() {
		this.greeting = "";
	}
	
	

	public String getGreeting() {
		return greeting;
	}
}
