package com.rudhraa.library.Service;
import com.rudhraa.library.Model.Books;
import com.rudhraa.library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }


    public Books addBook(Books books){
        books.setAvailable(true);
        return bookRepository.save(books);
    }

    public List<Books> getAll(){
        return bookRepository.findAll();
    }

    public Optional<Books> getBookByID(Long id){
        return bookRepository.findById(id);
    }

    public Books update(Long id, Books books){
        Books exisitingBook = bookRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Book is not Found"));

        exisitingBook.setTitle(books.getTitle());
        exisitingBook.setAuthor(books.getAuthor());
        exisitingBook.setIsbn(books.getIsbn());
        exisitingBook.setCategory(books.getCategory());

        return bookRepository.save(exisitingBook);
    }

    public String delete(Long id){
        bookRepository.deleteById(id);
        return "Deleted successfully";
    }
}
