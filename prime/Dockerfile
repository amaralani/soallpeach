FROM openjdk
ADD . /build/
WORKDIR /build/src
RUN javac ./ir/maralani/Main.java
RUN java ir/maralani/Main ../test.txt