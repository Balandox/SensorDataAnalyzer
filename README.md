# SensorDataAnalyzer
Данный проект представляет собой REST API сервис, принимающий данные от метеорологического датчика (сенсора).  
Работу реального сенсора эмулирует java приложение (SensorDataAnalyzerClient), приложение посылает запросы к REST API сервису.  
  
Всего 4 вида запросов:  
 - Регистрация нового сенсора (POST /sensors/registration)    
 - Добавление измерения от сенсора (POST /measurements/add)    
 - Получение всех измерений (GET /measurements). Также возможно выбрать измерения только для определенного датчика, передав в запросе параметр sensorName (Пример: GET /measurements?sensorName=Balandox sensor)          
 - Получение количества дождливых дней (GET /measurements/rainyDaysCount). Также возможно выбрать кол-во дождливых дней только для определенного датчика, передав в запросе параметр sensorName (Пример: GET /measurements/rainyDaysCount?sensorName=Balandox sensor)    

Основные технологии, используемые в проекте:
 - Spring Core
 - Spring REST
 - Spring Boot
 - Spring MVC
 - Spring Data JPA
 - Hibernate ORM (СУБД PostgreSQL)
 - Apache Maven  

Ниже представлены примеры выполнения корректных запросов (при некорректных запросах клиенту присылается json с текстом ошибки и временем, когда был сделан запрос):   
![image](https://user-images.githubusercontent.com/85318590/210885460-d94684e3-3dce-4e2c-b685-7f9eb0bb7ac2.png)
<p align="center">Рис. 1 - (Добавление нового датчика, при вводе данные валидируются)</p>  

![image](https://user-images.githubusercontent.com/85318590/210887706-30bf1ff3-e8c6-4897-9c50-5360668d3fdd.png)
<p align="center">Рис. 2 - (Добавление новых измерений, при вводе данные валидируются)</p> 

![image](https://user-images.githubusercontent.com/85318590/210888115-d944de15-2594-4e73-9243-9d20596e1352.png)
<p align="center">Рис. 3 - (Получение всех данных с датчика 'Balandox sensor')</p> 

![image](https://user-images.githubusercontent.com/85318590/210888574-65b3a8e8-7aa8-4c18-abe1-a85986453118.png)
<p align="center">Рис. 4 - (Получение колличества дождливых дней, зафиксированных датчиком 'Balandox sensor')</p> 

![image](https://user-images.githubusercontent.com/85318590/210889183-c72abbab-342c-4406-b060-fdc17bbff4c6.png)
<p align="center">Рис. 5 - (Пример обработки некорректного запроса)</p> 
 
