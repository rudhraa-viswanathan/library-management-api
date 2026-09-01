package com.rudhraa.library.Mapper;

import com.rudhraa.library.DTO.MemberRequestDTO;
import com.rudhraa.library.DTO.MemberResponseDTO;
import com.rudhraa.library.Model.Members;

public class MemberMapper {

    public static Members toEntity(MemberRequestDTO dto) {

        Members member = new Members();

        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());
        member.setAddress(dto.getAddress());

        return member;
    }

    public static MemberResponseDTO toResponseDTO(Members member) {

        return new MemberResponseDTO(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPhone(),
                member.getAddress()
        );
    }
}