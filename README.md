# 📦 Sistema de Gestión de Inventario (Full Stack Microservices)

Proyecto de arquitectura distribuida para el control integral de suministros y existencias. Se basa en una infraestructura de microservicios escalables utilizando el ecosistema de **Spring Cloud** y **Java 25**.

## 🏗️ Arquitectura del Sistema
El sistema implementa un patrón de **Base de Datos por Servicio** para asegurar el aislamiento y la escalabilidad independiente de cada módulo.

### Diagrama de Arquitectura
*Este diagrama se sincroniza con la lógica definida en `architecture/architectureMicroService.mmd`.*

### 🏗️ Arquitectura del Sistema
![Diagrama de Arquitectura](https://github.com/leandro12rk/SistemaDeGestionDeInventario/blob/main/architecture/architectureMicroService.mmd)

# 🛠️ Componentes Principales
Config Server: Centralización de propiedades y perfiles de configuración.

Discovery Server (Eureka): Directorio dinámico donde cada servicio se registra para ser localizado sin IPs estáticas.

Gateway Service: Punto de entrada único que gestiona el enrutamiento hacia los servicios internos.

Product Service (Puerto 8081): Gestión de catálogo, categorías y maestros de proveedores.

Inventory Service (Puerto 8082): Control de existencias en tiempo real y auditoría de movimientos.

Purchase Service (Puerto 8092): Orquestación de compras que utiliza OpenFeign para validar productos y actualizar el inventario.

# 🚀 Infraestructura y Tecnologías
``` 
Backend: Java 25 / Spring Boot 3.5.x

Comunicación: * OpenFeign: Llamadas declarativas entre microservicios.

Eureka: Service Discovery.

Persistencia (Docker Desktop):

db-products: PostgreSQL (Puerto 5433)

db-inventory: PostgreSQL (Puerto 5434)

db-purchases: PostgreSQL (Puerto 5435)
``` 
# 📂 Estructura del Proyecto

``` text
.
├── architecture/        # Documentación de diseño (.mmd)
├── config-service/      # Configuración centralizada
├── discovery-service/   # Eureka Server
├── gateway-service/     # API Gateway
├── product-service/     # Gestión de productos
├── inventory-service/   # Gestión de stock
└── purchase-service/    # Gestión de órdenes de compra
```
Licencia
Este proyecto está bajo la Apache License, Version 2.0. Puedes utilizar, distribuir y modificar este software de acuerdo con los términos de dicha licencia.

Consulta el archivo LICENSE para más detalles.

Desarrollado por Leandro.

© 2026 - Panamá.