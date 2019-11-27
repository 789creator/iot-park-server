@echo off

cd..
set jar_name=robot-agent.jar
set active=prod

echo start %jar_name%
java -jar %jar_name% --spring.profiles.active=%active%
echo 服务启动完成。
pause