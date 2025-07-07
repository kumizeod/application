package dao;

import model.Department;

import java.util.List;

public interface DepartmentDao {
    void addDepartment(Department department);
    List<Department> getDepartmentsByUniversity(int universityId);
    Department getDepartmentById(int id);
}