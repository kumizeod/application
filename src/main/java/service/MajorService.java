package service;

import dao.MajorDao;
import dao.impl.MajorDaoImpl;
import model.Major;

import java.util.List;

public class MajorService {
    private final MajorDao majorDao = new MajorDaoImpl();

    public void addMajor(Major major) {
        majorDao.addMajor(major);
    }

    public List<Major> getMajorsByDepartment(Integer departmentId) {
        return majorDao.getMajorsByDepartment(departmentId);
    }

    public Major getMajorById(Integer id) {
        return majorDao.getMajorById(id);
    }
}