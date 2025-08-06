# 📱 Automatización Móvil con Appium (Android)

Este proyecto contiene pruebas automatizadas para una aplicación Android utilizando **Appium**, **Java**, y **TestNG**.

---

## 🚀 Tecnologías utilizadas

- Appium
- Java 17+
- TestNG
- IntelliJ IDEA
- Appium Inspector (opcional)
- Emulador Android

---

## 🛠️ Estructura del Proyecto

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

## ⚙️ Configuración del entorno

1. Instala Appium
   ```bash
   npm install -g appium
   ```
2. Verifica que Appium esté funcionando
 ```bash
   appium doctor   
   ``` 
3. Instala los drivers necesarios (Por ahora android)
 ```bash
   appium driver install uiautomator2
   appium driver install xcuitest
 ``` 
4.	Inicia Appium Server
```bash
   appium   
``` 
5. Ejecuta un emulador Android desde Android Studio
    Para este caso es importante que tengas la version dev del .apk del chatbot (microapp)
    junto con que el emulador android a usar sea android 36 y preferiblemente Pixel 9.
    Se recomienda abrir en proyecto nuevo para apk y usar el emulador mencionado.

## 🧪 Ejecución de pruebas

Desde IntelliJ IDEA:
 - Click derecho en la clase de pruebas → Run 'ChatbotTests' 
 - Desde línea de comandos (si configuraste ejecución):
```bash
   mvn test
   ``` 
## 📂 Archivos importantes
•	BaseTest.java: Configura el AppiumDriver para Android
•	LoginScreen.java: Page Object del login (inputs y botón)
•	HomeScreen.java: Pantalla que aparece luego del login
•   ChatWithUendiScreen.java: Pantalla de chatbot 
•	ChatbotTests.java: Pruebas de navegación y validación de flujo

## 🧼 Limpieza antes de cada ejecución

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
   - Se está usando **.apk de microapp** la cual se debe definir la ruta donde se está guardando esta app en BaseTest.java (hay que modificarlo para sacarlo de ahi)
```bash
  .setApp(".../app-debug.apk") 
   ``` 
## ✅ Casos de prueba cubiertos
- Login exitoso 
- Navegación a la pantalla de bienvenida 
- Acceso al chatbot a través del botón “Open Chat with Uendi”
- Acceso a chatbot y hace un mini recorrido de opcion Tarjetas en el arbol
