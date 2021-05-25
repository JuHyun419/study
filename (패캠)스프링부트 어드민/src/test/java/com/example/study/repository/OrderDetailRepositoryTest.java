package com.example.study.repository;

import com.example.study.component.LoginUserAuditorAware;
import com.example.study.config.JpaConfig;
import com.example.study.model.entity.OrderDetail;
import com.example.study.respository.OrderDetailRepository;
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
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setStatus("WAITING");
        orderDetail.setArrivalDate(LocalDateTime.now().plusDays(2));
        orderDetail.setQuantity(1);
        orderDetail.setTotalPrice(BigDecimal.valueOf(900000));
       // orderDetail.setOrderGroupId(1L);  // Long -> OrderGroup 어떠한 장바구니에
        //orderDetail.setItemId(1L);        // 어떠한 상품

        /* LoginUserAuditorAware 적용으로 인한 자동 createAt, createdBy 설정 */
//        orderDetail.setCreatedAt(LocalDateTime.now());
//        orderDetail.setCreatedBy("AdminServer");

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        assertThat(newOrderDetail).isNotNull();
    }
}
