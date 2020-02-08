package main.java.com.epam.training.command.impl.admin;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.Pages;
import main.java.com.epam.training.dto.CourseDto;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.CourseDtoService;
import main.java.com.epam.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowEditCoursePageCommand implements Command {
    private static final String RESULT = "result";
    private CourseDtoService coursesService;
    private UserService userService;

    public ShowEditCoursePageCommand(CourseDtoService courseService, UserService userService) {
        this.coursesService = courseService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<CourseDto> courses = coursesService.showCourses();
        List<User> teachers = userService.showTeachers();
        request.setAttribute("teachers", teachers);
        request.setAttribute("courses", courses);
        String result = request.getParameter(RESULT);
        request.setAttribute("result",result);

        return CommandResult.forward(Pages.EDIT_COURSE);
    }
}
