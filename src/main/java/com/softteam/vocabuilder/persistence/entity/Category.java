package com.softteam.vocabuilder.persistence.entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categorys")
public class Category {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "color",nullable = false)
    private String color;

    @Column(name = "createdAt",nullable = false)
    private Date createdAt;

    @Column(name = "updatedAt",nullable = true)
    private Date updatedAt;

}
