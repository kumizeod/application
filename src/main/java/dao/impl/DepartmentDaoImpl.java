package dao.impl;

import dao.DepartmentDao;
import model.Department;
import model.University;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public void addDepartment(Department d) {
        String sql = "INSERT INTO department (university_id, name) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, d.getUniversityId());
            ps.setString(2, d.getName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) d.setId(rs.getInt(1));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Department> getDepartmentsByUniversity(int universityId) {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT id, name FROM department WHERE university_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, universityId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Department d = new Department(rs.getInt("id"), rs.getString("name"), new University(universityId, null, null).getId());
                list.add(d);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Department getDepartmentById(int id) {
        String sql = "SELECT id, name, university_id FROM department WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                University u = new University(rs.getInt("university_id"), null, null);
                return new Department(rs.getInt("id"), rs.getString("name"), (Integer) u.getId());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}