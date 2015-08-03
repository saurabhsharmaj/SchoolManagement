package com.sfm.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Entity
@Table(name = "faculty", catalog = "sfm")
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Faculty {
	
	private Integer id;
	private String facultyName;
	private Double facultyHourlyRate;
	private String subject;
	private String updateBy;
	private Date updatedOn;
	
	@JsonIgnore
	private Set<Attendance> attendance = new HashSet<Attendance>(0);
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "facultyName", length = 50)
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	
	@Column(name = "facultyHourlyRate", precision = 11)
	public Double getFacultyHourlyRate() {
		return facultyHourlyRate;
	}
	public void setFacultyHourlyRate(Double facultyHourlyRate) {
		this.facultyHourlyRate = facultyHourlyRate;
	}
	
	@Column(name = "subject", length = 50)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name = "updateBy", length = 50)
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedOn", length = 19)
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
	public Set<Attendance> getAttendance() {
		return attendance;
	}
	public void setAttendance(Set<Attendance> attendance) {
		this.attendance = attendance;
	}	
	
	
}
