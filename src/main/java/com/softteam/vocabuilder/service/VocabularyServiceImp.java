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
    public Vocabulary create(VocabularyDTO vocabularyDTO) {
        //Deberia validar aqui tambien que no hay datos con valor null
        //o solamente si cumplen con alguna caracteristica del negocio?
        //deberia enviar algun error aqui en capturarlo en el controller?
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setTitle(vocabularyDTO.getTitle());
        vocabulary.setDescription(vocabularyDTO.getDescription());
        vocabulary.setCreatedAt(new Date());
        vocabulary.setUpdatedAt(new Date());

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
