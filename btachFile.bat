@echo off

cd %~dp0src
javac project\*.java

color 0A
echo Testing Normal file case...
java project.main InputSample.arxml
echo ^

echo Testing NotValid file case...
java project.main text.txt
echo ^

echo Testing Empty file case...
java project.main emptyFile.arxml

pause 
