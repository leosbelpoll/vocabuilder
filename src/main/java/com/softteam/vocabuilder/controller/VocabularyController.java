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
        //Internal Server Error al faltar un columna
        if (vocabularyDTO.getTitle() == null || vocabularyDTO.getDescription() == null) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        vocabularyServiceImp.create(vocabularyDTO);
        return new ResponseEntity<Vocabulary>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vocabulary> updateVocabulary(@PathVariable(value = "id") String id, @RequestBody Vocabulary vocabulary) {
        UUID uuidID = UuidUtil.validateUUIDType(id);
        Optional<Vocabulary> vocabulary1 = Optional.of(new Vocabulary());
        try {
            vocabulary1 = vocabularyServiceImp.getVocabulary(uuidID);
        } catch (RuntimeException e) {
            throw new VocabularyNotFoundException(e.getMessage(), HttpStatus.NO_CONTENT);
        }
        if (vocabulary1.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (vocabulary.getTitle() == null) {
            vocabulary.setTitle(vocabulary1.get().getTitle());
        }
        if (vocabulary.getDescription() == null) {
            vocabulary.setDescription(vocabulary1.get().getDescription());
        }
        vocabulary.setId(uuidID);
        vocabulary.setCreatedAt(vocabulary1.get().getCreatedAt());
        vocabulary.setUpdatedAt(new Date());

        System.out.println("Datos del vocabulary" + vocabulary);
        vocabularyServiceImp.update(vocabulary);
        return new ResponseEntity<Vocabulary>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vocabulary>> getVocabulary(@PathVariable(value = "id") String id) {
        UUID uuidID = UuidUtil.validateUUIDType(id);
        Optional<Vocabulary> vocabulary = Optional.of(new Vocabulary());
        try {
            vocabulary = vocabularyServiceImp.getVocabulary(uuidID);
        } catch (RuntimeException e) {
            throw new VocabularyNotFoundException(e.getMessage(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Optional<Vocabulary>>(vocabulary, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Vocabulary>> listVocabulary() {
        List<Vocabulary> vocabularyList = vocabularyServiceImp.findAllVocabularies();
        if (vocabularyList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Vocabulary>>(vocabularyServiceImp.findAllVocabularies(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVocabuilder(@PathVariable(value = "id") String id) {
        UUID uuidID = UuidUtil.validateUUIDType(id);
        Optional<Vocabulary> vocabulary = Optional.of(new Vocabulary());
        try {
            vocabulary = vocabularyServiceImp.getVocabulary(uuidID);
        } catch (RuntimeException e) {
            throw new VocabularyNotFoundException(e.getMessage(), HttpStatus.NO_CONTENT);
        }
        vocabularyServiceImp.delete(uuidID);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
