package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.persistence.entity.Vocabulary;

import java.util.Optional;

public interface IVocabularyService {
    Vocabulary create(Vocabulary vocabulary);
    void update(Vocabulary vocabulary);
    Optional read(Long id);
    void delete(Long id);

}
