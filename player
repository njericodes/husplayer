ackage student_player;

import hus.HusBoardState;
import hus.HusPlayer;
import student_player.mytools.MyTools;
import hus.HusMove;

import java.util.ArrayList;

//import student_player.mytools.MyTools;

/** A Hus player submitted by a student. */
public class StudentPlayer extends HusPlayer {

    /** You must modify this constructor to return your student number.
     * This is important, because this is what the code that runs the
     * competition uses to associate you with your agent.
     * The constructor should do nothing else. */
    public StudentPlayer() { super("260588566"); }

    /** This is the primary method that you need to implement.
     * The ``board_state`` object contains the current state of the game,
     * which your agent can use to make decisions. See the class hus.RandomHusPlayer
     * for another example agent. */
    public HusMove chooseMove(HusBoardState board_state)
    {
    	//an odd level seems to work in my favor
        int level = 7;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        
        // Get the legal moves for the current board state.
        ArrayList<HusMove> moves = board_state.getLegalMoves();
        
        //get the index of the best legal move
        HusMove bestMove = moves.get(MyTools.search(level,player_id,opponent_id,alpha,beta,board_state)[1]);
        
        return bestMove;
    }
}
