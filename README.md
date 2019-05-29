### Instructions for start and test code:

1. Clone or download project.
2. Open folder with project and execute command for building jar - `./gradlew build`.
3. Go to folder with jar - `build/libs` and run it: `java -jar test-task-1.0.0.jar`.
4. After the program is run successfully, you can send requests to server by url - http://localhost:8080.
5. There are available three corresponding REST endpoints:
    - /accounts/{from}/transfer?to={to}&money={money} - transfer money
    - /accounts/{id}/add?money={money} - add money
    - /accounts/{id}/withdraw?money={money} - withdraw money