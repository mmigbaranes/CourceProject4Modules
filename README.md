# Дипломный проект профессии «Тестировщик»
______________________________________________
[План автоматизации](https://github.com/mmigbaranes/CourceProject4Modules/blob/main/Reporting_documentation/Plan.md)

[Отчет по итогам тестирования](https://github.com/mmigbaranes/CourceProject4Modules/blob/main/Reporting_documentation/ALLURE_REPORT_3march2024.jpg)

[Отчет по итогам автоматизации](https://github.com/mmigbaranes/CourceProject4Modules/blob/main/Reporting_documentation/Summary.md)

## Инструкция по запуску
_______________
1. Склонировать репозиторий
`git clone https://github.com/mmigbaranes/CourceProject4Modules`

2. Перейти в папку `CourceProject4Modules`


3. Запустить Docker Desktop:

Для работы с базой данных mysql выполнить команду:
`docker-compose up`
После прогона тестов остановить контейнеры:
`docker-compose down`

4. Запустить приложение:

Для запуска приложения с базой данных mysql выполнить команду:
`java -jar aqa-shop.jar`

5. Запустить тесты:

Для запуска тестов с базой данных mysql выполнить команду:
`gradlew test`

6. Сформировать отчеты командой:
`gradlew allureReport`

7. Открыть отчеты в браузере командой:
`gradlew allureServe`