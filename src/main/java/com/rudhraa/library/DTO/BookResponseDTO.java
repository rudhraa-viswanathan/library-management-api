package com.rudhraa.library.DTO;

public class BookResponseDTO {

    private long id;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private boolean available;

    public BookResponseDTO() {
    }

    public BookResponseDTO(long id, String title, String author,
                           String isbn, String category,
                           boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.available = available;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }
}