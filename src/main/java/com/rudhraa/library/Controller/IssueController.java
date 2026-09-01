package com.rudhraa.library.Controller;

import com.rudhraa.library.Model.Issue;
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
    public ResponseEntity<?> issueBook(@RequestBody Issue issue) {
        return ResponseEntity.ok(issueService.issueBook(issue));
    }

    @GetMapping
    public ResponseEntity<?> getAllIssues() {
        return ResponseEntity.ok(issueService.getAllIssues());
    }

    @GetMapping("/getIssue")
    public ResponseEntity<?> getIssue(@RequestParam Long id) {

        return ResponseEntity.ok(issueService.getIssueById(id));
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnBook(@RequestParam Long id) {
        return ResponseEntity.ok(issueService.returnBook(id));
    }
}