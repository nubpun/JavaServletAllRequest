package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import servlets.AllRequestsServlet;
import servlets.MirrorRequests;

public class Main {
    public static void main(String[] args) throws Exception {
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        MirrorRequests mirrorRequests = new MirrorRequests();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), "/*");
        context.addServlet(new ServletHolder(mirrorRequests), "/mirror");
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();

        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}