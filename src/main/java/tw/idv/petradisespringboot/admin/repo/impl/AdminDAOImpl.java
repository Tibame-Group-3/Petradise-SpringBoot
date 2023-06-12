package tw.idv.petradisespringboot.admin.repo.impl;

import tw.idv.petradisespringboot.admin.repo.AdminDAO;
import tw.idv.petradisespringboot.admin.vo.Admin;

import java.sql.*;

public class AdminDAOImpl implements AdminDAO {

    String driver = "com.sql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/gp3?serverTimezone=Asia/Taipei";
    String uid = "root";
    String pw = "123";

    public static final String FIND = "select * from admin where admin_id = ?;";
    public static final String INSERT = "insert into admin (admin_name, admin_account, admin_password, admin_phone, admin_address, admin_email, admin_title) values (?, ?, ?, ?, ?, ?, ?);";
    public static final String DELETE = "delete from admin where admin_id= ?;";
    public static final String UPDATE = "update admin set admin_name = ?, admin_account = ?, admin_password = ?, admin_phone = ?, admin_address = ?, admin_email = ?, admin_title = ?, admin_status = ? where admin_id = ?;";


    public static void main(String[] args) {

//        AdminDAOImpl run = new AdminDAOImpl();
//        System.out.println(run.findById(14));

//        Admin admin = new Admin();
//        admin.setName("測試");
//        admin.setAccount("測試");
//        admin.setPassword("測試");
//        admin.setPhone("測試");
//        admin.setAddress("測試");
//        admin.setEmail("測試");
//        admin.setTitle("測試");
//        System.out.println(run.insert(admin));

//        admin.setStatus('1');
//        admin.setId(14);
//        System.out.println(run.update(admin));

//        System.out.println(run.deleteById(18));
    }

    @Override
    public Admin findById(Integer id) {

        try (Connection con = DriverManager.getConnection(url, uid, pw);
             PreparedStatement ps = con.prepareStatement(FIND)) {

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
                admin.setTitle(rs.getString("admin_title"));
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

        try (Connection con = DriverManager.getConnection(url, uid, pw);
             PreparedStatement ps = con.prepareStatement(INSERT)) {

            ps.setString(1, admin.getName());
            ps.setString(2, admin.getAccount());
            ps.setString(3, admin.getPassword());
            ps.setString(4, admin.getPhone());
            ps.setString(5, admin.getAddress());
            ps.setString(6, admin.getEmail());
            ps.setString(7, admin.getTitle());
            ps.executeUpdate();

            return admin;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteById(Integer id) {

        try (Connection con = DriverManager.getConnection(url, uid, pw);
             PreparedStatement ps = con.prepareStatement(DELETE)) {

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

        try (Connection con = DriverManager.getConnection(url, uid, pw);
             PreparedStatement ps = con.prepareStatement(UPDATE)) {

            ps.setString(1, admin.getName());
            ps.setString(2, admin.getAccount());
            ps.setString(3, admin.getPassword());
            ps.setString(4, admin.getPhone());
            ps.setString(5, admin.getAddress());
            ps.setString(6, admin.getEmail());
            ps.setString(7, admin.getTitle());
            ps.setString(8, String.valueOf(admin.getStatus()));
            ps.setInt(9, admin.getId());

            ps.executeUpdate();
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
