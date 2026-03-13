package expense.expensetracking.service;

import expense.expensetracking.model.ExpenseModel;
import expense.expensetracking.model.UserModel;
import expense.expensetracking.repo.ExpenseRepository;
import expense.expensetracking.repo.UserRepository;
import expense.expensetracking.request.ExpenseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public String addExpanse(ExpenseRequest expenseRequest)
    {
        ExpenseModel model=new ExpenseModel();
        Optional<UserModel> user = Optional.ofNullable(userRepository.findByUsername(expenseRequest.getUsername()));

        if(user.isEmpty()) return "user not fould";

        model.setUserId(user.get().getUserId());
        model.setAmount(expenseRequest.getAmount());
        model.setPlatform(expenseRequest.getPlatform());
        model.setTransactionDate(expenseRequest.getTransactionDate());
        model.setDescription(expenseRequest.getDescription());
        expenseRepository.save(model);
        return "expanse added";

    }

}
