package sodikov.weather_simple.model

data class Weather(
    val city:String,
    val time:String,
    val condition:String,
    val currentTemp:String,
    val maxTemp:String,
    val minTemp:String,
    val imageUrl:String,

)
