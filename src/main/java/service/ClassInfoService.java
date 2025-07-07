package service;

import dao.ClassInfoDao;
import dao.impl.ClassInfoDaoImpl;
import model.ClassInfo;

import java.util.List;

public class ClassInfoService {
    private final ClassInfoDao classInfoDao = new ClassInfoDaoImpl();

    public void addClassInfo(ClassInfo c) {
        classInfoDao.addClassInfo(c);
    }

    public List<ClassInfo> getAllClasses() {
        return classInfoDao.getAllClasses();
    }

    public ClassInfo getClassInfoById(Integer id) {
        return classInfoDao.getClassInfoById(id);
    }
}