package com.hs;

import java.io.Serializable;
import java.util.List;

public class Pages implements Serializable {
	private static final long serialVersionUID = 1L;
	private String address;
	private List<String> links;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<String> getLinks() {
		return links;
	}

	public void setLinks(List<String> links) {
		this.links = links;
	}

}
