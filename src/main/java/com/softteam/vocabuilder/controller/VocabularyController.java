package com.softteam.vocabuilder.controller;

import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import com.softteam.vocabuilder.service.VocabularyServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    @PatchMapping("/update/{id}")
    public ResponseEntity<Vocabulary> updateVocabuilder(@PathVariable(value = "id")Long id, @RequestBody Vocabulary vocabulary){
        vocabulary.setId(id);
        vocabularyServiceImp.update(vocabulary);
        return new ResponseEntity<Vocabulary>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vocabulary>> readVocabuilder(@PathVariable(value = "id") Long id){
        Optional<Vocabulary> vocabulary = vocabularyServiceImp.read(id);
        return new ResponseEntity<Optional<Vocabulary>>(vocabulary,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteVocabuilder(@PathVariable(value = "id") Long id){
        vocabularyServiceImp.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
