package expense.expensetracking.service;

import expense.expensetracking.model.ExpenseModel;
import expense.expensetracking.model.UserModel;
import expense.expensetracking.repo.ExpenseRepository;
import expense.expensetracking.repo.UserRepository;
import expense.expensetracking.request.ExpenseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    private UserModel getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    public String addExpense(ExpenseRequest expenseRequest)
    {
        ExpenseModel model=new ExpenseModel();

        UserModel user=getLoggedInUser();

        if(user==null) return "user not found";

        model.setUserId(user.getUserId());
        model.setAmount(expenseRequest.getAmount());
        model.setPlatform(expenseRequest.getPlatform());
        model.setTransactionDate(expenseRequest.getTransactionDate());
        model.setDescription(expenseRequest.getDescription());
        expenseRepository.save(model);
        return "expanse added";

    }

    public String deleteExpense(Integer expenseId) {
        UserModel user = getLoggedInUser();
        if (user == null) return "user not found";

        Optional<ExpenseModel> expense = expenseRepository.findById(expenseId);

        // expense doesn't exist
        if (expense.isEmpty()) return "expense not found";

        // security check — make sure this expense belongs to the logged in user
        // without this, any logged in user could delete anyone else's expenses
        if (!expense.get().getUserId().equals(user.getUserId()))
            return "unauthorized";

        expenseRepository.deleteById(expenseId);
        return "expense deleted";
    }

    public String updateExpense(ExpenseRequest expenseRequest) {
        UserModel user = getLoggedInUser();
        if (user == null) return "user not found";

        if (expenseRequest.getExpenseId() == null) return "expenseId is required for update";

        Optional<ExpenseModel> existing = expenseRepository.findById(expenseRequest.getExpenseId());

        if (existing.isEmpty()) return "expense not found";

        // same security check as delete
        if (!existing.get().getUserId().equals(user.getUserId()))
            return "unauthorized";

        ExpenseModel model = existing.get();
        model.setAmount(expenseRequest.getAmount());
        model.setPlatform(expenseRequest.getPlatform());
        model.setTransactionDate(expenseRequest.getTransactionDate());
        model.setDescription(expenseRequest.getDescription());

        expenseRepository.save(model);
        return "expense updated";
    }
}
