package com.rudhraa.library.Service;

import com.rudhraa.library.DTO.BookRequestDTO;
import com.rudhraa.library.DTO.BookResponseDTO;
import com.rudhraa.library.Exception.ResourceNotFoundException;
import com.rudhraa.library.Mapper.BookMapper;
import com.rudhraa.library.Model.Books;
import com.rudhraa.library.Repository.BookRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDTO addBook(BookRequestDTO dto) {

        Books book = BookMapper.toEntity(dto);

        book.setAvailable(true);

        Books savedBook = bookRepository.save(book);

        return BookMapper.toResponseDTO(savedBook);
    }

    public Page<BookResponseDTO> getAll(Pageable pageable) {

        return bookRepository.findAll(pageable)
                .map(BookMapper::toResponseDTO);
    }


    public Page<BookResponseDTO> searchByTitle(String title, Pageable pageable) {

        return bookRepository.searchByTitleContainingIgnoreCase(title, pageable)
                .map(BookMapper::toResponseDTO);

    }


    public Page<BookResponseDTO> searchByCategory(String category, Pageable pageable) {

        return bookRepository.searchByCategoryIgnoreCase(category, pageable)
                .map(BookMapper::toResponseDTO);

    }

    public BookResponseDTO getBookByID(Long id) {

        Books book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));

        return BookMapper.toResponseDTO(book);
    }

    public BookResponseDTO update(Long id, BookRequestDTO dto) {

        Books book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setCategory(dto.getCategory());

        Books updatedBook = bookRepository.save(book);

        return BookMapper.toResponseDTO(updatedBook);
    }

    public BookResponseDTO delete(Long id) {

        Books book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));

        bookRepository.delete(book);

        return BookMapper.toResponseDTO(book);
    }

}