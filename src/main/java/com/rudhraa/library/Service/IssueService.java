package com.rudhraa.library.Service;

import com.rudhraa.library.Model.Books;
import com.rudhraa.library.Model.Issue;
import com.rudhraa.library.Model.Members;
import com.rudhraa.library.Repository.BookRepository;
import com.rudhraa.library.Repository.IssueRepository;
import com.rudhraa.library.Repository.MemberRepository;
import org.springframework.stereotype.Service;
import com.rudhraa.library.Exception.BookAlreadyReturnedException;
import com.rudhraa.library.Exception.BookNotAvailableException;
import com.rudhraa.library.Exception.ResourceNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public IssueService(IssueRepository issueRepository,
                        BookRepository bookRepository,
                        MemberRepository memberRepository) {

        this.issueRepository = issueRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public Issue issueBook(Issue issue) {

        Books book = bookRepository.findById(issue.getBook().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Members member = memberRepository.findById(issue.getMember().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book is already issued");
        }

        issue.setBook(book);
        issue.setMember(member);
        issue.setIssueDate(LocalDate.now());
        issue.setReturnDate(null);

        book.setAvailable(false);
        bookRepository.save(book);

        return issueRepository.save(issue);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue getIssueById(Long id) {
        return issueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));
    }

    public Issue returnBook(Long id) {

        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Issue record not found"));

        if (issue.getReturnDate() != null) {
            throw new BookAlreadyReturnedException("Book has already been returned");
        }

        Books book = issue.getBook();

        book.setAvailable(true);
        bookRepository.save(book);

        issue.setReturnDate(LocalDate.now());

        return issueRepository.save(issue);
    }
}