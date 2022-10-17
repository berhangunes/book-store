package com.readingisgood.bookstore.controller;

import com.readingisgood.bookstore.model.response.AddBookResponse;
import com.readingisgood.bookstore.model.response.GetAllBooksResponse;
import com.readingisgood.bookstore.model.response.GetBookByIdResponse;
import com.readingisgood.bookstore.model.request.AddBookRequest;
import com.readingisgood.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    @RequestMapping("/getbooks")
    public ResponseEntity<List<GetAllBooksResponse>> getAllBooks(){
      return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping
    @RequestMapping("/getbookbyid")
    public ResponseEntity<GetBookByIdResponse> getBookById(@RequestParam("bookId") Long bookId){
        return ResponseEntity.ok(bookService.findBookById(bookId));
    }


    @PostMapping
    @RequestMapping("/addbook")
    public ResponseEntity <AddBookResponse> addBook(@RequestBody AddBookRequest addBookRequest){
        return ResponseEntity.ok(bookService.addBook(addBookRequest.getName(),
                addBookRequest.getAuthor(),
                addBookRequest.getPrice(),
                addBookRequest.getStock()));
    }
}
