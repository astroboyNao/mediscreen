package com.abernathy.assess.repository;

import com.abernathy.assess.model.domain.Note;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    @Aggregation(pipeline = {
            "{ $match :{ \"patientId\" : ?0}}",
            "{ $addFields: {\"result\": { $regexFindAll: { input: \"$note\", regex: '?1', options: \"i\"} }}}",
            "{ $project: { numberOfTermByNote: { $cond: {if: {$isArray: \"$result\"}, then: {$size: \"$result\"}, else: \"NA\"}}}}",
            "{ $group: {_id: \"\",numberOfTerm: { $sum: \"$numberOfTermByNote\" }}}"
    })
    public Long countByPropertiesPatientIdAndSearchText(Long patientId, String text);
}