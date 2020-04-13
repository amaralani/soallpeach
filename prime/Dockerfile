FROM openjdk
ADD . /build/
WORKDIR /build/src
RUN javac ./ir/maralani/Main.java
ENTRYPOINT [ "sh", "-c", "java ir/maralani/Main" ]