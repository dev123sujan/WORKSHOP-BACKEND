package com.studyplan.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chapterId;

    @Column(name = "chapterTitle",nullable = false, length = 255)
    private String chapterTitle;

    @Lob
    @Column(name = "description", length = 255, nullable = false)
    private String chapterDescription;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "subject_id")
    private Subject subject;




}
