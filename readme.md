# 📱 Automatización Móvil con Appium (Android)

Este proyecto contiene pruebas automatizadas para una aplicación Android utilizando **Appium**, **Java**, y **TestNG**.
La configuración de este proyecto ha sido "homologada" para usar una combinación específica de Appium y Selenium para evitar conflictos de dependencia.

---

## 🚀 Tecnologías utilizadas

- Appium
- Java 
- TestNG
- IntelliJ IDEA
- Appium Inspector (opcional)
- Emulador Android

---

## 1. 💻 Pre-requisitos del Entorno (Instalación Manual)

Este software debe estar instalado manualmente en la máquina (Mac o Windows) antes de instalar el proyecto.

1.  **Java JDK 21:** El proyecto está configurado para compilar con Java 21. 
2.  **Node.js:** Se necesita una versión LTS activa (ej. **20.x** o **22.x**). Esto es necesario para instalar y correr el Servidor Appium.
3.  **Android Studio:** Necesario para el SDK de Android y para crear los Emuladores (AVD).
4.  **Maven:** (Opcional, pero recomendado) Para correr comandos desde la terminal. IntelliJ y VSC ya lo traen integrado.

---

## 2. 🛠️ Configuración de Variables de Entorno

Appium y Maven necesitan saber dónde están instaladas tus herramientas. Esto es **crítico**.

Asegúrate de que estas variables de entorno estén configuradas en tu sistema (en `.zshrc` o `.bash_profile` en Mac; en "Propiedades del Sistema" en Windows):

* `JAVA_HOME`: Debe apuntar a tu carpeta de instalación de JDK (ej. `/Library/Java/JavaVirtualMachines/jdk-21.jdk/...` o `C:\Program Files\Java\jdk-21`).
* `ANDROID_HOME`: Debe apuntar a tu carpeta del SDK de Android (ej. `/Users/tu_usuario/Library/Android/sdk` o `C:\Users\tu_usuario\AppData\Local\Android\Sdk`).

Asegúrate también de que tu variable `PATH` incluya las siguientes carpetas:
* `$JAVA_HOME/bin`
* `$ANDROID_HOME/platform-tools`
* `$ANDROID_HOME/emulator`
* `$ANDROID_HOME/tools` (y/o `tools/bin`)

---

## 3. 🛠️ Estructura del Proyecto

src  
├── main  
│   └── java  
│       └── org.example.screens       # Page Objects (LoginScreen, HomeScreen, etc.)  
└── test  
└── java  
└── org.example                       # Clases de prueba (ChatbotTests, BaseTest, etc.)

Otros archivos:
- .gitignore
- README.md

---

## 4. 🚀 Clonación e Instalación del Proyecto

Estos pasos descargarán el código e instalarán la combinación exacta de librerías.

### a. **Clona el proyecto:**
```bash
    git clone [URL_DE_TU_REPOSITORIO.git]
    cd MyAppiumTest
  ```

### b. **Instala las Dependencias de Java (Maven):**
Este comando leerá el `pom.xml` y descargará la combinación compatible que encontramos: **(Appium 9.2.1 + Selenium 4.21.0)**.
    ```bash
    mvn clean install
    ```
*(Si este comando falla por compilación, asegúrate de que tu IDE no esté bloqueando los archivos y que estés usando JDK 21).*

### c. **Instala el Servidor Appium (Node):**
Este comando leerá el `package.json` para instalar Appium localmente.
    ```bash
    npm install
    ```

### d. **Instala el Driver de Android:**
Este comando (definido en `package.json`) usa el Appium local para instalar el driver `uiautomator2`.
    ```bash
    npm run setup:drivers
    ```
---

## 4. ▶️ Cómo Ejecutar las Pruebas

Para ejecutar las pruebas, **siempre** deben estar corriendo dos cosas primero: el Emulador y el Servidor Appium.
### a.  **Inicia el Emulador:** 
Abre Android Studio y lanza el emulador (asegúrate de que el nombre coincida con el de `BaseTest.java`, ej: `emulator-5554`).
### b.  **Inicia el Servidor Appium:** 
Abre una terminal en la raíz del proyecto y corre:
    ```
    npx appium
    ```
    *No cierres esta terminal. Verás el log de Appium diciendo que está escuchando en el puerto 4723.*

### c. **Ejecuta las Pruebas:**
* **Desde el IDE (IntelliJ/VSC):** Abre el archivo `ChatbotTests.java` y haz clic en el botón (▶️) de "Run Test".
* **Desde la Terminal (Recomendado):** Abre una **segunda terminal** (deja `npx appium` corriendo en la primera) y ejecuta el comando de Maven:
    ```bash
    mvn test
    ```
---

## 5. 📂 Archivos importantes
•	BaseTest.java: Configura el AppiumDriver para Android
•	LoginScreen.java: Page Object del login (inputs y botón)
•	HomeScreen.java: Pantalla que aparece luego del login
•   ChatWithUendiScreen.java: Pantalla de chatbot 
•	ChatbotTests.java: Pruebas de navegación y validación de flujo

## 6. 🧼 Limpieza antes de cada ejecución

Para garantizar pruebas consistentes:
```bash
  driver.resetApp(); // Limpia datos entre ejecuciones
   ``` 
## 📝 Notas importantes
- El campo de usuario (teléfono) puede comportarse de forma flaky, por lo que se prioriza llenar el segundo campo (contraseña)
- El botón “Iniciar sesión” solo se habilita si ambos campos están completos
- Si Appium Inspector no está funcionando, puedes usar driver.getPageSource() para inspeccionar elementos manualmente
- Se utiliza WebDriverWait para asegurar que los elementos estén listos antes de interactuar.
- Mejorar Assert de resultado de las respuestas del chatbot
- Se usa archivo JSON para crear preguntas y archivo JSON de salida (test-output)
- Usa un emulador con version mayor a 35 de android preferiblemente Pixel 9
- Se está usando **.apk de microapp** la cual se debe definir la ruta donde se está guardando esta app en **BaseTest.java** (hay que modificarlo para sacarlo de ahi)
  
```bash
  .setApp(".../app-debug.apk") 
   ``` 
## ✅ Casos de prueba cubiertos
- Login exitoso 
- Navegación a la pantalla de bienvenida 
- Acceso al chatbot a través del botón “Open Chat with Uendi”
- Acceso a chatbot y hace un mini recorrido de opcion Tarjetas en el arbol
