El producto de software a ser producido es el Oracle Java Bot, un servicio de chatbot que automatiza la gestión de tareas para los desarrolladores y proporciona visibilidad al mánager sobre las tareas del equipo. El bot operará dentro de la plataforma de mensajería Telegram y se integrará con la infraestructura de Oracle Cloud y Oracle Autonomous Database.

### Software:
- **Docker:** Para la creación y gestión de contenedores, permitiendo un ambiente de pruebas consistente.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Los contenedores en Docker pueden reducir el tiempo de implementacion a segundos | No se pueden usar proceso como cron y syslog en el contenedor |
| Este soporta un enfoque de desarrollo agil y ayuda a hacer realidad la integracion e implementacion continuas | Existen cierto limites como la limpieza de los procesos despues de terminar con los procesos hijo

- **Java SE Development Kit (JDK) 8:** Se utilizará Java Development Kit versión 1.8 para el desarrollo del bot.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Es un lenguaje de programacion multiplataforma | Puede llegar a tener un rendimiento mas lento comparado con otros como C++ |
| Cuenta con un sistema de seguridad incorporado | Puede requerir mas memoria que otros lenguajes |
| Ofrece una gran cantidad de librerias y herramientas | Puede llegar a tener sobrecarga de codigo |

- **Apache Maven:** Para la gestión de dependencias y automatización de la construcción del proyecto.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Es mas facil la organizacion ya que todos los proyectos o trabajos se distribuyen como una estructura | Depende mucho de una conexion a una red WIFI |
| Los archivos jar tambien son otra gran ventaja ya que al dejarlo en un repositorio centralizado y el uso de Snapshots de maven se puede llegar a tener todos los ultimos archivos jar para todos | El uso de la red no permite realizar cambios fuera ya que para esto se tendria que clonar el repositorio completo para que asi se puedan realizar los cambios o hacer otro proyecto totalmente independiente de maven |

- **Telegram Bot API:** Para interactuar con el bot durante las pruebas.

| Ventajas| Desventajas |
| ----------- | ----------- |
| ventajas.. | desventajas.. |

- **Spring Boot:** Para el desarrollo del back-end del bot, utilizando Spring Boot para facilitar la creación de microservicios escalables.

| Ventajas| Desventajas |
| ----------- | ----------- |
| ventajas.. | desventajas.. |

- **Oracle Cloud Infrastructure y Oracle Autonomous Database:** Para alojar la aplicación y la base de datos, asegurando el acceso durante las pruebas a los servicios en la nube de Oracle.

| Ventajas| Desventajas |
| ----------- | ----------- |
| ventajas.. | desventajas.. |

- **Herramientas de Pruebas:** Se utilizarán frameworks específicos para Java como JUnit para pruebas unitarias, y Selenium para pruebas de interfaz de usuario en Telegram.

| Ventajas| Desventajas |
| ----------- | ----------- |
| ventajas.. | desventajas.. |

### Bibliotecas/frameworks:
- Documentación API de Telegram. [Telegram](https://core.telegram.org/bots/api)
- Documentación de Oracle Cloud Infrastructure (OCI) [OCI](https://docs.oracle.com/en-us/iaas/Content/home.htm)
- Documentación de Docker [Docker](https://docs.docker.com/)
- Documentación de Java [Java](https://docs.oracle.com/en/java/)
- Documentación de Spring Boot [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- Documentación de Java SE Development Kit (JDK) 8 [JDK 8](https://docs.oracle.com/javase/8/docs/)
- Documentación de Apache Maven [Maven](https://maven.apache.org/guides/index.html)
