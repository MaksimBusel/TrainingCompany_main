package main.java.com.epam.training.command.impl.admin;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.Pages;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAddCoursePageCommand implements Command {
    private static final String RESULT = "result";
    private UserService service;

    public ShowAddCoursePageCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<User> teachers = service.showTeachers();
        request.setAttribute("teachers", teachers);
        String result = request.getParameter(RESULT);
        request.setAttribute("result",result);

        return CommandResult.forward(Pages.ADD_COURSE);
    }
}
