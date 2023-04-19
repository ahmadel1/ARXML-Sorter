@echo off

cd D:\ARXMLSorter\src
javac project\*.java

color 0A
echo Testing Normal file case...
java project.main D:\ARXMLSorter\InputSample.arxml
echo ^

echo Testing NotValid file case...
java project.main D:\ARXMLSorter\text.txt
echo ^

echo Testing Empty file case...
java project.main D:\ARXMLSorter\emptyFile.arxml

pause 