package com.hari.mail.dump;

import com.dumbster.smtp.SmtpMessage;

import java.io.IOException;
import java.util.List;

public interface IMailListener {
    public void mail(List msg) throws IOException;
}
