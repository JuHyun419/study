package com.example.study.repository;

import com.example.study.component.LoginUserAuditorAware;
import com.example.study.config.JpaConfig;
import com.example.study.model.entity.OrderGroup;
import com.example.study.respository.OrderGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest    // JPA 테스트 관련 컴포넌트 import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // 실제 DB 사용
@DisplayName("ItemRepository 테스트")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class OrderGroupRepositoryTest {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Test
    public void create() {
        OrderGroup orderGroup = new OrderGroup();
        orderGroup.setStatus("COMPLETE");
        orderGroup.setRevAddress("서울시 강남구 역삼동");
        orderGroup.setRevName("홍길동");
        orderGroup.setPaymentType("CARD");
        orderGroup.setTotalPrice(BigDecimal.valueOf(900000));
        orderGroup.setTotalQuantity(1);
        orderGroup.setOrderAt(LocalDateTime.now().minusDays(2));
        orderGroup.setArrivalDate(LocalDateTime.now());

        /* LoginUserAuditorAware 적용으로 인한 자동 createAt, createdBy 설정 */
//        orderGroup.setCreatedAt(LocalDateTime.now());
//        orderGroup.setCreatedBy("AdminServer");
//        orderGroup.setUserId(1L);   // -> User

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

        assertThat(newOrderGroup).isNotNull();

    }
}
