package com.jhojan.amazoviewer.model;

import java.util.Date;

public class Publication {
	private String title;
	private Date editorialDate;
	private String editorial;
	private String[] authors;
	
	public Publication(String title, Date editorialDate, String editorial) {
		super();
		this.title = title;
		this.editorialDate = editorialDate;
		this.editorial = editorial;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getEditorialDate() {
		return editorialDate;
	}

	public void setEditorialDate(Date editorialDate) {
		this.editorialDate = editorialDate;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}
}
