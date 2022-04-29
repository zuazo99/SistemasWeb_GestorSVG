package servlets;

import HTTPeXist.HTTPeXist;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteSvg extends HttpServlet {

    private HTTPeXist eXist;

    public DeleteSvg() {
        super();

        System.out.println("---> Entering init() DeleteSvg");

        eXist = new HTTPeXist("http://localHost:8080");

        System.out.println("---> Exiting init() DeleteSvg");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---> Entering doGet() DeleteSvg");

        String collection = request.getParameter("collection");
        String svgName = request.getParameter("svgName");

        int status = eXist.delete(collection, svgName);
        if (status == 200) {
            request.setAttribute("informacion", "Irudia "+svgName+" kolekziokoa "+collection+" ondo ezabatuta");
        } else {
            request.setAttribute("informacion", "Arazo bat egon da. Saiatu berriro. Kodigoa --> " + status );
        }

        System.out.println("     Redireccionando a index.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        rd.forward(request, response);

        System.out.println("---> Exiting doGet() DeleteSvg");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
