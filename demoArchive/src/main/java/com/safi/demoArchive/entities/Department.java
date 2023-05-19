package com.safi.demoArchive.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

	
//the departmentID column is used for mapping relation only to make taple order_department.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long departmentID;
	
	@Column(name = "departmentN")
	private String departmentN;
		
	@OneToMany(targetEntity = Attachment.class,cascade = CascadeType.ALL )
	@JoinColumn(name = "department_fk",referencedColumnName = "departmentID")
	private List<Attachment>attachment = new ArrayList<>();
	
	@OneToMany(targetEntity = procedure.class,cascade = CascadeType.ALL )
	@JoinColumn(name = "department_fk",referencedColumnName = "departmentID")
	private List<procedure>procedure = new ArrayList<>();
	
	
	public Department() {
		
	}

	public Department(String name) {
		super();
		this.departmentN = name;
	}

	
	public long getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(long departmentID) {
		this.departmentID = departmentID;
	}

	public String getDepartmentN() {
		return departmentN;
	}

	public void setDepartmentN(String departmentN) {
		this.departmentN = departmentN;
	}
	

	public List<Attachment> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}


	public List<procedure> getProcedure() {
		return procedure;
	}

	public void setProcedure(List<procedure> procedure) {
		this.procedure = procedure;
	}

	@Override
	public String toString() {
		return "Department [departmentID=" + departmentID + ", departmentN=" + departmentN + ", attachment="
				+ attachment + ", procedure=" + procedure + "]";
	}

	

	

}
