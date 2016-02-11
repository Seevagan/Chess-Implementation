

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ChBoard {
	
	 static String chessBoard[][]={
	        {"r","k","b","q","a","b","k","r"},
	        {"p","p","p","p","p","p","p","p"},
	        {" "," "," "," "," "," "," "," "},
	        {" "," "," "," "," "," "," "," "},
	        {" "," "," "," "," "," "," "," "},
	        {" "," "," "," "," "," "," "," "},
	        {"P","P","P","P","P","P","P","P"},
	        {"R","K","B","Q","A","B","K","R"}};
	    static int whiteLead, blackLead;
	    static int UseWhite=-1;
	    static int globalDepth=4;
	    static JFrame f=new JFrame("Chess Board");
	    
	    
	    public static void main(String[] args) {
	        while (!"A".equals(chessBoard[whiteLead/8][whiteLead%8])) {whiteLead++;}
	        while (!"a".equals(chessBoard[blackLead/8][blackLead%8])) {blackLead++;}
	        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        UserInterface ui=new UserInterface();
	        f.add(ui);
	        f.setSize(526, 570);
	       
	       
	        JMenuBar menuBar=new JMenuBar();
	        JMenu menu=new JMenu("Options");
	        menuBar.add(menu);
	        JMenuItem exit=new JMenuItem("Close");
	        JMenuItem depth=new JMenuItem("Change Depth");
	        JMenu pieceValue=new JMenu("Change Item Value");
	        
	        JMenuItem King=new JMenuItem("King");
	        JMenuItem Queen=new JMenuItem("Queen");
	        JMenuItem Rook=new JMenuItem("Rook");
	        JMenuItem Knight=new JMenuItem("Knight");
	        JMenuItem Bishop=new JMenuItem("Bishop");
	        JMenuItem Pawn=new JMenuItem("Pawn");
	        
	        pieceValue.add(King);
	        pieceValue.add(Queen);
	        pieceValue.add(Rook);
	        pieceValue.add(Knight);
	        pieceValue.add(Bishop);
	        pieceValue.add(Pawn);
	                	
	    	exit.addActionListener(new ActionListener(){
	    		public void actionPerformed(ActionEvent event) {
	                System.exit(0);
	            }
	    		
	    	});
	        
	    	depth.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String answer= JOptionPane.showInputDialog(f,"Please enter a value for Depth", null);
					System.out.println("Value" + answer);
					ChBoard.globalDepth=Integer.parseInt(answer);
					System.out.println("Global Depth");
					System.out.println(globalDepth);
				}
			});
	    	
	    		
	    	
	    	King.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String answer= JOptionPane.showInputDialog(f,"Please enter a value for King Weight", null);
					System.out.println("Value" + answer);
					Integer value = Integer.parseInt(answer);
					MoveEvaluation.updateValue("A", value);
				}
				
			});
	    	Queen.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String answer= JOptionPane.showInputDialog(f,"Please enter a value for Queen Weight", null);
					System.out.println("Value" + answer);
					Integer value = Integer.parseInt(answer);
					MoveEvaluation.updateValue("Q", value);
					
				}
			});
	    	Rook.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String answer= JOptionPane.showInputDialog(f,"Please enter a value for Rook Weight", null);
					System.out.println("Value" + answer);
					Integer value = Integer.parseInt(answer);
					MoveEvaluation.updateValue("R", value);
				}
			});
	    	Knight.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String answer= JOptionPane.showInputDialog(f,"Please enter a value for Knight Weight", null);
					System.out.println("Value" + answer);
					Integer value = Integer.parseInt(answer);
					MoveEvaluation.updateValue("K", value);
					
				}
			});
	    	Bishop.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String answer= JOptionPane.showInputDialog(f,"Please enter a value for Bishop Weight", null);
					System.out.println("Value" + answer);
					Integer value = Integer.parseInt(answer);
					MoveEvaluation.updateValue("B", value);
					
				}
			});
	    	Pawn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					String answer= JOptionPane.showInputDialog(f,"Please enter a value for Pawn Weight", null);
					System.out.println("Value" + answer);
					Integer value = Integer.parseInt(answer);
					MoveEvaluation.updateValue("P", value);
					
				}
				
			});
	    	JMenu Strategy=new JMenu("Strategy");
	    	JMenuItem noking= new JMenuItem("Change King");
	    	JMenuItem quicksort=new JMenuItem("QuickPlay");
	    	Strategy.add(noking);
	    	Strategy.add(quicksort);
	    	
	    	noking.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					MoveEvaluation.king_choice=JOptionPane.showConfirmDialog(null,"Dont want king to be supreme??","No more King",JOptionPane.YES_NO_OPTION);
					if(MoveEvaluation.king_choice==0)
			        {
			        	System.out.println("No more king");
			        }
			        else
			        {
			        	System.out.println("king is still supreme");
			        }
					
				}
			});
	    	quicksort.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					ChessEngine.sort_choice=JOptionPane.showConfirmDialog(null,"DO want to speedup the sort??","Quick Sort",JOptionPane.YES_NO_OPTION);
					if(ChessEngine.sort_choice==0)
			        {
			        	System.out.println("quick sorted");
			        }
			        else
			        {
			        	System.out.println("no quick sorted");
			        }
					
				}
			});
	    	

            menu.add(Strategy);
	    	menu.add(pieceValue);
	    	menu.add(depth);
	    	menu.add(exit);
	    	
	    	f.setJMenuBar(menuBar);
	       
	    	 f.setVisible(true);
	        System.out.println(Move_Generator.posibleMoves());
	        Object[] option={"Computer","User"};
	        UseWhite=JOptionPane.showOptionDialog(null, "Select First Move", "User options", JOptionPane.YES_NO_OPTION,
	                JOptionPane.QUESTION_MESSAGE, null, option, option[1]);
	        if (UseWhite==0) {
	        	ChessEngine.makeMove(ChessEngine.alphaBeta(globalDepth, 1000000, -1000000, "", 0));
	        	ChessEngine.flipBoard();
	            f.repaint();
	        }
	        ChessEngine.makeMove("7655 ");
	        ChessEngine.undoMove("7655 ");
	        for (int i=0;i<8;i++) {
	            System.out.println(Arrays.toString(chessBoard[i]));
	        }
	    }
}
