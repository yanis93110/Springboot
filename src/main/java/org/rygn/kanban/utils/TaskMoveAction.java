package org.rygn.kanban.utils;

import lombok.Data;

@Data
public class TaskMoveAction {

	private String action;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
