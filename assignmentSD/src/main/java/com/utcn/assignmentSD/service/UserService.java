package com.utcn.assignmentSD.service;

import com.utcn.assignmentSD.model.Question;
import com.utcn.assignmentSD.model.User;
import com.utcn.assignmentSD.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    public List<User> getAllUsers() {
        return (List<User>) iUserRepository.findAll();
    }

    public User getUser(Integer id) {
        Optional<User> user = iUserRepository.findById(id);
        return user.orElse(null);
    }

    public String deleteUser(Integer id) {
        try {
            iUserRepository.delete(this.getUser(id));
            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }


    public User saveUser(User user){
        return iUserRepository.save(user);
    }

    public User updateUser(Integer id, User user){
        User initialUser= this.getUser(id);
        initialUser.setName(user.getName());
        initialUser.setUsername(user.getUsername());
        initialUser.setPassword(user.getPassword());
        return iUserRepository.save(initialUser);
    }

    public Set<Question> seeQuestions(Integer id)
    {
        Optional<User> user = iUserRepository.findById(id);
        User u = user.orElse(null);
        assert u != null;
        return u.getQuestions();
    }
}
