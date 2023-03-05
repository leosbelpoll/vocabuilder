package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.persistence.entity.Vocabulary;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IVocabularyService {
    Vocabulary create(Vocabulary vocabulary);

    void update(Vocabulary vocabulary);

    Optional getVocabulary(String id);

    List<Vocabulary> findAllVocabularies();

    void delete(String id);

}
