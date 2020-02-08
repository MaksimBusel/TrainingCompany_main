package main.java.com.epam.training.tag;

import main.java.com.epam.training.exception.TagException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class RedirectTag extends TagSupport {
    private String page;

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException| IOException e) {
            throw new TagException(e);
        }

        return SKIP_BODY;
    }
}
