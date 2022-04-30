package servlets;

import HTTPeXist.HTTPeXist;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class NewImage extends HttpServlet {

    private HTTPeXist eXist;

    public NewImage(){
        super();
        System.out.println("---> Entering init() NewImage");

        eXist = new HTTPeXist("http://localHost:8080");

        System.out.println("---> Exiting init() NewImage");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("---> Entering doGet() NewImage");

        String svgName = request.getParameter("svgName");
        String collection = request.getParameter("collection");

        eXist.create(collection, svgName);
        eXist.subirString(collection, "<svg>Idatzi hemen irudiaren kodea</svg>", svgName);

        String irudiSVG= eXist.read(collection, svgName);

        request.setAttribute("collection", collection);
        request.setAttribute("svgName", svgName);
        request.setAttribute("imagenSVG", irudiSVG);
        String irudiURI = "http://localhost:8080/exist/rest/db/" + collection + "/" + svgName + "/";
        request.setAttribute("imagenURI", irudiURI);

        System.out.println("\tRedirecting the user to imagenEdit.jsp");
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/imagenEdit.jsp");
        rd.forward(request, response);

        System.out.println("---> Exiting doGet() NewImage");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
