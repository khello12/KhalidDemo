package com.khalid.model;

public class AirportDetails {

	private String code;
	private String name;
	private String phoneNumber;
	private Address address;
	private FlightDetails flightDetails;
	
	public AirportDetails(String code, String name, String phoneNumber, Address address, FlightDetails flightDetails) {
		super();
		this.code = code;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.flightDetails = flightDetails;
		
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public FlightDetails getFlightDetails() {
		return flightDetails;
	}

	public void setFlightDetails(FlightDetails flightDetails) {
		this.flightDetails = flightDetails;
	}

}
