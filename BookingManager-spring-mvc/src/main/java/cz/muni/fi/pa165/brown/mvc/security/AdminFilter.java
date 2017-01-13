package cz.muni.fi.pa165.brown.mvc.security;

import cz.muni.fi.pa165.brown.dto.user.UserDTO;
import cz.muni.fi.pa165.brown.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = {"/hotels/create/*", "/hotels/edit/*", "/hotels/delete/*",
        "/reservations/create/*", "/reservations/edit/*", "/reservations/delete/*",
        "/rooms/create/*", "/rooms/edit/*", "/rooms/delete/*"})
public class AdminFilter implements Filter {

    final static Logger log = LoggerFactory.getLogger(AdminFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug("AdminFilter.doFilter called");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserDTO user = (UserDTO)request.getSession().getAttribute("user");
        if (user == null || ! user.isAdmin()) {
            response401(response);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1></body></html>");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
