package com.hari.mail.dump;

import com.beust.jcommander.Parameter;

public class Parameters {

    @Parameter(names = {"-smtpPort"},description = "Port where smtp runs")
    public int smtpPort = 25;

    @Parameter(names = {"-httpPort"},description = "Port where server runs")
    public int httpPort = 1111;

    @Parameter(names = {"-mailDir"},description = "Directory where mails are dumped")
    public String mailDir = "/tmp/mails";

}
