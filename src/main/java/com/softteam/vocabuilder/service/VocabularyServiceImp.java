package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import com.softteam.vocabuilder.persistence.repository.VocabularyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
    public Optional<Vocabulary> read(Long id) {
        return vocabularyRepository.findById(id);
    }
    @Transactional
    @Override
    public void delete(Long id) {
        vocabularyRepository.deleteById(id);
    }
}
