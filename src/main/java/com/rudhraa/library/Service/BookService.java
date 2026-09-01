package com.rudhraa.library.Service;

import com.rudhraa.library.DTO.BookRequestDTO;
import com.rudhraa.library.DTO.BookResponseDTO;
import com.rudhraa.library.Exception.ResourceNotFoundException;
import com.rudhraa.library.Mapper.BookMapper;
import com.rudhraa.library.Model.Books;
import com.rudhraa.library.Repository.BookRepository;
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

    public List<BookResponseDTO> getAll() {

        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toResponseDTO)
                .toList();
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