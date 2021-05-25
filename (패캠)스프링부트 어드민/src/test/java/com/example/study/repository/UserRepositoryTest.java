package com.example.study.repository;

import com.example.study.component.LoginUserAuditorAware;
import com.example.study.config.JpaConfig;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.respository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest    // JPA 테스트 관련 컴포넌트 import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // 실제 DB 사용
@DisplayName("UserRepository 테스트")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class UserRepositoryTest {

    // DI - Dependency Injection
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() {
        String account = "Test03";
        String password = "Test03";
        UserStatus status = UserStatus.REGISTERED;
        String email = "Test01@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();

        /* LoginUserAuditorAware 적용으로 인한 자동 createAt, createdBy 설정 */
//        LocalDateTime createdAt = LocalDateTime.now();
//        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
/*        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);*/

        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();

        User newUser = userRepository.save(user);

        Assertions.assertNotNull(newUser);

    }

    @Test
    @Transactional
    public void read() {
        String phoneNumber = "010-1111-2222";
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc(phoneNumber);
        
        // Accessors chain-true 속성
        user.setEmail("")
                .setPhoneNumber("")
                .setStatus(UserStatus.REGISTERED)
                .setEmail("");

        user.getOrderGroupList().stream().forEach(orderGroup -> {

            System.out.println("---------- 주문묶음 ----------");
            System.out.println("수령인: " + orderGroup.getRevName());
            System.out.println("수령지: " + orderGroup.getRevAddress());
            System.out.println("총금액: " + orderGroup.getTotalPrice());
            System.out.println("총수량: " + orderGroup.getTotalQuantity());

            System.out.println("---------- 주문상세 ----------");
            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("파트너사 이름: " + orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리: " + orderDetail.getItem().getPartner().getCategory().getTitle());

                System.out.println("주문 상품: " + orderDetail.getItem().getName());
                System.out.println("고객센터 번호: " + orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문의 상태: " + orderDetail.getStatus());
                System.out.println("도착예정일자: " + orderDetail.getArrivalDate());

            });
        });

        Assertions.assertNotNull(user);
    }

    @Test
    public void update() {
        Optional<User> user = userRepository.findById(6L);

        user.ifPresent(selectUser ->{
            selectUser.setAccount("PPPP");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            // 얘는 왜 Update가 되는건지는? 6L(id)값이 중요한데, SELECT를 했을때 존재하면 Update가 됨.
            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete() {
        Optional<User> user = userRepository.findById(6L);

        // user는 값이 존재해야 한다.
        Assertions.assertTrue(user.isPresent());

        user.ifPresent(selectUser ->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(6L);


        Assertions.assertFalse(deleteUser.isPresent()); // False
        assertThat(deleteUser.isPresent()).isEqualTo(false);
    }
}
