package com.readingisgood.bookstore.controller;

import com.readingisgood.bookstore.model.response.AddBookDto;
import com.readingisgood.bookstore.model.response.GetAllBooksDto;
import com.readingisgood.bookstore.model.response.GetBookByIdDto;
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
    public ResponseEntity<List<GetAllBooksDto>> getAllBooks(){
      return ResponseEntity.ok(bookService.getBooks());
    }

    @GetMapping
    @RequestMapping("/getbookbyid")
    public ResponseEntity<GetBookByIdDto> getBookById(@RequestParam("bookId") Long bookId){
        return ResponseEntity.ok(bookService.findBookById(bookId));
    }


    @PostMapping
    @RequestMapping("/addbook")
    public ResponseEntity <AddBookDto> addBook(@RequestBody AddBookRequest addBookRequest){
        return ResponseEntity.ok(bookService.addBook(addBookRequest.getName(),
                addBookRequest.getAuthor(),
                addBookRequest.getPrice(),
                addBookRequest.getStock()));
    }
}
