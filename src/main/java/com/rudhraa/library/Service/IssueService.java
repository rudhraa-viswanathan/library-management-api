package com.rudhraa.library.Service;

import com.rudhraa.library.DTO.IssueRequestDTO;
import com.rudhraa.library.DTO.IssueResponseDTO;
import com.rudhraa.library.Exception.BookAlreadyReturnedException;
import com.rudhraa.library.Exception.BookNotAvailableException;
import com.rudhraa.library.Exception.ResourceNotFoundException;
import com.rudhraa.library.Mapper.IssueMapper;
import com.rudhraa.library.Model.Books;
import com.rudhraa.library.Model.Issue;
import com.rudhraa.library.Model.Members;
import com.rudhraa.library.Repository.BookRepository;
import com.rudhraa.library.Repository.IssueRepository;
import com.rudhraa.library.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public IssueResponseDTO issueBook(IssueRequestDTO dto) {

        Books book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));

        Members member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not found"));

        if (!book.isAvailable()) {
            throw new BookNotAvailableException(
                    "Book is already issued");
        }

        Issue issue = new Issue();

        issue.setBook(book);
        issue.setMember(member);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(dto.getDueDate());
        issue.setReturnDate(null);

        book.setAvailable(false);
        bookRepository.save(book);

        Issue savedIssue = issueRepository.save(issue);

        return IssueMapper.toResponseDTO(savedIssue);
    }

    public List<IssueResponseDTO> getAll() {

        return issueRepository.findAll()
                .stream()
                .map(IssueMapper::toResponseDTO)
                .toList();
    }

    public IssueResponseDTO getById(Long id) {

        Issue issue = issueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Issue not found"));

        return IssueMapper.toResponseDTO(issue);
    }

    public IssueResponseDTO returnBook(Long id) {

        Issue issue = issueRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Issue not found"));

        if (issue.getReturnDate() != null) {
            throw new BookAlreadyReturnedException(
                    "Book has already been returned");
        }

        issue.setReturnDate(LocalDate.now());

        Books book = issue.getBook();
        book.setAvailable(true);

        bookRepository.save(book);

        Issue returnedIssue = issueRepository.save(issue);

        return IssueMapper.toResponseDTO(returnedIssue);
    }
}