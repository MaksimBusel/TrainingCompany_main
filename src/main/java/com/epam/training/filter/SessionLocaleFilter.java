package main.java.com.epam.training.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SessionLocaleFilter implements Filter {
    private static final String SESSION_LOCALE = "sessionLocale";
    private static final String LANGUAGE = "language";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getParameter(SESSION_LOCALE) != null) {
            req.getSession().setAttribute(LANGUAGE, req.getParameter(SESSION_LOCALE));
        }

        chain.doFilter(request, response);
    }

    public void destroy() {
    }

    public void init(FilterConfig arg) throws ServletException {
    }
}