package dao;

import model.ClassInfo;

import java.util.List;

public interface ClassInfoDao {
    void addClassInfo(ClassInfo c);
    List<ClassInfo> getAllClasses();
    ClassInfo getClassInfoById(int id);
}