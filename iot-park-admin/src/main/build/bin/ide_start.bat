@echo off
set jar_name=sikulixide-1.1.4-SNAPSHOT.jar
set mainClass=org.sikuli.ide.Sikulix
echo start %jar_name%
java -cp %jar_name% %mainClass%
echo 服务启动完成。
pause