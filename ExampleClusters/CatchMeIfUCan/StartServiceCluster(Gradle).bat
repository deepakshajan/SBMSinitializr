
cd ./ConfigurationProviderService
start cmd /c call gradle clean test bootRun

PING -n 20 -w 1 localhost >nul

cd ../MainService
start cmd /c call gradle clean test bootRun

PING -n 45 -w 1 localhost >nul

start chrome "http://localhost:5001"

