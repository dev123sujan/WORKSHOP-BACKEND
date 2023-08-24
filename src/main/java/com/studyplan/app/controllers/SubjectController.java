package com.studyplan.app.controllers;

import com.studyplan.app.entities.Subject;
import com.studyplan.app.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{subjectId}/subject")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long subjectId) {
        Subject subject = subjectService.getSubjectById(subjectId);
        if (subject != null) {
            return ResponseEntity.ok(subject);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject createdSubject = subjectService.createSubject(subject);
        return ResponseEntity.ok(createdSubject);
    }

    @PutMapping("/{subjectId}/updateSubject")
    public ResponseEntity<?> updateSubject(@PathVariable Long subjectId, @RequestBody Subject subject) {
        Subject updatedSubject = subjectService.updateSubject(subjectId, subject);
        if (updatedSubject != null) {
            return ResponseEntity.ok(updatedSubject);
        } else {
            return new ResponseEntity<>("Subject Not found with this Id", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{subjectId}/deleteSubject")
    public ResponseEntity<?> deleteSubject(@PathVariable Long subjectId) {
        boolean deleted = subjectService.deleteSubject(subjectId);
        if (deleted) {
            return ResponseEntity.ok("Subject Deleted Successfully");
        } else {
            return new ResponseEntity<>("Subject Not found with this Id", HttpStatus.NOT_FOUND);
        }
    }
}