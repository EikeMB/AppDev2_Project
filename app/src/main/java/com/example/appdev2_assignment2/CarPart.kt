package com.example.appdev2_assignment2


data class CarPart(var name:String, var image:Int, var modelNum: Int, var description: String, var type: PartType, var price: Long) {

}

enum class PartType {
    Body, Engine, Wheels, Aerodynamics, Interior, Accessories
}





val carPartsList = listOf(
    CarPart("V6 Turbocharged Engine", R.drawable.v6turbo, 12345, "300-400 horsepower high-performance engine for enhanced power.", PartType.Engine, 5000),
    CarPart("Inline-4 EcoBoost Engine", R.drawable.inline4ecoboost, 67891, "350 horsepower fuel-efficient engine with turbo boost technology.", PartType.Engine, 3500),
    CarPart("V8 Supercharged Engine", R.drawable.v8supercharge, 54322, "600 horsepower powerful V8 engine with supercharger for maximum performance.", PartType.Engine, 7000),
    CarPart("Electric Motor Kit", R.drawable.ev, 13580, "Conversion kit for electric propulsion, eco-friendly option.", PartType.Engine, 6000),
    CarPart("Rotary wankel engine", R.drawable.rotary, 24681, "100-700 horsepower rotary engine.", PartType.Engine, 5500),

    CarPart("Forged Racing Wheels", R.drawable.forgedracingwheels, 67891, "Lightweight and strong racing wheels for enhanced performance.", PartType.Wheels, 1200),
    CarPart("Off-Road Tires and Wheels Kit", R.drawable.offroadwheels, 67892, "Durable off-road tires and wheels for adventurous driving.", PartType.Wheels, 1500),
    CarPart("Chrome Spoke Wheels", R.drawable.chromewheel, 67893, "Classic chrome spoke wheels for a retro look.", PartType.Wheels, 1000),
    CarPart("Carbon Fiber Rim Set", R.drawable.carbonwheels, 67894, "High-tech carbon fiber rims for a modern and sporty appearance.", PartType.Wheels, 2000),


    CarPart("Front Bumper Lip Spoiler", R.drawable.frontbumper, 13579, "Enhances aerodynamics and adds a sporty look.", PartType.Aerodynamics, 150),
    CarPart("Rear Diffuser Kit", R.drawable.reardiffuser, 13580, "Improves rear aerodynamics with a stylish rear diffuser.", PartType.Aerodynamics, 200),
    CarPart("Aero Wing Spoiler", R.drawable.rearwing, 13581, "Adds downforce and a distinctive look with an aero wing spoiler.", PartType.Aerodynamics, 300),
    CarPart("Side Mirror Aerodynamic Caps", R.drawable.mirrorcaps, 13582, "Aerodynamic caps for side mirrors to reduce drag.", PartType.Aerodynamics, 100),
    CarPart("Hood Vent Set", R.drawable.hoodvent, 13583, "Functional hood vents for improved airflow and cooling.", PartType.Aerodynamics, 120),

    CarPart("Leather Racing Seats", R.drawable.racingseat, 24680, "Comfortable and stylish racing seats for the interior.", PartType.Interior, 1500),
    CarPart("Alcantara Wrapped Steering Wheel", R.drawable.alcantarasteering, 24681, "Luxurious Alcantara wrapped steering wheel for a premium feel.", PartType.Interior, 500),
    CarPart("Custom Floor Mats Set", R.drawable.customfloor, 24682, "Tailor-made floor mats for a personalized interior.", PartType.Interior, 100),
    CarPart("Interior LED Lighting Kit", R.drawable.lighting, 24683, "LED lighting kit to enhance the interior ambiance.", PartType.Interior, 200),
    CarPart("Carbon Fiber Trim Kit", R.drawable.carbontrim, 24684, "Interior trim kit made of carbon fiber for a modern look.", PartType.Interior, 800),

    CarPart("LED Headlights Kit", R.drawable.ledheadlight, 98765, "Upgrades standard headlights to LED for better visibility.", PartType.Accessories, 200),
    CarPart("Smart Infotainment System", R.drawable.infotainment, 98766, "High-tech infotainment system with smart features.", PartType.Accessories, 1000),
    CarPart("Wireless Phone Charger", R.drawable.charger, 98767, "Convenient wireless charger for smartphones.", PartType.Accessories, 50),
    CarPart("Backup Camera System", R.drawable.camera, 98768, "Adds a backup camera for improved safety while reversing.", PartType.Accessories, 300),
    CarPart("Performance Exhaust System", R.drawable.exhaust, 98769, "Upgrades exhaust system for improved performance and sound.", PartType.Accessories, 600)
)

