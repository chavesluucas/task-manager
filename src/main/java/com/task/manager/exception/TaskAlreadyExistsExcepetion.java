package com.task.manager.exception;

public class TaskAlreadyExistsExcepetion extends RuntimeException {

  public TaskAlreadyExistsExcepetion() {
    super();
  }

  public TaskAlreadyExistsExcepetion(String message) {
    super(message);
  }

}
