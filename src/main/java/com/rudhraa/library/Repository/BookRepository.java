package com.rudhraa.library.Repository;

import com.rudhraa.library.Model.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Books, Long> {

    Page<Books> searchByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Books> searchByCategoryIgnoreCase(String category, Pageable pageable);
}
