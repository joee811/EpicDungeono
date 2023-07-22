import scenes.Door
import scenes.SceneMap
import scenes.Scene

fun main(args: Array<String>) {
    val outside = Scene("Dark forest")
    val woodenHouseEntrance = Scene("A dark entrance hall")
    val shedOnSideOfHouse = Scene("Empty wooden shack, it is cold")

    val woodenHouseEntranceDoor = Door("wooden door", outside, woodenHouseEntrance)
    val shedDoor = Door("Rickety shed door", outside, shedOnSideOfHouse)

    val map = SceneMap(outside)

    map.startGameLoop()
}