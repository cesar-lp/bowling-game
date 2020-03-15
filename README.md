# Bowling game command line reader
[![License: ISC](https://img.shields.io/badge/License-ISC-blue.svg)](https://opensource.org/licenses/ISC)

Java program that processes a .txt file containing the results of a bowling game match and displays the results in the console.

- [Input](#input)
- [Output](#output)
- [Instructions](#instructions)
- [Examples](#examples)

### Input
1. The program runs from the command line and takes a text file as input.

2. The program parses the text file content, which contains the results for several players bowling 10 frames each. Each line represents a player and a chance with the subsequent number of pins knocked down. An 'F' indicates a foul on that chance and no pins knocked down (identical for scoring to a roll of 0).

Example
```
Jeff 10
John 3
John 7
Jeff 7
Jeff 3
John 6
John 3
Jeff 9
Jeff 0
John 10
Jeff 10
John 8
John 1
Jeff 0
Jeff 8
John 10
Jeff 8
Jeff 2
John 10
Jeff F
Jeff 6
John 9
John 0
Jeff 10
John 7
John 3
Jeff 10
John 4
John 4
Jeff 10
Jeff 8
Jeff 1
John 10
John 9
John 0
```
In case of bad input (negative pins knocked down, incorrect format, etc.), the error is notified and the execution is stopped.
    
### Output 
* Each player name is printed on a separate line, followed by that player's pinfalls and score.
* All values are tab-separated.
* Strikes ('X'), spares ('/') and extra chance scores are calculated.

### Instructions

1. Clone the repo
```shell script
git clone git@github.com:cesar-lp/bowling-game-command-line-reader.git
```

2. Navigate into the repository folder
```shell script
cd bowling-game-command-line-reader
```

3. Run the following command to build the project
```shell script 
./gradle build 
```

If you want to run the tests and build the project afterwards
```shell script 
./gradle test
```

4. Run the project
```shell script
./gradlew run --args='./src/test/resources/two_player_game.txt'
```

### Examples

Two players game
```
Frame       1       2       3       4       5       6       7       8       9       10
Jeff
Pinfalls        X   7   /   9   0       X   0   8   8   /   F   6       X       X   X   8   1
Score       20      39      48      66      74      84      90      120     148     167
John
Pinfalls    3   /   6   3       X   8   1       X       X   9   0   7   /   4   4   X   9   0	
Score       16      25      44      53      82      101     110     124     132     151
```

Zero score game
```
Frame       1       2       3       4       5       6       7       8       9       10
Carl
Pinfalls    F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F   F
Score       0       0       0       0       0       0       0       0       0       0
```

Perfect score game
```
Frame       1       2       3       4       5       6       7       8       9       10
Carl
Pinfalls        X       X       X       X       X       X       X       X       X   X   X   X
Score       30      60      90      120     150     180     210     240     270     300
```
