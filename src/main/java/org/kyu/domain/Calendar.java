package org.kyu.domain;

import java.awt.Color;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Calendar {
	@Id
	@GeneratedValue
	private Long id;
	
	private Color backgroundColor;
	private String title;
	private Date start;
	private Date end;
	private boolean allDay;
	private boolean editable;
	private boolean durationEditable;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public boolean isAllDay() {
		return allDay;
	}
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public boolean isDurationEditable() {
		return durationEditable;
	}
	public void setDurationEditable(boolean durationEditable) {
		this.durationEditable = durationEditable;
	}
	@Override
	public String toString() {
		return "Calendar [id=" + id + ", backgroundColor=" + backgroundColor + ", title=" + title + ", start=" + start
				+ ", end=" + end + ", allDay=" + allDay + ", editable=" + editable + ", durationEditable="
				+ durationEditable + "]";
	}
	
}