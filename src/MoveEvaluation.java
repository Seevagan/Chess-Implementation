import java.util.HashMap;


public class MoveEvaluation {
	static int king_choice = 1;
	static int pawnBoard[][]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        {50, 50, 50, 50, 50, 50, 50, 50},
        {10, 10, 20, 30, 30, 20, 10, 10},
        { 5,  5, 10, 25, 25, 10,  5,  5},
        { 0,  0,  0, 20, 20,  0,  0,  0},
        { 5, -5,-10,  0,  0,-10, -5,  5},
        { 5, 10, 10,-20,-20, 10, 10,  5},
        { 0,  0,  0,  0,  0,  0,  0,  0}};
    static int rookBoard[][]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        { 5, 10, 10, 10, 10, 10, 10,  5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        { 0,  0,  0,  5,  5,  0,  0,  0}};
    static int knightBoard[][]={
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}};
    static int bishopBoard[][]={
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5, 10, 10,  5,  0,-10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}};
    static int queenBoard[][]={
        {-20,-10,-10, -5, -5,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        { -5,  0,  5,  5,  5,  5,  0, -5},
        {  0,  0,  5,  5,  5,  5,  0, -5},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-20,-10,-10, -5, -5,-10,-10,-20}};
    static int kingMidBoard[][]={
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        { 20, 20,  0,  0,  0,  0, 20, 20},
        { 20, 30, 10,  0,  0, 10, 30, 20}};
    static int kingEndBoard[][]={
        {-50,-40,-30,-20,-20,-30,-40,-50},
        {-30,-20,-10,  0,  0,-10,-20,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-30,  0,  0,  0,  0,-30,-30},
        {-50,-30,-30,-30,-30,-30,-30,-50}};
    static HashMap<String, Integer> keyPrice=new HashMap<String, Integer>();
    static{
    keyPrice.put("A",20000);
    keyPrice.put("Q",900);
    keyPrice.put("R",500);
    keyPrice.put("K",320);
    keyPrice.put("K1",250);
    keyPrice.put("B",330);
    keyPrice.put("B1",250);
    keyPrice.put("P",100);
    
    }
   
    
    public static int rate(int list, int depth) {
        int counter=0, material=valMat();
        counter+=rateAttack();
        counter+=material;
        counter+=Move(list, depth, material);
        counter+=pos(material);
        ChessEngine.flipBoard();
        material=valMat();
        counter-=rateAttack();
        counter-=material;
        counter-=Move(list, depth, material);
        counter-=pos(material);
        ChessEngine.flipBoard();
        return -(counter+depth*50);
    }
    
    public static int rateAttack() {
    	
    	int count=0;
    	 int tempPositionC=ChBoard.whiteLead;
    	for (int i=0;i<64;i++) {
            switch (ChBoard.chessBoard[i/8][i%8]) {
            case "P": {ChBoard.whiteLead=i; if (!Move_Generator.kingSafe()) {count-=keyPrice.get("P");}}
                break;
            case "R": {ChBoard.whiteLead=i; if (!Move_Generator.kingSafe()) {count-=keyPrice.get("R");}}
                break;
            case "K": {ChBoard.whiteLead=i; if (!Move_Generator.kingSafe()) {count-=keyPrice.get("K");}}
                break;
            case "B": {ChBoard.whiteLead=i; if (!Move_Generator.kingSafe()) {count-=keyPrice.get("B");}}
                break;
            case "Q": {ChBoard.whiteLead=i; if (!Move_Generator.kingSafe()) {count-=keyPrice.get("Q");}}
                break;
            }
    	}
    	 ChBoard.whiteLead=tempPositionC;
         if (!Move_Generator.kingSafe()) {count-=200;}
         
        return count/2;
    }
    public static int valMat() {
        int counter=0, bishopCounter=0;
        for (int i=0;i<64;i++) {
            switch (ChBoard.chessBoard[i/8][i%8]) {
                case "P": counter+= keyPrice.get("P");
                    break;
                case "R": counter+=keyPrice.get("R");
                    break;
                case "K": counter+=keyPrice.get("K");
                    break;
                case "B": bishopCounter+=1;
                    break;
                case "Q": counter+=keyPrice.get("Q");
                    break;
            }
        }
        if (bishopCounter>=2) {
            counter+=keyPrice.get("B")*bishopCounter;
        } else {
            if (bishopCounter==1) {counter+=keyPrice.get("B1");}
        }
        return counter;
    }
    public static int Move(int listLength, int depth, int material) {
        int counter=0;
        counter+=listLength;
        if (listLength==0) {
            if (!Move_Generator.kingSafe()) {
                counter+=-200000*depth;
            } else {
                counter+=-150000*depth;
            }
        }
        if(king_choice == 0)
        {
        return 0;
        }
        else
        {
        return counter;
        }    }
    
    public static void updateValue(String str, Integer value) {
		// TODO Auto-generated method stub
		keyPrice.put(str,value);
		System.out.println("The new keyPrice are ");
		System.out.print(keyPrice);
	}
    
    public static int pos(int material) {
        int counter=0;
        for (int i=0;i<64;i++) {
            switch (ChBoard.chessBoard[i/8][i%8]) {
                case "P": counter+=pawnBoard[i/8][i%8];
                    break;
                case "R": counter+=rookBoard[i/8][i%8];
                    break;
                case "K": counter+=knightBoard[i/8][i%8];
                    break;
                case "B": counter+=bishopBoard[i/8][i%8];
                    break;
                case "Q": counter+=queenBoard[i/8][i%8];
                    break;
                case "A": if (material>=1750) {counter+=kingMidBoard[i/8][i%8]; counter+=Move_Generator.posibleA(ChBoard.whiteLead).length()*10;} else
                {counter+=kingEndBoard[i/8][i%8]; counter+=Move_Generator.posibleA(ChBoard.whiteLead).length()*30;}
                    break;
            }
        }
        return counter;
    }

	
	
}