package designpatternquiz;

import java.awt.EventQueue;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.ImageIcon;

public class SelectScreenWindow extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup optionsButtonGroup = new ButtonGroup();
	private JTextField toTextField;
	private JTextField fromTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectScreenWindow frame = new SelectScreenWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectScreenWindow() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				SelectScreenWindow.class.getResource("/designpatternquiz/512px-Eo_circle_pink_checkmark.svg.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 280);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false); // user cannot resize screen
		setLocationRelativeTo(null); // open in center of screen
		
		JLabel lauraLabel = new JLabel("by LauraTatianaToro");
		lauraLabel.setBounds(339, 40, 149, 14);
		contentPane.add(lauraLabel);

		JLabel welcomeMessageLabel = new JLabel("Welcome to the Design Pattern Quiz!");
		welcomeMessageLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		welcomeMessageLabel.setForeground(Color.BLACK);
		welcomeMessageLabel.setBounds(10, 0, 478, 33);
		welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(welcomeMessageLabel);

		JLabel selectSentenceLabel = new JLabel("Please select which questions you'd like to answer (1-10):");
		selectSentenceLabel.setBounds(10, 65, 478, 14);
		contentPane.add(selectSentenceLabel);

		final JRadioButton selectRangeRadioButton = new JRadioButton(
				"I only want a few questions!  (Select a range from 1-10)");
		optionsButtonGroup.add(selectRangeRadioButton);
		selectRangeRadioButton.setBounds(10, 112, 478, 23);
		contentPane.add(selectRangeRadioButton);
		selectRangeRadioButton.setOpaque(false);

		final JRadioButton allQuestionsRadioButton = new JRadioButton("I want every question! (All 10!)");
		optionsButtonGroup.add(allQuestionsRadioButton);
		allQuestionsRadioButton.setBounds(10, 86, 478, 23);
		allQuestionsRadioButton.setOpaque(false);

		final JLabel fromLabel = new JLabel("From:");
		fromLabel.setBounds(33, 138, 28, 14);
		contentPane.add(fromLabel);
		fromLabel.setVisible(false);

		toTextField = new JTextField();
		toTextField.setToolTipText("Enter the last number of your range. Ex: 10");
		toTextField.setBounds(136, 135, 28, 20);
		contentPane.add(toTextField);
		toTextField.setColumns(10);
		toTextField.setVisible(false);

		final JLabel toLabel = new JLabel("To:");
		toLabel.setBounds(116, 138, 21, 14);
		contentPane.add(toLabel);
		toLabel.setVisible(false);

		fromTextField = new JTextField();
		fromTextField.setToolTipText("Enter the begining of your range. Example: 6.");
		fromTextField.setColumns(10);
		fromTextField.setBounds(64, 135, 28, 20);
		contentPane.add(fromTextField);
		fromTextField.setVisible(false);

		final JButton startQuizButton = new JButton("Start the quiz!");
		startQuizButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == startQuizButton) {
					if (selectRangeRadioButton.isSelected() == true) {
						if (isInputCorrect(fromTextField, toTextField) == false
								&& allQuestionsRadioButton.isSelected() == false) {
							JOptionPane.showMessageDialog(null,
									"You must enter numbers for the question you want to be tested on in correct order (1 -10)");
						} else {
							EventQueue.invokeLater(new Runnable() {
								public void run() {
									try {
										OnlineTestingFrame frame = new OnlineTestingFrame(fromTextField.getText(),
												toTextField.getText());
										frame.setVisible(true);
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							});
							setVisible(false);
						}
					}
					if (allQuestionsRadioButton.isSelected() == true) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									OnlineTestingFrame frame = new OnlineTestingFrame();
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						setVisible(false);
					}
				}
			}
		});
		startQuizButton.setBounds(316, 192, 135, 23);
		startQuizButton.setEnabled(false);
		contentPane.add(startQuizButton);

		contentPane.add(allQuestionsRadioButton);

		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(
				new ImageIcon(SelectScreenWindow.class.getResource("/designpatternquiz/pink-romantic-background.jpg")));
		backgroundLabel.setBounds(0, 0, 488, 241);
		contentPane.add(backgroundLabel);
		
		
		allQuestionsRadioButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (allQuestionsRadioButton.isSelected() == true) {
					startQuizButton.setEnabled(true);
				}
			}
		});

		selectRangeRadioButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (selectRangeRadioButton.isSelected() == true) {
					fromTextField.setVisible(true);
					toTextField.setVisible(true);
					fromLabel.setVisible(true);
					toLabel.setVisible(true);
					startQuizButton.setEnabled(true);
				} else {
					fromTextField.setVisible(false);
					toTextField.setVisible(false);
					fromLabel.setVisible(false);
					toLabel.setVisible(false);
				}
			}
		});
	}

	// better implementation: create an Exam object and a getNumberOfQuestions
	// method, instead of writing 10
	public boolean isInputCorrect(JTextField from, JTextField to) {
		// convert to textfield objects to ints

		int IntFrom;
		int IntTo;

		// check if integers were entered
		try {
			Integer.parseInt(from.getText());
			IntFrom = Integer.parseInt(from.getText());
		} catch (Exception e) {
			return false;
		}

		try {
			Integer.parseInt(to.getText());
			IntTo = Integer.parseInt(to.getText());
		} catch (Exception e) {
			return false;
		}

		// check if empty text is entered (nothing is entered)
		if (from.getText().equals("") || to.getText().equals("")) {
			return false;
		} else if (IntFrom > IntTo || IntFrom < 1 || IntFrom > 10 || IntTo < 1 || IntTo > 10) {
			return false;
		} else {
			return true;
		}
	}

}
