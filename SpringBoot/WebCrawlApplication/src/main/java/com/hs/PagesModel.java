package com.hs;

import java.io.Serializable;
import java.util.List;

public class PagesModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Pages> pages;

	public void setPages(List<Pages> pages) {
		this.pages = pages;
	}

	public List<Pages> getPages() {
		return pages;
	}

}
