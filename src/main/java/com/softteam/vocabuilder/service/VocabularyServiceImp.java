package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.exections.VocabularyNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Category;
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
        if (vocabulary1.isEmpty()){
            throw new VocabularyNotFoundException("vocabulary not found", HttpStatus.NOT_FOUND);
        }
        vocabularyRepository.save(vocabulary);
    }

    @Override
    public Optional<Vocabulary> getVocabulary(UUID id) {
        if (vocabularyRepository.findById(id).isEmpty()){
            throw new VocabularyNotFoundException("vocabulary not found", HttpStatus.NOT_FOUND);
        }
        return vocabularyRepository.findById(id);
    }

    @Override
    public List<Vocabulary> findAllVocabularies() {
        return vocabularyRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        if (vocabularyRepository.findById(id).isEmpty()){
            throw new VocabularyNotFoundException("vocabulary not found", HttpStatus.NOT_FOUND);
        }
        vocabularyRepository.deleteById(id);
    }
}
