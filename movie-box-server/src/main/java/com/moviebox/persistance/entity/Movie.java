package com.moviebox.persistance.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author Shayan
 * @since 6/25/2020
 */
@Entity
@Table(name = "movie", uniqueConstraints = {
        @UniqueConstraint(name = "UK_MOVIE_1", columnNames = {"name"})},
        indexes = {
                @Index(name = "MOVIE_NAME", columnList = "name"),
                @Index(name = "MOVIE_DESCRIPTION", columnList = "description")
        })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MOVIE_SEQ")
    @SequenceGenerator(sequenceName = "movie_seq", allocationSize = 1, name = "MOVIE_SEQ")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", columnDefinition = "NVARCHAR2(100)", nullable = false)
    @NotNull
    private String name;

    @Column(name = "description", columnDefinition = "NVARCHAR2(256)")
    private String description;

    @Column(name = "director", columnDefinition = "NVARCHAR2(100)")
    @NotNull
    private String director;

    @Column(name = "writer", columnDefinition = "NVARCHAR2(100)")
    @NotNull
    private String writer;

    @Column(name = "actors", columnDefinition = "NVARCHAR2(500)")
    @NotNull
    private String actors;

    @Lob
    @NotNull
    @Column(name = "poster", columnDefinition = "BLOB")
    private byte[] poster;

    @NotNull
    @Column(name = "content_location", columnDefinition = "NVARCHAR2(256)")
    private String contentLocation;

}
