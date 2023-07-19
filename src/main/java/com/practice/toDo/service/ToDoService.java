package com.practice.toDo.service;

import com.practice.toDo.dto.ToDoDto;

import java.util.List;

public interface ToDoService {

     ToDoDto createToDo(ToDoDto toDoDto);
     ToDoDto getToDoById(Long id);
     List<ToDoDto> getToDos();
     ToDoDto updateToDo(ToDoDto toDoDto, Long id);
     void deleteToDo(Long id);
     ToDoDto completeToDo(Long id);
     ToDoDto inCompleteToDo(Long id);


}
