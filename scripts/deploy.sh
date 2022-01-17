#!/bin/bash

REPOSITORY=/home/ubuntu/app/step
PROJECT_NAME=eatda-server

echo "> BUILD 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동중인 APPLICATION PID"
CURRENT_PID=$(pgrep -fl eatda-server | grep jar | awk '{print $1}')

echo "현재 구동 중인 APPLICATION pid : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
  echo "> 현재 구동 중인 APPLICATION 없음"
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  sleep 5
fi

## 어플리에키션 배포 작업
JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)


chmod +x $JAR_NAME

# nohup 명령어로 jar 실행 부분


nohup java - jar \ -Dspring.config.location=classpath:/application.properties,/home/ubuntu/app/application-db.properties \ $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &