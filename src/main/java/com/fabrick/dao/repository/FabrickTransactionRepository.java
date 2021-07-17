package com.fabrick.dao.repository;

import com.fabrick.dao.model.FabrickTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabrickTransactionRepository extends JpaRepository<FabrickTransaction, Long> {
}
