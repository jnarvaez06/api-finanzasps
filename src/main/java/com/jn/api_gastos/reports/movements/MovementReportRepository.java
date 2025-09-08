package com.jn.api_gastos.reports.movements;

import com.jn.api_gastos.modules.movement.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementReportRepository extends JpaRepository<Movement, Long> {

    @Query(value = """
            SELECT
                m.id_movement AS idMovement,
                m.date_movement AS dateMovement,
                m.description AS description,
                m.value AS value,
                m.type AS type,
                m.id_account AS accountId,
                a.description AS accountDescription,
                m.id_category AS categoryId,
                c.description AS categoryDescription,
                m.id_subcategory AS subCategoryId,
                sc.description AS subCategoryDescription
            FROM movement m
            LEFT JOIN account a ON a.id_account = m.id_account
            LEFT JOIN category c ON c.id_category = m.id_category
            LEFT JOIN sub_category sc ON sc.id_sub_category = m.id_subcategory
            WHERE (:year IS NULL OR YEAR(m.date_movement) = :year)
              AND (:month IS NULL OR MONTH(m.date_movement) = :month)
              AND (:accountId IS NULL OR m.id_account = :accountId)
              AND (:categoryId IS NULL OR m.id_category = :categoryId)
              AND (:subCategoryId IS NULL OR m.id_subcategory = :subCategoryId)
            ORDER BY m.date_movement DESC
        """, nativeQuery = true)
    List<MovementReportProjection> findMovementsReport(
            @Param("year") Integer year,
            @Param("month") Integer month,
            @Param("accountId") Long accountId,
            @Param("categoryId") Long categoryId,
            @Param("subCategoryId") Long subCategoryId
    );
}