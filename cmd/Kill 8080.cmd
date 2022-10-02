set message = finish him
FOR /F "tokens=5" %%P IN ('netstat -a -n -o ^| findstr :8080') DO TaskKill.exe /F /PID %%P
set message = fatality	