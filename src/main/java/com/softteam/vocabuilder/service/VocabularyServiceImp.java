package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.exections.VocabularyIllegalArgumentException;
import com.softteam.vocabuilder.exections.VocabularyNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import com.softteam.vocabuilder.persistence.repository.VocabularyRepository;
import com.softteam.vocabuilder.util.validations.Validations;
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

    @Autowired
    private Validations validations;

    @Override
    public Vocabulary create(Vocabulary vocabulary) {
        return vocabularyRepository.save(vocabulary);
    }

    public void update2(Vocabulary vocabulary) {
        Optional<Vocabulary> vocabulary1 = vocabularyRepository.findById(vocabulary.getId());
        if (vocabulary1.isEmpty()) {
            throw new VocabularyNotFoundException("vocabulary not found", HttpStatus.NOT_FOUND);
        }
        vocabularyRepository.save(vocabulary);
    }
    @Transactional
    @Override
    public void update(Vocabulary vocabulary) {
        try {
            Optional<Vocabulary> updatedCategory = Optional.of(vocabularyRepository.save(vocabulary));
            if (!updatedCategory.isPresent()) {
                throw new VocabularyNotFoundException("vocabulary not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Optional<Vocabulary> getVocabulary(String id) {
        UUID uuidID = validations.validateUUIDType(id);
        Optional<Vocabulary> optionalVocabulary = vocabularyRepository.findById(uuidID);
        if (optionalVocabulary.isEmpty()) {
            throw new VocabularyNotFoundException("vocabulary not found", HttpStatus.NOT_FOUND);
        }
        return optionalVocabulary;
    }

    @Override
    public List<Vocabulary> findAllVocabularies() {
        return vocabularyRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(String id) {
        UUID uuidID = validations.validateUUIDType(id);
        if (vocabularyRepository.findById(uuidID).isEmpty()) {
            throw new VocabularyNotFoundException("vocabulary not found", HttpStatus.NOT_FOUND);
        }
        vocabularyRepository.deleteById(uuidID);
    }
}
