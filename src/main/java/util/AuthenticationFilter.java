package util;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        boolean isLoginPage = path.equals("/login.jsp") || path.equals("/LoginServlet");
        boolean isPublicResource = path.startsWith("/css/") || path.equals("/register.jsp");
        
        if (isLoggedIn && isLoginPage) {
            // Redirect to home if already logged in
            httpResponse.sendRedirect("home.jsp");
        } else if (!isLoggedIn && !isLoginPage && !isPublicResource) {
            // Redirect to login for protected resources
            httpResponse.sendRedirect("login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}
}