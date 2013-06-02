package com.hari.mail.dump;

import com.beust.jcommander.JCommander;
import com.dumbster.smtp.SimpleSmtpServer;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class MailServerRunner {

    public static void main(String[] args) throws Exception {

        Parameters parameters = new Parameters();
        new JCommander(parameters,args);

        System.out.println("Using SMTP port "+parameters.smtpPort);
        System.out.println("Using HTTP port "+parameters.httpPort);
        System.out.println("Using mail dump folder "+parameters.mailDir);

        String mailsDir = parameters.mailDir;
        File mails = new File(mailsDir);
        if(!mails.exists()) mails.mkdir();

        SimpleSmtpServer smtpServer = SimpleSmtpServer.start(parameters.smtpPort);

        new MailFileDumper(smtpServer,mailsDir);
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(parameters.httpPort);

        Context ctx = tomcat.addContext("/", mailsDir);
        Wrapper defaultServlet = ctx.createWrapper();
        defaultServlet.setName("default");
        defaultServlet.setServletClass("org.apache.catalina.servlets.DefaultServlet");
        defaultServlet.addInitParameter("debug", "0");
        defaultServlet.addInitParameter("listings", "true");
        defaultServlet.setLoadOnStartup(1);
        ctx.addChild(defaultServlet);
        ctx.addServletMapping("/", "default");
        tomcat.start();

        tomcat.getServer().await();
        //tomcat.wait();
    }
}
