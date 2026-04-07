# 📦 Sistema de Gestión de Inventario (Full Stack Microservices)
wwww
Proyecto educativo de alto nivel desarrollado por [Leandro](https://github.com/leandro12rk). Se enfoca en una arquitectura de microservicios distribuida para el control total de suministros, compras y existencias, utilizando tecnología de punta en el ecosistema Java.

## 🏗️ Arquitectura de Microservicios
El sistema utiliza una topología de "Base de Datos por Servicio" para garantizar el desacoplamiento:

1. **Config Server:** Centraliza la configuración de todos los nodos.
2. **Discovery Server (Eureka):** Registro y localización dinámica de servicios.
3. **Gateway Service:** Puerta de enlace única para las peticiones del Frontend.
4. **Product Service (Puerto 8081):** Gestión de Catálogo, Categorías y Proveedores.
5. **Inventory Service (Puerto 8082):** Control de Stock y Auditoría de Movimientos.
6. **Purchase Service (Puerto 8092):** Órdenes de Compra y Recepción de Mercancía.

## 🚀 Infraestructura con Docker
El proyecto incluye un entorno de contenedores para gestionar múltiples instancias de PostgreSQL en puertos dedicados:
* `db-products`: Puerto 5433
* `db-inventory`: Puerto 5434
* `db-purchases`: Puerto 5435

## 🛠️ Tecnologías
* **Backend:** Java 25 / Spring Boot 3.5.13
* **Comunicación:** Spring Cloud (Eureka, Config, OpenFeign)
* **Persistencia:** Spring Data JPA / PostgreSQL
* **Herramientas:** Maven, Docker Desktop, IntelliJ IDEA

## 📂 Estructura del Proyecto
```text
.
├── config-service/      # Servidor de configuración
├── discovery-service/   # Eureka Server
├── gateway-service/     # API Gateway
├── product-service/     # Catálogo y Maestros
├── inventory-service/   # Stock y Movimientos
└── purchase-service/    # Compras y Recepción
```
⚖️ Licencia
Este proyecto está bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

© 2026 Leandro (leandro12rk). Todos los derechos reservados.