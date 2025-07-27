plugins {
    kotlin("jvm") version "2.1.0"
    id("earth.terrarium.cloche") version "0.11.6"
}

repositories {
    cloche {
        mavenNeoforgedMeta()
        mavenNeoforged()
        mavenForge()
        mavenFabric()
        mavenParchment()
        librariesMinecraft()
        main()
    }
    mavenCentral()
    maven("https://api.modrinth.com/maven")
}

group = "net.thevaliantsquidward.rainbowreef"
version = "3.0.0"

cloche {
    mappings {
        official()
    }

    metadata {
        modId = "rainbowreef"
        name = "Rainbow Reef"
        description = "Rainbow Reef is a mod designed to enhance your aquarium experience!"
        license = "MIT"

        author("TheValiantSquidward")
        author("OmayPaty")
        contributor("Alpha")
        contributor("Gec Master")
        contributor("CodyHuh")
        contributor("TheIndominator (Animations)")
        contributor("Apollo (Fabric/1.21 Port)")
    }

    singleTarget {
        fabric {
            loaderVersion = "0.16.13"
            minecraftVersion = "1.21.8"
            mixins.from(file("src/main/rainbow_reef.mixins.json"))

            dependencies {
                fabricApi("0.129.0")
                modImplementation("maven.modrinth:lithostitched:1.4.11-fabric-1.21.6")
            }

            includedClient()
            runs {
                client()
                server()
            }

            metadata {
                entrypoint("main") {
                    value = "net.thevaliantsquidward.rainbowreef.RainbowReef"
                }

                entrypoint("client") {
                    value = "net.thevaliantsquidward.rainbowreef.RainbowReefClient"
                }
            }
        }
    }
}