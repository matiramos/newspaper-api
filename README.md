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

**Lista de comandos:** [cheatsheet](https://github.com/wsargent/docker-cheat-sheet)

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
    Ubicados sobre el directorio del proyecto, podemos ver los archivos mvnw y mvnw.cmd . Un paso adicional para aquellos que esten con **Linux** es modificar el archivo application.yml bajo el directorio /resources, cambiar el valor de url, por el siguiente  jdbc:mysql://localhost:3306/newspaper_db.
    
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
    
    * Sobre el directorio del proyecto ejecutar el archivo docker-compose.yml. **Los que esten con LINUX instalar** -> [docker-compose](https://docs.docker.com/compose/install/#install-compose)
            
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


## Ejemplo de request y response:

Pueden usar [POSTMAN](https://www.google.com.ar/url?sa=t&rct=j&q=&esrc=s&source=web&cd=1&cad=rja&uact=8&ved=0ahUKEwj10bzk4fzWAhWIfZAKHQ5ZDRIQFgglMAA&url=https%3A%2F%2Fchrome.google.com%2Fwebstore%2Fdetail%2Fpostman%2Ffhbjgbiflinjbdggehcddcbncdddomop&usg=AOvVaw0bEk4JkKX1g2WxEBFiQDnf)

### Traer categorias:

**GET** localhost:8080/categories o {docker-machine-ip}:8080/categories 

Response: 
```json
{
  "_embedded" : {
    "categories" : [ {
      "name" : "sports",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/categories/1"
        },
        "category" : {
          "href" : "http://localhost:8080/categories/1"
        },
        "news" : {
          "href" : "http://localhost:8080/categories/1/news"
        }
      }
    }, {
      "name" : "local",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/categories/2"
        },
        "category" : {
          "href" : "http://localhost:8080/categories/2"
        },
        "news" : {
          "href" : "http://localhost:8080/categories/2/news"
        }
      }
    }, {
      "name" : "world",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/categories/3"
        },
        "category" : {
          "href" : "http://localhost:8080/categories/3"
        },
        "news" : {
          "href" : "http://localhost:8080/categories/3/news"
        }
      }
    }, {
      "name" : "economy",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/categories/4"
        },
        "category" : {
          "href" : "http://localhost:8080/categories/4"
        },
        "news" : {
          "href" : "http://localhost:8080/categories/4/news"
        }
      }
    }, {
      "name" : "politics",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/categories/5"
        },
        "category" : {
          "href" : "http://localhost:8080/categories/5"
        },
        "news" : {
          "href" : "http://localhost:8080/categories/5/news"
        }
      }
    }, {
      "name" : "entertainment",
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/categories/6"
        },
        "category" : {
          "href" : "http://localhost:8080/categories/6"
        },
        "news" : {
          "href" : "http://localhost:8080/categories/6/news"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/categories{?page,size,sort}",
      "templated" : true
    },
    "profile" : {
      "href" : "http://localhost:8080/profile/categories"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 6,
    "totalPages" : 1,
    "number" : 0
  }
}
```              

### Cargar una noticia:

**POST** localhost:8080/news o {docker-machine-ip}:8080/news

Body:
```json
{
	  "title" : "Test title 4",
	  "body" : "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae urna ipsum. Pellentesque in quam nisl. Sed auctor mi vel sagittis suscipit.",
	  "date" : "2017-09-24",
	  "reporter" : "http://localhost:8080/reporters/1",
	  "category" : "http://localhost:8080/categories/1"
}
``` 


Response: status 201 Created

Headers:

    content-type →application/hal+json;charset=UTF-8
    date →Thu, 19 Oct 2017 13:31:14 GMT
    location →http://localhost:8080/news/2
    transfer-encoding →chunked

Body:
```json
{
    "title": "Test title 4",
    "body": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vitae urna ipsum. Pellentesque in quam nisl. Sed auctor mi vel sagittis suscipit.",
    "date": "2017-09-24T00:00:00.000+0000",
    "_links": {
        "self": {
            "href": "http://localhost:8080/news/2"
        },
        "news": {
            "href": "http://localhost:8080/news/2"
        },
        "category": {
            "href": "http://localhost:8080/news/2/category"
        },
        "reporter": {
            "href": "http://localhost:8080/news/2/reporter"
        }
    }
}
```
        
            
    
            
