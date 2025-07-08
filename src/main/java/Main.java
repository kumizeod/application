import model.*;
import service.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 实例化Service对象
        StudentService studentService = new StudentService();
        UniversityService universityService = new UniversityService();
        DepartmentService departmentService = new DepartmentService();
        MajorService majorService = new MajorService();
        ApplicationService applicationService = new ApplicationService();
        AdmissionResultService admissionResultService = new AdmissionResultService();

        while (true) {
            System.out.println("===== 高考志愿管理系统 =====");
            System.out.println("1. 学生端");
            System.out.println("2. 学校端");
            System.out.println("3. 系统端");
            System.out.println("0. 退出");
            System.out.print("请选择：");
            int mainChoice = input.nextInt();
            input.nextLine();

            switch (mainChoice) {
                case 1: // 学生端
                    studentMenu(input, studentService, universityService, majorService, applicationService, admissionResultService);
                    break;
                case 2: // 学校端
                    schoolMenu(input, universityService, admissionResultService);
                    break;
                case 3: // 系统端
                    systemMenu(input, admissionResultService);
                    break;
                case 0:
                    System.out.println("已退出。");
                    return;
                default:
                    System.out.println("无效操作！");
            }
        }
    }

    private static void studentMenu(Scanner input, StudentService studentService,
                                    UniversityService universityService, MajorService majorService,
                                    ApplicationService applicationService, AdmissionResultService admissionResultService) {
        System.out.print("请输入学生ID：");
        int studentId = input.nextInt();
        input.nextLine();
        Student student = studentService.getStudentById(studentId);
        if (student == null) {
            System.out.println("学生不存在！");
            return;
        }
        while (true) {
            System.out.println("===== 学生端 =====");
            System.out.println("1. 填报志愿");
            System.out.println("2. 查询志愿");
            System.out.println("3. 查询录取结果");
            System.out.println("0. 返回主菜单");
            System.out.print("请选择：");
            int stuChoice = input.nextInt();
            input.nextLine();

            switch (stuChoice) {
                case 1:
                    // 填报志愿（和你的原代码一致，略）
                    System.out.print("是否同意专业调剂(yes/no)：");
                    String adjustStr = input.nextLine();
                    boolean allowAdjustment = "yes".equalsIgnoreCase(adjustStr);

                    for (int i = 1; i <= 10; i++) {
                        System.out.println("第" + i + "志愿：");
                        System.out.print("学校ID(0结束)：");
                        int universityId = input.nextInt();
                        if (universityId == 0) break;
                        input.nextLine();
                        University university = universityService.getUniversityById(universityId);
                        if (university == null) {
                            System.out.println("学校不存在，跳过该志愿！");
                            continue;
                        }
                        List<Integer> majorIds = new ArrayList<>();
                        for (int j = 1; j <= 3; j++) {
                            System.out.print("第" + j + "专业ID(0结束)：");
                            int majorId = input.nextInt();
                            if (majorId == 0) break;
                            input.nextLine();
                            Major major = majorService.getMajorById(majorId);
                            if (major == null) {
                                System.out.println("专业不存在，跳过！");
                                continue;
                            }
                            majorIds.add(majorId);
                        }
                        Application app = new Application();
                        app.setStudentId(studentId);
                        app.setUniversityId(universityId);
                        app.setFirstMajorId(majorIds.size() >= 1 ? majorIds.get(0) : null);
                        app.setSecondMajorId(majorIds.size() >= 2 ? majorIds.get(1) : null);
                        app.setThirdMajorId(majorIds.size() >= 3 ? majorIds.get(2) : null);
                        app.setAllowAdjustment(allowAdjustment);
                        app.setPriority(i);
                        applicationService.addApplication(app);
                    }
                    System.out.println("志愿填报完成！");
                    break;
                case 2:
                    // 查询志愿
                    List<Application> apps = applicationService.getApplicationsByStudent(studentId);
                    if (apps.isEmpty()) {
                        System.out.println("该学生未填报志愿。");
                    } else {
                        for (Application app : apps) {
                            System.out.println("志愿顺序：" + app.getPriority() + " | 学校ID：" + app.getUniversityId() +
                                    " | 专业1：" + app.getFirstMajorId() +
                                    " | 专业2：" + app.getSecondMajorId() +
                                    " | 专业3：" + app.getThirdMajorId() +
                                    " | 调剂：" + (app.getAllowAdjustment() ? "是" : "否"));
                        }
                    }
                    break;
                case 3:
                    // 查询录取结果
                    AdmissionResult result = admissionResultService.getResultByStudent(studentId);
                    if (result == null) {
                        System.out.println("尚未被录取。");
                    } else {
                        System.out.println("录取学校ID：" + result.getUniversityId() +
                                "，专业ID：" + result.getMajorId());
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("无效操作！");
            }
        }
    }

    private static void schoolMenu(Scanner input, UniversityService universityService, AdmissionResultService admissionResultService) {
        System.out.print("请输入学校ID：");
        int uniId = input.nextInt();
        input.nextLine();
        University university = universityService.getUniversityById(uniId);
        if (university == null) {
            System.out.println("学校不存在！");
            return;
        }
        while (true) {
            System.out.println("===== 学校端 =====");
            System.out.println("1. 查询已录取学生");
            System.out.println("0. 返回主菜单");
            System.out.print("请选择：");
            int schChoice = input.nextInt();
            input.nextLine();

            switch (schChoice) {
                case 1:
                    List<AdmissionResult> results = admissionResultService.getResultsByUniversity(uniId);
                    if (results.isEmpty()) {
                        System.out.println("该学校暂无录取学生。");
                    } else {
                        for (AdmissionResult r : results) {
                            System.out.println("学生ID：" + r.getStudentId() + " | 专业ID：" + r.getMajorId());
                        }
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("无效操作！");
            }
        }
    }

    private static void systemMenu(Scanner input, AdmissionResultService admissionResultService) {
        while (true) {
            System.out.println("===== 系统端 =====");
            System.out.println("1. 自动分配录取结果");
            System.out.println("0. 返回主菜单");
            System.out.print("请选择：");
            int sysChoice = input.nextInt();
            input.nextLine();

            switch (sysChoice) {
                case 1:
                    System.out.println("正在模拟录取分配...");
                    admissionResultService.allocateAdmission();
                    System.out.println("录取分配完成！");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("无效操作！");
            }
        }
    }
}