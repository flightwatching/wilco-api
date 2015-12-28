This page defines the requirements to install WILCO on a host.

Host setup
-----

# host
WILCO runs on a linux host (currently ubuntu 14.04_LTS-server (64BITS) on our servers)
# JRE
you will need Oracle VM (no open java VM). Currently Oracle JAVA VM (V1.7)
```
java version "1.7.0_80"
Java(TM) SE Runtime Environment (build 1.7.0_80-b15)
Java HotSpot(TM) 64-Bit Server VM (build 24.80-b11, mixed mode)
```
# Database
postgresql database server (version 9.3.10) is required
* A user has to be created with read/write rights on the following database. You will have to provide us the user name/password
* A database has to be initiated like this
```
CREATE DATABASE wilco
  WITH OWNER = wilco
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;
```

# IFT runner
node.js has to be installed (needs root access)
```
sudo apt-get install --yes nodejs
sudo apt-get install --yes npm
sudo ln -s /usr/bin/nodejs /usr/bin/node
```

# File system
WILCO needs a folder in read write mode:
* read the files you dump into (ACARS messages)
* store the configuration + binaries
* store the logs (rolling files)

# notification service
email server has to be reachable to send automatic notifications to your teams
```
mail.smtp.host=
mail.smtp.user=
mail.smtp.pass=
mail.smtp.channel=
mail.smtp.port=
```


Input and Output
----
WILCO connects to any data source using several channels (direct DB connection, websocket, http post requests or folder watching). Here we describe the folder watching.

# folder watching
WILCO watches to a specified folder (RW access) and processes all the messages that are dropped in it. As soon as they are processed, they are removed. Therefore, do not use this folder to archive data.
As actions, wilco may send some data (uplinks).

# ACARS messages
## downlinks
the ACARS messages have to be raw messages. if it is some forwarding, please check there is no footer appended. The ACARS header has to be preserved too. Example:

```
QU FLIGHTW
.WATCHIN 281204
DFD
FI EY0276/AN FW-AAA
DT QXS MCT4 281204 D74A
-  XXXXXXXXXX:
```

##uplinks
WILCO has to send the uplinks to your aircom server. the way to do this heavily depends on your IT.

Specify the way to send uplink messages to your aircom server thru your intranet (file dropping, e-mail addess, direct connection...). It is part of the installation process to set up and test the connection.


Responsibilities
----
Flightwatching team provides the application and start/stop unix scripts. Flightwatching is not responsible for keeping the required OS, services servers available and up to date (Ubuntu, database server, e-mail server)

Automatic restarts are under the responsability of presidential flight
Backups are under the responsability of presidential flight
