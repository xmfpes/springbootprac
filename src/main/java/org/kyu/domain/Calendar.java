package org.kyu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Calendar {
	@Id
	@GeneratedValue
	private Long id;
	
	private int backgroundColor;
	private String title;
	private Date start;
	private Date end;
	private boolean allDay;
}
