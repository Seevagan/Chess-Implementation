
public class ChessEngine {
 
	static int sort_choice = 1;
	 public static String alphaBeta(int depth, int beta, int alpha, String move, int player) {
	   
	        String list=Move_Generator.posibleMoves();
	        if (depth==0 || list.length()==0) {return move+(MoveEvaluation.rate(list.length(), depth)*(player*2-1));}
	        if(sort_choice==0)
	        {
	        	list=sortMoves(list);
	        }
	     
	        player=1-player;
	        for (int i=0;i<list.length();i+=5) {
	            makeMove(list.substring(i,i+5));
	            flipBoard();
	            String returnString=alphaBeta(depth-1, beta, alpha, list.substring(i,i+5), player);
	            int value=Integer.valueOf(returnString.substring(5));
	            flipBoard();
	            undoMove(list.substring(i,i+5));
	            if (player==0) {
	                if (value<=beta) {beta=value; if (depth==ChBoard.globalDepth) {move=returnString.substring(0,5);}}
	            } else {
	                if (value>alpha) {alpha=value; if (depth==ChBoard.globalDepth) {move=returnString.substring(0,5);}}
	            }
	            if (alpha>=beta) {
	                if (player==0) {return move+beta;} else {return move+alpha;}
	            }
	        }
	        if (player==0) {return move+beta;} else {return move+alpha;}
	    }
	    public static void flipBoard() {
	        String temp;
	        for (int i=0;i<32;i++) {
	            int r=i/8, c=i%8;
	            if (Character.isUpperCase(ChBoard.chessBoard[r][c].charAt(0))) {
	                temp=ChBoard.chessBoard[r][c].toLowerCase();
	            } else {
	                temp=ChBoard.chessBoard[r][c].toUpperCase();
	            }
	            if (Character.isUpperCase(ChBoard.chessBoard[7-r][7-c].charAt(0))) {
	            	ChBoard.chessBoard[r][c]=ChBoard.chessBoard[7-r][7-c].toLowerCase();
	            } else {
	            	ChBoard.chessBoard[r][c]=ChBoard.chessBoard[7-r][7-c].toUpperCase();
	            }
	            ChBoard.chessBoard[7-r][7-c]=temp;
	        }
	        int kingTemp=ChBoard.whiteLead;
	        ChBoard.whiteLead=63-ChBoard.blackLead;
	        ChBoard.blackLead=63-kingTemp;
	    }
	    public static void makeMove(String move) {
	        if (move.charAt(4)!='P') {
	        	ChBoard.chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=ChBoard.chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
	        	ChBoard.chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=" ";
	            if ("A".equals(ChBoard.chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))])) {
	            	ChBoard.whiteLead=8*Character.getNumericValue(move.charAt(2))+Character.getNumericValue(move.charAt(3));
	            }
	        } else {
	        	ChBoard.chessBoard[1][Character.getNumericValue(move.charAt(0))]=" ";
	        	ChBoard.chessBoard[0][Character.getNumericValue(move.charAt(1))]=String.valueOf(move.charAt(3));
	        }
	    }
	    public static void undoMove(String move) {
	        if (move.charAt(4)!='P') {
	        	ChBoard.chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=ChBoard.chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
	            ChBoard.chessBoard[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=String.valueOf(move.charAt(4));
	            if ("A".equals(ChBoard.chessBoard[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))])) {
	            	ChBoard.whiteLead=8*Character.getNumericValue(move.charAt(0))+Character.getNumericValue(move.charAt(1));
	            }
	        } else {
	             ChBoard.chessBoard[1][Character.getNumericValue(move.charAt(0))]="P";
	        	ChBoard.chessBoard[0][Character.getNumericValue(move.charAt(1))]=String.valueOf(move.charAt(2));
	        }
	        
	    }
	    
	    public static String sortMoves(String list) {
	        int[] score=new int [list.length()/5];
	        for (int i=0;i<list.length();i+=5) {
	            makeMove(list.substring(i, i+5));
	            score[i/5]=-MoveEvaluation.rate(-1, 0);
	            undoMove(list.substring(i, i+5));
	        }
	        String newListA="", newListB=list;
	        for (int i=0;i<Math.min(6, list.length()/5);i++) {//first few moves only
	            int max=-1000000, maxLocation=0;
	            for (int j=0;j<list.length()/5;j++) {
	                if (score[j]>max) {max=score[j]; maxLocation=j;}
	            }
	            score[maxLocation]=-1000000;
	            newListA+=list.substring(maxLocation*5,maxLocation*5+5);
	            newListB=newListB.replace(list.substring(maxLocation*5,maxLocation*5+5), "");
	        }
	        return newListA+newListB;
	    }
}
