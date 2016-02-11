# Chess-Implementation
Implementation of chess program
Design Specifications
The design of the Simple Grand Master chess program is composed of following modules.
•	Board Design
•	Search Engine
•	Move Generator
•	Move Evaluation
•	Graphical User Interface 

Board Design
Chess board consists of a 8*8 squares implemented by a two dimensional array. It consists of 32 chess pieces. The code is indexed by rows and columns.
Search Engine
Searching involves looking ahead at different move sequences and evaluating the positions after making the moves. Alpha-Beta algorithm is used to build the search engine for this chess program which is discussed in detail in Search Engine Architecture. 
Move Generator
In this chess program evaluation function, a value is evaluated based on the piece weight, piece position, piece moving ability and king’s attack chances. The evaluated value is then returned to the search engine, based on which the best possible piece is moved. 
Move Evaluation
With the board representation, one big consideration is the generation of moves. This is essential to the game playing aspect of a chess program and it must be completely correct. In this chess program, Generation of moves is based on the opposition move, at every computer’s turn all the possible moves are generated in a list data structure. This list contains the possible moves of all the chess pieces. Based on the move evaluation, the alpha-beta search engine, selects the best and prunes all other moves. The best selected move is executed.


User Interface
In this chess program, the actual representation of the chess board is done with mouse movements. The mouse movements are captured in this module to move the chess pieces. There is an option for the user to make the first move or he can make the computer to make the first move.
Implementation of Search Architecture
The Search Engine in this chess program which takes the decision to move the chess piece based on the search result, move evaluation and maximum possibility to reach the goal. We use an adversarial search in our chess programming called Alpha-beta algorithm. The Alpha-Beta algorithm is a significant enhancement to the minimax search algorithm that eliminates the need to search large portions of the fame tree applying a branch-and-bound technique. The algorithm maintains two values alpha and beta. They represent the minimum score that the maximizing player is assured of and the maximum score that the minimizing player is assured of respectively.
Strategies
Chess game has more playing strategies with a minimum of 20 moves for the first step itself in the game. In this chess program, we included few playing strategies which allow the user to play the game in more advance manner. Some of the playing strategies are
•	Increase the speed of the game by sorting the list of possible moves, which helps to search engine to work in less amount of time.
•	During checkmate, king and king’s saver are only allowed to moved, rest of the chess pieces won’t be allowed to move.
•	In a chess game, where King is no more a super power, the game will get over, only when all the chess pieces of a player is destroyed. 

Technical Specifications
Platform: Java, Java Swing
Third party libraries: AIMA Code, GUI from internet.
Data Structures: 2-dimensional arrays, Hashmap, List
