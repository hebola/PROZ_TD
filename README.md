# Project template PROZ

## Version
```bash
Java version: 15
```
## Run
```bash
./gradlew run
```

## Test
```bash
./gradlew test
```

## How to play
```bash
Buy towers by clicking a tile in witch you want to build and choose a type from menu on the right
Every tower costs 100 gold, cost of the upgrade is writen in the description of the tower (upper right)
Selling towers returns 100 gold plus fraction of the upgrade cost
You will earn gold by clicking next wave button (all the enemies from previous  wave must be dead)

The enemies which are represented  by pink circles heve:
HP      the green bar
Armor   the red bar
Shield  the blue bar

Armor tower attacks armor first then HP when armor equals 0, attacks only one enemy at once
Shield tower attack shields first then HP when shield equals 0, attacks only one enemy at once
Poison tower poison all enemies in range for a while, attacks HP directly
Slowingdown tower slow downs enemies

You cant seperate spawn from the base using towers
```