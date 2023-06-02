FROM openjdk:19
ADD ./JavixTg-0.0.1-SNAPSHOT.jar bot.jar
ADD ./data/commands.json commands.json
ENV USER_NAME=h0riz4nbot
ENV TOKEN=6250257674:AAF_C7DJHniH4Yt24LdnkanEvwIKeJ8wI3o
ENTRYPOINT ["java", "-jar", "bot.jar"]