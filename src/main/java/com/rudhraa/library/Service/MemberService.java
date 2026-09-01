package com.rudhraa.library.Service;
import com.rudhraa.library.Exception.ResourceNotFoundException;
import com.rudhraa.library.Model.Members;
import com.rudhraa.library.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Members add(Members members){
        return memberRepository.save(members);
    }

    public List<Members> showAll(){
        return memberRepository.findAll();
    }

    public Members showById(Long id){

        return memberRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not found"));
    }

    public Members update(Long id, Members members){
        Members existingMember = memberRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Id not found"));

        existingMember.setName(members.getName());
        existingMember.setEmail(members.getEmail());
        existingMember.setPhone(members.getPhone());
        existingMember.setAddress(members.getAddress());

        return memberRepository.save(existingMember);
    }

    public String delete(Long id){
        if(!memberRepository.existsById(id)){
            return "Id not found";
        }
        memberRepository.deleteById(id);
        return "Member deleted successfully";
    }
}
