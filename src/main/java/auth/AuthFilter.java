package auth;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(urlPatterns = "/secured/*")
public class AuthFilter implements Filter {

    @EJB
    private SignInBean signInBean;

    @Inject
    private UserBean userBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String resource = request.getRequestURI();

        if (!userBean.isActive()) {
            response.sendRedirect(request.getContextPath() + "/profile.xhtml");
            return;
        }


//        if (!signInBean.isGranted(userBean.getEmail(), resource)) {
//            response.sendRedirect("redirect to access denied");
//            return;
//        }


        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
