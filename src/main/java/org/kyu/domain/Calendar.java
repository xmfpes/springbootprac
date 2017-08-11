package org.kyu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Calendar {
	@Id
	@GeneratedValue
	private Long id;
	
	private String backgroundColor;
	private String borderColor;
	
	@ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}
	public void updateDate(Calendar calendar) {
		this.start = calendar.getStart();
		this.end = calendar.getEnd();
	}
	public String getBorderColor() {
		return borderColor;
	}
	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}
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
	public String getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(String backgroundColor) {
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
		return "Calendar [id=" + id + ", backgroundColor=" + backgroundColor + ", borderColor=" + borderColor
				+ ", writer=" + writer + ", title=" + title + ", start=" + start + ", end=" + end + ", allDay=" + allDay
				+ ", editable=" + editable + ", durationEditable=" + durationEditable + "]";
	}
	
}
