package com.utcn.assignmentSD.repository;

import com.utcn.assignmentSD.model.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagRepository extends CrudRepository<Tag, Integer> {

}
