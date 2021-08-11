package com.example.fastcampussecretjpa.repository;

import com.example.fastcampussecretjpa.dto.UserNoticeCount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class UserCustomRepository {

    private final EntityManager em;

    public List<UserNoticeCount> findUserNoticeCount() {
        final String sql = "select " +
                "u.id, " +
                "u.email, " +
                "u.user_name, " +
                "(select count(*) from notice n where n.user_id = u.id) notice_count " +
                "from User u ";

        return em.createNativeQuery(sql).getResultList();
    }
}
