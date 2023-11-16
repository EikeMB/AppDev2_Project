package com.example.appdev2_assignment2

data class CarPart(var name:String, var image:Int, var modelNum: Int, var description: String, var type: PartType) {

}

enum class PartType {
    Body, Engine, Wheels, Aerodynamics, Interior, Accessories
}


var parts1: List<CarPart> = listOf(
    CarPart(
        name = "Body Part 1",
        image = 0, // Replace with your actual image resource
        modelNum = 101,
        description = "Description for Body Part 1",
        type = PartType.Body
    ),
    CarPart(
        name = "Engine Part 1",
        image = 0, // Replace with your actual image resource
        modelNum = 201,
        description = "Description for Engine Part 1",
        type = PartType.Engine
    ),
    CarPart(
        name = "Wheels Part 1",
        image = 0, // Replace with your actual image resource
        modelNum = 301,
        description = "Description for Wheels Part 1",
        type = PartType.Wheels
    ),
    CarPart(
        name = "Aerodynamics Part 1",
        image = 0, // Replace with your actual image resource
        modelNum = 401,
        description = "Description for Aerodynamics Part 1",
        type = PartType.Aerodynamics
    ),
    CarPart(
        name = "Interior Part 1",
        image = 0, // Replace with your actual image resource
        modelNum = 501,
        description = "Description for Interior Part 1",
        type = PartType.Interior
    ),
    CarPart(
        name = "Accessories Part 1",
        image = 0, // Replace with your actual image resource
        modelNum = 601,
        description = "Description for Accessories Part 1",
        type = PartType.Accessories
    )
)

var parts2: List<CarPart> = listOf(
    CarPart(
        name = "Body Part 2",
        image = 0,
        modelNum = 102,
        description = "Description for Body Part 2",
        type = PartType.Body
    ),
    CarPart(
        name = "Engine Part 2",
        image = 0,
        modelNum = 202,
        description = "Description for Engine Part 2",
        type = PartType.Engine
    ),
    CarPart(
        name = "Wheels Part 2",
        image = 0,
        modelNum = 302,
        description = "Description for Wheels Part 2",
        type = PartType.Wheels
    ),
    CarPart(
        name = "Aerodynamics Part 2",
        image = 0,
        modelNum = 402,
        description = "Description for Aerodynamics Part 2",
        type = PartType.Aerodynamics
    ),
    CarPart(
        name = "Interior Part 2",
        image = 0,
        modelNum = 502,
        description = "Description for Interior Part 2",
        type = PartType.Interior
    ),
    CarPart(
        name = "Accessories Part 2",
        image = 0,
        modelNum = 602,
        description = "Description for Accessories Part 2",
        type = PartType.Accessories
    )
)

var parts3: List<CarPart> = listOf(
    CarPart(
        name = "Body Part 3",
        image = 0,
        modelNum = 103,
        description = "Description for Body Part 3",
        type = PartType.Body
    ),
    CarPart(
        name = "Engine Part 3",
        image = 0,
        modelNum = 203,
        description = "Description for Engine Part 3",
        type = PartType.Engine
    ),
    CarPart(
        name = "Wheels Part 3",
        image = 0,
        modelNum = 303,
        description = "Description for Wheels Part 3",
        type = PartType.Wheels
    ),
    CarPart(
        name = "Aerodynamics Part 3",
        image = 0,
        modelNum = 403,
        description = "Description for Aerodynamics Part 3",
        type = PartType.Aerodynamics
    ),
    CarPart(
        name = "Interior Part 3",
        image = 0,
        modelNum = 503,
        description = "Description for Interior Part 3",
        type = PartType.Interior
    ),
    CarPart(
        name = "Accessories Part 3",
        image = 0,
        modelNum = 603,
        description = "Description for Accessories Part 3",
        type = PartType.Accessories
    )
)