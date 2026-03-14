package expense.expensetracking.service;

import java.util.List;
import expense.expensetracking.Dto.UserDto;
import expense.expensetracking.model.ExpenseModel;
import expense.expensetracking.model.UserModel;
import expense.expensetracking.repo.ExpenseRepository;
import expense.expensetracking.repo.UserRepository;
import expense.expensetracking.response.ExpenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


    public String getUserData(UserDto user) {
        UserModel user1 = userRepository.findByUsername(user.getUsername());

        if (user1 == null)
            throw new RuntimeException("4 user not found");

        if (!passwordEncoder.matches(user.getPassword(), user1.getPassword()))
            throw new RuntimeException("5 password not match");



        return user1.getUsername();
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
