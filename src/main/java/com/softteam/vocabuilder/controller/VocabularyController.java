package com.softteam.vocabuilder.controller;

import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import com.softteam.vocabuilder.service.VocabularyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/vocabulary")
public class VocabularyController {

    @Autowired
    private VocabularyServiceImp vocabularyServiceImp;

    @PostMapping
    public ResponseEntity<Vocabulary> createVocabuilder(@RequestBody Vocabulary vocabulary){
        vocabularyServiceImp.create(vocabulary);
        return new ResponseEntity<Vocabulary>(HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Vocabulary> updateVocabuilder(@PathVariable(value = "id")UUID id, @RequestBody Vocabulary vocabulary){
        vocabulary.setId(id);
        vocabularyServiceImp.update(vocabulary);
        return new ResponseEntity<Vocabulary>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vocabulary>> getVocabulary(@PathVariable(value = "id") UUID id){
        Optional<Vocabulary> vocabulary = vocabularyServiceImp.getVocabulary(id);
        return new ResponseEntity<Optional<Vocabulary>>(vocabulary,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Vocabulary>> listVocabulary(){
        return new ResponseEntity<List<Vocabulary>>(vocabularyServiceImp.findAllVocabularies(),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVocabuilder(@PathVariable(value = "id") UUID id){
        vocabularyServiceImp.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
