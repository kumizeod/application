package service;

import dao.ApplicationDao;
import dao.impl.ApplicationDaoImpl;
import model.Application;

import java.util.List;

public class ApplicationService {
    private final ApplicationDao applicationDao = new ApplicationDaoImpl();

    public void addApplication(Application application) {
        applicationDao.addApplication(application);
    }

    public List<Application> getApplicationsByStudent(Integer studentId) {
        return applicationDao.getApplicationsByStudent(studentId);
    }
}