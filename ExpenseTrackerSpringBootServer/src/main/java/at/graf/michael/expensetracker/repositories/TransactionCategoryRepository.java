package at.graf.michael.expensetracker.repositories;

import at.graf.michael.expensetracker.entities.TransactionCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionCategoryRepository extends JpaRepository<TransactionCategory, Integer> {
    List<TransactionCategory> findAllByUserId(int userId);
}
