package org.kyu.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Male {
	
	private String userId;
	private Date birth;
	private String height;
	private String job;
	private String location;
	private List<String> hobby;
	private boolean isfulfilled;
	
	public void updateMale(Male male) {
		this.userId = male.getUserId();
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<String> getHobby() {
		return hobby;
	}
	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}
	public boolean isIsfulfilled() {
		return isfulfilled;
	}
	public void setIsfulfilled(boolean isfulfilled) {
		this.isfulfilled = isfulfilled;
	}
	@Override
	public String toString() {
		return "Male [userId=" + userId + ", birth=" + birth + ", height=" + height + ", job=" + job + ", location="
				+ location + ", hobby=" + hobby + ", isfulfilled=" + isfulfilled + "]";
	}
}
