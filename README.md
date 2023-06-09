Сборка серверной части:
1. Из корня проекта выплнить команду - cd back
2. maven clean package
Запуск серверной части:
1. java -jar target/currency_converter-0.0.1-SNAPSHOT.jar

Сборка / запуск клиентской части:
1. Из корня проекта выполнить команду - cd front
2. npm install
3. vite

Скрипт базы лежит в src/main/resources/db/migration

Проект запускается по адресу http://127.0.0.1:5173
