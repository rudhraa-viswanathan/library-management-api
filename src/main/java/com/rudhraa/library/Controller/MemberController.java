package com.rudhraa.library.Controller;

import com.rudhraa.library.DTO.MemberRequestDTO;
import com.rudhraa.library.DTO.MemberResponseDTO;
import com.rudhraa.library.Service.MemberService;
import jakarta.validation.Valid;
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

        return ResponseEntity.ok(memberService.addMember(dto));
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> showAll() {

        return ResponseEntity.ok(memberService.getAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<MemberResponseDTO> getMemberById(
            @RequestParam Long id) {

        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<MemberResponseDTO> update(
            @RequestParam Long id,
            @Valid @RequestBody MemberRequestDTO dto) {

        return ResponseEntity.ok(memberService.update(id, dto));
    }

    @DeleteMapping("/del")
    public ResponseEntity<MemberResponseDTO> delete(
            @RequestParam Long id) {

        return ResponseEntity.ok(memberService.delete(id));
    }
}