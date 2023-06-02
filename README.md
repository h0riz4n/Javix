# Javix
![Screenshot of a comment on a GitHub issue showing an image, added in the Markdown, of an Octocat smiling and raising a tentacle.](https://github.com/h0riz4n/Javix/blob/main/f0znzTTw8F8.jpg)

> Javix is simple telegram bot with web-games.

# How to deploy?

First of all you need to up Nginx in your server. You can do it by docker + docker-compose. Clone the directory nginx and in this directory execute this commands.

```
docker network create host_env
docker-compose build
docker-compose up -d
```

After this you need to wait some time for updating nginx. My congratulations, now you have nginx in your server. 
Now go to home directory and there you should write this commands.
```
mkdir javixwebapp
mkdir javixbot
```

In home directory clone this [file](https://github.com/h0riz4n/Javix/blob/main/docker-compose.yaml)

> Directory javixbot
There you should have .jar file of this java application. Add .jar file and [docker file](https://github.com/h0riz4n/Javix/blob/main/JavixTg/Dockerfile), execute this commands.
```
mkdir data
cd data
```
Clone [dataJSON file](https://github.com/h0riz4n/Javix/blob/main/JavixTg/src/main/java/com/Javix/JavixTg/dataJSON/commands.json) there and go home directory.

> Directory javixwebapp

There you should have .jar file of this java application. Add .jar file and [docker file](https://github.com/h0riz4n/Javix/blob/main/JavixWeb/Dockerfile), execute this commands. 

After all instruction you should be in home directory, where you have this [docker-compose.yml](https://github.com/h0riz4n/Javix/blob/main/docker-compose.yaml).
Execute this program and test your bot.
```
docker-compose build
docker-compose up -d
```

# Technologies
- Docker
- PostgreSQL
- Java
- JavaScript
- Nginx
- Let's Encrypt
- HTML/CSS
- Spring Framework

# Student developers
- [Frontend dev. student - Misha Chernov](https://github.com/Mid1i/Mid1i)
- [Backend dev. student - Rustam Khudoyan](https://github.com/h0riz4n)

# F.A.Q.
- [Initial Server Setup with Ubuntu 18.04](https://www.digitalocean.com/community/tutorials/initial-server-setup-with-ubuntu-18-04)
- [How To Install and Use Docker on Ubuntu 20.04](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04)
- [How To Install and Use Docker Compose on Ubuntu 20.04](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-compose-on-ubuntu-20-04)
