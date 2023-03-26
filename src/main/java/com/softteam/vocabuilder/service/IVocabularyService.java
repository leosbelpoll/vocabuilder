package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.exections.ResourceNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import com.softteam.vocabuilder.service.dto.VocabularyDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IVocabularyService {
    Vocabulary create(Vocabulary vocabulary);

    Vocabulary update(Vocabulary vocabulary);

    Vocabulary partialUpdate(Vocabulary vocabulary) throws ResourceNotFoundException;

    Vocabulary getVocabulary(UUID id) throws ResourceNotFoundException;

    List<Vocabulary> findAllVocabularies();

    void delete(UUID id);

}
