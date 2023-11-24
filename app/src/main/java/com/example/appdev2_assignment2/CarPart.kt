package com.example.appdev2_assignment2


data class CarPart(var name:String, var image:Int, var modelNum: Int, var description: String, var type: PartType) {

}

enum class PartType {
    Body, Engine, Wheels, Aerodynamics, Interior, Accessories
}


var parts1: List<CarPart> = listOf(
    CarPart(
        name = "Mercedes A Class",
        image = R.drawable.a, // Replace with your actual image resource
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
        name = "Amg gt black series",
        image = R.drawable.amggtblackseries,
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
        name = "amg one",
        image = R.drawable.amgone,
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
var parts5 = listOf(
    CarPart(
        name = "ferrari f488gtb",
        image = R.drawable.f488gtb,
        modelNum = 103,
        description = "Description for Body Part 3",
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
var parts4: List<CarPart> = listOf(
    CarPart(
        name = "mercedes cla",
        image = R.drawable.cla,
        modelNum = 103,
        description = "Description for Body Part 3",
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
var parts22 = listOf(
    CarPart(
        name = "ferrari daytona",
        image = R.drawable.daytona,
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
var parts21 = listOf(
    CarPart(
        name = "mclaren artura",
        image = R.drawable.artura,
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
var parts20 = listOf(
    CarPart(
        name = "mercedes cls",
        image = R.drawable.cls,
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
var parts19 = listOf(
    CarPart(
        name = "mclaren speedtail",
        image = R.drawable.speedtail,
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
var parts18 = listOf(
    CarPart(
        name = "mclaren senna",
        image = R.drawable.senna,
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
var parts17 = listOf(
    CarPart(
        name = "ferrari roma",
        image = R.drawable.roma,
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
var parts16 = listOf(
    CarPart(
        name = "ferrari portofino",
        image = R.drawable.portofino,
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
var parts15 = listOf(
    CarPart(
        name = "mclaren p1",
        image = R.drawable.p1,
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
var parts14 = listOf(
    CarPart(
        name = "mclaren m720s",
        image = R.drawable.m720s,
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
var parts13 = listOf(
    CarPart(
        name = "ferrari laferrari",
        image = R.drawable.laferrari,
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
var parts12 = listOf(
    CarPart(
        name = "mercedes G wagon",
        image = R.drawable.gwagon,
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
var parts11 = listOf(
    CarPart(
        name = "ferrari fxxk",
        image = R.drawable.fxxk ,
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
var parts10 = listOf(
    CarPart(
        name = "ferrari f12",
        image = R.drawable.f12,
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
var parts9 = listOf(
    CarPart(
        name = "ferrari f8 tributo",
        image = R.drawable.f8tributo,
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
var parts8 = listOf(
    CarPart(
        name = "mclaren f1",
        image = R.drawable.f1,
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
var parts7 = listOf(
    CarPart(
        name = "mercedes eqe",
        image = R.drawable.eqe,
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


