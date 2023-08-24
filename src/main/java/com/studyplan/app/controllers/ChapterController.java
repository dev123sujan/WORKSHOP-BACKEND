package com.studyplan.app.controllers;

import com.studyplan.app.entities.Chapter;
import com.studyplan.app.entities.Subject;
import com.studyplan.app.repositories.ChapterRepository;
import com.studyplan.app.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    @PostMapping("/{subjectId}")
    public ResponseEntity<?> createNewChapter(@RequestBody Chapter chapter, @PathVariable("subjectId")Long subjectId){

        Optional<Subject> subjectById = subjectRepository.findById(subjectId);
        if(subjectById.isPresent()){
            Subject existingSubject = subjectById.get();
            chapter.setSubject(existingSubject);
            Chapter savedChapter = chapterRepository.save(chapter);
            return new ResponseEntity<>(savedChapter, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Subject Not Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{chapterId}/chapter")
    public ResponseEntity<?> getChapter(@PathVariable(value = "chapterId")Long chapterId){
        Optional<Chapter> chapterById = chapterRepository.findById(chapterId);
        Chapter chapter = chapterById.orElse(null);
        if(chapter == null){
            return new ResponseEntity<>("Chapter Not found with this Id: "+chapterId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chapter, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Chapter>> getAllChapter(){
        List<Chapter> allChapter = chapterRepository.findAll();
        return new ResponseEntity<>(allChapter, HttpStatus.CREATED);
    }

    @DeleteMapping("/{chapterId}/deleteChapter")
    public ResponseEntity<String> deleteChapter(@PathVariable("chapterId")Long chapterId){

        Optional<Chapter> chapterById = chapterRepository.findById(chapterId);
        if(chapterById.isPresent()){
            chapterRepository.deleteById(chapterId);
            return new ResponseEntity<>("Chapter Deleted Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Chapter Not found with this Id: "+chapterId,HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{chapterId}/updateChapter")
    public ResponseEntity<?> updateChapter(@RequestBody Chapter chapter, @PathVariable("chapterId")Long chapterId){

        Optional<Chapter> chapterById = chapterRepository.findById(chapterId);
        if(chapterById.isPresent()){
            Chapter chapterObj = chapterById.get();
            chapterObj.setChapterTitle(chapter.getChapterTitle());
            chapterObj.setChapterDescription(chapter.getChapterDescription());
            Chapter updatedChapter = chapterRepository.save(chapterObj);
            return new ResponseEntity<>(updatedChapter, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Chapter Not found with this Id: "+chapterId, HttpStatus.NOT_FOUND);
    }
}
