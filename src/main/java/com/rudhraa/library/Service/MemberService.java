package com.rudhraa.library.Service;
import com.rudhraa.library.Model.Members;
import com.rudhraa.library.Repository.MemberRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRespository memberRespository;

    public MemberService(MemberRespository memberRespository) {
        this.memberRespository = memberRespository;
    }

    public Members add(Members members){
        return memberRespository.save(members);
    }

    public List<Members> showAll(){
        return memberRespository.findAll();
    }

    public Optional<Members> showById(Long id){
        return memberRespository.findById(id);
    }

    public Members update(Long id, Members members){
        Members existingMember = memberRespository.findById(id)
                .orElseThrow(()-> new RuntimeException("Id not found"));

        existingMember.setName(members.getName());
        existingMember.setEmail(members.getEmail());
        existingMember.setPhone(members.getPhone());
        existingMember.setAddress(members.getAddress());

        return memberRespository.save(existingMember);
    }

    public String delete(Long id){
        if(!memberRespository.existsById(id)){
            return "Id not found";
        }
        memberRespository.deleteById(id);
        return "Member deleted successfully";
    }
}
