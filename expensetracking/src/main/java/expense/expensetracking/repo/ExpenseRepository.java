package expense.expensetracking.repo;

import expense.expensetracking.model.ExpenseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseModel,Integer> {
}
