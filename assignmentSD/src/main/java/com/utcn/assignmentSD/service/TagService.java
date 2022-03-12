package com.utcn.assignmentSD.service;

import com.utcn.assignmentSD.model.Question;
import com.utcn.assignmentSD.model.Tag;
import com.utcn.assignmentSD.repository.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    @Autowired
    public ITagRepository iTagRepository;

    public List<Tag> getAllTags()
    {
        return (List<Tag>) iTagRepository.findAll();
    }

    public Tag getById(Integer id)
    {
      Optional<Tag> tag = iTagRepository.findById(id);
      return tag.orElse(null);
    }

    public String deleteUser(Integer id) {
        try {
            iTagRepository.delete(this.getById(id));
            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }

    public Tag saveTag(String name, Question q)
    {
        Tag t = new Tag(name);
        t.addQuestion(q);
        q.addTag(t);
        iTagRepository.save(t);
        return t;
    }

    public Tag updateTag(Integer id, Tag tag)
    {
        Tag initialTag = this.getById(id);
        initialTag.setName(tag.getName());
        return iTagRepository.save(initialTag);
    }
}
