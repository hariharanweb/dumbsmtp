dumbsmtp
========

A fake smtp server using dumbster fake smtp server under the hoods but dumps the mails to a folder and allows access to dumped files using a server.

How to use
=========

Run the jar file at bin/dumbsmtp-1.0-SNAPSHOT.jar <path_to_folder_where_you_want_the_mails>

Command (sudo because mail smtp server runs on port 25

sudo java -jar bin/dumbsmtp-1.0-SNAPSHOT.jar

A server runs at http://localhost:1111/ where you can browse the mails.
