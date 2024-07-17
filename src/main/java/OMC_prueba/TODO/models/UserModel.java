package OMC_prueba.TODO.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @NotEmpty(message = "El campo no puede estar vacío")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "El campo no puede estar vacío")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<TodoModel> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoModel> todos) {
        this.todos = todos;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<TodoModel> todos;
}


