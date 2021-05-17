package cl.jhuenul.jdialog.ui;

import java.awt.Color;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;

public class Main extends JDialog {

	private static final long serialVersionUID = 5179955312468764442L;
	private static Main dialog;
	private static final int FIVE_SECONDS = 5000;
	private static final int MAX_Y = 400;
	private static final int MAX_X = 400;
	private static Timer timer;
	private static JButton btnStartThread;
	private static JButton btnStopThread;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UIManager.put("OptionPane.cancelButtonText", "Cancel");
	    UIManager.put("OptionPane.noButtonText", "No");
	    UIManager.put("OptionPane.okButtonText", "Ok");
	    UIManager.put("OptionPane.yesButtonText", "Yes");
		try {
			dialog = new Main();
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Main() {
		setTitle("Little Program");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int opcion = JOptionPane.showConfirmDialog(null, "Do you want close the application?", "Close", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(opcion==0) {
					if(timer.isRunning()) {
						timer.stop();
					}
					dialog.dispose();
				}
			}
		});
		setBounds(100, 100, 522, 371);
		getContentPane().setLayout(null);
		
		btnStartThread = new JButton("Start Thread");
		btnStartThread.setBounds(51, 25, 415, 278);
		btnStartThread.setBackground(Color.GREEN);
		btnStartThread.setBorderPainted(false);
		timer = initTimerAction(FIVE_SECONDS);
		timer.setRepeats(Boolean.TRUE);
		btnStartThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.start();
				if(!btnStopThread.isVisible()) {
					btnStopThread.setVisible(Boolean.TRUE);
				}
				btnStartThread.setVisible(Boolean.FALSE);
				if(timer.isRunning()) {
					JOptionPane.showMessageDialog(null, "Thread is running", "Thread", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		getContentPane().add(btnStartThread);
		
		btnStopThread = new JButton("Stop Thread");
		btnStopThread.setBounds(51, 25, 415, 278);
		btnStopThread.setVisible(false);
		btnStopThread.setBackground(Color.RED);
		btnStopThread.setBorderPainted(false);
		btnStopThread.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				if(!btnStartThread.isVisible()) {
					btnStartThread.setVisible(Boolean.TRUE);
				}
				btnStopThread.setVisible(Boolean.FALSE);
				if(!timer.isRunning()) {
					JOptionPane.showMessageDialog(null, "Thread was stopped", "Thread", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		getContentPane().add(btnStopThread);
	}
	
	private Timer initTimerAction(int delay) {
		return new Timer(delay, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveMouseAction();
			}
		});
	}
	
	private void moveMouseAction() {
		try {
			Robot robot = new Robot();
	        SecureRandom secureRandom = new SecureRandom();
	        robot.mouseMove(secureRandom.nextInt(MAX_X), secureRandom.nextInt(MAX_Y));
		}catch(Exception ex) {
			System.err.println("ERROR: " + ex.getMessage());
		}
	}
}
