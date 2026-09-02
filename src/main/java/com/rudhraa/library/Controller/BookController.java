package com.rudhraa.library.Controller;

import com.rudhraa.library.DTO.BookRequestDTO;
import com.rudhraa.library.DTO.BookResponseDTO;
import com.rudhraa.library.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
            @Valid @RequestBody BookRequestDTO dto) {

        return ResponseEntity.ok(bookService.addBook(dto));
    }

    @GetMapping
    public ResponseEntity<Page<BookResponseDTO>> showAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(bookService.getAll(PageRequest.of(page, size)));
    }

    @GetMapping("/searchTitle")
    public ResponseEntity<Page<BookResponseDTO>> searchByTitle(@RequestParam(required = false) String title,
                                                               @RequestParam(required = false, defaultValue = "0") int pageNo,
                                                               @RequestParam(required = false, defaultValue = "5") int pageSize){
        return ResponseEntity.ok(bookService.searchByTitle(title, PageRequest.of(pageNo, pageSize)));
    }


    @GetMapping("/searchCategory")
    public ResponseEntity<Page<BookResponseDTO>> searchByCategory(@RequestParam(required = false) String category,
                                                               @RequestParam(required = false, defaultValue = "0") int pageNo,
                                                               @RequestParam(required = false, defaultValue = "5") int pageSize){
        return ResponseEntity.ok(bookService.searchByCategory(category, PageRequest.of(pageNo, pageSize)));
    }

    @GetMapping("/getBook")
    public ResponseEntity<BookResponseDTO> showBook(
            @RequestParam Long id) {

        return ResponseEntity.ok(bookService.getBookByID(id));
    }

    @PutMapping("/update")
    public ResponseEntity<BookResponseDTO> update(
            @RequestParam Long id,
           @Valid @RequestBody BookRequestDTO dto) {

        return ResponseEntity.ok(bookService.update(id, dto));
    }

    @DeleteMapping("/del")
    public ResponseEntity<BookResponseDTO> delete(
            @RequestParam Long id) {

        return ResponseEntity.ok(bookService.delete(id));
    }
}