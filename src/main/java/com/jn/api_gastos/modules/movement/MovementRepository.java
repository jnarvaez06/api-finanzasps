package com.jn.api_gastos.modules.movement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Integer> {
    List<Movement> findAllByUser(String user);
}
