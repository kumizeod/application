/*import model.*;
import service.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 实例化Service对象
        StudentService studentService = new StudentService();
        ClassInfoService classInfoService = new ClassInfoService();
        UniversityService universityService = new UniversityService();
        DepartmentService departmentService = new DepartmentService();
        MajorService majorService = new MajorService();
        ApplicationService applicationService = new ApplicationService();
        AdmissionResultService admissionResultService = new AdmissionResultService();

        while (true) {
            System.out.println("===== 高考志愿管理系统 =====");
            System.out.println("1. 添加学生");
            System.out.println("2. 查询所有学生");
            System.out.println("3. 添加学校");
            System.out.println("4. 查询所有学校");
            System.out.println("5. 添加班级");
            System.out.println("6. 查询所有班级");
            System.out.println("7. 添加院系");
            System.out.println("8. 查询学校下所有院系");
            System.out.println("9. 添加专业");
            System.out.println("10. 查询院系下所有专业");
            System.out.println("0. 退出系统");
            System.out.print("请选择：");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("学生姓名：");
                    String stuName = input.nextLine();
                    System.out.print("性别：");
                    String gender = input.nextLine();
                    System.out.print("班级ID：");
                    int classId = input.nextInt();
                    input.nextLine();
                    ClassInfo classInfo = classInfoService.getClassInfoById(classId);
                    if (classInfo == null) {
                        System.out.println("班级不存在！");
                        break;
                    }
                    Student student = new Student(null, stuName, gender, classId);
                    studentService.addStudent(student);
                    System.out.println("添加成功！");
                    break;
                case 2:
                    List<Student> students = studentService.getAllStudents();
                    for (Student s : students) {
                        System.out.println(s.getId() + " | " + s.getName() + " | " + s.getGender() + " | 班级ID：" + s.getClassId());
                    }
                    break;
                case 3:
                    System.out.print("学校名称：");
                    String uniName = input.nextLine();
                    System.out.print("学校所在地：");
                    String location = input.nextLine();
                    University university = new University(null, uniName, location);
                    universityService.addUniversity(university);
                    System.out.println("添加成功！");
                    break;
                case 4:
                    List<University> universities = universityService.getAllUniversities();
                    for (University u : universities) {
                        System.out.println(u.getId() + " | " + u.getName() + " | " + u.getLocation());
                    }
                    break;
                case 5:
                    System.out.print("班级名称：");
                    String className = input.nextLine();
                    ClassInfo c = new ClassInfo(null, className);
                    classInfoService.addClassInfo(c);
                    System.out.println("添加成功！");
                    break;
                case 6:
                    List<ClassInfo> classes = classInfoService.getAllClasses();
                    for (ClassInfo ci : classes) {
                        System.out.println(ci.getId() + " | " + ci.getName());
                    }
                    break;
                case 7:
                    System.out.print("院系名称：");
                    String depName = input.nextLine();
                    System.out.print("所在学校ID：");
                    int uniId = input.nextInt();
                    input.nextLine();
                    University u = universityService.getUniversityById(uniId);
                    if (u == null) {
                        System.out.println("学校不存在！");
                        break;
                    }
                    Department department = new Department(null, depName, uniId);
                    departmentService.addDepartment(department);
                    System.out.println("添加成功！");
                    break;
                case 8:
                    System.out.print("学校ID：");
                    int universityId = input.nextInt();
                    input.nextLine();
                    List<Department> departments = departmentService.getDepartmentsByUniversity(universityId);
                    for (Department d : departments) {
                        System.out.println(d.getId() + " | " + d.getName() + " | 学校ID：" + d.getUniversityId());
                    }
                    break;
                case 9:
                    System.out.print("专业名称：");
                    String majorName = input.nextLine();
                    System.out.print("所属院系ID：");
                    int depId = input.nextInt();
                    input.nextLine();
                    Department dep = departmentService.getDepartmentById(depId);
                    if (dep == null) {
                        System.out.println("院系不存在！");
                        break;
                    }
                    Major major = new Major(null, majorName, depId);
                    majorService.addMajor(major);
                    System.out.println("添加成功！");
                    break;
                case 10:
                    System.out.print("院系ID：");
                    int departmentId = input.nextInt();
                    input.nextLine();
                    List<Major> majors = majorService.getMajorsByDepartment(departmentId);
                    for (Major m : majors) {
                        System.out.println(m.getId() + " | " + m.getName() + " | 院系ID：" + m.getDepartmentId());
                    }
                    break;
                case 0:
                    System.out.println("已退出。");
                    return;
                default:
                    System.out.println("无效操作！");
            }
            System.out.println();
        }
    }
}*/

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
            System.out.println("1. 学生志愿填报");
            System.out.println("2. 查询学生志愿");
            System.out.println("3. 自动分配录取结果");
            System.out.println("4. 查询学生录取结果");
            System.out.println("5. 查询学校已录取学生");
            System.out.println("0. 退出系统");
            System.out.print("请选择：");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: // 学生志愿填报
                    System.out.print("请输入学生ID：");
                    int studentId = input.nextInt();
                    input.nextLine();
                    Student student = studentService.getStudentById(studentId);
                    if (student == null) {
                        System.out.println("学生不存在！");
                        break;
                    }
                    System.out.print("是否同意专业调剂(yes/no)：");
                    String adjustStr = input.nextLine();
                    boolean allowAdjustment = "yes".equalsIgnoreCase(adjustStr);

                    List<Application> applications = new ArrayList<>();
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

                case 2: // 查询学生志愿
                    System.out.print("请输入学生ID：");
                    int stuId = input.nextInt();
                    input.nextLine();
                    List<Application> apps = applicationService.getApplicationsByStudent(stuId);
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

                case 3: // 自动分配录取（模拟一轮录取）
                    System.out.println("正在模拟录取分配...");
                    admissionResultService.allocateAdmission(); // 假设有该方法
                    System.out.println("录取分配完成！");
                    break;

                case 4: // 查询学生录取结果
                    System.out.print("请输入学生ID：");
                    int stuId4 = input.nextInt();
                    input.nextLine();
                    AdmissionResult result = admissionResultService.getResultByStudent(stuId4);
                    if (result == null) {
                        System.out.println("尚未被录取。");
                    } else {
                        System.out.println("录取学校ID：" + result.getUniversityId() +
                                "，专业ID：" + result.getMajorId());
                    }
                    break;

                case 5: // 查询学校已录取学生
                    System.out.print("请输入学校ID：");
                    int uniId = input.nextInt();
                    input.nextLine();
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
                    System.out.println("已退出。");
                    return;
                default:
                    System.out.println("无效操作！");
            }
            System.out.println();
        }
    }
}