package com.rudhraa.library.Controller;
import com.rudhraa.library.Model.Books;
import com.rudhraa.library.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Books books){
        return ResponseEntity.ok(bookService.addBook(books));
    }

   @GetMapping
    public ResponseEntity<?> showAll(){
        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/getBook")
    public ResponseEntity<?> showAll(@RequestParam Long id){
        return ResponseEntity.ok(bookService.getBookByID(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long id, @RequestBody Books books){
        return ResponseEntity.ok(bookService.update(id, books));
    }

    @DeleteMapping("/del")
    public ResponseEntity<?> delete(@RequestParam Long id){
        return ResponseEntity.ok(bookService.delete(id));
    }
}
