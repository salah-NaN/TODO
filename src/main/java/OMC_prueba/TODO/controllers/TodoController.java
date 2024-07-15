package OMC_prueba.TODO.controllers;

import OMC_prueba.TODO.models.TodoModel;
import OMC_prueba.TODO.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/")
    public String home(Model model){

        List<TodoModel> asdf =  this.todoService.getAllTodos();
        model.addAttribute("todoList", asdf);
        return "home";
    }

    @GetMapping("/filter")
    public String filterTodos(@RequestParam(value = "todoTitle", required = false) String todoTitle,
                              @RequestParam(value = "todoUsername", required = false) String todoUsername,
                              Model model) {
        List<TodoModel> filteredTodos = this.todoService.filter(todoTitle, todoUsername);
        model.addAttribute("todoList", filteredTodos);
        return "home";
    }

    @GetMapping("/pagination")
    public String pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "size", defaultValue = "5") int size,
                       Model model) {
        // lista vacía para guardar los todos finales
        /*List<TodoModel> pagedTodos = new LinkedList<TodoModel>();

        Page<TodoModel> returnedPage = this.todoService.pagination(page, size);*/


        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("totalPages", 14);
        return "home";
    }



    @GetMapping("/allFilters")
    public String todos(
            @RequestParam(required = false) String todoTitle,
            @RequestParam(required = false) String todoUsername,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "title") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection,

            Model model) {


        System.out.println("quequieres");
        System.out.println(todoTitle);
        System.out.println(todoUsername);
        System.out.println(page);
        System.out.println(size);
        System.out.println(sortField);
        System.out.println(sortDirection);

        List<TodoModel> filteredTodos = this.todoService.getAllTodos();


        model.addAttribute("todoList", filteredTodos);
        model.addAttribute("totalPages", 15);
        model.addAttribute("currentPage", page);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", "asc".equals(sortDirection) ? "desc" : "asc");
        model.addAttribute("todoTitle", todoTitle);
        model.addAttribute("todoUsername", todoUsername);



        return "home";
    }

}
