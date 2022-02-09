package org.rygn.kanban.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
public class Task {

	private @Id @GeneratedValue Long id;
	
	@NotNull(message = "Title cannot be null")
	@NotEmpty(message = "Title cannot be empty")
	private String title;

	@NotNull(message = "NbHoursForecast cannot be null")
	@Min(value = 0, message = "NbHoursForecast should not be less than 0")
    @Max(value = 144, message = "NbHoursForecast should not be greater than 144")
	private Integer nbHoursForecast;
	
	@NotNull(message = "NbHoursReal cannot be null")
	@Min(value = 0, message = "NbHoursReal should not be less than 0")
    @Max(value = 144, message = "NbHoursReal should not be greater than 144")
	private Integer nbHoursReal;
	
	private LocalDate created;
	
	@ManyToOne
	@Valid
	private TaskType type;
	
	@ManyToOne
	private TaskStatus status;	
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JsonIgnoreProperties({"password", "startContract", "tasks"})
	@EqualsAndHashCode.Exclude
	@NotEmpty(message = "Developers cannot be empty")
    private Set<Developer> developers;
	
	@OneToMany(mappedBy="task", cascade={CascadeType.ALL}, orphanRemoval=true)
	@JsonIgnoreProperties("task")
	@EqualsAndHashCode.Exclude
	private Set<ChangeLog> changeLogs;
	
	public Task() {
		
		this.developers = new HashSet<>();
		
		this.changeLogs = new HashSet<>();
	}
	
	public void addDeveloper(Developer developer) {
		
		developer.getTasks().add(this);
		
		this.developers.add(developer);
	}
	
	public void addChangeLog(ChangeLog changeLog) {
		
		changeLog.setTask(this);
		
		this.changeLogs.add(changeLog);
	}

	public void clearChangeLogs() {
		
		for (ChangeLog changeLog :  this.changeLogs) {
			
			changeLog.setTask(null);
		}
		
		this.changeLogs.clear();
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

	public Integer getNbHoursForecast() {
		return nbHoursForecast;
	}

	public void setNbHoursForecast(Integer nbHoursForecast) {
		this.nbHoursForecast = nbHoursForecast;
	}

	public Integer getNbHoursReal() {
		return nbHoursReal;
	}

	public void setNbHoursReal(Integer nbHoursReal) {
		this.nbHoursReal = nbHoursReal;
	}

	public LocalDate getCreated() {
		return created;
	}

	public void setCreated(LocalDate created) {
		this.created = created;
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Set<Developer> getDevelopers() {
		return developers;
	}

	public void setDevelopers(Set<Developer> developers) {
		this.developers = developers;
	}

	public Set<ChangeLog> getChangeLogs() {
		return changeLogs;
	}

	public void setChangeLogs(Set<ChangeLog> changeLogs) {
		this.changeLogs = changeLogs;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", nbHoursForecast=" + nbHoursForecast + ", nbHoursReal="
				+ nbHoursReal + ", created=" + created + ", type=" + type + ", status=" + status + ", developers="
				+ developers + ", changeLogs=" + changeLogs + "]";
	}

}


