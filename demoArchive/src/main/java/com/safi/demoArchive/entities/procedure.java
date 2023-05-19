package com.safi.demoArchive.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "procedures")
public class procedure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "procedureID")
	private long id;
	
	@Column(name = "procedure")
	String procedureString;
	
	@Column(name = "procedure'sDate")
	private Timestamp date;

	public procedure() {
		
	}
	
	public procedure(String procedureString, Timestamp date) {
		super();
		this.procedureString = procedureString;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProcedureString() {
		return procedureString;
	}

	public void setProcedureString(String procedureString) {
		this.procedureString = procedureString;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	

}
