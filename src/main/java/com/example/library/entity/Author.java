package com.example.library.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "authors")

// Denna klass representerar en author i databasen
// Här finns namn, födelseår och nationalitet

public class Author {
    @Id
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_year")
    private Integer birthYear;

    private String nationality;

    public Long getAuthorId() { return authorId; }
    public void setAuthorId(Long authorId) { this.authorId = authorId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }
}
