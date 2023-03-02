package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.persistence.entity.Vocabulary;

<<<<<<< HEAD
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IVocabularyService {
    Vocabulary create(Vocabulary vocabulary);

    void update(Vocabulary vocabulary);

    Optional getVocabulary(UUID id);

    List<Vocabulary> findAllVocabularies();

    void delete(UUID id);
=======
import java.util.Optional;

public interface IVocabularyService {
    Vocabulary create(Vocabulary vocabulary);
    void update(Vocabulary vocabulary);
    Optional read(Long id);
    void delete(Long id);
>>>>>>> f93a88afac596b8434e4aa7a030f67215dee11fc

}
