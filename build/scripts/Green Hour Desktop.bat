@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Green Hour Desktop startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

@rem Add default JVM options here. You can also use JAVA_OPTS and GREEN_HOUR_DESKTOP_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windowz variants

if not "%OS%" == "Windows_NT" goto win9xME_args
if "%@eval[2+2]" == "4" goto 4NT_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*
goto execute

:4NT_args
@rem Get arguments from the 4NT Shell from JP Software
set CMD_LINE_ARGS=%$

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\Green Hour Desktop.jar;%APP_HOME%\lib\httpclient-4.5.2.jar;%APP_HOME%\lib\httpcore-4.4.5.jar;%APP_HOME%\lib\httpasyncclient-4.1.2.jar;%APP_HOME%\lib\quick-json-1.0.4.jar;%APP_HOME%\lib\async-http-client-2.0.11.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-codec-1.9.jar;%APP_HOME%\lib\httpcore-nio-4.4.5.jar;%APP_HOME%\lib\netty-codec-http-4.0.39.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.0.39.Final-linux-x86_64.jar;%APP_HOME%\lib\netty-resolver-dns-2.0.11.jar;%APP_HOME%\lib\reactive-streams-1.0.0.jar;%APP_HOME%\lib\netty-reactive-streams-1.0.6.jar;%APP_HOME%\lib\javassist-3.20.0-GA.jar;%APP_HOME%\lib\slf4j-api-1.7.21.jar;%APP_HOME%\lib\netty-codec-4.0.39.Final.jar;%APP_HOME%\lib\netty-handler-4.0.39.Final.jar;%APP_HOME%\lib\netty-common-4.0.39.Final.jar;%APP_HOME%\lib\netty-buffer-4.0.39.Final.jar;%APP_HOME%\lib\netty-transport-4.0.39.Final.jar;%APP_HOME%\lib\netty-resolver-2.0.11.jar;%APP_HOME%\lib\netty-codec-dns-2.0.11.jar

@rem Execute Green Hour Desktop
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GREEN_HOUR_DESKTOP_OPTS%  -classpath "%CLASSPATH%" com.inkchild.l2nsoft.greenhour.Main %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable GREEN_HOUR_DESKTOP_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%GREEN_HOUR_DESKTOP_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
