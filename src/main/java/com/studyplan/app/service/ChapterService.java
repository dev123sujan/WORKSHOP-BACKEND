package com.studyplan.app.service;

import com.studyplan.app.entities.Chapter;

import java.util.List;

public interface ChapterService {

    public Chapter saveOneChapter(Chapter chapter, Long subjectId);

    public Chapter getOneChapter(Long chapterId);

    public boolean deleteOneChapter(Long chapterId);

    public Chapter updateOneChapter(Chapter chapter, Long chapterId);

    public List<Chapter> getAllChapter();

}
