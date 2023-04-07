package com.jbk.Product.response;

import java.util.Date;

public class ErrorResponce {
	private String message;
	private Date date;
	
	public ErrorResponce() {
		// TODO Auto-generated constructor stub
	}

	public ErrorResponce(String message, Date date) {
		super();
		this.message = message;
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ErrorResponce [message=" + message + ", date=" + date + "]";
	}
	
	

}
