# Bowling game command line reader

### Description

1. The program runs from the command-line and takes a text file as input

2. The program parses the text file content, which contains the results for several players bowling 10 frames each:
    * Each line represents a player and a chance with the subsequent number of pins knocked down.
    * An 'F' indicates a foul on that chance and no pins knocked down (identical for scoring to a roll of 0).
    * The rows are tab-separated.
    * In case of bad input (negative pins knocked down, incorrect format, etc.), the error is notified and the execution is stopped.
    
3. Output
      * Each player name is printed on a separate line, followed by that player's pinfalls and score.
      * All values are tab-separated.
      * Calculate if a player scores a strike ('X'), a spare ('/') and allow for extra chances in the tenth frame.

### How to run it

1. Download or clone the repo
2. Navigate into the repository folder
3. Run ./gradlew build to build the project or ./gradlew test to run tests & build the project
4. Run the project
> ./gradlew run --args='./src/test/resources/two_player_game.txt'
