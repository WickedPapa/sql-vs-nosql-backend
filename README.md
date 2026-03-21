# SQL vs NoSQL Backend

This project demonstrates a backend application that can run using either **PostgreSQL (relational)** or **MongoDB (NoSQL)**.

Everything is fully containerized with Docker, so no local setup (Java, Maven, DB) is required.

---

## 🚀 Prerequisites

* Docker
* Docker Compose

---

## ▶️ Run the application

### Using PostgreSQL (default relational DB)

```bash
APP_DATASOURCE=POSTGRES docker-compose up --build
```

---

### Using MongoDB (NoSQL DB)

```bash
APP_DATASOURCE=MONGODB docker-compose up --build
```

---

## 🌐 Services

### Application (API)

* Swagger UI → http://localhost:8080/swagger-ui/index.html
  → Graphical interface to test APIs directly from the browser

* OpenAPI JSON → http://localhost:8080/v3/api-docs
  → Raw API specification in JSON format

* OpenAPI YAML → http://localhost:8080/v3/api-docs.yaml
  → Downloadable API specification (useful for tools or documentation)

---

### PostgreSQL (pgAdmin)

* URL → http://localhost:5050
* Email → `pg@admin.com`
* Password → `pg-psw`

→ Web interface to explore the relational database (tables, rows, queries)

---

### MongoDB (Mongo Express)

* URL → http://localhost:8081
* Username → `admin-basic`
* Password → `psw-basic`

→ Web interface to explore MongoDB collections and documents

---

## 🔄 Reset databases

To completely reset all databases (Postgres + Mongo):

```bash
docker-compose down -v
```

Then restart:

```bash
docker-compose up --build
```

---

## 🧠 Notes

* The application automatically switches between PostgreSQL and MongoDB based on the `APP_DATASOURCE` variable.
* No manual configuration is required.
* Data is persisted using Docker volumes (removed only with `-v`).

---

## 📦 Tech stack

* Java 21
* Spring Boot
* PostgreSQL
* MongoDB
* Docker & Docker Compose
* OpenAPI (API documentation)
* Swagger UI (API exploration)
* Swagger Codegen (client/server generation)

---

![Language](https://img.shields.io/badge/language-Java_21-blue)
![Framework](https://img.shields.io/badge/framework-Spring_Boot-brightgreen)
![Database](https://img.shields.io/badge/database-PostgreSQL-blue)
![Database](https://img.shields.io/badge/database-MongoDB-green)
![Container](https://img.shields.io/badge/container-Docker-blue)
![API](https://img.shields.io/badge/API-OpenAPI-orange)
![Codegen](https://img.shields.io/badge/codegen-Swagger_Codegen-orange)
![Mapper](https://img.shields.io/badge/mapper-MapStruct-yellow)
![Boilerplate](https://img.shields.io/badge/boilerplate-Lombok-red)