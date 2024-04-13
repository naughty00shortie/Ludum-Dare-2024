In the domain we represent our Summons with the `Piece` abstraction. 

- Every summon listed below implements this contract, namely `potentailMoveSet`.
	- Only two methods need to be implemented for the Piece engine to work:
		- `isPlayerPiece` (which is simply to say your Piece needs state to represent who owns it)
		- `potentialMoveSet` which will return a set of coordinates this Piece could move to, if it was positioned at the coordinate that is passed in as an argument. Explained more below.
![[piece_abstraction.png]]


- This is the *engine* the runs the `Piece` abstraction, a series of filters to validate which of a Piece's potential moves are valid places for it to go on a board.
	- This is explained better with the examples below.
![[piece_engine.png]]

### Grunts
*Grunts are the basic Summon of the game, and for simplicity sake, the only Summon the enemy will use.*

Grunt implements the `Piece#potentialMoveSet` contract by returning three co-ordinates to the positions marked in red. 
- Grunt class does not need to do anything further. 
	- Grunt class does not care about validating these positions.
- The *engine* will take these potential locations and check if they are on the board, and if they can be occupied (see above).


![[grunt.png]]

### Sentinels

We can implement summons with different move sets quite easily.

- The Sentinel's biggest advantage is being able to move backwards and defend ranks (since Grunts can only move *forwards* towards their enemies, if an enemy gets behind them, they cannot intercept them.)
	- This is a ridiculously simple *buff*, but simply being able to reposition a Summon and move it backwards to intercept enemy Grunts is probably going to be OP.
- Sentinels will be the first *upgraded* Summon you can bring in, they will cost additional mana.

![[sentinel.png]]

### Hopper

Again, we can easily implement new Summons with different move sets.
- Hoppers can 'leap' over enemies. This is actually emergent behaviour we get for free from how our engine works (the way we filter out moves means we cannot *block* the path of a piece with another).
	- Every Piece can move to any position in its move set, as long as that position is in the bounds of the Board and is unoccupied. 
	- If it is occupied by an enemy, it can capture and move to that location. 
	- **There are no other restrictions for movement.** - adding more restrictions makes implementing Summons & validating their moves considerably more difficult so uh, let's not.

![[hopper.png]]


