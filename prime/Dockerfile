FROM openjdk
COPY Main.java Main.java
COPY test.txt test.txt
RUN javac Main.java
ENTRYPOINT [ "java", "Main" ]