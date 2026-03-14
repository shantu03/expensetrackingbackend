package expense.expensetracking.repo;

import expense.expensetracking.model.ExpenseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseModel,Integer> {

    Page<ExpenseModel> findByUserId(Integer userId, Pageable pageable);
}
