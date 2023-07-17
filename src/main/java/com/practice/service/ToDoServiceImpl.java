package com.practice.service;

import com.practice.dto.ToDoDto;
import com.practice.exception.ResourceNotFound;
import com.practice.model.ToDo;
import com.practice.repository.ToDoRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ToDoServiceImpl implements ToDoService{
    private ToDoRepository toDoRepository;
    ModelMapper modelMapper;
    @Override
    public ToDoDto createToDo(ToDoDto toDoDto) {
       ToDo toDo = modelMapper.map(toDoDto, ToDo.class);
         ToDo savedToDo = toDoRepository.save(toDo);
         ToDoDto returnToDo = modelMapper.map(savedToDo, ToDoDto.class);
         return returnToDo;
    }

    @Override
    public ToDoDto getToDoById(Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("ToDo not find with id: " + id));
        return modelMapper.map(toDo, ToDoDto.class);
    }

    @Override
    public List<ToDoDto> getToDos() {
        List<ToDo> toDo = toDoRepository.findAll();
        return toDo.stream().map((toDo1 -> modelMapper.map(toDo1, ToDoDto.class)))
                .collect(Collectors.toList());
    }

    @Override
    public ToDoDto updateToDo(ToDoDto toDoDto, Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("ToDo does not exist with id: " + id));
        toDo.setTitle(toDoDto.getTitle());
        toDo.setDescription(toDoDto.getDescription());
        toDo.setCompleted(toDoDto.isCompleted());
        ToDo updatedToDo = toDoRepository.save(toDo);
        return modelMapper.map(updatedToDo, ToDoDto.class);
    }

    @Override
    public void deleteToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("ToDo not found with id: " + id));
        toDoRepository.deleteById(id);
    }

    @Override
    public ToDoDto completeToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("ToDo does not exist with id: " + id));
        toDo.setCompleted(Boolean.TRUE);
        ToDo updatedToDo = toDoRepository.save(toDo);
        return modelMapper.map(updatedToDo, ToDoDto.class);
    }

    @Override
    public ToDoDto inCompleteToDo(Long id) {
        ToDo toDo = toDoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("ToDo does not exist with id: " + id));
        toDo.setCompleted(Boolean.FALSE);
        ToDo updatedToDo = toDoRepository.save(toDo);
        return modelMapper.map(updatedToDo, ToDoDto.class);
    }
}
