package expense.expensetracking.service;


import expense.expensetracking.Dto.UserDto;
import expense.expensetracking.model.UserModel;
import expense.expensetracking.repo.ExpenseRepository;
import expense.expensetracking.repo.UserRepository;
import expense.expensetracking.response.ExpenseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public ExpenseDto getUserData(UserDto user)
    {

        UserModel user1=userRepository.findByUsername(user.getUsername());
        if(user1!=null){
            if(passwordEncoder.matches(user.getPassword(), user1.getPassword()))
            {
                return new ExpenseDto(user1.getExpenses(),user1.getUsername());
            }
            else
            {
                throw new RuntimeException("5 password not match");
            }
        }else throw new RuntimeException("4 user Not Found");

    }

    public Boolean signupUser(UserDto user)
    {
            if(Objects.nonNull(checkIfUserAlreadyExists(user)))
            {
                return false;
            }


            user.setPassword(passwordEncoder.encode(user.getPassword()));

            userRepository.save(new UserModel(user.getUsername(),user.getPassword()));
            return true;

    }

    public UserModel checkIfUserAlreadyExists(UserDto userDto)
    {
        return userRepository.findByUsername(userDto.getUsername());
    }


}
