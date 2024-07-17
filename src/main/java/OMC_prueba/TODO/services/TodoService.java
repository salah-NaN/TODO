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

    public Page<TodoModel> getAllTodos(Pageable pageable) {
        return this.todoRepository.findAll(pageable);
    }

    public Page<TodoModel> filter(Pageable pageable, String title, String username) {
        if ((username == null || username.isEmpty()) && (title == null || title.isEmpty())) {
            return this.todoRepository.findAll(pageable);
        } else if (username == null || username.isEmpty()) {
            return this.todoRepository.findByTitleContaining(title, pageable);
        } else {
            return this.todoRepository.findByTitleContainingAndUserUsernameLike(title, username, pageable);
        }
    }

    public void addTodo(TodoModel newTodo) {
        this.todoRepository.save(newTodo);
    }

    public Optional<TodoModel> getTodoById(Long id) {
        return this.todoRepository.findById(id);
    }

    public String editTodo(TodoModel editTodo, Long id) {
        // aunque la acción de editar ya revisa que el todo está creado, añado una capa
        // de seguridad más para asegurar de que no se haya borrado
        Optional<TodoModel> editableTodo = this.todoRepository.findById(id);

        if (editableTodo.isPresent()) {
            TodoModel provisionalTodo = editableTodo.get();
            provisionalTodo.setTitle(editTodo.getTitle());
            provisionalTodo.setCompleted(editTodo.isCompleted());
            this.todoRepository.save(provisionalTodo);
            return "Todo editada";
        } else {
            throw new RuntimeException("Todo con id " + id + "no existe");
        }
    }

    public boolean deleteTodo(Long id) {
        try {
            this.todoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
