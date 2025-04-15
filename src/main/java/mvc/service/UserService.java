package mvc.service;

import mvc.dao.UserDAO;
import mvc.model.User;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;

public class UserService {
    private UserDAO userDAO;
    
    public UserService() {
        this.userDAO = new UserDAO();
    }
    
    public User authenticate(String email, String password) throws SQLException {
        User user = userDAO.findByEmail(email);
        
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            // Don't send password back to client
            user.setPassword(null);
            return user;
        }
        return null;
    }
}