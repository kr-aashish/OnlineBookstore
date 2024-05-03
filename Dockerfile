FROM openjdk

WORKDIR ${HOME}

COPY target/online-book-store.jar ${HOME}

# RUN yum update
# RUN yum install postgresql-client
COPY initialiseDatabase.sql ${HOME}
# RUN psql -U postgres -d postgres_password < initialiseDatabase.sql

EXPOSE 5050


CMD ["java", "-jar", "online-book-store.jar"]