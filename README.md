# Bowling game command line reader

### Excercise requirements

1. The program should run from the command-line and take a text file as input

2. The program should read the input text file and parse its content, which should have the
results for several players bowling 10 frames each, written according to these guidelines:
    * Each line represents a player and a chance with the subsequent number of pins knocked down.
    * An 'F' indicates a foul on that chance and no pins knocked down (identical for scoring to a roll of 0).
    * The rows are tab-separated.
    * The program should handle bad input like more than ten throws (i.e., no chance will produce a negative number of knocked down pins or more than 10, etc), invalid score value or incorrect format
    * The program should output the scoring for the associated game according to these guidelines:
      * For each player, print their name on a separate line before printing that player's pinfalls and score.
      * All values are tab-separated.
      * The output should calculate if a player scores a strike ('X'), a spare ('/') and allow for extra chances in the tenth frame.
