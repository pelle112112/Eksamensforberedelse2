# Eksamen 3. semester efterår 2023 - Pelle Vedsmand

## Task 1
GET localhost:7777/api/plants
````
[
{
"id": 1,
"planttype": "Rose",
"name": "Albertine",
"maxheight": 400,
"price": 199.5
},
{
"id": 2,
"planttype": "Bush",
"name": "Aronia",
"maxheight": 100,
"price": 169.5
},
{
"id": 3,
"planttype": "FruitAndBerries",
"name": "AromaApple",
"maxheight": 100,
"price": 399.5
},
{
"id": 4,
"planttype": "Rhododendron",
"name": "Astrid",
"maxheight": 100,
"price": 269.5
},
{
"id": 5,
"planttype": "Rose",
"name": "The DarkLady",
"maxheight": 100,
"price": 199.5
},
{
"id": 0,
"planttype": "Tulipan",
"name": "Slaskeret",
"maxheight": 178,
"price": 12.5
}
]
````
______________________________________________________
GET localhost:7777/api/plants/3
````
{
"id": 3,
"planttype": "FruitAndBerries",
"name": "AromaApple",
"maxheight": 100,
"price": 399.5
}
````
___________________________________________________________
GET localhost:7777/api/plants/type/Rose
````
[
{
"id": 1,
"planttype": "Rose",
"name": "Albertine",
"maxheight": 400,
"price": 199.5
},
{
"id": 5,
"planttype": "Rose",
"name": "The DarkLady",
"maxheight": 100,
"price": 199.5
}
]
````
________________________________________________________________
POST localhost:7777/api/plants
Content-Type: application/json

Request:
````
{
"name": "Slaskeret",
"planttype": "Tulipan",
"maxheight": "178",
"price": "12.50"
}
````
Response:
````
{
"id": 0,
"planttype": "Tulipan",
"name": "Slaskeret",
"maxheight": 178,
"price": 12.5
}
````
## Task 2
I have handled my errors, by creating an errorhandler and using it to control the errorcodes and messages.
I have also created a custom exception.
The errors are handled in the controller:

GET localhost:7777/api/plants
```
public Handler getAll() {
        PlantDAO plantDAO = PlantDAO.getInstance();

        return ctx->{
            if(plantDAO.getAll().isEmpty()){
                ApiException apiException = new ApiException(404, "No plants found");
                ctx.status(404);
                ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
            }
            else {
                ctx.status(200);
                ctx.json(plantDAO.getAll());
            }
        };
    }
```
Here I check if the list is empty, and if it is, I throw an exception with errorcode 404, which is handled by the exceptionhandler. I use my custom exception to create a message, and I add the errorcode to the message.
If the code goes well, I return the list of plants with statuscode 200.
______________________________________________________________________________________________

GET localhost:7777/api/plants/{id}
```
public Handler getById() {
        PlantDAO plantDAO = PlantDAO.getInstance();

        return ctx->{
            int id = Integer.parseInt(ctx.pathParam("id"));
            try {
                PlantDTO foundPlant = plantDAO.getById(id);
                ctx.status(200);
                ctx.json(foundPlant);
            } catch (Exception e) {
                ctx.status(404);
                ApiException apiException = new ApiException(404, "No plant found with id: " + id);
                ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
            }
        };
    }
```
Here I try to find a plant with the given id, and if it is not found, I throw an exception with errorcode 404, which is handled by the exceptionhandler. I also handle if theres something wrong with the input (if its null or not a number), with a try/catch.
If the code goes well, I return the plant with statuscode 200.

______________________________________________________________________________________________
GET localhost:7777/api/plants/type/{type}
```
public Handler getByType() {
        PlantDAO plantDAO = PlantDAO.getInstance();

        return ctx->{
            String type = "";
            try {
                type = ctx.pathParam("type");
                List<PlantDTO> foundPlants = plantDAO.getByType(type);
                if(foundPlants.isEmpty()){
                    ctx.status(404);
                    ApiException apiException = new ApiException(404, "No plants found with type: " + type);
                    ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
                }
                else {
                    ctx.status(200);
                    ctx.json(plantDAO.getByType(type));
                }
            } catch (Exception e) {
                ctx.status(400);
                ApiException apiException = new ApiException(400, "Something went wrong with your input: " + type);
                ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
            }
        };
    }
```
Here I try to find all plants with the given type, and if none are found, I throw an exception with errorcode 404, which is handled by the exceptionhandler. I also handle if theres something wrong with the input (if its null), with a try/catch and a 400 error code.
If the code goes well, I return the list of plants with statuscode 200.

