dumbsmtp
========

A fake smtp server using dumbster fake smtp server under the hoods but dumps the mails to a folder and allows access to dumped files using a server.

How to use
=========

Run the jar file at bin/dumbsmtp-1.0-SNAPSHOT.jar 

Command (sudo because mail smtp server runs on port 25)

sudo java -jar dumbsmtp-1.0-SNAPSHOT.jar

Default ports 

smtpPort = 25
httpPort=1111
mailDir=/tmp/mails

To change use the below command 

sudo java -jar dumbsmtp-1.0-SNAPSHOT.jar -httpPort <httpPort> -smtpPort <smtpPort> -mailDir <folder_to_dump_mails>

Example: sudo java -jar target/dumbsmtp-1.0-SNAPSHOT.jar -httpPort 1000 -smtpPort 500 -mailDir /Users/Admin/JavaWorks/

A server runs at http://localhost:1111/ where you can browse the mails.
