package vpProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class StateBoard extends JPanel implements ActionListener{
	JPanel statusPl, boardPl, buttonPl;
	JLabel boardLb, turnLb, timerLb, boardCountLb, playerLb, botLb;
	ImageIcon timerIcon = IconClass.createIcon("icon/clock.png",25,25);
	ImageIcon boardIcon = IconClass.createIcon("icon/boardinfo.png",25,25);
	ImageIcon playerIcon = IconClass.createIcon("icon/human.png",25,25);
	ImageIcon botPlayerIcon = IconClass.createIcon("icon/ai.png",25,25);
	static int match = 0, player = 0, botPlayer = 0;
	int timer = 0, board = 0;
	Timer t;
	
	StateBoard(){
		readDB();
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		turnLb = new JLabel();
		turnLb.setFont(new Font("TimesRoman", Font.BOLD, 36));
		turnLb.setHorizontalTextPosition(JLabel.CENTER);
		turnLb.setAlignmentX(Component.CENTER_ALIGNMENT);
		turnPanel(1);
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap((TicTacToe.frame.getWidth()-timerIcon.getIconWidth()*4)/4);
		
		boardPl = new JPanel();
		boardPl.setLayout(flowLayout);
		boardPl.setAlignmentX(Component.CENTER_ALIGNMENT);
		setBoard();
		
		statusPl = new JPanel();
		statusPl.setLayout(new BoxLayout(statusPl,BoxLayout.Y_AXIS));
		statusPl.add(turnLb);
		statusPl.add(boardPl);
		
		add(statusPl);
		
		t = new Timer(1000,this);
		t.start();
	}
	
	public void turnPanel(int status) {
		switch(status) {
		case 1:
			if(TicTacToe.gamemode == 0) {
				turnLb.setText("You Turn");
			}else if(TicTacToe.gamemode == 1) {
				turnLb.setText("Player 1 Turn");
			}
			break;
		case 2:
			if(TicTacToe.gamemode == 0) {
				turnLb.setText("Bot Turn");
			}else if(TicTacToe.gamemode == 1) {
				turnLb.setText("Player 2 Turn");
			}
			break;
		case 3:
			if(TicTacToe.gamemode == 0) {
				turnLb.setText("You Won!");
			}else if(TicTacToe.gamemode == 1) {
				turnLb.setText("Player 1 Won");
			}
			
			break;
		case 4:
			turnLb.setText("Draw!");
			break;
		case 5:
			if(TicTacToe.gamemode == 0) {
				turnLb.setText("You Lost!");
			}else if(TicTacToe.gamemode == 1) {
				turnLb.setText("Player 2 Win");
			}
			
			break;
		default :
			System.out.println("Error in switch case of turnPanel");
			break;
		}
	}
	
	public void setBoard() {
		timerLb = new JLabel();
		timerLb.setIcon(timerIcon);
		timerLb.setVisible(TicTacToe.matchTimer);
		timerLb.setText(String.valueOf(timer));
		
		boardLb = new JLabel();
		boardLb.setIcon(boardIcon);
		boardLb.setVisible(TicTacToe.boardInfo);
		boardLb.setText(String.valueOf(board));
		
		playerLb = new JLabel();
		playerLb.setIcon(playerIcon);
		playerLb.setVisible(TicTacToe.playerCounter);
		playerLb.setText(String.valueOf(player));
		
		botLb = new JLabel();
		botLb.setIcon(botPlayerIcon);
		botLb.setVisible(TicTacToe.playerCounter);
		botLb.setText(String.valueOf(botPlayer));

		boardPl.add(timerLb);
		boardPl.add(boardLb);
		boardPl.add(playerLb);
		boardPl.add(botLb);
	}
	
	public void addBoard() {
		board++;
		boardLb.setText(String.valueOf(board));
	}
	
	public void numPlWin() {
		player++;
		playerLb.setText(String.valueOf(player));
	}
	
	public void numAiWin() {
		botPlayer++;
		botLb.setText(String.valueOf(botPlayer));
	}
	
	public void actionPerformed(ActionEvent arg0) {
		timer++;
		timerLb.setText(String.valueOf(timer));
	}
	
	public void stopTimer() {
		t.stop();
	}

	public void readDB() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe","root","");
		
				String sql = "SELECT * FROM player_match "
						+ "WHERE player_id =" + TicTacToe.playerId + " AND board_id = " + TicTacToe.board + ";";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("yes" + TicTacToe.board);
				if(!rs.next()) {
					match = 0;
					player = 0;
					botPlayer = 0;
				}else {
					match = rs.getInt(1);
					player = rs.getInt(4);
					botPlayer = rs.getInt(5);
				}
				
				rs.close();
				stmt.close();
				con.close();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		
	}
	
	public void updateDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe","root","");
			String sql = null;
			if(match == 0) {
			sql = "INSERT INTO player_match VALUES("
					+  match
					+ "," + TicTacToe.board 
					+ "," + TicTacToe.playerId
					+ "," + player
					+ "," + botPlayer + ");";
			}else {
				sql = "UPDATE player_match SET "
						+ "board_id = "+ TicTacToe.board 
						+ ", player_id = "+ TicTacToe.playerId
						+ ", match_win_count = "+ player
						+ ", match_lose_count = " + botPlayer 
						+ " WHERE match_id = "+ match+ ";";
			}
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			
			stmt.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}