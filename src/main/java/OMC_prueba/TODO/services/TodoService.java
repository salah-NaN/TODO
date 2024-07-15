package OMC_prueba.TODO.services;

import OMC_prueba.TODO.models.TodoModel;
import OMC_prueba.TODO.respositories.ITodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    ITodoRepository todoRepository;

    public List<TodoModel> getAllTodos() {
        return this.todoRepository.findAll();
    }

    public List<TodoModel> filter(String title, String username) {
        if(username == null || username.isEmpty()){
            return this.todoRepository.findByTitleContaining(title);
        }
        return this.todoRepository.findByTitleContainingAndUserUsernameLike(title, username);
    }

    public Page<TodoModel> pagination(int page, int size){
        Pageable pageOfTodos = PageRequest.of(page -  1, size);
        return this.todoRepository.findAll(pageOfTodos);
    }
}
