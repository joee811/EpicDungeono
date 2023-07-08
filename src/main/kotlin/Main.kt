import scenes.Door
import scenes.SceneMap
import scenes.Scene

fun main(args: Array<String>) {
    val outside = Scene("Dark forest, there is a wooden house in front of you")
    val woodenHouseEntrance = Scene("A dark entrance hall")

    val woodenHouseEntranceDoor = Door("A wooden door", outside, woodenHouseEntrance)

    val map = SceneMap(outside)

    map.startGameLoop()
}