package com.utcn.assignmentSD.repository;


import com.utcn.assignmentSD.model.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends CrudRepository<Question, Integer> {}

