package vpProject;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import swing.*;

public class GameSetting extends JPanel{
	JPanel generalPl, matchInfoPl, gamemodePl, boardPl, difficultyPl, matchTimerPl, boardInfoPl, playerCounterPl, backgroundMusicPl;
	JLabel settingLb, generalLb, matchInfoLb, gamemodeLb, boardLb, difficultyLb, matchTimerLb, boardInfoLb, playerCounterLb, backgroundLb, matchTimerDesc, boardInfoDesc, playerCounterDesc;
	JLabel bgmInfoDesc, matchTimerOF, boardInfoOF, playerCounterOF, bgmInfoOF;
	JComboBox gamemodeCb, boardCb, difficultyCb;
	JScrollPane settingSp;
	SwitchButton matchTimerSb, boardInfoSb, playerCounterSb, backgroundMusicSb;
	
	
	ImageIcon gameplayIcon = IconClass.createIcon("icon/gameplay.png",25,25);
	ImageIcon boardIcon = IconClass.createIcon("icon/board.png",25,25);
	ImageIcon timerIcon = IconClass.createIcon("icon/clock.png",25,25);
	ImageIcon boardInfoIcon = IconClass.createIcon("icon/boardInfo.png",25,25);
	ImageIcon playerIcon = IconClass.createIcon("icon/human.png",25,25);
	ImageIcon difficultyIcon = IconClass.createIcon("icon/easy.png",25,25);
	ImageIcon musicIcon = IconClass.createIcon("icon/music.png",25,25);
	ImageIcon resetIcon = IconClass.createIcon("icon/reset.png",25,25);
	ImageIcon returnIcon = IconClass.createIcon("icon/return.png",25,25);
	
	GameSetting(){
		
		settingLb = new JLabel("Settings");
		settingLb.setFont(new Font("TimesRoman", Font.BOLD, 36));
		settingLb.setHorizontalAlignment(SwingConstants.CENTER);
		settingLb.setBounds(135, 10, 150, 40);
		
		setGeneral();
		
		setLayout(null);
		add(settingLb);
		add(settingSp);
	}
	
