package com.somecompany.card.management.service;

import com.somecompany.card.management.dao.UserRepository;
import com.somecompany.card.management.entity.User;
import com.somecompany.card.management.utils.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public void checkExist(long id) {
        User user = userRepository.getById(id);
        if (user == null) {
            throw new EntityNotFoundException(User.class.getSimpleName(), id);
        }
    }
}
