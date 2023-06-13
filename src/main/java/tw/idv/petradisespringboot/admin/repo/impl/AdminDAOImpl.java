package tw.idv.petradisespringboot.admin.repo.impl;

import tw.idv.petradisespringboot.admin.repo.AdminDAO;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    String driver = "com.sql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/gp3?serverTimezone=Asia/Taipei";
    String uid = "root";
    String pw = "123";

    public static final String FIND = "select * from admin where admin_id = ?;";
    public static final String INSERT = "insert into admin (admin_name, admin_account, admin_password, admin_email, admin_title) values (?, ?, ?, ?, ?);";
    public static final String DELETE = "delete from admin where admin_id= ?;";
    public static final String UPDATE = "update admin set admin_name = ?, admin_account = ?, admin_password = ?, admin_phone = ?, admin_address = ?, admin_email = ?, admin_title = ?, admin_status = ? where admin_id = ?;";
    public static final String ALL = "select * from admin order by admin_title;";

    @Override
    public Admin findById(Integer id) {

        try (Connection con = DriverManager.getConnection(url, uid, pw); PreparedStatement ps = con.prepareStatement(FIND)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("admin_id"));
                admin.setName(rs.getString("admin_name"));
                admin.setAccount(rs.getString("admin_account"));
                admin.setPassword(rs.getString("admin_password"));
                admin.setPhone(rs.getString("admin_phone"));
                admin.setAddress(rs.getString("admin_address"));
                admin.setEmail(rs.getString("admin_email"));
                admin.setTitle(rs.getString("admin_title").charAt(0));
                admin.setStatus(rs.getString("admin_status").charAt(0));

                return admin;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Admin insert(Admin admin) {

        try (Connection con = DriverManager.getConnection(url, uid, pw); PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setString(1, admin.getName());
            ps.setString(2, admin.getAccount());
            ps.setString(3, admin.getPassword());
            ps.setString(4, admin.getEmail());
            ps.setString(5, String.valueOf(admin.getTitle()));
            ps.executeUpdate();

            return admin;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteById(Integer id) {

        try (Connection con = DriverManager.getConnection(url, uid, pw); PreparedStatement ps = con.prepareStatement(DELETE)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Admin update(Admin admin) {

        try (Connection con = DriverManager.getConnection(url, uid, pw); PreparedStatement ps = con.prepareStatement(UPDATE)) {

            ps.setString(1, admin.getName());
            ps.setString(2, admin.getAccount());
            ps.setString(3, admin.getPassword());
            ps.setString(4, admin.getPhone());
            ps.setString(5, admin.getAddress());
            ps.setString(6, admin.getEmail());
            ps.setString(7, String.valueOf(admin.getTitle()));
            ps.setString(8, String.valueOf(admin.getStatus()));
            ps.setInt(9, admin.getId());

            ps.executeUpdate();
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Admin> getAll() {

        try (Connection con = DriverManager.getConnection(url, uid, pw);
             PreparedStatement ps = con.prepareStatement(ALL)) {

            ResultSet rs = ps.executeQuery();
            List<Admin> list = new ArrayList<Admin>();

            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(rs.getInt("admin_id"));
                admin.setName(rs.getString("admin_name"));
                admin.setAccount(rs.getString("admin_account"));
                admin.setPassword(rs.getString("admin_password"));
                admin.setPhone(rs.getString("admin_phone"));
                admin.setAddress(rs.getString("admin_address"));
                admin.setEmail(rs.getString("admin_email"));
                admin.setTitle(rs.getString("admin_title").charAt(0));
                admin.setStatus(rs.getString("admin_status").charAt(0));
                list.add(admin);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
