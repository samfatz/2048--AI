package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.AbstractState.MOVE;
import model.State;

public class RandomAI extends AbstractPlayer {

	private Random rng = new Random();

	@Override
	public MOVE getMove(State game) {
		// Delay for the view
		pause();
		// Get available moves
		List<MOVE> moves = game.getMoves();

		MOVE topMove = moves.get(0);
		int topScore = 0;
		int currentScore = 0;
		// Pick a move at random
		for (int i = 0; i < moves.size(); i++) {
			State testGame = game.copy();
			testGame.move(moves.get(i));
			currentScore = highestScore(testGame);
			if (currentScore > topScore) {
				topScore = currentScore;
				topMove = moves.get(i);
				// System.out.println(topScore);
			}
		}

		return topMove;
	}

	private int highestScore(State game) {
		int score = 0;
		int highestTile = 0;

		for (int i = 0; i < 1000; i++) {
			State newGame = game.copy();
			while (newGame.getMoves().size() > 0) {
				randomGame(newGame);

				if (newGame.getHighestTileValue() > highestTile) {
					highestTile = newGame.getHighestTileValue();
					System.out.println(highestTile);
				}

				score = score + newGame.getScore();

			}
		}
		return score / 1000;
	}

	private void randomGame(State game) {

		int Low = 0;
		int High = game.getMoves().size();
		int Result = rng.nextInt(High - Low) + Low;
		List<MOVE> moves = game.getMoves();

		game.move(moves.get(Result));
	}

	@Override
	public int studentID() {
		return 201181111;
	}

	@Override
	public String studentName() {
		return "Phil Rodgers";
	}
}
