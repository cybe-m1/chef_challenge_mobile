# Repositories

Repositories are used to fetch objects from firebase. Each repository must follow the following pattern :

```Kotlin
@Singleton
class ObjectRepository @Inject constructor() {
    private val mObjectCollection = Firebase.firestore.collection("OBJECTS")

    # Add your functions
}
```