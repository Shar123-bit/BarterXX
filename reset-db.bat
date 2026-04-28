@echo off
echo ============================================
echo  BarterX — Reset Database
echo ============================================
echo WARNING: This will DELETE all users, items, and barter requests.
echo.
set /p confirm="Type YES to confirm: "
if /i "%confirm%" NEQ "YES" (
    echo Cancelled.
    exit /b
)
echo.
echo Clearing all data...
"C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql.exe" -u root -padmin@123 barterx_db -e "SET FOREIGN_KEY_CHECKS=0; TRUNCATE TABLE barter_requests; TRUNCATE TABLE items; TRUNCATE TABLE users; SET FOREIGN_KEY_CHECKS=1;"
echo Done! All data has been wiped.
pause
