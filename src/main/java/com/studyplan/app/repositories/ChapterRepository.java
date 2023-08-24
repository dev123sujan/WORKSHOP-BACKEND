package com.studyplan.app.repositories;

import com.studyplan.app.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
