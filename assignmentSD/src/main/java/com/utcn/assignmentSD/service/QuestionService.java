package com.utcn.assignmentSD.service;

import com.utcn.assignmentSD.model.Question;
import com.utcn.assignmentSD.model.User;
import com.utcn.assignmentSD.repository.IQuestionRepository;
import com.utcn.assignmentSD.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    IQuestionRepository iQuestionRepository;
    @Autowired
    IUserRepository iUserRepository;

    public List<Question> getAllQuestion(){
       return (List<Question>) iQuestionRepository.findAll();
    }

    public List<Question> getAllQuestionSorted(){
       List<Question> q = (List<Question>) iQuestionRepository.findAll();
       q.sort(Comparator.comparing(Question::getCreationDate).reversed());
       return q;
    }
    public Question getQuestion(Integer id)
    {
        Optional<Question> question = iQuestionRepository.findById(id);
        return question.orElse(null);
    }


    public String deleteQuestion(Integer id) {
        try {
            iQuestionRepository.delete(this.getQuestion(id));

            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }
    public Question saveQuestion(String name, String body, Integer id){
        Optional<User> user = iUserRepository.findById(id);
        User insertedUser =  user.orElse(null);
        if(user.isPresent())
        {
            Question q = new Question(name,body,Calendar.getInstance().getTime(),insertedUser);
            insertedUser.addQuestion(q);
            return iQuestionRepository.save(q);
        }
        return null;
    }
}