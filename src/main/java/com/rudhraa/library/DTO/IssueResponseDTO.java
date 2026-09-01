package com.rudhraa.library.DTO;

import java.time.LocalDate;

public class IssueResponseDTO {

    private long id;
    private Long bookId;
    private Long memberId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public IssueResponseDTO() {
    }

    public IssueResponseDTO(long id,
                            Long bookId,
                            Long memberId,
                            LocalDate issueDate,
                            LocalDate dueDate,
                            LocalDate returnDate) {

        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public long getId() {
        return id;
    }

    public Long getBookId() {
        return bookId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}