package com.codelab.backend.service;

import com.codelab.backend.enums.UserRoles;
import com.codelab.backend.exceptions.AlreadyExistException;
import com.codelab.backend.exceptions.AuthException;
import com.codelab.backend.model.Users;
import com.codelab.backend.repository.UsersRepository;
import com.codelab.backend.request.AddUserReq;
import com.codelab.backend.request.LoginApi;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public Users registerUser(AddUserReq req){
        if(usersRepository.existsByEmail(req.getEmail())){
            throw new AlreadyExistException("Email id Already Exist");
        } else {
            UserRoles roles = req.getUserRole();
            Users newUsers = new Users(
                    req.getUsername(),
                    req.getEmail(),
                    hashPassword(req.getPassword()),
                    req.getSpr(),
                    roles
            );

            System.out.println("added");
            return usersRepository.save(newUsers);
        }
    }
    private String hashPassword(String password){
        return BCrypt.hashpw(password,BCrypt.gensalt());
    }


    public Users loginUser(LoginApi req) {
        if(!usersRepository.existsByEmail(req.getEmail())){
            throw new AlreadyExistException("User Not Found");
        } else {

           Users user = usersRepository.findByEmail(req.getEmail());
           if (!BCrypt.checkpw(req.getPassword(),user.getPassword())){

               throw new AuthException("Invalid Password");
           }

       return user;

        }

    }
}
