package vpProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePlayPage extends JPanel{
	static StateBoard stateBoard;
	static Board board;
	JPanel footer;
	static JButton playAgainBt, tryAgainBt;
	JButton exitBt;
	
	GamePlayPage(){
		initialize();
		footer = new JPanel();
		
		playAgainBt = new JButton("Play Again");
		playAgainBt.setIcon(null);
		playAgainBt.setVisible(false);
		playAgainBt.setPreferredSize(new Dimension(100,40));
		playAgainBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SoundEffect.buttonSound();
				setVisible(false);
				removeAll();
				TicTacToe.frame.setSize(330,394);
				TicTacToe.main.setVisible(true);
				TicTacToe.frame.repaint();
				TicTacToe.buildGamePlay();
			}		
		});
		footer.add(playAgainBt);
		
		tryAgainBt = new JButton("Try Again");
		tryAgainBt.setIcon(null);
		tryAgainBt.setVisible(false);
		tryAgainBt.setPreferredSize(new Dimension(100,40));
		tryAgainBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SoundEffect.buttonSound();
				setVisible(false);
				removeAll();
				TicTacToe.frame.setSize(330,394);
				TicTacToe.main.setVisible(true);
				TicTacToe.frame.repaint();
				TicTacToe.buildGamePlay();
			}		
		});
		footer.add(tryAgainBt);
		
		exitBt = new JButton("Exit");
		exitBt.setIcon(null);
		exitBt.setPreferredSize(new Dimension(100,40));
		exitBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SoundEffect.buttonSound();
				setVisible(false);
				removeAll();
				TicTacToe.frame.setSize(330,394);
				TicTacToe.main.setVisible(true);
				TicTacToe.frame.repaint();
			}		
		});
		footer.add(exitBt);

		add(footer);
	}
	
	public void initialize() {
		stateBoard = new StateBoard();
		board = new Board();
		add(stateBoard);
		add(board);
	}
}
