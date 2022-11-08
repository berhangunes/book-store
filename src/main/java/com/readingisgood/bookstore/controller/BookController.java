package com.readingisgood.bookstore.controller;

import com.readingisgood.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request.AddBookRequest;
import response.AddBookDto;
import response.GetAllBooksDto;
import response.GetBookByIdDto;

import java.util.List;


@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @GetMapping()
    public ResponseEntity<List<GetAllBooksDto>> getAllBooks(){
      return ResponseEntity.ok(bookService.getBooks());
    }
    @GetMapping("/get-book-by-id")
    public ResponseEntity<GetBookByIdDto> getBookById(@RequestParam("bookId") Long bookId){
        return ResponseEntity.ok(bookService.findBookById(bookId));
    }
    @PostMapping("/add-book")
    public ResponseEntity <AddBookDto> addBook(@RequestBody AddBookRequest addBookRequest){
        return ResponseEntity.ok(bookService.addBook(addBookRequest.getName(),
                addBookRequest.getAuthor(),
                addBookRequest.getPrice(),
                addBookRequest.getStock()));
    }
}
