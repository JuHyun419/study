package com.example.study.repository;

import com.example.study.component.LoginUserAuditorAware;
import com.example.study.config.JpaConfig;
import com.example.study.model.entity.Partner;
import com.example.study.respository.PartnerRepository;
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

@DataJpaTest    // JPA 테스트 관련 컴포넌트 import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // 실제 DB 사용
@DisplayName("UserRepository 테스트")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class PartnerRepositoryTest {

    @Autowired
    PartnerRepository partnerRepository;

    @Test
    public void create() {
        String name = "Partner01";
        String status = "REGISTERED";
        String address = "서울시 강남구 역삼로";
        String callCenter = "070-1111-2222";
        String partnerNumber = "010-1111-2222";
        String businessNumber = "1234567890123";
        String ceoName = "홍길동";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";
        Long categoryId = 1L;

        Partner partner = new Partner();
        partner.setName(name);
        partner.setStatus(status);
        partner.setAddress(address);
        partner.setCallCenter(callCenter);
        partner.setPartnerNumber(partnerNumber);
        partner.setBusinessNumber(businessNumber);
        partner.setCeoName(ceoName);
        partner.setRegisteredAt(registeredAt);

        /* LoginUserAuditorAware 적용으로 인한 자동 createAt, createdBy 설정 */
//        partner.setCreatedAt(createdAt);
//        partner.setCreatedBy(createdBy);
        //partner.setCategoryId(categoryId);

        Partner newPartner = partnerRepository.save(partner);
        Assertions.assertNotNull(newPartner);
    }

    @Test
    public void read() {

    }
}
