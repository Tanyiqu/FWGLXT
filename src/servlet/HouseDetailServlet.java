package servlet;

import bean.House;
import bean.User;
import service.HouseService;
import service.UserService;
import service.impl.HouseServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HouseDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("utf-8");
        //接收参数
        String id = req.getParameter("id");
        //查找房源
        HouseService houseService = new HouseServiceImpl();
        House house = houseService.getHouse(id);
        //查找房主
        UserService userService = new UserServiceImpl();
        User host = userService.find(house.getHost(),"1");
        //存入request域
        req.setAttribute("house",house);
        req.setAttribute("host",host);
        //转发
        RequestDispatcher dispatcher = req.getRequestDispatcher("house.jsp");
        dispatcher.forward(req, resp);
    }
}
