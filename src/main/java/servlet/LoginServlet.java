package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import util.DatabaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        try (Connection conn = DatabaseUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next() && BCrypt.checkpw(password, rs.getString("password"))) {
                User user = new User(
                    rs.getInt("user_id"),
                    rs.getString("email"),
                    rs.getString("password"),
                    User.Role.valueOf(rs.getString("role"))
                );
                
                // Set the timestamp after construction
                user.setCreatedAt(rs.getTimestamp("created_at"));
                
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                // Redirect based on role
                switch (user.getRole()) {
                    case ADMIN:
                        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
                        break;
                    case CHEF:
                        response.sendRedirect(request.getContextPath() + "/chef/dashboard");
                        break;
                    case CUSTOMER:
                        response.sendRedirect(request.getContextPath() + "/customer/dashboard");
                        break;
                }
            } else {
                request.setAttribute("error", "Invalid email or password");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }
}
