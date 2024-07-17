package OMC_prueba.TODO.respositories;

import OMC_prueba.TODO.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
}
