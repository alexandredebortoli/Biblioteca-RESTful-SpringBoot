package com.saks.biblioteca.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;

@Data
@Entity
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
}
