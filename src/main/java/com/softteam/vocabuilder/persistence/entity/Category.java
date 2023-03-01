package com.softteam.vocabuilder.persistence.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String color;

    private Date createdAt;
    private Date updatedAt;

}
