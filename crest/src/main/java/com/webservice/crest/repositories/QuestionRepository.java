package com.webservice.crest.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.webservice.crest.models.Question;
import com.webservice.crest.models.User;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}
