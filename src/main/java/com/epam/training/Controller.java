package main.java.com.epam.training;

import main.java.com.epam.training.command.Command;
import main.java.com.epam.training.command.CommandFactory;
import main.java.com.epam.training.command.CommandResult;
import main.java.com.epam.training.exception.ServiceException;
import main.java.com.epam.training.exception.UnknownCommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        CommandFactory factory = new CommandFactory();
        try {
            Command concreteCommand = factory.findCommand(command);
            CommandResult result = concreteCommand.execute(request, response);
            dispatch(request, response, result);
        } catch (ServiceException | UnknownCommandException e) {
           LOGGER.error(e);
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, CommandResult result) throws ServletException, IOException {
        if (result != null) {
            String page = result.getPage();
            if (result.isRedirect()) {
                response.sendRedirect(request.getContextPath() + page);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
        } //exception or  log
    }
}

