package service;

import dao.AdmissionResultDao;
import dao.impl.AdmissionResultDaoImpl;
import model.AdmissionResult;

import java.util.List;

public class AdmissionResultService {
    private final AdmissionResultDao admissionResultDao = new AdmissionResultDaoImpl();

    public void addAdmissionResult(AdmissionResult r) {
        admissionResultDao.addAdmissionResult(r);
    }

    public List<AdmissionResult> getResultsByUniversity(Integer universityId) {
        return admissionResultDao.getResultsByUniversity(universityId);
    }

    public AdmissionResult getResultByStudent(Integer studentId) {
        return admissionResultDao.getResultByStudent(studentId);
    }
}