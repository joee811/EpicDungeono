package scenes

interface Renderable {
    fun render(): String
}

class Scene(
    val description: String
): Renderable {

    val doors = mutableListOf<Door>()

    override fun render(): String {
        var fulldesc = "I am in $description"
        doors.forEach { door ->
            fulldesc += "\n there is a ${door.render()}"
        }
        return fulldesc
    }
}

data class Door(
    val description: String,
    val areaA: Scene,
    val areaB: Scene
): Renderable {

    init{
        areaA.doors.add(this)
        areaB.doors.add(this)
    }

    override fun render(): String {
        return description
    }

    fun openFrom(scene: Scene): Scene {
        val toScene = if(scene.description == areaA.description){
            areaB
        }else{
            areaA
        }
        println("The door opens with a creak, and I enter...")
        return toScene
    }
}

class SceneMap(
    startingScene: Scene
){
    private var currentScene = startingScene

    fun startGameLoop(){
        while (true){
            println("What do you want to do?")
            val command = readln()
            if(command.startsWith("look")){
                lookAround()
            }else if(command.startsWith("open")){
                open(command.substringAfter("open").trim())
            }
            println()
        }
    }

    private fun lookAround(){
        println("${currentScene.render()}")
    }

    private fun open(thingToOpen: String){
        val thingToOpenClean = thingToOpen.lowercase().trim()
        val foundDoors = mutableListOf<Door>()
        for(currentDoor in currentScene.doors){
            val simpleDescription = currentDoor.description.lowercase()
            if(simpleDescription.contains(thingToOpenClean)){
                foundDoors.add(currentDoor)
            }
        }
        if(foundDoors.isEmpty()){
            println("I can't see a $thingToOpen to open")
        }else if(foundDoors.size > 1){
            println("Hmm, which one should I open?")
            foundDoors.forEach {
                println(" ${it.render()}")
            }
        }else{
            val actualDoor = foundDoors.first()
            println("I walk over and open ${actualDoor.render()}")
            currentScene = actualDoor.openFrom(currentScene)
        }
    }
}