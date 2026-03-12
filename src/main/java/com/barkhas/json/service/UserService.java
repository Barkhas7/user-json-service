package com.barkhas.json.service;
import com.barkhas.json.model.UserProfile;
import com.barkhas.json.model.UserRequest;
import com.barkhas.json.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {

    public String createUserProfile(UserRequest request) {
        String sql = "INSERT INTO profiles(user_id, name, email, bio, phone) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, request.getUserId());
            pstmt.setString(2, request.getName());
            pstmt.setString(3, request.getEmail());
            pstmt.setString(4, request.getBio());
            pstmt.setString(5, request.getPhone());

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    return "Хэрэглэгчийн хуудас амжилттай үүслээ. Хэрэглэгчийн дугаар: " + generatedId;
                }
                return "Хэрэглэгчийн хуудас амжилттай үүслээ.";
            } else {
                return "Хэрэглэгчийн хуудас үүсгэхэд алдаа гарлаа.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Үүсгэхэд алдаа гарлаа: " + e.getMessage();
        }
    }

    public UserProfile getUserProfileById(int id) {
        String sql = "SELECT * FROM profiles WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UserProfile profile = new UserProfile();
                profile.setId(rs.getInt("id"));
                profile.setUserId(rs.getInt("user_id"));
                profile.setName(rs.getString("name"));
                profile.setEmail(rs.getString("email"));
                profile.setBio(rs.getString("bio"));
                profile.setPhone(rs.getString("phone"));
                return profile;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public UserProfile getUserProfileByUserId(int userId) {
        String sql = "SELECT * FROM profiles WHERE user_id = ? LIMIT 1";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                UserProfile profile = new UserProfile();
                profile.setId(rs.getInt("id"));
                profile.setUserId(rs.getInt("user_id"));
                profile.setName(rs.getString("name"));
                profile.setEmail(rs.getString("email"));
                profile.setBio(rs.getString("bio"));
                profile.setPhone(rs.getString("phone"));
                return profile;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String updateUserProfile(int id, UserRequest request) {
        String sql = "UPDATE profiles SET user_id = ?, name = ?, email = ?, bio = ?, phone = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, request.getUserId());
            pstmt.setString(2, request.getName());
            pstmt.setString(3, request.getEmail());
            pstmt.setString(4, request.getBio());
            pstmt.setString(5, request.getPhone());
            pstmt.setInt(6, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                return "Хэрэглэгчийн хуудас амжилттай шинэчлэгдлээ.";
            } else {
                return "Хэрэглэгчийн хуудас олдсонгүй.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Update failed: " + e.getMessage();
        }
    }

    public String deleteUserProfile(int id) {
        String sql = "DELETE FROM profiles WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                return "Хэрэглэгчийн хуудас амжилттай устлаа.";
            } else {
                return "Хэрэглэгчийн хуудас олдсонгүй.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Устгалт амжилтгүй: " + e.getMessage();
        }
    }
}