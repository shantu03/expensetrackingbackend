package expense.expensetracking.repo;

import expense.expensetracking.model.ExpenseModel;
import expense.expensetracking.response.ExpenseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseModel,Integer> {
    public List<ExpenseModel> findByUserId(Integer id);
}
