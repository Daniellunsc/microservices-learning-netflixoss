package com.luna.userservice;

import com.luna.userservice.model.Book;
import com.luna.userservice.services.BookService;
import com.luna.userservice.model.User;
import com.luna.userservice.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @GetMapping("/users")
    public List<User> getAllUsers () {
        List<User> users = userRepository.findAll();
        List<User> usersWithBook = users.stream().map(user -> {
            List<Book> userBooks = bookService.getBooks();
            user.setBooks(userBooks);
            return user;
        }).collect(Collectors.toList());
        return usersWithBook;
    }
}
