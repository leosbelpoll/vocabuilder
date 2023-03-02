package com.softteam.vocabuilder.persistence.repository;

import com.softteam.vocabuilder.persistence.entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.UUID;

@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, UUID> {
=======
@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary,Long> {
>>>>>>> f93a88afac596b8434e4aa7a030f67215dee11fc
}
