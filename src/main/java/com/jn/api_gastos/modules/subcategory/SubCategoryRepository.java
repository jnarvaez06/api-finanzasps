package com.jn.api_gastos.modules.subcategory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    List<SubCategory> findAllByUser(String user);

    List<SubCategory> findAllByUserAndStateTrue(String user);
}
