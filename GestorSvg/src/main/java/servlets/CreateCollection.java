package servlets;

import HTTPeXist.HTTPeXist;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CreateCollection extends HttpServlet {

    private HTTPeXist eXist;

    public CreateCollection() {
        super();

        System.out.println("---> Entering init() CreateCollection");

        eXist = new HTTPeXist("http://localHost:8080");

        System.out.println("---> Exiting init() CreateCollection");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        System.out.println("---> Entering doGet() CreateCollection");

        String collection = request.getParameter("collection");

        int status = eXist.create(collection, "");

        if (status == 201) {
            request.setAttribute("informacion", "Kolekzioa "+collection+" sortu egin da ondo");
        } else {
            request.setAttribute("informacion", "Arazo bat egon da. Inténtalo de nuevo. (Código "+status+")");
        }


        System.out.println("\tRedirecting the user to index.jsp");
        //request.setAttribute("informacion", collection + " kolekzioa sortu da");
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        rd.forward(request, response);

        System.out.println("---> Exiting doGet() CreateCollection");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
