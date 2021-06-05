package com.example.study.respository;

import com.example.study.model.entity.Category;
import com.example.study.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    List<Partner> findByCategory(Category category);

}
