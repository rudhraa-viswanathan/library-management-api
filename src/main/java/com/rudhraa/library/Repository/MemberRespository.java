package com.rudhraa.library.Repository;


import com.rudhraa.library.Model.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;

@Repository
public interface MemberRespository extends JpaRepository<Members, Long> {
}
