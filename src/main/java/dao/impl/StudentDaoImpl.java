package dao.impl;

import dao.StudentDao;
import model.ClassInfo;
import model.Student;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public void addStudent(Student student) {
        String sql = "INSERT INTO student (name, gender, class_id) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getGender());
            ps.setInt(3, student.getClassId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) student.setId(rs.getInt(1));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int id) {
        String sql = "DELETE FROM student WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(int id) {
        String sql = "SELECT s.id, s.name, s.gender, c.id as class_id, c.name as class_name FROM student s LEFT JOIN class_info c ON s.class_id = c.id WHERE s.id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                ClassInfo c = new ClassInfo(rs.getInt("class_id"), rs.getString("class_name"));
                return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), c.getId());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT s.id, s.name, s.gender, c.id as class_id, c.name as class_name FROM student s LEFT JOIN class_info c ON s.class_id = c.id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ClassInfo c = new ClassInfo(rs.getInt("class_id"), rs.getString("class_name"));
                Student s = new Student(rs.getInt("id"), rs.getString("name"), rs.getString("gender"), c.getId());
                list.add(s);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}