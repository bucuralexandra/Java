package com.utcn.assignmentSD.controller;

import com.utcn.assignmentSD.model.Question;
import com.utcn.assignmentSD.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllQ")
    @ResponseBody
    public List<Question> getQuestions()
    {
        return questionService.getAllQuestion();

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllQSorted")
    @ResponseBody
    public List<Question> getQuestionsSorted()
    {
        return questionService.getAllQuestionSorted();

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getQuestion")
    @ResponseBody
    public Question getQuestion(@RequestParam(name = "id") Integer id) {
        return questionService.getQuestion(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteQuestion")
    @ResponseBody
    public String deleteQuestion(@RequestParam(name = "id") Integer id) {
        return questionService.deleteQuestion(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/saveQuestion")
    @ResponseBody
    public Question saveQuestion(@RequestBody Question question, @RequestParam(name="id")Integer id) {
        return questionService.saveQuestion(question.getTitle(), question.getBody(), id);
    }
}


