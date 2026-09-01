package com.rudhraa.library.DTO;

public class MemberResponseDTO {

    private long id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public MemberResponseDTO() {
    }

    public MemberResponseDTO(long id, String name, String email,
                             String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}