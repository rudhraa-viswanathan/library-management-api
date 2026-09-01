package com.rudhraa.library.Mapper;

import com.rudhraa.library.DTO.IssueResponseDTO;
import com.rudhraa.library.Model.Issue;

public class IssueMapper {

    public static IssueResponseDTO toResponseDTO(Issue issue) {

        return new IssueResponseDTO(
                issue.getId(),
                issue.getBook().getId(),
                issue.getMember().getId(),
                issue.getIssueDate(),
                issue.getDueDate(),
                issue.getReturnDate()
        );
    }
}