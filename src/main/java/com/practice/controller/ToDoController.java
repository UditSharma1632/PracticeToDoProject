package com.practice.controller;

import com.practice.dto.ToDoDto;
import com.practice.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("MyToDos")
public class ToDoController {
    ToDoService toDoService;

    @PostMapping("create")
    public ResponseEntity<ToDoDto> createToDo(@RequestBody  ToDoDto toDoDto){
        ToDoDto savedToDo = toDoService.createToDo(toDoDto);
        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<ToDoDto>> getAllToDos(){
        List<ToDoDto> toDoDto = toDoService.getToDos();
        return ResponseEntity.ok(toDoDto);
    }

    @GetMapping("getById")
    public ResponseEntity<ToDoDto> getToDoById(@RequestParam Long id){
            ToDoDto returnedToDo = toDoService.getToDoById(id);
            return ResponseEntity.ok(returnedToDo);
    }

    @PutMapping("updateToDo")
    public ResponseEntity<ToDoDto> updateToDo(@RequestBody ToDoDto toDoDto, @RequestParam Long id){
        ToDoDto updatedToDo = toDoService.updateToDo(toDoDto, id);
        return ResponseEntity.ok(updatedToDo);
    }

    @DeleteMapping("deleteToDo")
    public ResponseEntity<String> deleteToDo(@RequestParam Long id){
        toDoService.deleteToDo(id);
        return ResponseEntity.ok("ToDo deleted");
    }

    @PatchMapping("{id}/completed")
    public ResponseEntity<ToDoDto> completeToDo(@PathVariable("id") Long id){
        ToDoDto toDoDto = toDoService.completeToDo(id);
        return ResponseEntity.ok(toDoDto);
    }
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<ToDoDto> inCompleteToDo(@PathVariable("id") Long id){
        ToDoDto toDoDto = toDoService.inCompleteToDo(id);
        return ResponseEntity.ok(toDoDto);
    }
}
