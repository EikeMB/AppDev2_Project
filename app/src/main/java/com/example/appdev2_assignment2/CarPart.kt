package com.example.appdev2_assignment2

open class CarPart(var modelNum: Int, var description: String) {


    lateinit var combatibilityList: List<CarPart>
}

var parts1: List<CarPart> = listOf(
    Body("R34", 0, 0, "Body of a nissan skyline r34"),
    Engine("V12", 0, 1, "V12 Engine"),
    Wheels("Alloy Wheels", 0, 2, "High-performance alloy wheels"),
    Aerodynamics("Spoiler", 0, 3, "Aerodynamic spoiler for improved downforce"),
    Interior("Leather Seats", 0, 4, "Luxurious leather seats"),
    Accessories("GPS Navigation", 0, 5, "Advanced GPS navigation system"),
)

var parts2: List<CarPart> = listOf(
    Body("Ferrari F430 Body", 0, 6, "Sleek body for Ferrari F430"),
    Engine("Turbocharged V8", 0, 7, "Turbocharged V8 Engine"),
    Wheels("Performance Tires", 0, 8, "High-performance tires for superior grip"),
    Aerodynamics("Front Splitter", 0, 9, "Front splitter for enhanced aerodynamics"),
    Interior("Carbon Fiber Trim", 0, 10, "Carbon fiber interior trim for a sporty look"),
    Accessories("Premium Sound System", 0, 11, "High-end premium sound system"),
)

var parts3: List<CarPart> = listOf(
    Body("2023 Porsche 911 GT3", 0, 101, "Aerodynamically designed body for the Porsche 911 GT3"),
    Engine("Mercedes-AMG GT 4.0L V8", 0, 102, "4.0-liter V8 engine from the Mercedes-AMG GT"),
    Wheels("BBS Forged Alloy Wheels", 0, 103, "High-performance BBS forged alloy wheels"),
    Aerodynamics("Carbon Fiber Front Splitter", 0, 104, "Carbon fiber front splitter for improved downforce"),
    Interior("Recaro Racing Seats", 0, 105, "Sporty and comfortable Recaro racing seats"),
    Accessories("Bose Surround Sound System", 0, 106, "Premium Bose surround sound audio system")
)