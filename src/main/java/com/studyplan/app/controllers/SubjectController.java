package com.studyplan.app.controllers;

import com.studyplan.app.entities.Subject;
import com.studyplan.app.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return ResponseEntity.ok(subjects);
    }

    @GetMapping("/{subjectId}/subject")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long subjectId) {
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        if (subject.isPresent()) {
            return ResponseEntity.ok(subject.orElse(null));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        subject.setCreatedAt(new Date());
        System.out.println(new Date());
        Subject createdSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(createdSubject);
    }

    @PutMapping("/{subjectId}/updateSubject")
    public ResponseEntity<?> updateSubject(@PathVariable Long subjectId, @RequestBody Subject subject) {
        Optional<Subject> subjectById = subjectRepository.findById(subjectId);
        if (subjectById.isPresent()) {
            Subject existingSubject = subjectById.get();
            existingSubject.setSubjectName(subject.getSubjectName());
            Subject updatedSubject =  subjectRepository.save(existingSubject);
            return ResponseEntity.ok(updatedSubject);
        }
        return new ResponseEntity<>("Subject Not found with this Id", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{subjectId}/deleteSubject")
    public ResponseEntity<?> deleteSubject(@PathVariable Long subjectId) {
        Optional<Subject> subjectById = subjectRepository.findById(subjectId);
        if (subjectById.isPresent()) {
            subjectRepository.delete(subjectById.get());
            return ResponseEntity.ok("Subject Deleted Successfully");
        }
        return new ResponseEntity<>("Subject Not found with this Id", HttpStatus.NOT_FOUND);
    }
}