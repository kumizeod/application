package dao.impl;

import dao.ClassInfoDao;
import model.ClassInfo;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassInfoDaoImpl implements ClassInfoDao {
    @Override
    public void addClassInfo(ClassInfo c) {
        String sql = "INSERT INTO class_info (name) VALUES (?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) c.setId(rs.getInt(1));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ClassInfo> getAllClasses() {
        List<ClassInfo> list = new ArrayList<>();
        String sql = "SELECT id, name FROM class_info";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                list.add(new ClassInfo(rs.getInt("id"), rs.getString("name")));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ClassInfo getClassInfoById(int id) {
        String sql = "SELECT id, name FROM class_info WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) return new ClassInfo(rs.getInt("id"), rs.getString("name"));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}