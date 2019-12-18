package com.example.sneakysneaks.service;

import com.example.sneakysneaks.model.SneakyUser;
import com.example.sneakysneaks.repository.SneakyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SneakerUserServiceImpl implements SneakyUserService {

//	@Autowired
//    @Qualifier("encoder")
//    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private SneakyUserRepository sneakyUserRepository;
    

    /**
     * @param SneakyUser
     * calls repository layer and saves a SneakyUser
     */
    @Override
    public SneakyUser saveUser(SneakyUser sneakyUser) {
    	//You can encrypt your password here instead if you want
    	//makes the service layer useful -- for all business logic such as this
//    	sneakyUser.setPassword(bCryptPasswordEncoder.encode(sneakyUser.getPassword()));
        return sneakyUserRepository.save(sneakyUser);
    }

}
    /**
     * @param username
     * @param password
     * calls repository layer and saves a SneakyUser
     */
//	@Override
//	public SneakyUser login(String name, String password) {
//		SneakyUser newUser = sneakyUserRepository.findByName(name);
//		if(newUser != null && bCryptPasswordEncoder.matches(password, newUser.getPassword())){
//			return newUser;
//		}
//		return null;
//	}
//}
