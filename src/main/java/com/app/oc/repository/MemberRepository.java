package com.app.oc.repository;



import com.app.oc.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {

    void deleteByNickname(String nickname);


}
