liquibase --url=jdbc:postgresql://localhost/cms --driver=org.postgresql.Driver --username=postgres --password="postgres" --changeLogFile=sdk/workspace/changelog/cumulative.xml update
pause;