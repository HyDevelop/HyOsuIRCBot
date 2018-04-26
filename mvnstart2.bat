@echo "start"

del /a /f /q target\app\webRoot\*.properties

copy target\classes\*.properties target\app\webRoot\*.properties

@echo "end"

pause