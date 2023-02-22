package com.example.practic4.repository;

import com.example.practic4.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
}
