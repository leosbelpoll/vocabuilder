package com.softteam.vocabuilder.controller;

import com.softteam.vocabuilder.exections.VocabularyNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import com.softteam.vocabuilder.service.VocabularyServiceImp;
import com.softteam.vocabuilder.service.dto.VocabularyDTO;
import com.softteam.vocabuilder.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vocabulary")
public class VocabularyController {

    @Autowired
    private VocabularyServiceImp vocabularyServiceImp;

    @PostMapping
    public ResponseEntity<Vocabulary> createVocabulary(@RequestBody VocabularyDTO vocabularyDTO) {
        Vocabulary vocabulary = new Vocabulary();
        if (vocabularyDTO.getTitle() == null || vocabularyDTO.getDescription() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        vocabulary.setTitle(vocabularyDTO.getTitle());
        vocabulary.setDescription(vocabularyDTO.getDescription());
        vocabulary.setCreatedAt(new Date());
        vocabulary.setUpdatedAt(new Date());

        Vocabulary newVocabulary = vocabularyServiceImp.create(vocabulary);
        return new ResponseEntity<Vocabulary>(newVocabulary,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vocabulary> updateVocabulary(@PathVariable(value = "id") String id, @RequestBody Vocabulary vocabulary) {
        UUID uuidID = UuidUtil.getUUID(id);
        Optional<Vocabulary> vocabularyOptional = Optional.of(new Vocabulary());
        try {
            vocabularyOptional = vocabularyServiceImp.getVocabulary(uuidID);
        } catch (RuntimeException e) {
            throw new VocabularyNotFoundException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (vocabulary.getTitle() == null) {
            vocabulary.setTitle(vocabularyOptional.get().getTitle());
        }
        if (vocabulary.getDescription() == null) {
            vocabulary.setDescription(vocabularyOptional.get().getDescription());
        }
        vocabulary.setId(uuidID);
        vocabulary.setCreatedAt(vocabularyOptional.get().getCreatedAt());
        vocabulary.setUpdatedAt(new Date());

        System.out.println("Datos del vocabulary" + vocabulary);
        Vocabulary updateVocabulary = vocabularyServiceImp.update(vocabulary);
        return new ResponseEntity<Vocabulary>(updateVocabulary,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vocabulary>> getVocabulary(@PathVariable(value = "id") String id) {
        UUID uuidID = UuidUtil.getUUID(id);
        Optional<Vocabulary> vocabulary = Optional.of(new Vocabulary());
        try {
            vocabulary = vocabularyServiceImp.getVocabulary(uuidID);
        } catch (RuntimeException e) {
            throw new VocabularyNotFoundException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Optional<Vocabulary>>(vocabulary, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Vocabulary>> listVocabulary() {
        List<Vocabulary> vocabularyList = vocabularyServiceImp.findAllVocabularies();
        return new ResponseEntity<List<Vocabulary>>(vocabularyList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVocabuilder(@PathVariable(value = "id") String id) {
        UUID uuidID = UuidUtil.getUUID(id);
        Optional<Vocabulary> vocabulary = Optional.of(new Vocabulary());
        try {
            vocabulary = vocabularyServiceImp.getVocabulary(uuidID);
        } catch (RuntimeException e) {
            throw new VocabularyNotFoundException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        vocabularyServiceImp.delete(uuidID);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
