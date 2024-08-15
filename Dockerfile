# Utiliza una imagen base de Ubuntu compatible con ARM64 para la compilación
FROM ubuntu:22.04 AS base

# Instala dependencias necesarias
RUN apt-get update && apt-get install -y \
    curl \
    unzip \
    zip \
    maven \
    && rm -rf /var/lib/apt/lists/*

# Instala SDKMAN para gestionar JDK
RUN curl -s "https://get.sdkman.io" | bash

# Carga SDKMAN y utiliza SDKMAN para instalar JDK 21 (o la versión más cercana disponible)
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && sdk install java 21.0.1-open"

# Establece JDK como la versión predeterminada de Java
ENV JAVA_HOME="/root/.sdkman/candidates/java/current"
ENV PATH="$JAVA_HOME/bin:$PATH"

# Establece el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo de configuración de Maven y el archivo pom.xml al directorio de trabajo
COPY pom.xml .

# Copia el resto del código fuente al directorio de trabajo
COPY src ./src

# Descarga las dependencias de Maven y compila el proyecto
RUN mvn clean compile

# Imagen final basada en la construcción anterior
FROM ubuntu:22.04

# Instala Maven, JDK y otras dependencias necesarias en la imagen final
RUN apt-get update && apt-get install -y \
    maven \
    openjdk-21-jdk \
    && rm -rf /var/lib/apt/lists/*

# Copia el código fuente y las dependencias compiladas desde la etapa anterior
COPY --from=base /app /app

# Establece el directorio de trabajo en /app
WORKDIR /app

# Establece JDK como la versión predeterminada de Java
ENV JAVA_HOME="/usr/lib/jvm/java-21-openjdk-arm64"
ENV PATH="$JAVA_HOME/bin:$PATH"

# Configura el punto de entrada para ejecutar las pruebas con Maven
ENTRYPOINT ["mvn", "clean", "test"]
