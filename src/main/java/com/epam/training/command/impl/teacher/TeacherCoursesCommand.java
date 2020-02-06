package main.java.com.epam.training.command.impl.teacher;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.constant.Pages;
import main.java.com.epam.training.entity.Course;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.CoursesService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TeacherCoursesCommand implements Command {
    private CoursesService service;

    public TeacherCoursesCommand(CoursesService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User teacher =(User) session.getAttribute("user");
        long teacherId = teacher.getId();
        List<Course> teacherCourses = service.showTeacherCourses(teacherId);
        request.setAttribute("teacherCourses", teacherCourses);

        return CommandResult.forward(Pages.TEACHER_COURSES);
    }
}
