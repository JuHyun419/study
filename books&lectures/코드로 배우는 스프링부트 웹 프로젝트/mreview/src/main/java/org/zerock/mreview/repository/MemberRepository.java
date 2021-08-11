package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.mreview.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
