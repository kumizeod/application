package service;

import dao.ApplicationDao;
import dao.factory.DaoFactory;
import dao.impl.ApplicationDaoImpl;
import model.Application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationService {
    //private final ApplicationDao applicationDao = new ApplicationDaoImpl();
    private final ApplicationDao applicationDao = DaoFactory.createApplicationDao();

    public void addApplication(Application application) {
        applicationDao.addApplication(application);
    }

    public List<Application> getApplicationsByStudent(Integer studentId) {
        return applicationDao.getApplicationsByStudent(studentId);
    }

    /**
     * 获取所有填报过志愿的学生ID列表（去重）
     */
    public List<Integer> getAllStudentIdsWithApplications() {
        List<Application> allApps = applicationDao.getAllApplications();
        Set<Integer> studentIds = new HashSet<>();
        for (Application app : allApps) {
            if (app.getStudentId() != null) {
                studentIds.add(app.getStudentId());
            }
        }
        return new ArrayList<>(studentIds);
    }
}