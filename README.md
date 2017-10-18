## Newspaper-api

API Rest desarrollada con Spring boot [data-rest](https://projects.spring.io/spring-data-rest/) y [data-jpa](https://projects.spring.io/spring-data-jpa/) que se utilizara como backend en Programación Avz II.

Esta API hace uso de [HATEOAS](https://en.wikipedia.org/wiki/HATEOAS) y expone los siguientes endpoints:
 * /reporters
 * /categories
 * /news

Informacion sobre los [rest repositories](https://docs.spring.io/spring-data/rest/docs/2.2.0.RELEASE/reference/html/#repository-resources) de spring, responses de codigos http y verbos disponibles.

## Opciones para hacer uso de la API localmente

### Docker:

Este proyecto se puede ejecutar dentro de un container utilizando Docker, de la siguiente forma:

1. Instalar Docker en nuestro sistema:
    * [Windows 7, 8.1, 10 Home](https://www.docker.com/products/docker-toolbox)
    * [Windows 10 Pro 64 bit](https://download.docker.com/win/stable/Docker%20for%20Windows%20Installer.exe)
    * [Mac](https://store.docker.com/editions/community/docker-ce-desktop-mac)
    * [Ubuntu](https://docs.docker.com/engine/installation/linux/docker-ce/ubuntu/)
    * [Debian](https://docs.docker.com/engine/installation/linux/docker-ce/debian/)
    * [Fedora](https://docs.docker.com/engine/installation/linux/docker-ce/fedora/)

2. Buildear el proyecto:
    * Como hacemos uso de MySQL como base de datos, necesitamos tener instalada la misma. Con docker esto es facil:
    
            docker run --name mysql-db -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=newspaper_db -p 3306:3306 -d mysql:latest
     
    * Una vez que nuestro container de MySQL este corriendo, lo podemos verificar con el comando "docker ps", estamos listos para buildear el proyecto. 
    Generalmente es necesario tener instalado Maven, pero Spring boot nos provee un wrapper del mismo para poder buildear sin necesidad de tener maven instalado. 
    Ubicados sobre el directorio del proyecto, podemos ver los archivos mvnw y mvnw.cmd .
    
    * Si estamos desde Linux o la consola de Git Bash:
            
            ./mvnw package
            
    * Si estamos desde la consola de Windows:
    
            ./mvnw.cmd package        
            
3. Una vez que el proceso de build este terminado satisfactoriamente y veamos el archivo "newspaper-1.0.0.jar" dentro del directorio /target.
Podemos generar la imagen de Docker que necesitamos. Sobre el directorio que contiene el archivo Dockerfile, ejecutamos:

            docker build -t docker:newspaper .
            
4. Con la imagen de Docker lista, pueden verlo con el comando, "docker images". Podemos seguir con el paso siguiente, levantar ambos containers juntos, por un lado
el container de MySQL y por el otro el container de la API. Para esto, ejecutamos:
            
    * Detener el container ejecutado en el paso 2:
            
            docker stop mysql-db
            
    * Eliminar el container detenido anteriormente:
            
            docker rm mysql-db
    
    * Sobre el directorio del proyecto ejecutar el archivo docker-compose.yml
            
            docker-compose up
    
    Listo, ya podemos acceder a la api utilizando el puerto 8080 y la ip asignada a la docker machine, la misma se puede ver con el comando "docker-machine ip". Generalmente http://192.168.99.100:8080

### Entorno local

Otra forma de poder ejecutar la api es instalando las herramientas necesarias en nuestro equipo, de la siguiente forma.

1. Instalar y confgiurar el motor de base de datos [MySQL](https://dev.mysql.com/downloads/mysql/)
2. Instalar [Java JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
3. Modificar las propiedades en el archivo application.yml para poder conectar con la base de datos instalada en el paso 1. 
    
    * datasource.url
    * datasource.username
    * datasource.password 

4. Ejecuta la aplicacion de spring boot.
        
    Desde la terminal de Linux o Git Bash
        
        ./mvnw spring-boot:run
    
    Desde la consola de Windows
        
        ./mvnw.cmd spring-boot:run

5. Si todo se configuro correctamente ya podemos ingresar a la api con "http://localhost:8080/"                
        
            
    
            
