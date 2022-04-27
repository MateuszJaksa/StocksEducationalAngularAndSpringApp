# StocksEducationalAngularAndSpringApp

To start the app:
1) Change the API_KEY in src/main/java/org/jaksa/Consts.java to your own to ensure that credits will be available.
It can be obtained by signing up on twelvedata.com.
2) To guarantee proper container interaction replace every INSERT YOUR IP HERE by your own id, you can get appearances of it by running on Linux
- grep -r INSERT YOUR IP HERE - in the main directory
4) To build proper Docker images run :
- docker build -t front . - in /app-front
- docker build -t back-dependencies . - in /app-back
- docker build -t back . - in /app-back
5) Finnaly run docker-compose up - in the main directory 
