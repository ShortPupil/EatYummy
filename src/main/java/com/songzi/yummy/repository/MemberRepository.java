package com.songzi.yummy.repository;

import com.songzi.yummy.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select a from Member a where a.name like ?1 and a.state <> 0")
    List<Member> searchMemberByName(String name);

    @Query("select a from Member a where a.email = ?1 and a.state <> 0")
    Member findMemberByEmail(String email);
}
