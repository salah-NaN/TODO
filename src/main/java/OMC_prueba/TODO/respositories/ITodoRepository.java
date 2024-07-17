package OMC_prueba.TODO.respositories;

import OMC_prueba.TODO.models.TodoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITodoRepository extends JpaRepository<TodoModel, Long> {
    Page<TodoModel> findByTitleContainingAndUserUsernameLike(String title, String username, Pageable pageable);
    Page<TodoModel> findByTitleContaining(String title, Pageable pageable);
    Page<TodoModel> findAll(Pageable pageable);

}
