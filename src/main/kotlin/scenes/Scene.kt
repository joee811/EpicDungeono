package scenes

interface Renderable {
    fun render(): String
}

class Scene(
    private val description: String
): Renderable {

    val doors = mutableListOf<Door>()

    override fun render(): String {
        var fulldesc = description
        doors.forEach { door ->
            fulldesc += "\n there is a ${door.render()}"
        }
        return fulldesc
    }
}

class Door(
    private val description: String,
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
                open(command.substringAfter("open"))
            }
        }
    }

    private fun lookAround(){
        println("I am looking around")
        println("${currentScene.render()}")
    }

    private fun open(thingToOpen: String){
        println("I am trying to open $thingToOpen")
    }
}