package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.exections.ResourceNotFoundException;
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
    public Vocabulary update(Vocabulary vocabulary) {
        Optional<Vocabulary> optionalFoundVocabulary = vocabularyRepository.findById(vocabulary.getId());
        if (optionalFoundVocabulary.isEmpty()) {
            throw new ResourceNotFoundException("Vocabulary not found");
        }

        Vocabulary foundVocabulary = optionalFoundVocabulary.get();
        foundVocabulary.setTitle(vocabulary.getTitle());
        foundVocabulary.setDescription(vocabulary.getDescription());
        foundVocabulary.setUpdatedAt(new Date());

         return vocabularyRepository.save(foundVocabulary);
    }

    @Transactional
    @Override
    public Vocabulary partialUpdate(Vocabulary vocabulary) throws ResourceNotFoundException{
        Optional<Vocabulary> optionalFoundVocabulary = vocabularyRepository.findById(vocabulary.getId());
        if (optionalFoundVocabulary.isEmpty()) {
            throw new ResourceNotFoundException("Vocabulary not found");
        }

        Vocabulary foundVocabulary = optionalFoundVocabulary.get();
        foundVocabulary.setUpdatedAt(new Date());

        if (vocabulary.getTitle() != null) {
           foundVocabulary.setTitle(vocabulary.getTitle());
        }

        if (vocabulary.getDescription() != null) {
            foundVocabulary.setDescription(vocabulary.getDescription());
        }

        return vocabularyRepository.save(foundVocabulary);
    }

    @Override
    public Vocabulary getVocabulary(UUID id) throws ResourceNotFoundException {
        Optional<Vocabulary> vocabulary = vocabularyRepository.findById(id);
        if (vocabulary.isEmpty()) {
            throw new ResourceNotFoundException("Vocabulary not found");
        }

        return vocabulary.get();
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
