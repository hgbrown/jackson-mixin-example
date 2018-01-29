# Jackson Mixin Example

Prior to Jackson 1.2, the only way to serialize or deserialize JSON using Jackson was by using one of the following two methods:

- Adding annotations to modify the POJO classes
- Writing custom serializers and deserializers

What if you want to serialize or deserialize a 3rd party POJO which you don’t have access to its source code. 
Or you might want your code clean and open to other JSON library, such as GSON.

Jackson mix-in annotations helps you resolve this kind of problems. 
These annotations are used in a mix-in class or interface but function as if they were directly included in the target class.

## The Jackson Mix-in Class

For mix-in annotation, you first need to define a mix-in class or interface.

Ensure that the mix-in class has a constructor matching the source POJO.

Use the `@JsonCreator` annotation on the constructor and the `@JsonProperty` property to specify all the properties of the POJO.

After creating the mix-in class, you must configure the `ObjectMapper` to use the mix-in.

