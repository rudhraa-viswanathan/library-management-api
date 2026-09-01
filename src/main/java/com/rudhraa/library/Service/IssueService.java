package com.rudhraa.library.Service;

import com.rudhraa.library.Model.Books;
import com.rudhraa.library.Model.Issue;
import com.rudhraa.library.Repository.IssueRepository;
import com.rudhraa.library.Repository.MemberRepository;
import com.rudhraa.library.Repository.BookRepository;
import org.springframework.stereotype.Service;

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

        Books book = bookRepository.findById(issue.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is already issued");
        }

        if (!memberRepository.existsById(issue.getMemberId())) {
            throw new RuntimeException("Member not found");
        }

        issue.setIssueDate(LocalDate.now());
        issue.setReturnDate(null);

        book.setAvailable(false);
        bookRepository.save(book);

        return issueRepository.save(issue);
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Optional<Issue> getIssueById(Long id) {
        return issueRepository.findById(id);
    }

    public Issue returnBook(Long id) {

        Issue issue = issueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue record not found"));

        if (issue.getReturnDate() != null) {
            throw new RuntimeException("Book has already been returned");
        }

        Books book = bookRepository.findById(issue.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        book.setAvailable(true);
        bookRepository.save(book);

        issue.setReturnDate(LocalDate.now());

        return issueRepository.save(issue);
    }
}