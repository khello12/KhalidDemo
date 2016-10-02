package com.khalid.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FlightDetails {

	private Integer inbound;
	private Integer outbound;
	private ArrayList<String> carriers;

	public FlightDetails(ArrayList<String> carriers, Integer inbound, Integer outbound) {
		super();
		this.inbound = inbound;
		this.outbound = outbound;
		this.carriers = carriers;
	}

	public Integer getInbound() {
		return inbound;
	}

	public void setInbound(Integer inbound) {
		this.inbound = inbound;
	}

	public Integer getOutbound() {
		return outbound;
	}

	public void setOutbound(Integer outbound) {
		this.outbound = outbound;
	}

	public ArrayList<String> getCarriers() {
		return carriers;
	}

	public void setCarriers(ArrayList<String> carriers) {
		this.carriers = carriers;
	}
}
