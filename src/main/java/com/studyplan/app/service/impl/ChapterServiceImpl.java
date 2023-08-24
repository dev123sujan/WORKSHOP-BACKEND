package com.studyplan.app.service.impl;

import com.studyplan.app.entities.Chapter;
import com.studyplan.app.entities.Subject;
import com.studyplan.app.repositories.ChapterRepository;
import com.studyplan.app.repositories.SubjectRepository;
import com.studyplan.app.repositories.UserRepository;
import com.studyplan.app.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Chapter saveOneChapter(Chapter chapter, Long subjectId) {
        Optional<Subject> subjectById = subjectRepository.findById(subjectId);
        if(subjectById.isPresent()){
            Subject existingSubject = subjectById.get();
            chapter.setSubject(existingSubject);
            Chapter savedChapter = chapterRepository.save(chapter);
            return savedChapter;
        }
        return null;
    }

    @Override
    public Chapter getOneChapter(Long chapterId) {
        Optional<Chapter> chapterById = chapterRepository.findById(chapterId);
        return chapterById.orElse(null);
    }

    @Override
    public boolean deleteOneChapter(Long chapterId) {
        Optional<Chapter> chapterById = chapterRepository.findById(chapterId);
        if(chapterById.isPresent()){
            chapterRepository.deleteById(chapterId);
            return true;
        }
        return false;
    }

    @Override
    public Chapter updateOneChapter(Chapter chapter, Long chapterId) {
        Optional<Chapter> chapterById = chapterRepository.findById(chapterId);
        if(chapterById.isPresent()){
            Chapter chapterObj = chapterById.get();
            chapterObj.setChapterTitle(chapter.getChapterTitle());
            chapterObj.setChapterDescription(chapter.getChapterDescription());
            Chapter updatedChapter = chapterRepository.save(chapterObj);
            return updatedChapter;
        }
     return null;
    }

    @Override
    public List<Chapter> getAllChapter() {
        List<Chapter> allChapter = chapterRepository.findAll();
        return allChapter;
    }
}
