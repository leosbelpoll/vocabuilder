package com.softteam.vocabuilder.controller;

import com.softteam.vocabuilder.exections.ResourceNotFoundException;
import com.softteam.vocabuilder.exections.VocabularyNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import com.softteam.vocabuilder.service.IVocabularyService;
import com.softteam.vocabuilder.service.dto.UpdateVocabularyRequestDTO;
import com.softteam.vocabuilder.service.dto.VocabularyDTO;
import com.softteam.vocabuilder.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vocabulary")
public class VocabularyController {

    @Autowired
    private IVocabularyService vocabularyService;

    @PostMapping
    public ResponseEntity<Vocabulary> createVocabulary(@RequestBody @Validated VocabularyDTO vocabularyDTO) {
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setTitle(vocabularyDTO.getTitle());
        vocabulary.setDescription(vocabularyDTO.getDescription());
        vocabulary.setCreatedAt(new Date());
        vocabulary.setUpdatedAt(new Date());

        Vocabulary newVocabulary = vocabularyService.create(vocabulary);
        return new ResponseEntity<Vocabulary>(newVocabulary, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateVocabulary(@PathVariable(value = "id") String id, @RequestBody Vocabulary vocabulary) {
        UUID uuidID = UuidUtil.getUUID(id);
        vocabulary.setId(uuidID);

        try {
            Vocabulary updatedVocabulary = vocabularyService.partialUpdate(vocabulary);
            return new ResponseEntity<>(updatedVocabulary, HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            return new ResponseEntity<>("Oops, we couldn't find the vocabulary", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVocabulary(@PathVariable(value = "id") String id, @RequestBody @Validated UpdateVocabularyRequestDTO vocabularyDto) {
        UUID uuidID = UuidUtil.getUUID(id);
        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setId(uuidID);
        vocabulary.setTitle(vocabularyDto.getTitle());
        vocabulary.setDescription(vocabularyDto.getDescription());

        try {
            Vocabulary updatedVocabulary = vocabularyService.update(vocabulary);
            return new ResponseEntity<>(updatedVocabulary, HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            return new ResponseEntity<>("Oops, we couldn't find the vocabulary", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>("Sorry, something wrong happened, we're working to solve it", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vocabulary> getVocabulary(@PathVariable(value = "id") String id) {
        UUID uuidID = UuidUtil.getUUID(id);
        Vocabulary vocabulary = new Vocabulary();
        try {
            vocabulary = vocabularyService.getVocabulary(uuidID);
            return new ResponseEntity<Vocabulary>(vocabulary, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new VocabularyNotFoundException("Oops, we couldn't find the vocabulary", HttpStatus.BAD_REQUEST);
        }
        //deberia poner otro catch para si pasa algo que no estemos atrapando
    }

    @GetMapping
    public ResponseEntity<List<Vocabulary>> listVocabulary() {
        List<Vocabulary> vocabularyList = vocabularyService.findAllVocabularies();
        return new ResponseEntity<List<Vocabulary>>(vocabularyList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVocabuilder(@PathVariable(value = "id") String id) {
        UUID uuidID = UuidUtil.getUUID(id);
        try {
            vocabularyService.delete(uuidID);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new VocabularyNotFoundException("Oops, we couldn't find the vocabulary", HttpStatus.NOT_FOUND);
        }
    }
}
