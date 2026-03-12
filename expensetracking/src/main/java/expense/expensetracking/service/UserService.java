package expense.expensetracking.service;


import expense.expensetracking.Dto.UserDto;
import expense.expensetracking.model.UserModel;
import expense.expensetracking.repo.ExpenseRepository;
import expense.expensetracking.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    public UserModel getUserData(UserDto user)
    {
        try{
//            validate user
        UserModel user1=userRepository.findByUsername(user.getUsername());

        if(user1!=null){
            if(user1.getPassword().equals(user.getPassword()))
            {
                return user1;
            }
            else
            {
                throw new RuntimeException("5 password not match");
            }

        }else throw new RuntimeException("4 user Not Found");
        }
        catch (Exception e){

            System.out.println(e.getMessage());
        }
        // --> to-do -- check here
        return new UserModel();
    }
}
