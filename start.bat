@echo off
echo ============================================
echo  BarterX — Safe Start Script
echo ============================================

echo [1/2] Killing any process on port 8085...
for /f "tokens=5" %%a in ('netstat -ano ^| findstr :8085 ^| findstr LISTENING') do (
    echo     Killing PID %%a
    taskkill /PID %%a /F >nul 2>&1
)
echo     Done.

echo [2/2] Starting BarterX...
echo.
call mvnw.cmd spring-boot:run
