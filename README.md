# PSPUT3Tiqueto
Simulación de compras de un cine usando hilos, basado en los proyectos de productor y consumidor. El productor es el promotor y los FANS los consumidores.

PARA ESTA PRÁCTICA, ES NECESARIO ENVIAR UNA ESTIMACIÓN INICIAL DE TIEMPO, Y UN SEGUIMIENTO DE TIEMPO USANDO LAS APLICACIONES TOGGLE O CLOCKIFY. Más detalles en las normas de entrega al final.

Considera el caso de una web como TicketMaster

Un grupo de fervientes FANs están al acecho de unas entradas para su concierto favorito. Cuando entran a comprar y no hay entradas, necesitan esperar a que se repongan las entradas. El PROMOTOR del concierto pone entradas a la venta (repone entradas). Como quiere ir aumentando el precio según se van acabando, decide reponer entradas en la WEB de TicketMaster si no hay más entradas. Es decir, si hay entradas disponibles, se quedará esperando a que se terminen para reponer de nuevo. Y como le interesa subir el precio cada vez (menudo pirata), va reponiendo unas pocas cada vez, hasta llegar al total de entradas especificado.  En ese momento, decidirá cerrar la venta en la Web de compra de entradas.

 

ATENCIÓN: Se os da un proyecto de ejemplo con las clases necesarias.

 

Puntos clave (varios ya formalizados en el código que os doy):

·         Puede haber varios fans, pero sólo va a haber 1 promotor.

·         Tanto la compra de entradas, como la reposición, se gestiona dentro de una “web” de compra de entradas.

·         Cada fan sólo puede comprar 1 entrada cada vez que prueba.

·         Cada fan puede comprar las entradas que pueda para sus amigos mientras haya entradas disponibles.

·         Cada fan, tras comprar una entrada, se quedará durmiendo entre 1 y 3 segundos

·         El promotor, tras reponer entradas, se quedará durmiendo entre 3 y 8 segundos.

·         Cuando ya se alcanza el número de entradas total deseado por el promotor, se cierra la venta. Esto significa que los fans no podrán comprar más entradas, y detendrán su ansia de compra.

 

Pistas:

1.      Identifica qué papel juega cada actor dentro de los ejemplos que ya hemos visto de wait/notify.

2.      Identifica cuáles son las secciones críticas dentro de este ejercicio.

3.      Identifica las condiciones dentro de las secciones críticas que hacen a cada actor quedarse esperando por la notificación del otro.

4.      Puedes probar primero a hacer el ejercicio sin el cierre de la venta, y luego ya abordar el cierre de la venta.

5.   Leed los comentarios en la clase IOperacionesWeb.

 

Normas:

Sólo hay que escribir código en las clases proporcionadas.
 Todos los mensajes a imprimir por pantalla tienen que ser realizados con los métodos mensaje* que hay en cada clase.
En el caso del fan:
     Antes de comprar entrada, se imprimirá un mensaje por pantalla
     Después de comprar entrada, se imprimirá un mensaje por pantalla con las entradas totales que lleva ese fan
      Si detecta que se cierra la venta, lo dirá por pantalla también.

En el caso del promotor: 
     Antes de reponer entradas, se imprimirá un mensaje por pantalla.
     Cuando ya no tenga más entradas que reponer, imprimirá un mensaje por pantalla.

En el caso de la web:
       A la hora de comprar:
                   Si no hay entradas disponibles a comprar, se pondrá un mensaje antes de esperar.
                   Si se puede comprar una entrada, poner un mensaje de entrada comprada, y cuántas entradas quedan disponibles.
Normas de entrega:

La entrega por Aula Virtual consistirá en la exportación del proyecto base a un .jar, que incluya los fuentes del proyecto. El .jar deberá llamarse “tiqueto.jar”. El .jar exportado deberá poderse lanzar vía línea de comandos con la instrucción: java -jar tiqueto.jar
Se informará en la entrega el número de horas estimadas para la realización.
Se enviará un fichero de exportación o informe de Toggl o Clockify con los tiempos REALES medidos para la realización de la práctica. NO es un requisito que este número sea igual al estimado. Quiero el número REAL.
El fuente del proyecto se deberá entregar en un proyecto de IntelliJ en un repositorio de código en GitHub.
Informar en la entrega la URL del repositorio. Puede ser privado, pero podéis darme acceso (diego.di1@educa.madrid.org)
