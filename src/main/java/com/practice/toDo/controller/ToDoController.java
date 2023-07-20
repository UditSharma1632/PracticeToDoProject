package com.practice.toDo.controller;

import com.practice.toDo.dto.ToDoDto;
import com.practice.toDo.service.ToDoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("MyToDos")
public class ToDoController {
    ToDoService toDoService;

    // pre authorize is used to implement method level security to access via roles
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("create")
    public ResponseEntity<ToDoDto> createToDo(@RequestBody  ToDoDto toDoDto){
        ToDoDto savedToDo = toDoService.createToDo(toDoDto);
        return new ResponseEntity<>(savedToDo, HttpStatus.CREATED);
    }

    // pre authorize is used to implement method level security to access via roles
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("getAll")
    public ResponseEntity<List<ToDoDto>> getAllToDos(){
        List<ToDoDto> toDoDto = toDoService.getToDos();
        return ResponseEntity.ok(toDoDto);
    }

    // pre authorize is used to implement method level security to access via roles
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("getById")
    public ResponseEntity<ToDoDto> getToDoById(@RequestParam Long id){
            ToDoDto returnedToDo = toDoService.getToDoById(id);
            return ResponseEntity.ok(returnedToDo);
    }

    // pre authorize is used to implement method level security to access via roles
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("updateToDo")
    public ResponseEntity<ToDoDto> updateToDo(@RequestBody ToDoDto toDoDto, @RequestParam Long id){
        ToDoDto updatedToDo = toDoService.updateToDo(toDoDto, id);
        return ResponseEntity.ok(updatedToDo);
    }

    // pre authorize is used to implement method level security to access via roles
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("deleteToDo")
    public ResponseEntity<String> deleteToDo(@RequestParam Long id){
        toDoService.deleteToDo(id);
        return ResponseEntity.ok("ToDo deleted");
    }

    // pre authorize is used to implement method level security to access via roles
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/completed")
    public ResponseEntity<ToDoDto> completeToDo(@PathVariable("id") Long id){
        ToDoDto toDoDto = toDoService.completeToDo(id);
        return ResponseEntity.ok(toDoDto);
    }

    // pre authorize is used to implement method level security to access via roles
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("{id}/incomplete")
    public ResponseEntity<ToDoDto> inCompleteToDo(@PathVariable("id") Long id){
        ToDoDto toDoDto = toDoService.inCompleteToDo(id);
        return ResponseEntity.ok(toDoDto);
    }
}
