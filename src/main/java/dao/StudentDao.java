package dao;

import model.Student;

import java.util.List;

public interface StudentDao {
    void addStudent(Student student);
    void deleteStudent(int id);
    Student getStudentById(int id);
    List<Student> getAllStudents();
}