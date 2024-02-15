package com.webservice.crest.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.webservice.crest.models.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{   
    // NOTE: This will work as such without the Query Annotation
    List<Answer> findByQuestionId(long questionId);
}
