package com.hari.mail.dump;

import com.dumbster.smtp.SimpleSmtpServer;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class MailServerRunner {

    public static void main(String[] args) throws Exception {
        String mailsDir = args.length == 1? args[0]:"/Users/Admin/JavaWorks/dumbsmtp/mails";
        File mails = new File(mailsDir);
        if(!mails.exists()) mails.mkdir();

        SimpleSmtpServer smtpServer = SimpleSmtpServer.start();
        new MailFileDumper(smtpServer,mailsDir);
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(1111);

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
