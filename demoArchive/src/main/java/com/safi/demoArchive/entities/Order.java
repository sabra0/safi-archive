package com.safi.demoArchive.entities;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@Column(name = "orderID")
	// from Hibernate version 5.0. using generation type as IDENTITY not AUTO to avoid getting: _seq table
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderID;
	
	@Column(name = "registrationNum")
	private long regNum;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "recievedFrom")
	private String from;
	
	@Column(name = "sentTo")
	private String to;
	
	@Column(name = "order'sDate")
	private Timestamp date;
	
	@Column(name = "replyDate")
	private Timestamp replyDate;
	
	@Column(name = "informedDept")
	private String informedDept;
	
	@Column(name = "order'sSummery")
	private String summary;
	
	@OneToMany(targetEntity = Attachment.class,cascade = CascadeType.ALL )
	@JoinColumn(name = "order_fk",referencedColumnName = "orderID")
	private List<Attachment>attachment =new ArrayList<>();
	
	@OneToMany(targetEntity = procedure.class,cascade = CascadeType.ALL )
	@JoinColumn(name = "order_fk",referencedColumnName = "orderID")
	private List<procedure>procedures=new ArrayList<>();
	
	//I used cascade because there is an error while making order object by inserting a department name to the child table(order_department) which is not saved yet in the parent table(department) so cascade save the child save the both.
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "order_department", joinColumns = @JoinColumn(name = "order_orderID", referencedColumnName = "orderID"),
	inverseJoinColumns = @JoinColumn(name = "department_departmentID", referencedColumnName = "departmentID"))
	private List<Department>department=new ArrayList<>();
	
	public Order() {
		
	}

	public Order(long regNum, String type, String from, String to, Timestamp date, Timestamp replyDate,
			String informedDept, String summary) {
		super();
		this.regNum = regNum;
		this.type = type;
		this.from = from;
		this.to = to;
		this.date = date;
		this.replyDate = replyDate;
		this.informedDept = informedDept;
		this.summary = summary;
	}

	public long getId() {
		return orderID;
	}

	public void setId(long id) {
		this.orderID = id;
	}

	public long getRegNum() {
		return regNum;
	}

	public void setRegNum(long regNum) {
		this.regNum = regNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Timestamp getReplyDate() {
		return replyDate;
	}

	public void setReplyDate(Timestamp replyDate) {
		this.replyDate = replyDate;
	}

	public String getInformedDept() {
		return informedDept;
	}

	public void setInformedDept(String informedDept) {
		this.informedDept = informedDept;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<Attachment> getAttachment() {
		return attachment;
	}

	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}

	public List<procedure> getProcedures() {
		return procedures;
	}

	public void setProcedures(List<procedure> procedures) {
		this.procedures = procedures;
	}

	public List<Department> getDepartment() {
		return department;
	}

	public void setDepartment(List<Department> department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Order [orderID=" + orderID + ", regNum=" + regNum + ", type=" + type + ", from=" + from + ", to=" + to
				+ ", date=" + date + ", replyDate=" + replyDate + ", informedDept=" + informedDept + ", summary="
				+ summary + ", attachment=" + attachment + ", procedures=" + procedures + ", department=" + department
				+ "]";
	}
	
	


}
