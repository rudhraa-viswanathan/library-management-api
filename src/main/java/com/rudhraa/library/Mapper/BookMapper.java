package com.rudhraa.library.Mapper;

import com.rudhraa.library.DTO.BookRequestDTO;
import com.rudhraa.library.DTO.BookResponseDTO;
import com.rudhraa.library.Model.Books;

public class BookMapper {

    public static Books toEntity(BookRequestDTO dto) {

        Books book = new Books();

        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setCategory(dto.getCategory());

        return book;
    }

    public static BookResponseDTO toResponseDTO(Books book) {

        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getIsbn(),
                book.getCategory(),
                book.isAvailable()
        );
    }
}