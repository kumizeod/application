package service;

import dao.AdmissionResultDao;
import dao.factory.DaoFactory;
import dao.impl.AdmissionResultDaoImpl;
import model.AdmissionResult;
import model.Application;
import service.ApplicationService;

import java.util.List;

public class AdmissionResultService {
    //private final AdmissionResultDao admissionResultDao = new AdmissionResultDaoImpl();
    private final AdmissionResultDao admissionResultDao = DaoFactory.createAdmissionResultDao();

    // 可注入或直接 new，视你的项目结构
    private final ApplicationService applicationService = new ApplicationService();

    public void addAdmissionResult(AdmissionResult r) {
        admissionResultDao.addAdmissionResult(r);
    }

    public List<AdmissionResult> getResultsByUniversity(Integer universityId) {
        return admissionResultDao.getResultsByUniversity(universityId);
    }

    public AdmissionResult getResultByStudent(Integer studentId) {
        return admissionResultDao.getResultByStudent(studentId);
    }

    // ====== 简单的模拟录取分配 ======
    public void allocateAdmission() {
        // 获取所有填报过志愿的学生ID
        List<Integer> studentIds = applicationService.getAllStudentIdsWithApplications();
        for (Integer studentId : studentIds) {
            // 如果已录取，跳过
            if (getResultByStudent(studentId) != null) continue;

            List<Application> apps = applicationService.getApplicationsByStudent(studentId);
            if (apps == null || apps.isEmpty()) continue;

            boolean admitted = false;
            for (Application app : apps) {
                // 依次尝试3个专业
                Integer[] majorIds = {app.getFirstMajorId(), app.getSecondMajorId(), app.getThirdMajorId()};
                for (Integer majorId : majorIds) {
                    if (majorId == null) continue;
                    // 简单起见，不判断专业容量
                    AdmissionResult result = new AdmissionResult();
                    result.setStudentId(studentId);
                    result.setUniversityId(app.getUniversityId());
                    result.setMajorId(majorId);
                    addAdmissionResult(result);
                    admitted = true;
                    break;
                }
                // 如果同意调剂但前3个专业都没录取，可以在此扩展调剂逻辑（这里略）
                if (admitted) break;
            }
        }
    }
}