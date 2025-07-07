package service;

import dao.DepartmentDao;
import dao.impl.DepartmentDaoImpl;
import model.Department;

import java.util.List;

public class DepartmentService {
    private final DepartmentDao departmentDao = new DepartmentDaoImpl();

    public void addDepartment(Department department) {
        departmentDao.addDepartment(department);
    }

    public List<Department> getDepartmentsByUniversity(Integer universityId) {
        return departmentDao.getDepartmentsByUniversity(universityId);
    }

    public Department getDepartmentById(Integer id) {
        return departmentDao.getDepartmentById(id);
    }
}