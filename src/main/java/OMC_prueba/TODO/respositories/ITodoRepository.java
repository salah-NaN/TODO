package OMC_prueba.TODO.respositories;

import OMC_prueba.TODO.models.TodoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITodoRepository extends JpaRepository<TodoModel, Long> {
    List<TodoModel> findByTitleContainingAndUserUsernameLike(String title, String username);
    List<TodoModel> findByTitleContaining(String title);
    Page<TodoModel> findAll(Pageable pageable);

}
