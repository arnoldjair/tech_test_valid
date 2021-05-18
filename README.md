# Technical test for Valid

## Backend

El proyecto backend fue desarrollado utilizando un plugin de gradle liberado por bancolombia.

### Arquitectura

La arquitectura está basada en la arquitectura hexagonal, en la cual se busca la capa de negocio sea independiente del framework. La comunicación entre la capa de negocio y el exterior se realiza por medio de puertos y adaptadores.

Tenemos tres paquetes principales: Aplicación, dominio y arquitectura.

En aplicación tenemos lo necesario para hacer que la aplicación sea ejecutada como cualquier otra aplicación desarrollada en springBoot. En este paquete se realizan tareas de configuración por medio de anotaciones.

En dominio tenemos el core de la aplicación, que consiste básicamente en useCases y entidades del dominio, com clases y enums.

En infraestructura tenemos los diferentes mecanismos para hacer que el core se comunique con el exterior. Tenemos cosas como los entry points (puntos de entrada, que en el dominio de spring boot son son los cotroladores) y los driven adapters (conexiones con bases de datos o servicios de terceros)

## Frontend

El proyecto fronted fue desarrollado utilizando Angular y Angular material (para los componentes gráficos)

### Arquitectura

La arquitectura consiste en la separación de módulos independientes, la definición de elementos transversales (como el modulo core, aunque en este proyecto no se usa específicamente) y la carga perezosa (lazing loading) de componentes en la medida en que son requeridos, como el módulo de listar usuarios y el módulo de registarar usuarios. 



