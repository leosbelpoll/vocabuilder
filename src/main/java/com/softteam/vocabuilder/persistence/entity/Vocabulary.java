package com.softteam.vocabuilder.persistence.entity;

import lombok.Data;
<<<<<<< HEAD
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vocabularies")
public class Vocabulary {
    @Id
    @Type(type = "uuid-char")
    @GeneratedValue
    private UUID id;
    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "createdAt",nullable = false)
    private Date createdAt;

    @Column(name = "updatedAt", nullable = false)
=======

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "vocabulary")
public class Vocabulary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Date createdAt;
>>>>>>> f93a88afac596b8434e4aa7a030f67215dee11fc
    private Date updatedAt;
}
