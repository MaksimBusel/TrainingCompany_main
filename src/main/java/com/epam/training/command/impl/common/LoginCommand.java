package main.java.com.epam.training.command.impl.common;

import main.java.com.epam.training.constant.CommandType;
import main.java.com.epam.training.constant.Pages;
import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.RedirectUrlCreator;
import main.java.com.epam.training.entity.User;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String USER = "user";

    private UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        Optional<User> user = service.login(login, password);
        HttpSession session = request.getSession();
        user.ifPresent(u -> session.setAttribute(USER, u));

        return user.isPresent() ? CommandResult.redirect(RedirectUrlCreator.create(CommandType.SHOW_MAIN_PAGE)) :
                                  CommandResult.forward(Pages.LOGIN);
    }
}