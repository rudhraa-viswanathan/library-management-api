package com.rudhraa.library.Controller;

import com.rudhraa.library.DTO.BookRequestDTO;
import com.rudhraa.library.DTO.BookResponseDTO;
import com.rudhraa.library.Service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(
            @RequestBody BookRequestDTO dto) {

        return ResponseEntity.ok(bookService.addBook(dto));
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> showAll() {

        return ResponseEntity.ok(bookService.getAll());
    }

    @GetMapping("/getBook")
    public ResponseEntity<BookResponseDTO> showBook(
            @RequestParam Long id) {

        return ResponseEntity.ok(bookService.getBookByID(id));
    }

    @PutMapping("/update")
    public ResponseEntity<BookResponseDTO> update(
            @RequestParam Long id,
            @RequestBody BookRequestDTO dto) {

        return ResponseEntity.ok(bookService.update(id, dto));
    }

    @DeleteMapping("/del")
    public ResponseEntity<BookResponseDTO> delete(
            @RequestParam Long id) {

        return ResponseEntity.ok(bookService.delete(id));
    }
}