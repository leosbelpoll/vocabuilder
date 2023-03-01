package com.softteam.vocabuilder.persistence.repository;

import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary,Long> {
}
