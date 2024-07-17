package OMC_prueba.TODO.services;

import OMC_prueba.TODO.models.UserModel;
import OMC_prueba.TODO.respositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public List<UserModel> getAllUsers(){
        return this.userRepository.findAll();
    }

    public UserModel getUserByUsername(String username){
        return this.userRepository.findByUsername(username);
    }

    public void saveUser(UserModel user){
        // validacion en capa interna del back-end
        if(!user.getUsername().isEmpty() && !user.getUsername().isEmpty()){
            this.userRepository.save(user);
        } else {
            throw new RuntimeException("Los campos de 'username' y 'password' no pueden estar vacíos");
        }
    }

}
