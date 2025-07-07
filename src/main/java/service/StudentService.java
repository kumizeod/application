package service;

import dao.StudentDao;
import dao.impl.StudentDaoImpl;
import model.Student;

import java.util.List;

public class StudentService {
    private final StudentDao studentDao = new StudentDaoImpl();

    public void addStudent(Student student) {
        studentDao.addStudent(student);
    }

    public void deleteStudent(Integer id) {
        studentDao.deleteStudent(id);
    }

    public Student getStudentById(Integer id) {
        return studentDao.getStudentById(id);
    }

    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }
}