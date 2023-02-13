package com.example.practic4.service;

import com.example.practic4.entity.Spend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpendRepository extends JpaRepository<Spend, Long> {
}
