package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import com.softteam.vocabuilder.service.dto.VocabularyDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IVocabularyService {
    Vocabulary create(Vocabulary vocabulary);

    Vocabulary update(Vocabulary vocabulary);

    Optional getVocabulary(UUID id);

    List<Vocabulary> findAllVocabularies();

    void delete(UUID id);

}