	public void setGeneral() {
		JButton returnBt = new JButton();
		returnBt.setIcon(returnIcon);
		returnBt.setBounds(10, 10, 40, 33);
		add(returnBt);
		returnBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundEffect.buttonSound();
				TicTacToe.matchTimer = matchTimerSb.isSelected();
				TicTacToe.boardInfo = boardInfoSb.isSelected();
				TicTacToe.playerCounter = playerCounterSb.isSelected();
				TicTacToe.backgroundMusic = backgroundMusicSb.isSelected();
				TicTacToe.gamemode = gamemodeCb.getSelectedIndex();
				TicTacToe.board = boardCb.getSelectedIndex();
				TicTacToe.difficulty = difficultyCb.getSelectedIndex();
				
				setVisible(false);
				TicTacToe.frame.setBounds(700,300,330,394);
				TicTacToe.main.setVisible(true);
				TicTacToe.frame.repaint();
				try {
					updateDB();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		//General
		generalPl = new JPanel();
		generalPl.setLayout(null);
		generalPl.setPreferredSize(new Dimension(450,650));
		
		generalLb = new JLabel("General");
		generalLb.setFont(new Font("TimesRoman", Font.BOLD, 18));
		generalLb.setBounds(10, 53, 94, 20);
		generalPl.add(generalLb);
		
		gamemodePl = new JPanel();
		gamemodePl.setBackground(Color.WHITE);
		gamemodePl.setBounds(10, 79, 416, 66);
		generalPl.add(gamemodePl);
		gamemodePl.setLayout(null);
		
		String gamemodeS[] = {"Singleplayer", "Multiplayer"};
		gamemodeCb = new JComboBox(gamemodeS);
		gamemodeCb.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		gamemodeCb.setSelectedIndex(TicTacToe.gamemode);
		gamemodeCb.setBounds(260, 24, 124, 21);
		gamemodePl.add(gamemodeCb);
		
		gamemodeLb = new JLabel();
		gamemodeLb.setIcon(gameplayIcon);
		gamemodeLb.setText("Gamemode");
		gamemodeLb.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		gamemodeLb.setBounds(10, 10, 163, 46);
		gamemodePl.add(gamemodeLb);
		
		boardPl = new JPanel();
		boardPl.setBackground(Color.WHITE);
		boardPl.setBounds(10, 155, 416, 66);
		generalPl.add(boardPl);
		boardPl.setLayout(null);
		
		boardLb = new JLabel();
		boardLb.setIcon(boardIcon);
		boardLb.setText("Board");
		boardLb.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		boardLb.setBounds(10, 10, 163, 46);
		boardPl.add(boardLb);
		
		String sizeS[] = {"Default(3x3)", "4x4", "5x5","6X6"};
		boardCb = new JComboBox(sizeS);
		boardCb.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		boardCb.setSelectedIndex(TicTacToe.board);
		boardCb.setBounds(260, 23, 124, 21);
		boardPl.add(boardCb);
		
		difficultyPl = new JPanel();
		difficultyPl.setLayout(null);
		difficultyPl.setBackground(Color.WHITE);
		difficultyPl.setBounds(10, 231, 416, 66);
		generalPl.add(difficultyPl);
		
		difficultyLb = new JLabel();
		difficultyLb.setIcon(difficultyIcon);
		difficultyLb.setText("Difficulty");
		difficultyLb.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		difficultyLb.setBounds(10, 10, 163, 46);
		difficultyPl.add(difficultyLb);
		
		String difficultyS[]= {"Easy", "Hard"};
		difficultyCb = new JComboBox(difficultyS);
		difficultyCb.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		difficultyCb.setSelectedIndex(TicTacToe.difficulty);
		difficultyCb.setBounds(260, 23, 124, 21);
		difficultyPl.add(difficultyCb);
		
		//match info
		matchInfoLb = new JLabel("Match Info");
		matchInfoLb.setFont(new Font("TimesRoman", Font.BOLD, 18));
		matchInfoLb.setBounds(10, 307, 94, 30);
		generalPl.add(matchInfoLb);
		
		//matchTimerPl, matchTimerLb, matchTimerSb
		matchTimerPl = new JPanel();
		matchTimerPl.setLayout(null);
		matchTimerPl.setBackground(Color.WHITE);
		matchTimerPl.setBounds(10, 336, 416, 66);
		generalPl.add(matchTimerPl);
		
		matchTimerLb = new JLabel("Match Timer");
		matchTimerLb.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		matchTimerLb.setIcon(timerIcon);
		matchTimerLb.setBounds(10, 10, 163, 46);
		matchTimerPl.add(matchTimerLb);
		
		matchTimerDesc = new JLabel("Keep Track of how long the match takes");
		matchTimerDesc.setFont(new Font("Times New Romen", Font.PLAIN, 12));
		matchTimerDesc.setBounds(40, 27, 220, 46);
		matchTimerPl.add(matchTimerDesc);
		
		matchTimerOF = new JLabel();
		matchTimerOF.setText((TicTacToe.matchTimer? "On":"Off"));
		matchTimerOF.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		matchTimerOF.setBounds(370, 21, 50, 21);
		matchTimerPl.add(matchTimerOF);
		
		matchTimerSb = new SwitchButton();
		matchTimerSb.setBounds(310, 21, 50, 21);
		matchTimerSb.setSelected(TicTacToe.matchTimer);
		matchTimerPl.add(matchTimerSb);
		matchTimerSb.addEventSelected(new EventSwitchSelected() {
            public void onSelected(boolean selected) {
                if(selected) {
                	matchTimerOF.setText("On");
                }else {
                	matchTimerOF.setText("Off");
                }
            }
        });
		
		//boardInfoPl, boardInfoLb, boardInfoSb
		boardInfoPl = new JPanel();
		boardInfoPl.setLayout(null);
		boardInfoPl.setBackground(Color.WHITE);
		boardInfoPl.setBounds(10, 412, 416, 66);
		generalPl.add(boardInfoPl);
		
		boardInfoLb = new JLabel("Board Info");
		boardInfoLb.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		boardInfoLb.setIcon(boardIcon);
		boardInfoLb.setBounds(10, 10, 163, 46);
		boardInfoPl.add(boardInfoLb);
		
		boardInfoDesc = new JLabel("Displays number of spots taken");
		boardInfoDesc.setFont(new Font("Times New Romen", Font.PLAIN, 12));
		boardInfoDesc.setBounds(40, 27, 200, 46);
		boardInfoPl.add(boardInfoDesc);
		
		boardInfoOF = new JLabel();
		boardInfoOF.setText((TicTacToe.boardInfo? "On":"Off"));
		boardInfoOF.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		boardInfoOF.setBounds(370, 21, 50, 21);
		boardInfoPl.add(boardInfoOF);
		
		boardInfoSb = new SwitchButton();
		boardInfoSb.setBounds(310, 23, 50, 21);
		boardInfoSb.setSelected(TicTacToe.boardInfo);
		boardInfoPl.add(boardInfoSb);
		boardInfoSb.addEventSelected(new EventSwitchSelected() {
            public void onSelected(boolean selected) {
                if(selected) {
                	boardInfoOF.setText("On");
                }else {
                	boardInfoOF.setText("Off");
                }
            }
        });

		//playerCounterPl, playerCounterLb, playerCounterSb
		playerCounterPl = new JPanel();
		playerCounterPl.setLayout(null);
		playerCounterPl.setBackground(Color.WHITE);
		playerCounterPl.setBounds(10, 488, 416, 66);
		generalPl.add(playerCounterPl);
		
		playerCounterLb = new JLabel("Player Counter");
		playerCounterLb.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		playerCounterLb.setIcon(playerIcon);
		playerCounterLb.setBounds(10, 10, 163, 46);
		playerCounterPl.add(playerCounterLb);
		
		playerCounterDesc = new JLabel("Display number of human/bots wins");
		playerCounterDesc.setFont(new Font("Times New Romen", Font.PLAIN, 12));
		playerCounterDesc.setBounds(40, 27, 200, 46);
		playerCounterPl.add(playerCounterDesc);
		
		playerCounterOF = new JLabel();
		playerCounterOF.setText((TicTacToe.playerCounter? "On":"Off"));
		playerCounterOF.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		playerCounterOF.setBounds(370, 21, 50, 21);
		playerCounterPl.add(playerCounterOF);
		
		playerCounterSb = new SwitchButton();
		playerCounterSb.setBounds(310, 21, 50, 21);
		playerCounterSb.setSelected(TicTacToe.playerCounter);
		playerCounterPl.add(playerCounterSb);
		playerCounterSb.addEventSelected(new EventSwitchSelected() {
            public void onSelected(boolean selected) {
                if(selected) {
                	playerCounterOF.setText("On");
                }else {
                	playerCounterOF.setText("Off");
                }
            }
        });
		
		JPanel backgroundPl = new JPanel();
		backgroundPl.setLayout(null);
		backgroundPl.setBackground(Color.WHITE);
		backgroundPl.setBounds(10, 564, 416, 66);
		generalPl.add(backgroundPl);
		
		backgroundLb = new JLabel("BGM");
		backgroundLb.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		backgroundLb.setIcon(musicIcon);
		backgroundLb.setBounds(10, 10, 163, 46);
		backgroundPl.add(backgroundLb);
		
		bgmInfoDesc = new JLabel("Switch on/off background music");
		bgmInfoDesc.setFont(new Font("Times New Romen", Font.PLAIN, 12));
		bgmInfoDesc.setBounds(40, 27, 200, 46);
		backgroundPl.add(bgmInfoDesc);
		
		bgmInfoOF = new JLabel();
		bgmInfoOF.setText((TicTacToe.backgroundMusic? "On":"Off"));
		bgmInfoOF.setFont(new Font("TimesRoman", Font.PLAIN, 16));
		bgmInfoOF.setBounds(370, 21, 50, 21);
		backgroundPl.add(bgmInfoOF);
		
		backgroundMusicSb = new SwitchButton();
		backgroundMusicSb.setBounds(310, 21, 50, 21);
		backgroundMusicSb.setSelected(TicTacToe.backgroundMusic);
		backgroundMusicSb.addEventSelected(new EventSwitchSelected() {
            public void onSelected(boolean selected) {
                if(selected) {
                	TicTacToe.bgm.resumeBgm();
                	bgmInfoOF.setText("On");
                }else {
                	TicTacToe.bgm.pauseBgm();
                	bgmInfoOF.setText("Off");
                }
            }
        });
		backgroundPl.add(backgroundMusicSb);
		
		settingSp = new JScrollPane(generalPl);
		settingSp.setBounds(0,50,436,350);
		settingSp.setBorder(null);
		settingSp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		settingSp.getVerticalScrollBar().setUnitIncrement(16);
		settingSp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton resetBt = new JButton("Reset to default");
		resetBt.setFont(new Font("TimesRoman", Font.BOLD, 16));
		resetBt.setIcon(resetIcon);
		resetBt.setBounds(135, 450, 170, 42);
		resetBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SoundEffect.buttonSound();
				TicTacToe.matchTimer = true;
				TicTacToe.boardInfo = true;
				TicTacToe.playerCounter = true;
				TicTacToe.backgroundMusic = true;
				TicTacToe.gamemode = 0;
				TicTacToe.board = 0;
				TicTacToe.difficulty = 0;
				removeAll();
				setGeneral();
				add(settingLb);
				add(settingSp);
				revalidate();
				repaint();
				TicTacToe.bgm.resumeBgm();
			}
		});
		add(resetBt);
	}
	
	public void updateDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe","root","");
		Statement stmt = con.createStatement();
		
		String findsetting = "SELECT * FROM setting WHERE player_id = " + TicTacToe.playerId;
		ResultSet rs = stmt.executeQuery(findsetting);
		String sql;
		if (rs.next() == false) {
			sql = "INSERT INTO setting VALUES("
					+"0"
					+"," + TicTacToe.playerId 
					+"," + TicTacToe.board 
					+"," + TicTacToe.difficulty 
					+"," + (TicTacToe.matchTimer? 1:0) 
					+"," + (TicTacToe.boardInfo? 1:0) 
					+"," + (TicTacToe.playerCounter? 1:0) 
					+"," + TicTacToe.gamemode 
					+"," + (TicTacToe.backgroundMusic? 1:0) + ");";
			stmt.executeUpdate(sql);
		}else {
			sql = "UPDATE setting "
					+ "SET setting.board_id = '"+ TicTacToe.board +"', "
					+ "setting.difficulty_id = '"+ TicTacToe.difficulty +"', "
					+ "setting_show_timer = '"+ (TicTacToe.matchTimer? 1:0) +"',"
					+ "setting_show_board_info = '"+ (TicTacToe.boardInfo? 1:0) +"', "
					+ "setting_show_player_win_count = '"+ (TicTacToe.playerCounter? 1:0) +"', "
					+ "gamemode = '"+ TicTacToe.gamemode +"',"
					+ "setting_background_music = '"+ (TicTacToe.backgroundMusic? 1:0) + "'"
					+ "WHERE setting.player_id = '"+ TicTacToe.playerId +"';";
			stmt.executeUpdate(sql);
		}
		
		stmt.close();
		con.close();
		rs.close();
	}
	
}
