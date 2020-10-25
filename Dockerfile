FROM adoptopenjdk/openjdk11:latest
COPY /out/artifacts/BackendApi_jar/BackendApi.jar BackendApi.jar
ENTRYPOINT ["java","-jar","BackendApi.jar"]