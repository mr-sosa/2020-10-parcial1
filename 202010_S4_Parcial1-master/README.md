# Instrucciones

1. Haga un fork de este repositorio
2. Clone el repositorio bifurcado en su máquina virtual
3. Abra los proyectos en Netbeans
4. En Netbeans vaya a Services > Databases > JavaDB y cree una base de datos que se llame **recipe** (los demás campos déjelos en blanco)

## Contexto

Esta aplicación permite gestionar recetas. El recurso presente en la aplicación es Receta, la cual tiene un nombre (name), un año de creación (Integer), un tipo (string), una descripción (String) y un id (Long) que es la llave primaria.

Se nos ha solicitado completar la aplicación:

* Se desea que el sistema permita gestionar ahora el listado de ingredientes de cada receta.
* De cada ingrediente se conoce su nombre (name) (String), su proporción en gramos (Integer), la cantidad de calorías (Integer), si tiene gluten o no (boleano) y se tiene un campo
id (Long) que representa la llave primaria del ingrediente.

## Punto 1 (30%): Persitencia

1. (5%) Cree el entity para el ingrediente
2. (5%) Asocie la entidad ingrediente con la entidad receta. La relación es de uno a muchos, de tipo composición. Una receta tiene muchos ingredientes.
3. (10%) Implemente la clase persistence para la entidad receta solo con el método create.
4. (10%) Implemente la prueba unitaria que valida que se ha creado una receta correctamente. No valide la asociación.

## Punto 2 (40%): Lógica

Usted debe crear la lógica de la aplicación que cubra las reglas de negocio para la entidad RecipeEntity. Las reglas de negocio para crear una receta son:

El nombre de la receta no puede ser ni nula ni vacía.
El año de creación no debe ser inferior al año actual menos 10 años.
Si el tipo de comida es "Sin gluten" ningún ingrediente debe tener gluten.
Si el tipo de comida es "Baja en grasa" la cantidad de calorías de todos los ingredientes no debe superar 1200.

1. (20%) Crear el método en la capa de lógica que valide las reglas de negocio y solicita persistir en caso que todas pasen. (Sólo para método crear)

2. (20%) Crear al menos dos pruebas unitarias: una que valida el escenario ideal en que todas las reglas de negocio se aprueban, y otra en que valide cuando una regla de negocio falla (la que desee). Si las reglas de negocio se cumplen, se debe llamar la persistencia para que el objeto sea persistido, de lo contrario debe lanzar una excepción BusinessLogicException con un mensaje donde se especifique el problema.

## Punto 3 (30%): API Rest

En la aplicación le hemos brindado parte de la capa REST API para probar. Usted debe:

1. (5%) Crear las clases DTO correspondientes con un constructor vacío
2. (5%) Crear el método toEntity en ambos DTO que creen un objeto entity de un objeto DTO.
3. (5%) Agregue los métodos constructores que reciben un entities y hacen el mapeo correspondiente

En la clase RecipeResource usted debe:

4. (5%) Modificar el método create para que llame al método de la lógica que crea la receta, y retorne al usuario el nuevo elemento creado.
5. (10%) Realice las siguientes pruebas y tome pantallazos donde se evidencie la hora y la IP de su máquina.

Ud. debe extender su programa para que cuando ejecute

```POST localhost:8080/recipes-web/api/recipes```

Con el body:

```json
{
  "name": "Arroz con pollo",
  "year": 2018,
  "description": "Plato delicioso del centro del país"
  "tipo": "Comida criolla",
  "ingredientes": [{
               "name": "Arroz",
  "grams": 100,
  "calories": 690,
  "gluten": true
            },{
               "name": "Pollo",
  "grams": 500,
  "calories": 1200,
  "gluten": false
}]
}
```
Respuesta 200

```json
{
  "name": "Ensalada César",
  "year": 2013,
  "description": "ensalada baja en grasa, perfecta para la noche"
  "tipo": "Comida saludable",
  "ingredientes": [{
               "name": "Lechuga",
  "grams": 100,
  "calories": 690,
  "gluten": false
            },{
               "name": "Pollo",
  "grams": 500,
  "calories": 1200,
  "gluten": false
}]
}
```
Respuesta 412


```json
{
  "name": "Ensalada César",
  "year": 1980,
  "description": "ensalada baja en grasa, perfecta para la noche"
  "tipo": "Comida saludable",
  "ingredientes": []
}
```
Respuesta 412

Por último tome foto de las tablas de la base de datos donde se presente el registro que se guardó.

## Entrega

Agregue los pantallazos de las pruebas de Postman a la carpeta images de su repositorio

Haga commit y push a la rama master

Cree un release de su código con el nombre "Parcial1_2603_<usuario_uniandes>".

Suba el archivo zip del release como respuesta a la evaluación en SICUA
