FROM openjdk:19
ADD ./JavixWeb-0.0.1-SNAPSHOT.jar backend.jar
ENV DB_USER=postgres
ENV DB_PASS=2003
ENV DB_URL=jdbc:postgresql://postgres:5432/Javix
ENV KEY=GGeAmVIuh2JTNFRE
ENV BOT_TOKEN=6250257674:AAF_C7DJHniH4Yt24LdnkanEvwIKeJ8wI3o
ENTRYPOINT ["java", "-jar", "backend.jar"]