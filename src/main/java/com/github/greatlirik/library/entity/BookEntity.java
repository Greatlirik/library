package com.github.greatlirik.library.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name="genre", nullable = false)
    private String genre;
    @Column(name="year", nullable = false)
    private Integer year;
    @Column(name="quantity", nullable = false)
    private Integer quantity;
    @Column(name="free", nullable = false)
    private Boolean free;



    //TODO add many to many here with authors
}
