# URL Shortener API

## Descripción

Este proyecto es un **acortador de URLs** construido utilizando **Spring Boot**, **PostgreSQL** y **Redis**. La aplicación permite acortar URLs largas, redirigir a las URLs originales y almacenar estadísticas de acceso en **tiempo real** utilizando **Redis Streams**. Está diseñado para ser escalable, eficiente y manejar grandes volúmenes de tráfico.

## Características

- Acortamiento de URLs utilizando codificación en **Base 62**.
- Almacenamiento de URLs en **PostgreSQL** y **Redis** para mejorar el rendimiento.
- Redirección rápida de URLs cortas a las URLs originales.
- Estadísticas en tiempo real utilizando **Redis Streams** para capturar accesos.
- Persistencia de estadísticas en **PostgreSQL**.
- API REST con endpoints sencillos

## Tecnologías Utilizadas

- **Spring Boot**: Framework principal para la creación de la API REST.
- **PostgreSQL**: Base de datos relacional para almacenar las URLs acortadas y estadísticas de acceso.
- **Redis**: Utilizado como caché para mejorar el rendimiento y para manejar eventos de acceso en tiempo real mediante Redis Streams.
- **Maven**: Herramienta de construcción y gestión de dependencias.
- **Docker (opcional)**: Para el despliegue local de PostgreSQL y Redis.

## Requisitos

- **Java 8** o superior (Ideal 17)
- **Maven** 3.6+
- **PostgreSQL** 9.6+ (puede ejecutarse en la nube o localmente)
- **Redis** 5+ (puede ejecutarse en la nube o localmente)

## Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone https://github.com/Saanti1535/urlShortener.git
cd urlShortener
```

### 2. Configuración de la Base de Datos y Redis

Crear una base de datos en PostgreSQL y una instancia de Redis. Actualizar el archivo application.yml con las configuraciones de acceso:

```bash
spring:
  datasource:
    url: jdbc:postgresql://<tu-host>:<puerto>/<nombre-base-datos>
    username: <tu-usuario>
    password: <tu-password>
    driver-class-name: org.postgresql.Driver
  redis:
    host: <tu-host-redis>
    port: <puerto-redis>
    password: <tu-password>
    ssl: true  # si es necesario
```


### 3. Construir y Ejecutar la Aplicación

Compilar el proyecto utilizando Maven:

```bash
mvn clean install
```

Luego ejecutar la aplicación: 

```bash
mvn spring-boot:run
```


La API estará disponible en http://localhost:9000.
