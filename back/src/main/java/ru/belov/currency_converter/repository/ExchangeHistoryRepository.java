package ru.belov.currency_converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belov.currency_converter.entity.ExchangeHistory;
import ru.belov.currency_converter.entity.Users;

import java.util.Set;

@Repository
public interface ExchangeHistoryRepository extends JpaRepository<ExchangeHistory, Long> {

    Set<ExchangeHistory> findByUser(Users user);
}
