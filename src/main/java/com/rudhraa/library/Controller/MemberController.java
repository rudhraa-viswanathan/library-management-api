package com.rudhraa.library.Controller;

import com.rudhraa.library.DTO.MemberRequestDTO;
import com.rudhraa.library.DTO.MemberResponseDTO;
import com.rudhraa.library.Service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<MemberResponseDTO> addMember(
            @Valid @RequestBody MemberRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.addMember(dto));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> showAll() {

        return ResponseEntity.ok(memberService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberById(
            @PathVariable Long id) {

        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody MemberRequestDTO dto) {

        return ResponseEntity.ok(memberService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> delete(
            @PathVariable Long id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}