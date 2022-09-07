# EC2 인스턴스 타입이 ARM이기 때문에 arm64v8/amazoncorretto 사용
FROM arm64v8/amazoncorretto:8u332-al2
# linux update timezone
RUN yum install -y tzdata curl && yum install -y mysql
# timezone settings
RUN cp /usr/share/zoneinfo/Asia/Seoul /etc/localtime

ARG JAR_FILE=/target/food-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} application.jar

# container port
EXPOSE 5000

ENTRYPOINT ["java","-jar", "/application.jar"]
