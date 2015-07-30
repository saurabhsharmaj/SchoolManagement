package com.sfm.model;

// Generated Jul 16, 2015 11:21:28 AM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "sfm")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User implements java.io.Serializable {

	private Integer id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String fullName;
	private String fatherName;
	private String userName;
	private String password;
	private String batch;
	private String session;
	private Date addmissionDate;
	private Integer status;
	private Double studentFees;
	private String updateBy;
	private Date updatedOn;
	@JsonIgnore
	private Set<Fees> feeses = new HashSet<Fees>(0);
	@JsonIgnore
	private Set<Charges> chargeses = new HashSet<Charges>(0);
	@JsonIgnore
	private Set<Payment> payments = new HashSet<Payment>(0);
	@JsonIgnore
	private UserProfile userProfile;

	public User() {
	}

	public User(Date addmissionDate) {
		this.addmissionDate = addmissionDate;
	}

	public User(String firstName, String middleName, String lastName,
			String fatherName,String userName, String password, String batch, String session,
			Date addmissionDate, Integer status, String updateBy,
			Date updatedOn, Set<Fees> feeses, Set<Charges> chargeses,
			Set<Payment> payments, UserProfile userprofile) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.fatherName=fatherName;
		this.userName = userName;
		this.password = password;
		this.batch = batch;
		this.session = session;
		this.addmissionDate = addmissionDate;
		this.status = status;
		this.updateBy = updateBy;
		this.updatedOn = updatedOn;
		this.feeses = feeses;
		this.chargeses = chargeses;
		this.payments = payments;
		this.userProfile = userprofile;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "firstName", length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "middleName", length = 50)
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "lastName", length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "fatherName", length = 150)
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	@Column(name = "userName", length = 50)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "Batch", length = 25)
	public String getBatch() {
		return this.batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	@Column(name = "session", length = 10)
	public String getSession() {
		return this.session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "addmissionDate", nullable = false, length = 19)
	public Date getAddmissionDate() {
		return this.addmissionDate;
	}

	public void setAddmissionDate(Date addmissionDate) {
		this.addmissionDate = addmissionDate;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "updateBy", length = 50)
	public String getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedOn", length = 19)
	public Date getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Fees> getFeeses() {
		return this.feeses;
	}

	public void setFeeses(Set<Fees> feeses) {
		this.feeses = feeses;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Charges> getChargeses() {
		return this.chargeses;
	}

	public void setChargeses(Set<Charges> chargeses) {
		this.chargeses = chargeses;
	}

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@OneToOne(mappedBy = "user",fetch = FetchType.LAZY, targetEntity = UserProfile.class,
	/* fetch=FetchType.LAZY, */cascade = CascadeType.ALL)
	@JoinColumn(name = "id", nullable = true)
	public UserProfile getUserProfile() {
		return this.userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Transient
	public String getFullName(){
		return (StringUtils.isEmpty(firstName)? "": firstName)+
				(StringUtils.isEmpty(middleName)?"": " "+middleName)+
				(StringUtils.isEmpty(lastName)?"": " "+ lastName);
	}
	
	
	@Column(name = "studentFees", precision = 11)
	public Double getStudentFees() {
		return studentFees;
	}

	public void setStudentFees(Double studentFees) {
		this.studentFees = studentFees;
	}

}
