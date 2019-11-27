@echo off
echo open 10.225.1.174>ftp.up
echo user testftp testftp>>ftp.up 
Echo Cd testfile >>ftp.up
Echo binary>>ftp.up 
Echo prompt >>ftp.up 

Echo lcd "C:\Users\ardo-001\Desktop\getfile">>ftp.up
Echo mget *.*>>ftp.up
echo bye>>ftp.up
ftp -n -s:ftp.up
del ftp.up
pause
