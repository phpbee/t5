FROM java:8

COPY ./build/libs/t5-0.1.0.jar /opt/t5/t5-0.1.0.jar

CMD ["java","-jar", "/opt/t5/t5-0.1.0.jar"]
