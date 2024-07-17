package OMC_prueba.TODO.controllers;

import OMC_prueba.TODO.models.TodoModel;
import OMC_prueba.TODO.models.UserModel;
import OMC_prueba.TODO.services.TodoService;
import OMC_prueba.TODO.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class TodoController {

    @Autowired
    TodoService todoService;

    // inyectado solo para extraer lista de todos los usuarios metodo: addTodo
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String home(Model model) {

        // página principal con vista por defecto
        int defaultPage = 0;
        int defaultSize = 5;
        String defaultSortField = "title";

        Pageable pageableDefault = PageRequest.of
                (defaultPage,
                defaultSize,
                Sort.by(Sort.Direction.ASC, defaultSortField));

        Page<TodoModel> allPageableTodos = this.todoService.getAllTodos(pageableDefault);
        model.addAttribute("todoList", allPageableTodos.getContent());

        model.addAttribute("totalPages", allPageableTodos.getTotalPages());
        model.addAttribute("currentPage", defaultPage);
        return "home";
    }

    // metodo que se ocupa de filtrar dinámicamente las 3 acciones requeridas:
        // filtrar
        // paginación
        // ordenar por orden ascendente y descendente por cada columna
    @GetMapping("/allFilters")
    public String todos(
            @RequestParam(value = "todoTitle", required = false) String todoTitle,
            @RequestParam(value = "todoUsername", required = false) String todoUsername,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection,
            Model model) {

        // configuracion del objeto en base a los parametros de busqueda por la url
        Pageable pageableTodos = PageRequest.of(page, size, Sort.by(
                sortDirection.equals("asc")
                        ?
                        Sort.Direction.ASC
                        :
                        Sort.Direction.DESC,
                sortField
        ));

        // realizamos la consulta
        Page<TodoModel> pageableFilteredTodos = this.todoService.filter(pageableTodos, todoTitle, todoUsername);

        // extraemos las todos recuperadas
        List<TodoModel> filteredTodos = pageableFilteredTodos.getContent();



        model.addAttribute("todoList", filteredTodos);
        model.addAttribute("totalPages", pageableFilteredTodos.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");
        model.addAttribute("todoTitle", todoTitle);
        model.addAttribute("todoUsername", todoUsername);
        model.addAttribute("todoTitle", todoTitle);
        return "home";
    }


    // método para redirigir a la ruta del formulario de creación de TODO
    @GetMapping("/addTodo")
    public String addTodo(Model model){

        // crear todo vacía
        TodoModel newTodo = new TodoModel();

        // recuperar todos los usuarios
        List<UserModel> allUsers = userService.getAllUsers();

        model.addAttribute("userList", allUsers);
        model.addAttribute("newTodo", newTodo);

        return "addTodoForm";
    }


    // método para redirigir a la ruta del formulario de creación de TODO
    @PostMapping("/addTodo")
    public String addTodoPost(
            @Valid @ModelAttribute("newTodo") TodoModel newTodo,
            BindingResult bindingResult,
            Model model){

        // si hay errores volver al formulario con el fetch de todos los users
        if (bindingResult.hasErrors()){
            // recuperar todos los usuarios
            List<UserModel> allUsers = userService.getAllUsers();
            model.addAttribute("userList", allUsers);
            return "addTodoForm";
        }

        this.todoService.addTodo(newTodo);
        return "redirect:/";
    }


    // método para redirigir a la ruta del formulario de ediciçon con los datos preparados
    @GetMapping("/editTodo")
    public String editTodo(Model model,
                           @RequestParam() Long id){
        // recuperar los datos de la todo seleccionada
        Optional<TodoModel> editableTodo = todoService.getTodoById(id);

        // comprueba que el todo exista antes de redirigir al formulario
        if(editableTodo.isPresent()){
            TodoModel provisional = editableTodo.get();
            System.out.println("iguess");
            System.out.println(provisional.getUser().getUsername());
            provisional.setId(id);
            model.addAttribute("editTodo", provisional);
        return "editTodoForm";
        }
        return "redirect:/";
    }

    // método para editar la tarea si es del usuario logueado
    @PostMapping("/editTodo")
    public String editTodoPost(
            @Valid @ModelAttribute("editTodo") TodoModel editTodo,
            BindingResult bindingResult,
            Model model,
            Principal session){

        // comprobar que el user logueado es el que tiene la todo
        if(!session.getName().equals(editTodo.getUser().getUsername())){
            return "redirect:/?error";
        } else {
            this.todoService.editTodo(editTodo, editTodo.getId());
        }

        // comprobar que no haya errores en la validacion del formulario
        if(bindingResult.hasErrors()){
                model.addAttribute("editTodo", editTodo);
                return "editTodoForm";
        }

        return "redirect:/";
    }

    // endpoint para borrar un todo en base a si es tuya como user logueado o no
    @GetMapping("/deleteTodo")
    public String deleteodo(Model model,
                            @RequestParam() Long id,
                            @RequestParam() String username,
                            Principal session
                            ){

        // comprobacion que es el user logueado el que tiene la todo
        if(!session.getName().equals(username)){
            return "redirect:/?notDeleted";
        }

        // borrar con el servicio
        boolean response = this.todoService.deleteTodo(id);

        // comprobar que se haya borrado
        if(!response){
            return "redirect:/?notDeleted";
        }

        return "redirect:/";
    }
}
