package service;

import dao.StudentInterestCourseDao;
import dao.impl.StudentInterestCourseDaoImpl;

import java.util.List;

public class StudentInterestCourseService {
    private final StudentInterestCourseDao interestCourseDao = new StudentInterestCourseDaoImpl();

    public void addInterestCourse(Integer studentId, String courseName) {
        interestCourseDao.addInterestCourse(studentId, courseName);
    }

    public List<String> getInterestCourses(Integer studentId) {
        return interestCourseDao.getInterestCourses(studentId);
    }
}