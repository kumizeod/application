package dao;

import java.util.List;

public interface StudentInterestCourseDao {
    void addInterestCourse(int studentId, String courseName);
    List<String> getInterestCourses(int studentId);
}