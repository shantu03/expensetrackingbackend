package expense.expensetracking.service;

import expense.expensetracking.model.ExpenseModel;
import expense.expensetracking.model.UserModel;
import expense.expensetracking.repo.ExpenseRepository;
import expense.expensetracking.repo.UserRepository;
import expense.expensetracking.request.ExpenseRequest;
import expense.expensetracking.response.ExpenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
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

        if(user==null) throw new RuntimeException("user not found");

        model.setUserId(user.getUserId());
        model.setAmount(expenseRequest.getAmount());
        model.setPlatform(expenseRequest.getPlatform());
        model.setTransactionDate(expenseRequest.getTransactionDate());
        model.setDescription(expenseRequest.getDescription());
        expenseRepository.save(model);
        return "expense added";

    }

    public ExpenseResponse getExpenses(int page, int size) {

        UserModel user = getLoggedInUser();

        Pageable pageable = PageRequest.of(page, size, Sort.by("transactionDate").descending());

        Page<ExpenseModel> expensePage =
                expenseRepository.findByUserId(user.getUserId(), pageable);

        List<ExpenseResponse.ExpenseItem> items =
                expensePage.getContent().stream()
                        .map(e -> new ExpenseResponse.ExpenseItem(
                                e.getExpenseId(),
                                e.getDescription(),
                                e.getAmount(),
                                e.getPlatform(),
                                e.getTransactionDate()
                        )).toList();

        return new ExpenseResponse(user.getUsername(), items);
    }

    public String deleteExpense(Integer expenseId) {
        UserModel user = getLoggedInUser();
        if(user == null) throw new RuntimeException("user not found");

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
        if(user == null) throw new RuntimeException("user not found");
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
