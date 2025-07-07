package dao;

import model.Major;

import java.util.List;

public interface MajorDao {
    void addMajor(Major major);
    List<Major> getMajorsByDepartment(int departmentId);
    Major getMajorById(int id);
}