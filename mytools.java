package student_player.mytools;
import java.util.ArrayList;
import java.util.Iterator;

import hus.HusBoardState;
import hus.HusMove;

public class MyTools {
	
	 public static double getSomething()
	 {
	     return Math.random();
	 }
	 
	 //this is minmax with alphabeta pruning
	 //returns an array of {bestValue,bestIndex}
	 public static int[] search(int level,int player1,int player2,int alpha,int beta,HusBoardState board_state)
	 {
		 int bestValue = 0;
		 int bestIndex = 0;
		 boolean play;
		 
		 ArrayList<HusMove> moves = board_state.getLegalMoves();
		 Iterator<HusMove> moveIterator = moves.iterator();
		 
		 //even levels are the opponent's levels
		 if(level%2 == 0) play=false; 
		 //if its an odd level then its my turn to play
		 else play = true;
		 
		 if(level==1 || board_state.gameOver()) 
		 {
			 //calculate the heuristic
			 bestValue = evalFun(board_state,player1,player2);
			 return new int[] {bestValue,bestIndex} ;
		 }
		 else
		 { 
			 while(moveIterator.hasNext())
			 {
				 HusBoardState cloned_board_state = (HusBoardState) board_state.clone();
				 HusMove currentMove = moveIterator.next();
				 
				 //apply the current move and see the effect
			 	 cloned_board_state.move(currentMove);
			 	
				 //my player
				 if(play)
				 {
				 	bestValue = search(level-1,player1,player2,alpha,beta,cloned_board_state)[0];
				 
				 	if(bestValue > alpha)
				 	{
				 		//change the value of alpha
				 		alpha = bestValue;
				 		bestIndex = moves.indexOf(currentMove);
				 	}
				 }
				 //opponent's player
				 else
				 {
					 bestValue = search(level-1,player1,player2,alpha,beta,cloned_board_state)[0];
				 
					 if(bestValue < beta)
					 {
						 //change the value of beta
						 beta = bestValue;
						 bestIndex = moves.indexOf(currentMove);
					 }
				 }
				 if(beta <= alpha) break; //cutoff
			 }
		 }
		 return new int[]{(play)? alpha : beta, bestIndex}; 
	 }
	 
	 //an evaluation function 
	 public static int evalFun(HusBoardState state, int player1, int player2)
	 {
		 int[][] pits = state.getPits();
		 int[] my_pits = pits[player1];
		 int[] op_pits = pits[player2];
		 
		 int value;
		 
		 int weight1 = 3*heuristic1(my_pits);
		 int weight2 = 3*heuristic2(op_pits);
		 int weight3 = 3*heuristic3(my_pits);

		value = weight1+weight3-weight2;
		 
		 return value;
	 }
	 
	//heuristic1 - number of seeds that I have
	 public static int heuristic1(int[] my_pits)
	 {
		int seeds  = 0;
		
		for(int i=0; i<my_pits.length; i++)
		{
			seeds = seeds+my_pits[i];
		}
		return seeds; 
	 }
	 
	 //heuristic2 - number of seeds the opponent has
	 public static int heuristic2(int[] op_pits)
	 {
		 int opSeeds = 0;
		 
		 for(int i=0; i<op_pits.length; i++)
		 {
			 opSeeds = opSeeds+op_pits[i];
		 }
		 return opSeeds;
	 }
	 
	 //heuristic3 - get seeds in left most pit 
	 public static int heuristic3(int[] my_pits)
	 {
		 int left = my_pits[0];
		return left;
	 }  
}



	    

