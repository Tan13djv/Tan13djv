package com.example.practic4.repository;

import com.example.practic4.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT op FROM Transaction op WHERE op.client_id =?1 and op.datetime_op >=to_date(?2,'DD-MM-YYYY') and op.datetime_op <=to_date(?3,'DD-MM-YYYY')")
    public List<Transaction> getOpersByClient_idAndDatetime_op( Long client_id,  String dateFrom,  String dateTo);

}
