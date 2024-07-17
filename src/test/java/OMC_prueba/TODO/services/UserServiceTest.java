package OMC_prueba.TODO.services;

import OMC_prueba.TODO.models.UserModel;
import OMC_prueba.TODO.respositories.IUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    IUserRepository userRepository;

    @Test
    void testGetUserByUsername(){
        UserModel userTest = new UserModel();
        userTest.setUsername("alfredo");

        when(userRepository.findByUsername("alfredo")).thenReturn(userTest);

        UserModel modelReturned = userService.getUserByUsername("alfredo");

        assertEquals("alfredo", modelReturned.getUsername());
    }
}
