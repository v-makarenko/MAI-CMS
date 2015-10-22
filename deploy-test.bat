call mvn clean install
rem pscp -r -pw 3i6MQIolde %~dp0liquibase\* root@146.185.243.78:/root/mai-cms/liquibase/
pscp -r -pw 3i6MQIolde %~dp0liquibase\sdk\workspace\* root@146.185.243.78:/root/mai-cms/liquibase/sdk/workspace
pscp -pw 3i6MQIolde %~dp0target\maicms.war root@146.185.243.78:/root/mai-cms/maicms.war
plink -batch -pw 3i6MQIolde root@146.185.243.78  "./mai-cms/deploy.sh"