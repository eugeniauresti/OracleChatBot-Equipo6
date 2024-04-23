El producto de software a ser producido es el Oracle Java Bot, un servicio de chatbot que automatiza la gestión de tareas para los desarrolladores y proporciona visibilidad al mánager sobre las tareas del equipo. El bot operará dentro de la plataforma de mensajería Telegram y se integrará con la infraestructura de Oracle Cloud y Oracle Autonomous Database.

### Software:
- **Docker:** Para la creación y gestión de contenedores, permitiendo un ambiente de pruebas consistente.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Los contenedores en Docker pueden reducir el tiempo de implementación a segundos | No se pueden usar proceso como cron y syslog en el contenedor |
| Este soporta un enfoque de desarrollo ágil y ayuda a hacer realidad la integración e implementación continuas | Existen cierto límites como la limpieza de los procesos después de terminar con los procesos hijo |

- **Java SE Development Kit (JDK) 8:** Se utilizará Java Development Kit versión 1.8 para el desarrollo del bot.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Es un lenguaje de programación multiplataforma | Puede llegar a tener un rendimiento más lento comparado con otros como C++ |
| Cuenta con un sistema de seguridad incorporado | Puede requerir más memoria que otros lenguajes |
| Ofrece una gran cantidad de librerías y herramientas | Puede llegar a tener sobrecarga de código |

- **Apache Maven:** Para la gestión de dependencias y automatización de la construcción del proyecto.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Es más fácil la organización ya que todos los proyectos o trabajos se distribuyen como una estructura | Depende mucho de una conexión a una red WIFI |
| Los archivos jar también son otra gran ventaja ya que al dejarlo en un repositorio centralizado y el uso de Snapshots de maven se puede llegar a tener todos los últimos archivos jar para todos | El uso de la red no permite realizar cambios fuera ya que para esto se tendría que clonar el repositorio completo para que así se puedan realizar los cambios o hacer otro proyecto totalmente independiente de maven |

- **Telegram Bot API:** Para interactuar con el bot durante las pruebas.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Proporciona una API de bot y un marco de bot que esto puede simplificar el desarrollo e implementación del bot | Es más complicado de usar que otras plataformas (facebook, whatsapp) ya que el usuario tendrá que aprender nuevos comandos |
| Permite crear grupos, canales u otros bots a los propietarios del bot | No está tan integrado con servicios de terceros | 

- **Spring Boot:** Para el desarrollo del back-end del bot, utilizando Spring Boot para facilitar la creación de microservicios escalables.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Hacen de un enfoque ágil para el desarrollo de aplicaciones | La comunicación de entre los microservicios puede ser compleja |
| Los fallos en un servicio no llegan a afectar el funcionamiento del programa | Cada microservicio requiere su propia estructura |

- **Oracle Cloud Infrastructure y Oracle Autonomous Database:** Para alojar la aplicación y la base de datos, asegurando el acceso durante las pruebas a los servicios en la nube de Oracle.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Agiliza el proceso ya que proporciona la base de datos donde se almacenarán los datos | Llega a tener un espacio limitado después se empieza a cobrar si se ha pasado de ese límite |

- **Herramientas de Pruebas:** Se utilizarán frameworks específicos para Java como JUnit para pruebas unitarias, y Selenium para pruebas de interfaz de usuario en Telegram.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Aunque añade pasos adicionales al desarrollo reduce el tiempo y el costo al hacer pruebas unitarias | No llega a abordar todas las situaciones posibles |
| Hay un aumento en la eficiencia en general | Puede llegar a requerir mas codigo |

- **Kubernetes:** Es un administrador del sistema, en este se crea el contenedor en OCI incluyendo los fault domains y los docker images que ayudará a mantener la conectividad estable.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Facilita la organización del servicio con pods | Llegan a ser incompatibles con las herramientas de Docker |
| Una gran variedad de opciones de almacenamiento (SAN, locales y nubes públicas) | Implementación de un cluster manual llega a ser muy complejo |

- **ReactJS:** Para el desarrollo de Front-End, que contiene la interfaz web para el acceso a la base de datos que contiene las tareas asignadas.

| Ventajas| Desventajas |
| ----------- | ----------- |
| Los componentes de React agilizan la creación de una interfaz | La biblioteca puede llegar a incrementar el tamaño de tu aplicación |
| Las interfaces creadas con React garantizan una mejor experiencia para el usuario | Solo visualiza la interfaz, para el proyecto completo se necesita una pila de tecnología |


### Bibliotecas/frameworks:
- Documentación API de Telegram. [Telegram](https://core.telegram.org/bots/api)
- Documentación de Oracle Cloud Infrastructure (OCI) [OCI](https://docs.oracle.com/en-us/iaas/Content/home.htm)
- Documentación de Docker [Docker](https://docs.docker.com/)
- Documentación de Java [Java](https://docs.oracle.com/en/java/)
- Documentación de Spring Boot [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- Documentación de Java SE Development Kit (JDK) 8 [JDK 8](https://docs.oracle.com/javase/8/docs/)
- Documentación de Apache Maven [Maven](https://maven.apache.org/guides/index.html)
