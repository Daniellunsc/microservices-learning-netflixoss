package com.luna.userservice.services;

import com.luna.userservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name="book-service", fallback = BookService.BookServiceFallBack.class)
public interface BookService {
    @GetMapping("/books")
    public List<Book> getBooks();

    @Component
    class BookServiceFallBack implements BookService {

        @Override
        public List<Book> getBooks() {
            return new ArrayList<>();
        }
    }
}

