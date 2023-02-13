package com.example.practic4.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class StatsRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public int getCountOfIncomesThatGreaterThan(BigDecimal amount){
        Map<String,Object> parametrs= new HashMap<>();
        parametrs.put("amount",amount);
        return namedParameterJdbcTemplate.queryForObject("Select count(*) from incomes where income > :amount", parametrs, new StatsRowMapper());
    }
    private static final class StatsRowMapper implements RowMapper<Integer>{

        @Override
        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
            return resultSet.getInt("COUNT");
        }
    }
}
