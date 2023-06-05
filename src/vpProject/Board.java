package vpProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel{
	int numberOfBoard = TicTacToe.board + 3;
	JButton board[][];
	ImageIcon XIcon, OIcon;
	ImageIcon winnerIcon;
	Boolean player1 = true, win = false;
	int lenght, boardnumber = numberOfBoard*numberOfBoard;
	
	Board() {
		lenght = TicTacToe.frame.getWidth()/numberOfBoard;
		XIcon = IconClass.createIcon("icon/x.png",lenght,lenght);
		OIcon = IconClass.createIcon("icon/circle.png",lenght,lenght);
		setLayout(new GridLayout(numberOfBoard,numberOfBoard,5,5));
		setBoard();
	}
	
	public void setBoard() {

		board = new JButton[numberOfBoard][numberOfBoard];
		for(int i = 0; i < numberOfBoard ; i++) {
			for(int j = 0 ; j < numberOfBoard ; j++) {
				board[i][j] = new JButton();
				board[i][j].setPreferredSize(new Dimension(lenght,lenght));
				board[i][j].setActionCommand(i + "/" + j);
				board[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String sp = e.getActionCommand();
						String p[] = sp.split("/");
						int i = Integer.valueOf(p[0]);
						int j = Integer.valueOf(p[1]);
						if(TicTacToe.gamemode == 0) {
							player1(i,j);
							boardnumber--;
							GamePlayPage.stateBoard.addBoard();
							if(win){
								won();
								return;
							}else if(boardnumber < 1 ) { //check draw for the numberOfBoard%2 != 0 board. 
								draw();
								return;
							}
							if(TicTacToe.difficulty == 0) {
								easyAi();
							}else {
								hardAi();
							}
						boardnumber--;
						GamePlayPage.stateBoard.addBoard();
						if(boardnumber < 1 ) { //check draw for the numberOfBoard%2 == 0 board. 
							draw();
							return;
						}
						}else {
							if(player1) {
								player1(i,j);
								player1 = false;
								GamePlayPage.stateBoard.addBoard();
								GamePlayPage.stateBoard.turnPanel(2);
							}else {
								player2(i,j);
								player1 = true;
								GamePlayPage.stateBoard.addBoard();
								GamePlayPage.stateBoard.turnPanel(1);
							}
							if(win){
								won();
								return;
							}else if(boardnumber < 2) { 
								draw();
							}
							boardnumber--;
						}
					}
				});
				add(board[i][j]);
			}
		}
	}
	
	public void won() {
		GamePlayPage.stateBoard.turnPanel(((winnerIcon==XIcon)? 3:5));
		GamePlayPage.stateBoard.stopTimer();
		if(winnerIcon == XIcon) {
			GamePlayPage.stateBoard.numPlWin();
			GamePlayPage.playAgainBt.setVisible(true);
		}else {
			GamePlayPage.stateBoard.numAiWin();
			GamePlayPage.tryAgainBt.setVisible(true);
		}
		GamePlayPage.stateBoard.updateDB();
	}
	
	public void draw() {
		GamePlayPage.tryAgainBt.setVisible(true);
		GamePlayPage.stateBoard.turnPanel(4);
		GamePlayPage.stateBoard.stopTimer();
	}
	
	public void player1(int i, int j) {
		board[i][j].setEnabled(false);
		board[i][j].setIcon(XIcon);
		calWin(XIcon, Color.RED);
	}
	
	public void player2(int i, int j) {
		board[i][j].setEnabled(false);
		board[i][j].setIcon(OIcon);
		calWin(OIcon, Color.BLUE);
	}
	
	public void easyAi() {
		int x = 0, y = 0;
		do {
			x = (int) (Math.random()*numberOfBoard);
			y = (int) (Math.random()*numberOfBoard);
		}while(board[x][y].getIcon() != null);
		board[x][y].setIcon(OIcon);
		board[x][y].setEnabled(false);
		calWin(OIcon, Color.CYAN);
		if(win) {
			won();
		}
	}
	
	public void hardAi() {
	    int countPl = 0;
	    int countBotH = 0, countBotV = 0;
	    int countEty = 0;
	    boolean botmove = false;
	    boolean ety = false, ety2 = false;
	    int etyR = 0, etyC = 0;
	    int etyR2 = 0, etyC2 = 0;
	    int colunm = numberOfBoard -1;
	    
	    //Check horizon and vertical (computer)
	    for(int i=0; i < numberOfBoard; i++) {
	    	countBotH = 0;
		    countBotV = 0;
		    ety = false;
		    ety2 = false;
			for(int j = 0; j < numberOfBoard; j++) {
				if(board[i][j].getIcon() == OIcon) {
					countBotH++;
				}
				if(board[i][j].getIcon() == null) {
					etyR = i;
					etyC = j;
					ety = true;
				}
				if(board[j][i].getIcon() == OIcon) {
					countBotV++;
				}
				if(board[j][i].getIcon() == null) {
					etyR2 = j;
					etyC2 = i;
					ety2 = true;
				}
				
				if(j == numberOfBoard-1) {
					if(countBotH == colunm && !botmove && ety) {
						board[etyR][etyC].setIcon(OIcon);
						board[etyR][etyC].setEnabled(false);
						botmove = true;
					}
					if(countBotV == colunm && !botmove && ety2) {
						board[etyR2][etyC2].setIcon(OIcon);
						board[etyR2][etyC2].setEnabled(false);
						botmove = true;
					}
				}
			}
	    }
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    
		//check the \ line
	    if(!botmove) {
			for(int i = 0; i < numberOfBoard; i++) {
				if(board[i][i].getIcon() == OIcon) {
					countBotH++;
				}
				if(board[i][i].getIcon() == null) {
					etyR = etyC = i;
					ety = true;
				}
			}
			if(countBotH == colunm && ety) {
				board[etyR][etyC].setIcon(OIcon);
				board[etyR][etyC].setEnabled(false);
				botmove = true;
			}
	    }
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    
	    //check the / line
	    if(!botmove) {
	    	for(int i = 0, j = numberOfBoard - 1; i < numberOfBoard; i++,j--) {
				if(board[i][j].getIcon() == OIcon) {
					countBotH++;
				}
				if(board[i][j].getIcon() == null) {
					etyR = i;
					etyC = j;
					ety = true;
				}
			}
	    	if(countBotH == colunm && ety) {
	    		board[etyR][etyC].setIcon(OIcon);
				board[etyR][etyC].setEnabled(false);
				botmove = true;
	    	}
	    }
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    
	    //Check horizon and vertical (player)
	    for(int i=0; i < numberOfBoard; i++) {
	    	countBotH = 0;
		    countBotV = 0;
		    ety = false;
		    ety2 = false;
			for(int j = 0; j < numberOfBoard; j++) {
				if(board[i][j].getIcon() == XIcon) {
					countBotH++;
				}
				if(board[i][j].getIcon() == null) {
					etyR = i;
					etyC = j;
					ety = true;
				}
				if(board[j][i].getIcon() == XIcon) {
					countBotV++;
				}
				if(board[j][i].getIcon() == null) {
					etyR2 = j;
					etyC2 = i;
					ety2 = true;
				}
				if(j == colunm) {
					if(countBotH == colunm && !botmove && ety) {
						board[etyR][etyC].setIcon(OIcon);
						board[etyR][etyC].setEnabled(false);
						botmove = true;
					}
					if(countBotV == colunm && !botmove && ety2) {
						board[etyR2][etyC2].setIcon(OIcon);
						board[etyR2][etyC2].setEnabled(false);
						botmove = true;
					}
				}
			}
	    }
	    
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    
		//check the \ line
	    if(!botmove) {
			for(int i = 0; i < numberOfBoard; i++) {
				if(board[i][i].getIcon() == XIcon) {
					countBotH++;
				}
				if(board[i][i].getIcon() == null) {
					etyR = etyC = i;
					ety = true;
				}
			}
			if(countBotH == colunm && ety) {
				board[etyR][etyC].setIcon(OIcon);
				board[etyR][etyC].setEnabled(false);
				botmove = true;
			}
	    }
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    
	    //check the / line
	    if(!botmove) {
	    	for(int i = 0, j = numberOfBoard - 1; i < numberOfBoard; i++,j--) {
				if(board[i][j].getIcon() == XIcon) {
					countBotH++;
				}
				if(board[i][j].getIcon() == null) {
					etyR = i;
					etyC = j;
					ety = true;
				}
			}
	    	if(countBotH == colunm && ety) {
	    		board[etyR][etyC].setIcon(OIcon);
				board[etyR][etyC].setEnabled(false);
				botmove = true;
	    	}
	    }
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
		
	    if(numberOfBoard >3) {
	    //Check horizon and vertical (computer second)
	    for(int i=0; i < numberOfBoard; i++) {
	    	countBotH = 0;
		    countBotV = 0;
		    ety = false;
		    ety2 = false;
			for(int j = 0; j < numberOfBoard; j++) {
				if(board[i][j].getIcon() == OIcon) {
					countBotH++;
				}
				if(board[i][j].getIcon() == null) {
					etyR = i;
					etyC = j;
					ety = true;
				}
				if(board[j][i].getIcon() == OIcon) {
					countBotV++;
				}
				if(board[j][i].getIcon() == null) {
					etyR2 = j;
					etyC2 = i;
					ety2 = true;
				}
				
				if(j == numberOfBoard-1) {
					if(countBotH == colunm -1 && !botmove && ety) {
						board[etyR][etyC].setIcon(OIcon);
						board[etyR][etyC].setEnabled(false);
						botmove = true;
					}
					if(countBotV == colunm -1 && !botmove && ety2) {
						board[etyR2][etyC2].setIcon(OIcon);
						board[etyR2][etyC2].setEnabled(false);
						botmove = true;
					}
				}
			}
	    }
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    
		//check the \ line
	    if(!botmove) {
			for(int i = 0; i < numberOfBoard; i++) {
				if(board[i][i].getIcon() == OIcon) {
					countBotH++;
				}
				if(board[i][i].getIcon() == null) {
					etyR = etyC = i;
					ety = true;
				}
			}
			if(countBotH == colunm -1 && ety) {
				board[etyR][etyC].setIcon(OIcon);
				board[etyR][etyC].setEnabled(false);
				botmove = true;
			}
	    }
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    
	    //check the / line
	    if(!botmove) {
	    	for(int i = 0, j = numberOfBoard - 1; i < numberOfBoard; i++,j--) {
				if(board[i][j].getIcon() == OIcon) {
					countBotH++;
				}
				if(board[i][j].getIcon() == null) {
					etyR = i;
					etyC = j;
					ety = true;
				}
			}
	    	if(countBotH == colunm -1 && ety) {
	    		board[etyR][etyC].setIcon(OIcon);
				board[etyR][etyC].setEnabled(false);
				botmove = true;
	    	}
	    }
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    
	  //Check horizon and vertical (player)
	    for(int i=0; i < numberOfBoard; i++) {
	    	countBotH = 0;
		    countBotV = 0;
		    ety = false;
		    ety2 = false;
			for(int j = 0; j < numberOfBoard; j++) {
				if(board[i][j].getIcon() == XIcon) {
					countBotH++;
				}
				if(board[i][j].getIcon() == null) {
					etyR = i;
					etyC = j;
					ety = true;
				}
				if(board[j][i].getIcon() == XIcon) {
					countBotV++;
				}
				if(board[j][i].getIcon() == null) {
					etyR2 = j;
					etyC2 = i;
					ety2 = true;
				}
				if(j == colunm) {
					if(countBotH == colunm -1 && !botmove && ety) {
						board[etyR][etyC].setIcon(OIcon);
						board[etyR][etyC].setEnabled(false);
						botmove = true;
					}
					if(countBotV == colunm -1 && !botmove && ety2) {
						board[etyR2][etyC2].setIcon(OIcon);
						board[etyR2][etyC2].setEnabled(false);
						botmove = true;
					}
				}
			}
	    }
	    
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    
		//check the \ line
	    if(!botmove) {
			for(int i = 0; i < numberOfBoard; i++) {
				if(board[i][i].getIcon() == XIcon) {
					countBotH++;
				}
				if(board[i][i].getIcon() == null) {
					etyR = etyC = i;
					ety = true;
				}
			}
			if(countBotH == colunm -1 && ety) {
				board[etyR][etyC].setIcon(OIcon);
				board[etyR][etyC].setEnabled(false);
				botmove = true;
			}
	    }
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    
	    //check the / line
	    if(!botmove) {
	    	for(int i = 0, j = numberOfBoard - 1; i < numberOfBoard; i++,j--) {
				if(board[i][j].getIcon() == XIcon) {
					countBotH++;
				}
				if(board[i][j].getIcon() == null) {
					etyR = i;
					etyC = j;
					ety = true;
				}
			}
	    	if(countBotH == colunm -1 && ety) {
	    		board[etyR][etyC].setIcon(OIcon);
				board[etyR][etyC].setEnabled(false);
				botmove = true;
	    	}
	    }
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    }
	    //If not board is choose by computer, it will random select the middle board
	    if(!botmove) {
	    	boolean middleEty = false;
	    	for(int i = 1; i < numberOfBoard -1; i++) {
				for(int j = 1; j < numberOfBoard -1; j++) {
					if(board[i][j].getIcon() == null) {
						middleEty = true;
					}
				}
	    	}
	    	if(middleEty) {
	    		do {
	    			etyR = (int)(Math.random()*(numberOfBoard-2)+1);
	    			etyC = (int)(Math.random()*(numberOfBoard-2)+1);
	    		}while(board[etyR][etyC].getIcon() != null);
	    		board[etyR][etyC].setIcon(OIcon);
	    		board[etyR][etyC].setEnabled(false);
	    		botmove = true;
	    	}
	    	
	    }
	    countBotH = 0;
	    countBotV = 0;
	    ety = false;
	    ety2 = false;
	    
	    //select the outer round when does not have any choose
	    if(!botmove) {
    		do {
    			etyR = (int)(Math.random()*(numberOfBoard));
    			etyC = (int)(Math.random()*(numberOfBoard));
    		}while(board[etyR][etyC].getIcon() != null);
    		board[etyR][etyC].setIcon(OIcon);
    		board[etyR][etyC].setEnabled(false);
    		botmove = true;
	    	
	    }
	    
	    calWin(OIcon, Color.CYAN);
	    if(win) {
			won();
		}
	}
	
	public boolean calWin(ImageIcon Icon, Color color) {
		int rowCount = 0;
		int colunmCount = 0;
		int xCount = 0; 
		//check the board for which have become a line, for horizontal and vertical only
		for(int i=0; i < numberOfBoard; i++) {
			for(int j = 0; j < numberOfBoard; j++) {
				if(board[i][j].getIcon() == Icon) {
					rowCount++;
				}
				if(board[j][i].getIcon() == Icon) {
					colunmCount++;
				}
			}
			if(rowCount == numberOfBoard) {
				win = true;
				for(int a=0; a < numberOfBoard; a++) {
					board[i][a].setBackground(color);
				}
				break;
			}else if(colunmCount == numberOfBoard) {
				win = true;
				for(int a=0; a < numberOfBoard; a++) {
					board[a][i].setBackground(color);
				}
				break;
			}else {
				rowCount = colunmCount = 0;
			}
		}
		
		if(!win) {
			//check the \ line
			for(int i = 0; i < numberOfBoard; i++) {
				if(board[i][i].getIcon() == Icon) {
					xCount++;
				}
			}
			if(xCount == numberOfBoard) {
				win = true;
				for(int i = 0; i < numberOfBoard; i++) {
					board[i][i].setBackground(color);
				}
			}else {
				xCount = 0;
			}
		}
		
		if(!win) {
			//Check for / line
			for(int i = 0, j = numberOfBoard - 1; i < numberOfBoard; i++,j--) {
				if(board[i][j].getIcon() == Icon) {
					xCount++;
				}
			}
			if(xCount == numberOfBoard) {
				win = true;
				for(int i = 0, j = numberOfBoard - 1; i < numberOfBoard; i++,j--) {
					board[i][j].setBackground(color);
				}
			}
		}
		if(win) {
			winnerIcon = Icon;
			whenWin();
		}
		return win;
	}
	
	public void whenWin() {
		for(int i=0; i < numberOfBoard; i++) {
			for(int j = 0; j < numberOfBoard; j++) {
				board[i][j].setEnabled(false);
			}
		}
	}
}
