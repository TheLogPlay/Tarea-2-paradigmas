Trabajo realizado por Benjamin Troncoso

Explicacion de cada archivo:

TestController
Esta clase esta destinada para llevar el seguimiento de la pregunta actual, permite la navegacion entre preguntas, guarda las respuestas, las evalua, muestra los porcentajes de aprobacion por nivel y tipo de pregunta ademas de exponerlos.

Pregunta
Esta clase corresponde a la clase "padre" de los tipos de pregunta, es decir, contiene sus atributos como metodos generales de la clase.

PreguintaMulti y PreguntaVF
Estas clases son las clases derivadas de la clase pregunta, y contienen los atributos y metodos asociados a cada tipo de pregunta.

CargaPregunta
Es la funcion que permite leer un archivo CSV, y cargar las preguntas para poder procesarlas por el controlador y permitir la ejecucion de la prueba.

HomeView
Esta clase pertenece a la ventana de inicio, la cual muestra la prueba cargada mediante el archivo CSV, la cantidad de preguntas y el tiempo total para su resolucion. Ademas indica si el archivo se cargo de manera correcta o si hubo un error al cargarlo.

TestView
Esta clase permite la realizacion de la prueba. Al finalizar la prueba, dirigira al usuario a la revision de esta.

ResumeView
Esta clase permite visualizar el porcentaje de acierto en cada tipo de pregunta y segun su nivel de taxonomia de Bloom, ademas permite redirigirse a revisar la prueba pregunta por preguntas.

ReviewView
Esta clase se encuentra disponible luego de realizar la prueba y permite revisar una a una cada pregunta, para ver si esta correcta o no, y en dado caso saber cual es la respuesta correcta.

Funcionamiento:
En primer lugar se debe ejecutar la clase principal "Main".
Luego de esto, se desplegara una ventana para elegir el archivo CSV, el cual debe tener un formato especifico para su correcto funcionamiento.
Si la carga se ha realizdo correctamente, se vera un resumen de la cantidad de preguntas y el tiempo estimado de resolucion en segundos. Ademas se habilitara el boton para iniciar la evaluacion.
Al iniciar la evaluacion, se podra navegar entre las distintas preguntas, en caso de responder una, la respuesta quedara guardada.
Al llegar a la ultima pregunta, se deshabilitara el avanzar a la siguiente pregunta, y a su vez se mostrara el boton para entregar la prueba.
Al entregarla, se mostrara un resumen con el porcentaje de preguntas acertadas para cada tipo de pregunta como tambien cada nivel de la taxonomia de Bloom.
A su vez, se podra revisar pregunta a pregunta si la respuesta selecionada esta correcta o no.

Formato para el archivo CSV:
tipo,nivel_bloom,enunciado,opcionA,opcionB,opcionC,opcionD,respuesta_correcta,tiempo_estimado (La respuesta correcta se refiere a la letra asociada, ya sea A, B, C o D)
vf,nivel_bloom,enunciado,verdadero,falso,,,respuesta_correcta,tiempo_estimado

Ejemplos de preguntas:
multiple,Entender,¿Qué representa el denominador en una fracción?,La parte total,La parte dividida,La unidad,El número menor,A,35
vf,Entender,La Tierra gira alrededor del Sol.,verdadero,falso,,,verdadero,25
