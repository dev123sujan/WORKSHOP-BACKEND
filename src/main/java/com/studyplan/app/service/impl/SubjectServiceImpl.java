package com.studyplan.app.service.impl;

import com.studyplan.app.entities.Subject;
import com.studyplan.app.repositories.SubjectRepository;
import com.studyplan.app.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        return optionalSubject.orElse(null);
    }

    @Override
    public Subject createSubject(Subject subject) {
        subject.setCreatedAt(new Date());
        System.out.println(new Date());
        return subjectRepository.save(subject);
    }

    @Override
    public Subject updateSubject(Long id, Subject subject) {
        Optional<Subject> subjectById = subjectRepository.findById(id);
        if (subjectById.isPresent()) {
            Subject existingSubject = subjectById.get();
            existingSubject.setSubjectName(subject.getSubjectName());
            return subjectRepository.save(existingSubject);
        }
        return null;
    }

    @Override
    public boolean deleteSubject(Long id) {
        Optional<Subject> subjectById = subjectRepository.findById(id);
        if (subjectById.isPresent()) {
            subjectRepository.delete(subjectById.get());
            return true;
        }
        return false;
    }
}
