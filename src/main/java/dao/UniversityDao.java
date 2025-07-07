package dao;

import model.University;

import java.util.List;

public interface UniversityDao {
    void addUniversity(University university);
    University getUniversityById(int id);
    List<University> getAllUniversities();
}