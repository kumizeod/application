package dao;

import model.Application;

import java.util.List;

public interface ApplicationDao {
    void addApplication(Application app);
    List<Application> getApplicationsByStudent(int studentId);
}