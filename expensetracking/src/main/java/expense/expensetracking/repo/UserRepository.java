package expense.expensetracking.repo;

import expense.expensetracking.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Integer> {
    public UserModel findByUsername(String username);
}
