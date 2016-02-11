

public class Move_Generator {
	 public static String posibleMoves() {
	        String list="";
	        for (int i=0; i<64; i++) {
	            switch (ChBoard.chessBoard[i/8][i%8]) {
	                case "P": list+=posibleP(i);
	                    break;
	                case "R": list+=posibleR(i);
	                    break;
	                case "K": list+=posibleK(i);
	                    break;
	                case "B": list+=posibleB(i);
	                    break;
	                case "Q": list+=posibleQ(i);
	                    break;
	                case "A": list+=posibleA(i);
	                    break;
	            }
	        }
	        return list;
	    }
	    public static String posibleP(int i) {
	        String list="", oldPiece;
	        int r=i/8, c=i%8;
	        for (int j=-1; j<=1; j+=2) {
	            try {
	                if (Character.isLowerCase(ChBoard.chessBoard[r-1][c+j].charAt(0)) && i>=16) {
	                    oldPiece=ChBoard.chessBoard[r-1][c+j];
	                    ChBoard.chessBoard[r][c]=" ";
	                    ChBoard.chessBoard[r-1][c+j]="P";
	                    if (kingSafe()) {
	                        list=list+r+c+(r-1)+(c+j)+oldPiece;
	                    }
	                    ChBoard.chessBoard[r][c]="P";
	                    ChBoard.chessBoard[r-1][c+j]=oldPiece;
	                }
	            } catch (Exception e) {}
	            try {//promotion && capture
	                if (Character.isLowerCase(ChBoard.chessBoard[r-1][c+j].charAt(0)) && i<16) {
	                    String[] temp={"Q","R","B","K"};
	                    for (int k=0; k<4; k++) {
	                        oldPiece=ChBoard.chessBoard[r-1][c+j];
	                        ChBoard.chessBoard[r][c]=" ";
	                        ChBoard.chessBoard[r-1][c+j]=temp[k];
	                        if (kingSafe()) {	                            
	                            list=list+c+(c+j)+oldPiece+temp[k]+"P";
	                        }
	                        ChBoard.chessBoard[r][c]="P";
	                        ChBoard.chessBoard[r-1][c+j]=oldPiece;
	                    }
	                }
	            } catch (Exception e) {}
	        }
	        try {
	            if (" ".equals(ChBoard.chessBoard[r-1][c]) && i>=16) {
	                oldPiece=ChBoard.chessBoard[r-1][c];
	                ChBoard.chessBoard[r][c]=" ";
	                ChBoard.chessBoard[r-1][c]="P";
	                if (kingSafe()) {
	                    list=list+r+c+(r-1)+c+oldPiece;
	                }
	                ChBoard.chessBoard[r][c]="P";
	                ChBoard.chessBoard[r-1][c]=oldPiece;
	            }
	        } catch (Exception e) {}
	        try {
	            if (" ".equals(ChBoard.chessBoard[r-1][c]) && i<16) {
	                String[] temp={"Q","R","B","K"};
	                for (int k=0; k<4; k++) {
	                    oldPiece=ChBoard.chessBoard[r-1][c];
	                    ChBoard.chessBoard[r][c]=" ";
	                    ChBoard.chessBoard[r-1][c]=temp[k];
	                    if (kingSafe()) {
	                        list=list+c+c+oldPiece+temp[k]+"P";
	                    }
	                    ChBoard.chessBoard[r][c]="P";
	                    ChBoard.chessBoard[r-1][c]=oldPiece;
	                }
	            }
	        } catch (Exception e) {}
	        try {
	            if (" ".equals(ChBoard.chessBoard[r-1][c]) && " ".equals(ChBoard.chessBoard[r-2][c]) && i>=48) {
	                oldPiece=ChBoard.chessBoard[r-2][c];
	                ChBoard.chessBoard[r][c]=" ";
	                ChBoard.chessBoard[r-2][c]="P";
	                if (kingSafe()) {
	                    list=list+r+c+(r-2)+c+oldPiece;
	                }
	                ChBoard.chessBoard[r][c]="P";
	                ChBoard.chessBoard[r-2][c]=oldPiece;
	            }
	        } catch (Exception e) {}
	        return list;
	    }
	    public static String posibleR(int i) {
	        String list="", oldPiece;
	        int r=i/8, c=i%8;
	        int temp=1;
	        for (int j=-1; j<=1; j+=2) {
	            try {
	                while(" ".equals(ChBoard.chessBoard[r][c+temp*j]))
	                {
	                    oldPiece=ChBoard.chessBoard[r][c+temp*j];
	                    ChBoard.chessBoard[r][c]=" ";
	                    ChBoard.chessBoard[r][c+temp*j]="R";
	                    if (kingSafe()) {
	                        list=list+r+c+r+(c+temp*j)+oldPiece;
	                    }
	                    ChBoard.chessBoard[r][c]="R";
	                    ChBoard.chessBoard[r][c+temp*j]=oldPiece;
	                    temp++;
	                }
	                if (Character.isLowerCase(ChBoard.chessBoard[r][c+temp*j].charAt(0))) {
	                    oldPiece=ChBoard.chessBoard[r][c+temp*j];
	                    ChBoard.chessBoard[r][c]=" ";
	                    ChBoard.chessBoard[r][c+temp*j]="R";
	                    if (kingSafe()) {
	                        list=list+r+c+r+(c+temp*j)+oldPiece;
	                    }
	                    ChBoard.chessBoard[r][c]="R";
	                    ChBoard.chessBoard[r][c+temp*j]=oldPiece;
	                }
	            } catch (Exception e) {}
	            temp=1;
	            try {
	                while(" ".equals(ChBoard.chessBoard[r+temp*j][c]))
	                {
	                    oldPiece=ChBoard.chessBoard[r+temp*j][c];
	                    ChBoard.chessBoard[r][c]=" ";
	                    ChBoard.chessBoard[r+temp*j][c]="R";
	                    if (kingSafe()) {
	                        list=list+r+c+(r+temp*j)+c+oldPiece;
	                    }
	                    ChBoard.chessBoard[r][c]="R";
	                    ChBoard.chessBoard[r+temp*j][c]=oldPiece;
	                    temp++;
	                }
	                if (Character.isLowerCase(ChBoard.chessBoard[r+temp*j][c].charAt(0))) {
	                    oldPiece=ChBoard.chessBoard[r+temp*j][c];
	                    ChBoard.chessBoard[r][c]=" ";
	                    ChBoard.chessBoard[r+temp*j][c]="R";
	                    if (kingSafe()) {
	                        list=list+r+c+(r+temp*j)+c+oldPiece;
	                    }
	                    ChBoard.chessBoard[r][c]="R";
	                    ChBoard.chessBoard[r+temp*j][c]=oldPiece;
	                }
	            } catch (Exception e) {}
	            temp=1;
	        }
	        return list;
	    }
	    public static String posibleK(int i) {
	        String list="", oldPiece;
	        int r=i/8, c=i%8;
	        for (int j=-1; j<=1; j+=2) {
	            for (int k=-1; k<=1; k+=2) {
	                try {
	                    if (Character.isLowerCase(ChBoard.chessBoard[r+j][c+k*2].charAt(0)) || " ".equals(ChBoard.chessBoard[r+j][c+k*2])) {
	                        oldPiece=ChBoard.chessBoard[r+j][c+k*2];
	                        ChBoard.chessBoard[r][c]=" ";
	                        if (kingSafe()) {
	                            list=list+r+c+(r+j)+(c+k*2)+oldPiece;
	                        }
	                        ChBoard.chessBoard[r][c]="K";
	                        ChBoard.chessBoard[r+j][c+k*2]=oldPiece;
	                    }
	                } catch (Exception e) {}
	                try {
	                    if (Character.isLowerCase(ChBoard.chessBoard[r+j*2][c+k].charAt(0)) || " ".equals(ChBoard.chessBoard[r+j*2][c+k])) {
	                        oldPiece=ChBoard.chessBoard[r+j*2][c+k];
	                        ChBoard.chessBoard[r][c]=" ";
	                        if (kingSafe()) {
	                            list=list+r+c+(r+j*2)+(c+k)+oldPiece;
	                        }
	                        ChBoard.chessBoard[r][c]="K";
	                        ChBoard.chessBoard[r+j*2][c+k]=oldPiece;
	                    }
	                } catch (Exception e) {}
	            }
	        }
	        return list;
	    }
	    public static String posibleB(int i) {
	        String list="", oldPiece;
	        int r=i/8, c=i%8;
	        int temp=1;
	        for (int j=-1; j<=1; j+=2) {
	            for (int k=-1; k<=1; k+=2) {
	                try {
	                    while(" ".equals(ChBoard.chessBoard[r+temp*j][c+temp*k]))
	                    {
	                        oldPiece=ChBoard.chessBoard[r+temp*j][c+temp*k];
	                        ChBoard.chessBoard[r][c]=" ";
	                        ChBoard.chessBoard[r+temp*j][c+temp*k]="B";
	                        if (kingSafe()) {
	                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
	                        }
	                        ChBoard.chessBoard[r][c]="B";
	                        ChBoard.chessBoard[r+temp*j][c+temp*k]=oldPiece;
	                        temp++;
	                    }
	                    if (Character.isLowerCase(ChBoard.chessBoard[r+temp*j][c+temp*k].charAt(0))) {
	                        oldPiece=ChBoard.chessBoard[r+temp*j][c+temp*k];
	                        ChBoard.chessBoard[r][c]=" ";
	                        ChBoard.chessBoard[r+temp*j][c+temp*k]="B";
	                        if (kingSafe()) {
	                            list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
	                        }
	                        ChBoard.chessBoard[r][c]="B";
	                        ChBoard.chessBoard[r+temp*j][c+temp*k]=oldPiece;
	                    }
	                } catch (Exception e) {}
	                temp=1;
	            }
	        }
	        return list;
	    }
	    public static String posibleQ(int i) {
	        String list="", oldPiece;
	        int r=i/8, c=i%8;
	        int temp=1;
	        for (int j=-1; j<=1; j++) {
	            for (int k=-1; k<=1; k++) {
	                if (j!=0 || k!=0) {
	                    try {
	                        while(" ".equals(ChBoard.chessBoard[r+temp*j][c+temp*k]))
	                        {
	                            oldPiece=ChBoard.chessBoard[r+temp*j][c+temp*k];
	                            ChBoard.chessBoard[r][c]=" ";
	                            ChBoard.chessBoard[r+temp*j][c+temp*k]="Q";
	                            if (kingSafe()) {
	                                list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
	                            }
	                            ChBoard.chessBoard[r][c]="Q";
	                            ChBoard.chessBoard[r+temp*j][c+temp*k]=oldPiece;
	                            temp++;
	                        }
	                        if (Character.isLowerCase(ChBoard.chessBoard[r+temp*j][c+temp*k].charAt(0))) {
	                            oldPiece=ChBoard.chessBoard[r+temp*j][c+temp*k];
	                            ChBoard.chessBoard[r][c]=" ";
	                            ChBoard.chessBoard[r+temp*j][c+temp*k]="Q";
	                            if (kingSafe()) {
	                                list=list+r+c+(r+temp*j)+(c+temp*k)+oldPiece;
	                            }
	                            ChBoard.chessBoard[r][c]="Q";
	                            ChBoard.chessBoard[r+temp*j][c+temp*k]=oldPiece;
	                        }
	                    } catch (Exception e) {}
	                    temp=1;
	                }
	            }
	        }
	        return list;
	    }
	    public static String posibleA(int i) {
	        String list="", oldPiece;
	        int r=i/8, c=i%8;
	        for (int j=0; j<9; j++) {
	            if (j!=4) {
	                try {
	                    if (Character.isLowerCase(ChBoard.chessBoard[r-1+j/3][c-1+j%3].charAt(0)) || " ".equals(ChBoard.chessBoard[r-1+j/3][c-1+j%3])) {
	                        oldPiece=ChBoard.chessBoard[r-1+j/3][c-1+j%3];
	                        ChBoard.chessBoard[r][c]=" ";
	                        ChBoard.chessBoard[r-1+j/3][c-1+j%3]="A";
	                        int kingTemp=ChBoard.whiteLead;
	                        ChBoard.whiteLead=i+(j/3)*8+j%3-9;
	                        if (kingSafe()) {
	                            list=list+r+c+(r-1+j/3)+(c-1+j%3)+oldPiece;
	                        }
	                        ChBoard.chessBoard[r][c]="A";
	                        ChBoard.chessBoard[r-1+j/3][c-1+j%3]=oldPiece;
	                        ChBoard.whiteLead=kingTemp;
	                    }
	                } catch (Exception e) {}
	            }
	        }
	        return list;
	    }
	    public static boolean kingSafe() {
	        int temp=1;
	        for (int i=-1; i<=1; i+=2) {
	            for (int j=-1; j<=1; j+=2) {
	                try {
	                    while(" ".equals(ChBoard.chessBoard[ChBoard.whiteLead/8+temp*i][ChBoard.whiteLead%8+temp*j])) {temp++;}
	                    if ("b".equals(ChBoard.chessBoard[ChBoard.whiteLead/8+temp*i][ChBoard.whiteLead%8+temp*j]) ||
	                            "q".equals(ChBoard.chessBoard[ChBoard.whiteLead/8+temp*i][ChBoard.whiteLead%8+temp*j])) {
	                        return false;
	                    }
	                } catch (Exception e) {}
	                temp=1;
	            }
	        }
	        for (int i=-1; i<=1; i+=2) {
	            try {
	                while(" ".equals(ChBoard.chessBoard[ChBoard.whiteLead/8][ChBoard.whiteLead%8+temp*i])) {temp++;}
	                if ("r".equals(ChBoard.chessBoard[ChBoard.whiteLead/8][ChBoard.whiteLead%8+temp*i]) ||
	                        "q".equals(ChBoard.chessBoard[ChBoard.whiteLead/8][ChBoard.whiteLead%8+temp*i])) {
	                    return false;
	                }
	            } catch (Exception e) {}
	            temp=1;
	            try {
	                while(" ".equals(ChBoard.chessBoard[ChBoard.whiteLead/8+temp*i][ChBoard.whiteLead%8])) {temp++;}
	                if ("r".equals(ChBoard.chessBoard[ChBoard.whiteLead/8+temp*i][ChBoard.whiteLead%8]) ||
	                        "q".equals(ChBoard.chessBoard[ChBoard.whiteLead/8+temp*i][ChBoard.whiteLead%8])) {
	                    return false;
	                }
	            } catch (Exception e) {}
	            temp=1;
	        }
	        for (int i=-1; i<=1; i+=2) {
	            for (int j=-1; j<=1; j+=2) {
	                try {
	                    if ("k".equals(ChBoard.chessBoard[ChBoard.whiteLead/8+i][ChBoard.whiteLead%8+j*2])) {
	                        return false;
	                    }
	                } catch (Exception e) {}
	                try {
	                    if ("k".equals(ChBoard.chessBoard[ChBoard.whiteLead/8+i*2][ChBoard.whiteLead%8+j])) {
	                        return false;
	                    }
	                } catch (Exception e) {}
	            }
	        }
	        if (ChBoard.whiteLead>=16) {
	            try {
	                if ("p".equals(ChBoard.chessBoard[ChBoard.whiteLead/80-1][ChBoard.whiteLead%8-1])) {
	                    return false;
	                }
	            } catch (Exception e) {}
	            try {
	                if ("p".equals(ChBoard.chessBoard[ChBoard.whiteLead/80-1][ChBoard.whiteLead%8+1])) {
	                    return false;
	                }
	            } catch (Exception e) {}
	            for (int i=-1; i<=1; i++) {
	                for (int j=-1; j<=1; j++) {
	                    if (i!=0 || j!=0) {
	                        try {
	                            if ("a".equals(ChBoard.chessBoard[ChBoard.whiteLead/8+i][ChBoard.whiteLead%8+j])) {
	                                return false;
	                            }
	                        } catch (Exception e) {}
	                    }
	                }
	            }
	        }
	        return true;
	    }
	}