______________________________________________________________________________________________
POST localhost:7777/api/plants
```
public Handler add() {
        PlantDAO plantDAO = PlantDAO.getInstance();

        return ctx->{
            try {
                PlantDTO plant = ctx.bodyAsClass(PlantDTO.class);
                ctx.status(201);
                ctx.json(plantDAO.add(plant));
            }
            catch (Exception e){
                ctx.status(400);
                ApiException apiException = new ApiException(400, "Something went wrong with your input: " + ctx.body());
                ctx.json(apiException.getMessage()+ ".  Error code: " + apiException.getStatusCode());
            }

        };
    }
```
Here I try to add a plant, and if it fails, I throw an exception with errorcode 400, which is handled by the exceptionhandler.
If the code goes well, I return the plant with statuscode 201.


## Task 3

Stream API is inspired by functional programming, which has a strong focus on immutability and pure functions.
Im supposed to not change the input data, but instead create a new List or Map with the result.


## Task 4
Endpoints:
GET localhost:7777/api/plants
```
[
  {
    "id": 1,
    "type": "Rose",
    "name": "Albertine",
    "maxheight": 400,
    "price": 199.5,
    "reseller": {
      "id": 1,
      "name": "Lyngby Plancecenter",
      "address": "Firskovvej 18",
      "phone": "33212334"
    }
  },
  {
    "id": 2,
    "type": "Bush",
    "name": "Aronia",
    "maxheight": 200,
    "price": 169.5,
    "reseller": {
      "id": 1,
      "name": "Lyngby Plancecenter",
      "address": "Firskovvej 18",
      "phone": "33212334"
    }
  },
  {
    "id": 3,
    "type": "FruitAndBerries",
    "name": "AromaApple",
    "maxheight": 350,
    "price": 399.5,
    "reseller": {
      "id": 2,
      "name": "Glostrup Planter",
      "address": "Tværvej 35",
      "phone": "32233232"
    }
  },
  {
    "id": 4,
    "type": "Rhododendron",
    "name": "Astrid",
    "maxheight": 40,
    "price": 269.5,
    "reseller": {
      "id": 2,
      "name": "Glostrup Planter",
      "address": "Tværvej 35",
      "phone": "32233232"
    }
  },
  {
    "id": 5,
    "type": "Rose",
    "name": "The DarkLady",
    "maxheight": 100,
    "price": 199.5,
    "reseller": {
      "id": 3,
      "name": "Holbæk Planteskole",
      "address": "Stenhusvej 49",
      "phone": "59430945"
    }
  }
]
```
______________________________________________________________________________________________

GET localhost:7777/api/plants/3
````
{
  "id": 3,
  "type": "FruitAndBerries",
  "name": "AromaApple",
  "maxheight": 350,
  "price": 399.5,
  "reseller": {
    "id": 2,
    "name": "Glostrup Planter",
    "address": "Tværvej 35",
    "phone": "32233232"
  }
}
````
______________________________________________________________________________________________

GET localhost:7777/api/plants/type/Rose
````
[
  {
    "id": 1,
    "type": "Rose",
    "name": "Albertine",
    "maxheight": 400,
    "price": 199.5,
    "reseller": {
      "id": 1,
      "name": "Lyngby Plancecenter",
      "address": "Firskovvej 18",
      "phone": "33212334"
    }
  },
  {
    "id": 5,
    "type": "Rose",
    "name": "The DarkLady",
    "maxheight": 100,
    "price": 199.5,
    "reseller": {
      "id": 3,
      "name": "Holbæk Planteskole",
      "address": "Stenhusvej 49",
      "phone": "59430945"
    }
  }
]
````
______________________________________________________________________________________________

POST localhost:7777/api/plants
Content-Type: application/json

Request:
````
{

    "name": "Slaskeret",
    "planttype": "Tulipan",
    "maxheight": "178",
    "price": "12.50"
}
````
Response:
````
{
  "id": 8,
  "type": "Tulipan",
  "name": "Slaskeret",
  "maxheight": 178,
  "price": 12.5,
  "reseller": null
}
````
## Task 5

## Task 6