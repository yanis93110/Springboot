package org.rygn.kanban.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class TaskStatus {

	private @Id Long id;
	
	private String label;

	public TaskStatus(Long id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public TaskStatus() {		
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
