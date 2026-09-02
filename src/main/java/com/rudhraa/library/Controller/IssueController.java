package com.rudhraa.library.Controller;

import com.rudhraa.library.DTO.IssueRequestDTO;
import com.rudhraa.library.DTO.IssueResponseDTO;
import com.rudhraa.library.Service.IssueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @PostMapping
    public ResponseEntity<IssueResponseDTO> issueBook(
            @Valid @RequestBody IssueRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(issueService.issueBook(dto));
    }

    @GetMapping
    public ResponseEntity<List<IssueResponseDTO>> getAll() {

        return ResponseEntity.ok(issueService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IssueResponseDTO> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(issueService.getById(id));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<IssueResponseDTO> returnBook(
            @Valid @PathVariable Long id) {

        return ResponseEntity.ok(issueService.returnBook(id));
    }
}