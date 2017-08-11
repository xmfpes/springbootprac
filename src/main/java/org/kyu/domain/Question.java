package org.kyu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private Long id;
	
	public Question() {}
	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	
	@ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}

	@Column(nullable=false, length=30)
	private String title;
	
	@Column(nullable=false)
	private String contents;
	
	public void updateQuestion(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}
	public boolean isCurrentWriter(User user) {
		return writer.getId() == user.getId();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}
	
}
