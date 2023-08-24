package com.studyplan.app.service;

import com.studyplan.app.entities.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubjects();

    Subject getSubjectById(Long id);

    Subject createSubject(Subject subject);

    Subject updateSubject(Long id, Subject subject);

    boolean deleteSubject(Long id);
}
