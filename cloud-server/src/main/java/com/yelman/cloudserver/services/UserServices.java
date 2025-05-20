package com.yelman.cloudserver.services;

import com.yelman.cloudserver.api.dto.UsersCreateDto;
import com.yelman.cloudserver.model.Company;
import com.yelman.cloudserver.model.Users;
import com.yelman.cloudserver.model.Vet;
import com.yelman.cloudserver.repository.CompanyRepository;
import com.yelman.cloudserver.repository.UserRepository;
import com.yelman.cloudserver.repository.VetRepository;
import com.yelman.cloudserver.services.impl.UserServicesImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices implements UserServicesImpl {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final VetRepository vetRepository;

    public UserServices(UserRepository userRepository, CompanyRepository companyRepository, VetRepository vetRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.vetRepository = vetRepository;
    }

    @Override
    @Transactional
    public boolean registerUser(UsersCreateDto user) {
        Users s = new Users();
        s.setEnable(true);
        s.setEmail(user.getEmail());
        s.setUsername(user.getUsername());
        s.setPassword(user.getPassword());
        s.setFirstName(user.getFirstName());
        s.setLastName(user.getLastName());
        s.setRole(user.getRole());
        Users newUser = userRepository.save(s);
        if (user.getRole().name().equals("VET")) {
            Company a = companyRepository.findByName(user.getCompanyName());
            vetRepository.save(new Vet(user.getVetName(), newUser, a, user.getVetTitle()));
            return true;
    } if(user.getRole().name().equals("COMPANY")) {
            companyRepository.save(new Company(user.getCompanyName(), newUser));
            return true;
        }
        return s.getId() != null;
    }

    @Override
    public Users getUserByUsername(String username) {
        return null;
    }

    @Override
    public Users getUserByEmail(String email) {
        Optional<Users> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    @Override
    public Users getUserById(Long id) {
        Optional<Users> user = userRepository.findById(id);
        return user.orElse(null);
    }


    @Override
    public void updateUser(Users user) {

    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public List<Users> getAllUser() {
        List<Users> user = userRepository.findAll();
        if (user.size() == 0) return null;
        return user;
    }

    @Override
    public Long loginUserAndGetCompanyId(String username, String password) {
        Users user = userRepository.findByUsernameAndPassword(username, password).orElse(null);
        assert user != null;
        Company company = companyRepository.findByUser_Id(user.getId());
        if (company != null) return company.getId();
        else return null;
    }


}
