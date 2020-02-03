# gamification Isma

# Construyendo el proyecto

Para construir y ejecutar los tests del proyecto usamos un wrapper de Maven, que 
actualmente es la versión 3.6.3

## Construir el proyecto

Para construir el proyecto usamos el comando 

```./mvnw clean install```

## Tests unitarios

Para ejecutar los tests unitarios usamos el comando  

```./mvnw clean test```

## Tests de integración

Para ejecutar los tests de integración usamos el comando  

```./mvnw clean verify```

## Cobertura de código

Añadiendo el plugin de JaCoCo, en la fase de test se generará un informe de cobertura de código del proyecto.

El informe se genera en el directorio **/target/coverage/jacoco**.
