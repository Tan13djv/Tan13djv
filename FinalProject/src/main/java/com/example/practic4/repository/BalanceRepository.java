package com.example.practic4.repository;

import com.example.practic4.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    @Query("SELECT bal FROM Balance bal WHERE bal.client_id =?1")
    public Balance getBalanceByClient_id(Long client_id);
}