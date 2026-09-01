package com.rudhraa.library.Controller;


import com.rudhraa.library.Model.Members;
import com.rudhraa.library.Service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Members members){
        return ResponseEntity.ok(memberService.add(members));
    }

    @GetMapping
    public ResponseEntity<?> getALl(){
        return ResponseEntity.ok(memberService.showAll());
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getById(@RequestParam Long id){
        return ResponseEntity.ok(memberService.showById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long id, @RequestBody Members members){
        return ResponseEntity.ok(memberService.update(id, members));
    }

    @DeleteMapping("/del")
    public ResponseEntity<?> delete(@RequestParam Long id){
        return ResponseEntity.ok(memberService.delete(id));
    }
}
