package androidtown.org.mp2023termp

data class Post(var title : String? = null,
                var content : String? = null,
                var image : String? = null) {
    constructor() : this(null, null, null)
}

