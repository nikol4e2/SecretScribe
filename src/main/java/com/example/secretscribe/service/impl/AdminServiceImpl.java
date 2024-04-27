package com.example.secretscribe.service.impl;

import com.example.secretscribe.model.Admin;
import com.example.secretscribe.model.exceptions.InvalidArgumentsException;
import com.example.secretscribe.model.exceptions.InvalidUserCredentialsException;
import com.example.secretscribe.model.exceptions.PasswordsDoNotMatchException;
import com.example.secretscribe.model.exceptions.UserNameAlreadyExistsException;
import com.example.secretscribe.repository.AdminRepository;
import com.example.secretscribe.service.AdminService;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;


    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin register(String username, String password, String repeatPassword) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
        {
            throw new InvalidUserCredentialsException();
        }

        if(!password.equals(repeatPassword))
        {
            throw new PasswordsDoNotMatchException();
        }

        if(this.adminRepository.findByUsername(username).isPresent())
        {
            throw new UserNameAlreadyExistsException(username);
        }

        Admin admin=new Admin(username,password);
        return adminRepository.save(admin);
    }

    @Override
    public Admin login(String username, String password) {
        if(username==null || username.isEmpty() || password==null || password.isEmpty())
        {
            throw new InvalidArgumentsException();
        }
        return adminRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidArgumentsException::new);
    }
}
