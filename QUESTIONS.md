# PARTE TEORICA

### Arquitecturas de UI: MVP, MVVM y MVI

#### MVVM

##### ¿En qué consiste esta arquitectura?
MVVM son las siglas para la arquitectura Model-View-ViewModel.
Su principal propósito es la completa separación de los roles y responsabilidades de los elementos
que forman la aplicación.

- View: Muestra la información e informa a las otras capas sobre las acciones del usuario.
- ViewModel: Expone la información a las vistas.
- Model: Obtiene la información de un repositorio y la expone a los ViewModels.

La principal diferencia con otras arquitecturas es que el ViewModel no contiene ninguna referencia a
la vista. Únicamente expone la información sin importar quién la consume.

El ViewModel expone eventos que la View puede observar, además es ajeno al ciclo de vida de las
Activities. Esto evita que se pierda la información si la Activity se destruye.

##### ¿Cuáles son sus ventajas?
Facilita mucho el testeo de la lógica de negocio de la app, ya que se encuentra contenida en los
ViewModels y es totalmente ajena  la vista. Por lo tanto esta se puede testear sin requerir el
Android Runtime.

Esta arquitectura también evita el problema de los "fat controllers" separando claramente las
responsabilidades de los módulos que forman la app.

##### ¿Qué inconvenientes tiene?
Esta arquitectura puede resultar compleja para aplicaciones con una IU muy simple, añadir tanto
nivel de abstacción puede resutar en mucho código "boilerplate" y complejidad innecesaria.

#### MVP

##### ¿En qué consiste esta arquitectura?
MVP son las siglas para la arquitectura Model-View-Presenter.
Su principal propósito es la separación de responsabilidades entre los módulos de la app para
poder someter la app a "unit tests". Para ello conviene que el modelo y el presentador no
implementen lógica del Framework de Android.

A diferencia de MVC, el punto de entrada de la aplicación será la Vista (en lugar del controlador).

Los elementos que la componen son:
- Model: Gestiona la capa de datos y la lógica de negocio.
- Presenter: Gestiona la presentación de los datos vinculando la vista con el modelo. No debe
contener clases específicas del framework de Android.
- View: Se implementará con una Activity o un Fragment. Mostrará los datos y gestinará las acciones
del usuario.

El presentador y la vista se comunicarán entre sí mediante interfaces, evtand así que el presentador
contenga una referencia directa a la Activity o Fragment de la view.

##### ¿Cuáles son sus ventajas?
Con esta arquitectura podemos crear Modelos y Presentadores que puedan someterse a test unitarios,
así podemos testear la lógica de la app de manera sencilla utilizando JUnit.

MVP es una adaptación de MVC para qe pueda funcionar sobre aplicaciones Android.

##### ¿Qué inconvenientes tiene?
Con esta arquitectura debemos gestionar qué ocurre cuando se destruye una Activity.
Deberemos asegurar que el Presentador elimina sus suscripciones a la vista y cancela sus AsyncTasks
si esto ocurre.

Si queremos destruir el presentador con la Activity deberemos asegurarnos de no tener ninguna
referncia activa para que el recolector de basura pueda hacer su trabajo.

#### MVI

##### ¿En qué consiste esta arquitectura?
MVI son las siglas para la arquitectura Model-View-Intent.
Es una de las arquitecturas más recientes en Android y funciona de manera muy distinta a las
anteriores.

El rol de cada un de sus componentes es el siguiente:
-Model: Representa un estado de la aplicación, deben ser inmutables para garantizar el flujo de
datos unidireccional.
-Intent: Representa la intención o deseo de realizar una acción por parte del usuario.
-View: Al igual que en la arquitectura MVP son representadas con interfaces que son implementadas
mediante activities o fragmens.

Esta arquitectura crea un flujo de datos circular y unidireccional entre sus componentes.
De esta forma la view observa la las aaciones del usuario y renderiza el modelo, el presentador crea
un nuevo modelo que representa un estado de la aplicación y el modelo controla la lógica de negocio
y almacena el estado de la aplicación.

##### ¿Cuáles son sus ventajas?
Sus principales ventajas son: utlizar un flujo de daos unidireccional y circular en la app, mantener
un estado consistente durante todo el ciclo de voda de las vistas y gestionar el modelo de manera
fiable y segura ante múltples threads en aplicaciones grandes.

##### ¿Qué inconvenientes tiene?
Esta arquitectura posee una curva de aprendizaje más alta que las anteriores ya que requiere
conocimientos extensos sobre progamación reactiva. No es recomendada para desarrolladores novatos.

---

### Testing

#### ¿Qué tipo de tests se deberían incluir en cada parte de la pirámide de test? Pon ejemplos de librerías de testing para cada una de las partes. 
Escribe aquí tu respuesta

#### ¿Por qué los desarrolladores deben centrarse sobre todo en los Unido Tests?
Escribe aquí tu respuesta

---

### Inyección de dependencias

#### Explica en qué consiste y por qué nos ayuda a mejorar nuestro código.
Escribe aquí tu respuesta

#### Explica cómo se hace para aplicar inyección de dependencias de forma manual a un proyecto (sin utilizar librerías externas).
Escribe aquí tu respuesta