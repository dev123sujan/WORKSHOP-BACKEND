package com.studyplan.app.controllers;

import com.studyplan.app.entities.Chapter;
import com.studyplan.app.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    @PostMapping("/{subjectId}")
    public ResponseEntity<?> createNewChapter(@RequestBody Chapter chapter, @PathVariable("subjectId")Long subjectId){
        Chapter savedChapter = chapterService.saveOneChapter(chapter, subjectId);
        if(savedChapter == null){
            return new ResponseEntity<>("Subject Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(savedChapter, HttpStatus.CREATED);
    }

    @GetMapping("/{chapterId}/chapter")
    public ResponseEntity<?> getChapter(@PathVariable(value = "chapterId")Long chapterId){
        Chapter chapter = chapterService.getOneChapter(chapterId);
        if(chapter == null){
            return new ResponseEntity<>("Chapter Not found with this Id: "+chapterId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chapter, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Chapter>> getAllChapter(){
        List<Chapter> allChapter = chapterService.getAllChapter();
        return new ResponseEntity<>(allChapter, HttpStatus.CREATED);
    }

    @DeleteMapping("/{chapterId}/deleteChapter")
    public ResponseEntity<String> deleteChapter(@PathVariable("chapterId")Long chapterId){
        boolean chapterDeleted = chapterService.deleteOneChapter(chapterId);
        if(!chapterDeleted){
            return new ResponseEntity<>("Chapter Not found with this Id: "+chapterId,HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>("Chapter Deleted Successfully",HttpStatus.OK);
    }

    @PutMapping("/{chapterId}/updateChapter")
    public ResponseEntity<?> updateChapter(@RequestBody Chapter chapter, @PathVariable("chapterId")Long chapterId){
        Chapter updatedChapter = chapterService.updateOneChapter(chapter, chapterId);
        if (updatedChapter != null) {
            return new ResponseEntity<>(updatedChapter, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Chapter Not found with this Id: "+chapterId, HttpStatus.NOT_FOUND);
        }
    }
}
