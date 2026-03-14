package expense.expensetracking.repo;

import expense.expensetracking.model.ExpenseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseModel,Integer> {

    Page<ExpenseModel> findByUserId(Integer userId, Pageable pageable);

    @Query("""
SELECT COALESCE(SUM(e.amount),0)
FROM ExpenseModel e
WHERE e.userId = :userId
AND YEAR(e.transactionDate) = :year
AND MONTH(e.transactionDate) = :month
""")
    Double getMontlyExpense(Integer userId,int year,int month);
}
