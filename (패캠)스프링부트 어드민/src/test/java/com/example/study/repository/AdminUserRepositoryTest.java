package com.example.study.repository;

import com.example.study.component.LoginUserAuditorAware;
import com.example.study.config.JpaConfig;
import com.example.study.model.entity.AdminUser;
import com.example.study.respository.AdminUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest    // JPA 테스트 관련 컴포넌트 import
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)  // 실제 DB 사용
@DisplayName("ItemRepository 테스트")
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@Import({JpaConfig.class, LoginUserAuditorAware.class})
public class AdminUserRepositoryTest {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create() {
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser01");
        adminUser.setPassword("AdminUser01");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");

        /* LoginUserAuditorAware 적용으로 인한 자동 createAt, createdBy 설정 */
//        adminUser.setCreatedAt(LocalDateTime.now());
//        adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);

        assertThat(newAdminUser).isNotNull();
    }
}
