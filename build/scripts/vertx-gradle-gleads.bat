@rem
@rem Copyright 2015 the original author or authors.
@rem
@rem Licensed under the Apache License, Version 2.0 (the "License");
@rem you may not use this file except in compliance with the License.
@rem You may obtain a copy of the License at
@rem
@rem      https://www.apache.org/licenses/LICENSE-2.0
@rem
@rem Unless required by applicable law or agreed to in writing, software
@rem distributed under the License is distributed on an "AS IS" BASIS,
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@rem See the License for the specific language governing permissions and
@rem limitations under the License.
@rem

@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  vertx-gradle-gleads startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and VERTX_GRADLE_GLEADS_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

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
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\vertx-gradle-gleads-1.0.0-SNAPSHOT.jar;%APP_HOME%\lib\vertx-web-client-3.8.3.jar;%APP_HOME%\lib\vertx-web-3.8.3.jar;%APP_HOME%\lib\vertx-auth-mongo-3.8.3.jar;%APP_HOME%\lib\vertx-mongo-client-3.8.3.jar;%APP_HOME%\lib\vertx-web-common-3.8.3.jar;%APP_HOME%\lib\vertx-auth-common-3.8.3.jar;%APP_HOME%\lib\vertx-core-3.8.3.jar;%APP_HOME%\lib\vertx-bridge-common-3.8.3.jar;%APP_HOME%\lib\mongodb-driver-async-3.10.1.jar;%APP_HOME%\lib\netty-handler-proxy-4.1.42.Final.jar;%APP_HOME%\lib\netty-codec-http2-4.1.42.Final.jar;%APP_HOME%\lib\netty-codec-http-4.1.42.Final.jar;%APP_HOME%\lib\netty-handler-4.1.42.Final.jar;%APP_HOME%\lib\netty-resolver-dns-4.1.42.Final.jar;%APP_HOME%\lib\netty-codec-socks-4.1.42.Final.jar;%APP_HOME%\lib\netty-codec-dns-4.1.42.Final.jar;%APP_HOME%\lib\netty-codec-4.1.42.Final.jar;%APP_HOME%\lib\netty-transport-4.1.42.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.42.Final.jar;%APP_HOME%\lib\netty-resolver-4.1.42.Final.jar;%APP_HOME%\lib\netty-common-4.1.42.Final.jar;%APP_HOME%\lib\jackson-databind-2.9.9.1.jar;%APP_HOME%\lib\jackson-core-2.9.9.jar;%APP_HOME%\lib\mongodb-driver-core-3.10.1.jar;%APP_HOME%\lib\bson-3.10.1.jar;%APP_HOME%\lib\jackson-annotations-2.9.0.jar

@rem Execute vertx-gradle-gleads
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %VERTX_GRADLE_GLEADS_OPTS%  -classpath "%CLASSPATH%" io.vertx.core.Launcher %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable VERTX_GRADLE_GLEADS_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%VERTX_GRADLE_GLEADS_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega