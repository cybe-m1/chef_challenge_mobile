# Entities

Each entitie represents an object in the project. To instantiate a entity, use the following pattern :

```Kotlin
data class NewEntity(
    val id: String,     # The string id (for Firebase)
    val label: String   # Every other field in objectd
)
```