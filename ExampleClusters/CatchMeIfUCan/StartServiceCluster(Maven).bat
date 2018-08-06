
cd ./ConfigurationProviderService
start cmd /c call mvn clean test spring-boot:run

PING -n 20 -w 1 localhost >nul

cd ../MainService
start cmd /c call mvn clean test spring-boot:run

PING -n 45 -w 1 localhost >nul

start chrome "http://localhost:5001"

