package dao.factory;

import dao.AdmissionResultDao;
import dao.ApplicationDao;
import dao.impl.AdmissionResultDaoImpl;
import dao.impl.ApplicationDaoImpl;

public class DaoFactory {
    public static AdmissionResultDao createAdmissionResultDao() {
        return new AdmissionResultDaoImpl();
    }
    public static ApplicationDao createApplicationDao() {
        return new ApplicationDaoImpl();
    }
}
