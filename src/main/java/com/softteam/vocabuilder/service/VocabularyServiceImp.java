package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.exections.VocabularyIllegalArgumentException;
import com.softteam.vocabuilder.exections.VocabularyNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import com.softteam.vocabuilder.persistence.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        Optional<Vocabulary> vocabulary1 = vocabularyRepository.findById(vocabulary.getId());
        if (vocabulary1.isEmpty()) {
            throw new VocabularyNotFoundException("vocabulary not found", HttpStatus.NOT_FOUND);
        }
        vocabularyRepository.save(vocabulary);
    }

    public void update2(Vocabulary vocabulary) {
        try {
            Optional<Vocabulary> updatedCategory = Optional.of(vocabularyRepository.save(vocabulary));
            if (!updatedCategory.isPresent()) {
                throw new VocabularyNotFoundException("Category not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<Vocabulary> getVocabulary(String id) {
        Optional<Vocabulary> optionalVocabulary = Optional.of(new Vocabulary());
        try {

            optionalVocabulary = vocabularyRepository.findById(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            throw new VocabularyIllegalArgumentException("the id provided is not valid", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (optionalVocabulary.isEmpty()) {
            throw new VocabularyNotFoundException("Category not found", HttpStatus.NOT_FOUND);
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
        if (vocabularyRepository.findById(id).isEmpty()) {
            throw new VocabularyNotFoundException("vocabulary not found", HttpStatus.NOT_FOUND);
        }
        vocabularyRepository.deleteById(id);
    }
}
