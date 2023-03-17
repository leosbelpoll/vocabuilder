package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.exections.VocabularyNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import com.softteam.vocabuilder.persistence.repository.VocabularyRepository;
import com.softteam.vocabuilder.service.dto.VocabularyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VocabularyServiceImp implements IVocabularyService {
    @Autowired
    private VocabularyRepository vocabularyRepository;

    @Override
    public Vocabulary create(Vocabulary vocabulary) {
        return vocabularyRepository.save(vocabulary);
    }

    @Transactional
    @Override
    public void update(Vocabulary vocabulary) {
        vocabularyRepository.save(vocabulary);
    }

    @Override
    public Optional<Vocabulary> getVocabulary(UUID id) {
        Optional<Vocabulary> optionalVocabulary = vocabularyRepository.findById(id);
        if (optionalVocabulary.isEmpty()) {
            throw new RuntimeException("vocabulary not found");
        }
        return optionalVocabulary;
    }

    @Override
    public List<Vocabulary> findAllVocabularies() {
        return vocabularyRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        vocabularyRepository.deleteById(id);
    }
}
