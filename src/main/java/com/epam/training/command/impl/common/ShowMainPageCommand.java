package main.java.com.epam.training.command.impl.common;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.Pages;
import main.java.com.epam.training.dto.CourseDto;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.CourseDtoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowMainPageCommand implements Command {
    private CourseDtoService service;

    public ShowMainPageCommand(CourseDtoService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<CourseDto> courses = service.showCourses();
        request.setAttribute("courses", courses);

        return CommandResult.forward(Pages.MAIN);
    }
}
