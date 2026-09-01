package com.rudhraa.library.Service;

import com.rudhraa.library.DTO.MemberRequestDTO;
import com.rudhraa.library.DTO.MemberResponseDTO;
import com.rudhraa.library.Exception.ResourceNotFoundException;
import com.rudhraa.library.Mapper.MemberMapper;
import com.rudhraa.library.Model.Members;
import com.rudhraa.library.Repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDTO addMember(MemberRequestDTO dto) {

        Members member = MemberMapper.toEntity(dto);

        Members savedMember = memberRepository.save(member);

        return MemberMapper.toResponseDTO(savedMember);
    }

    public List<MemberResponseDTO> getAll() {

        return memberRepository.findAll()
                .stream()
                .map(MemberMapper::toResponseDTO)
                .toList();
    }

    public MemberResponseDTO getMemberById(Long id) {

        Members member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not found"));

        return MemberMapper.toResponseDTO(member);
    }

    public MemberResponseDTO update(Long id, MemberRequestDTO dto) {

        Members member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not found"));

        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());
        member.setAddress(dto.getAddress());

        Members updatedMember = memberRepository.save(member);

        return MemberMapper.toResponseDTO(updatedMember);
    }

    public MemberResponseDTO delete(Long id) {

        Members member = memberRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Member not found"));

        MemberResponseDTO response = MemberMapper.toResponseDTO(member);

        memberRepository.delete(member);

        return response;
    }
}