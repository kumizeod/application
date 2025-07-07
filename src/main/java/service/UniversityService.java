package service;

import dao.UniversityDao;
import dao.impl.UniversityDaoImpl;
import model.University;

import java.util.List;

public class UniversityService {
    private final UniversityDao universityDao = new UniversityDaoImpl();

    public void addUniversity(University university) {
        universityDao.addUniversity(university);
    }

    public University getUniversityById(Integer id) {
        return universityDao.getUniversityById(id);
    }

    public List<University> getAllUniversities() {
        return universityDao.getAllUniversities();
    }
}