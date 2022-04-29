package servlets;

import HTTPeXist.HTTPeXist;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteCollection extends HttpServlet {
    private HTTPeXist eXist;

    public DeleteCollection(){

        super();
        System.out.println("---> Entering init() DeleteCollection");
        eXist = new HTTPeXist("http://localHost:8080");
        System.out.println("---> Exiting init() DeleteCollection");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---> Entering doGet() DeleteCollection");

        String collection = request.getParameter("collection");

        int status = eXist.delete(collection);

        if (status == 200){
            request.setAttribute("informacion", collection + " kolekzioa ezabatu da");
        }else{
            request.setAttribute("informacion", "Arazo bat egon da. Saiatu berriro. Kodigoa --> " + status );

        }

        System.out.println("\tRedirecting the user to index.jsp");
       // request.setAttribute("informacion", collection + " kolekzioa ezabatu da");
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/index.jsp");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        rd.forward(request, response);

        System.out.println("---> Exiting doGet() DeleteCollection");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
