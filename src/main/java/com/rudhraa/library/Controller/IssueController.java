package com.rudhraa.library.Controller;

import com.rudhraa.library.DTO.IssueRequestDTO;
import com.rudhraa.library.DTO.IssueResponseDTO;
import com.rudhraa.library.Service.IssueService;
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
            @RequestBody IssueRequestDTO dto) {

        return ResponseEntity.ok(issueService.issueBook(dto));
    }

    @GetMapping
    public ResponseEntity<List<IssueResponseDTO>> getAll() {

        return ResponseEntity.ok(issueService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<IssueResponseDTO> getById(
            @RequestParam Long id) {

        return ResponseEntity.ok(issueService.getById(id));
    }

    @PostMapping("/return")
    public ResponseEntity<IssueResponseDTO> returnBook(
            @RequestParam Long id) {

        return ResponseEntity.ok(issueService.returnBook(id));
    }
}