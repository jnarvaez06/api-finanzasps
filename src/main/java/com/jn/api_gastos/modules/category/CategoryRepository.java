package com.jn.api_gastos.modules.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findAllByUser(String user);

    List<Category> findAllByUserAndStateTrue(String user);
}
