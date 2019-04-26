package data.servlets;

import data.daoIF.ManagerDAO;
import data.model.Manager;
import data.storage.DAOFactory;
import data.storage.sqlPerRequestDAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory daoFactory = sqlPerRequestDAOFactory.getDAOFactory();
        ManagerDAO managerDAO = daoFactory.getManagerDAO();
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();
        List<Manager> managerList;
        if (req.getParameter("month") == null) {
            managerList = managerDAO.getAll();
        }
        else {
            managerList = managerDAO.findByMonth(Integer.parseInt(req.getParameter("month").split("-")[1]));
        }
        sb.append("<table><tbody><tr><td>").append("income").append("</td><td>").append("consumption")
                .append("</td><td>").append("date")
                .append("</td><td>").append("description").append("</td></tr>");
        for (Manager manager: managerList) {
            sb.append("<tr><td>").append(manager.getIncome()).append("</td><td>")
                    .append(manager.getConsumption()).append("</td><td>")
                    .append(manager.getDate()).append("</td><td>")
                    .append(manager.getDescription()).append("</td></tr>");
        }
        sb.append("</tbody></table>");
        sb.append("<p>—чет: ").append(managerDAO.getBalance()).append("</p>");
        req.setAttribute("message", sb.toString());
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOFactory daoFactory = sqlPerRequestDAOFactory.getDAOFactory();
        ManagerDAO managerDAO = daoFactory.getManagerDAO();
        managerDAO.insert(new Manager(Integer.parseInt(req.getParameter("income")),
                Integer.parseInt(req.getParameter("consumption")),
                Date.valueOf(req.getParameter("date")),
                req.getParameter("description")));
        doGet(req,resp);
    }

}
