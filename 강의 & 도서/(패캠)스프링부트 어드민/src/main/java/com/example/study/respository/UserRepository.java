package com.example.study.respository;

import com.example.study.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { // Long => User 클래스에서식별자인 id 타입

    User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber);

}
