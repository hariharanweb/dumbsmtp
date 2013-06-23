package com.hari.mail.dump;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class MailFileDumper implements IMailListener{

    private final String dumpFolderName;

    public MailFileDumper(SimpleSmtpServer server, String dumpFolderName){
        this.dumpFolderName = dumpFolderName;
        server.addListener(this);
    }

    @Override
    public void mail(List msgs) throws IOException {
        for(Object msg:msgs)
        dumpMail((SmtpMessage) msg);
    }

    private void dumpMail(SmtpMessage msg) throws IOException {
        String subject = msg.getHeaderValue("Subject");
        String to = msg.getHeaderValue("To").split("@")[0];
        StringBuffer content = getHeaderMessage(msg).append(msg.getBody());

        String fileName = to + "-" + subject + "-" + System.currentTimeMillis();
        System.out.println("Writing to file " + dumpFolderName + "/" + fileName);
        File file = new File(dumpFolderName, fileName);
        File contentFile = new File(dumpFolderName, fileName+".html");

        FileUtils.writeStringToFile(file, content.toString());
        FileUtils.writeStringToFile(contentFile, msg.getBody());
    }

    private StringBuffer getHeaderMessage(SmtpMessage msg) {
        StringBuffer buffer = new StringBuffer();
        Iterator headerNames = msg.getHeaderNames();
        while(headerNames.hasNext()){
            String headerKey = (String) headerNames.next();
            buffer.append(headerKey+":"+msg.getHeaderValue(headerKey)+"\n");
        }
        return buffer;
    }
}
