package designpatternquiz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OnlineTestingFrame extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup answersButtonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the frame.
	 */
	public OnlineTestingFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(OnlineTestingFrame.class.getResource("/designpatternquiz/512px-Eo_circle_pink_checkmark.svg.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 504, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel questionNumberLabel = new JLabel("Question Number:");
		questionNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		questionNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionNumberLabel.setBounds(10, 11, 478, 33);
		contentPane.add(questionNumberLabel);
		
		JRadioButton answer1RadioButton = new JRadioButton("Answer 1");
		answersButtonGroup.add(answer1RadioButton);
		answer1RadioButton.setBounds(20, 73, 109, 23);
		contentPane.add(answer1RadioButton);
		
		JRadioButton answer2RadioButton = new JRadioButton("Answer 2");
		answersButtonGroup.add(answer2RadioButton);
		answer2RadioButton.setBounds(20, 99, 109, 23);
		contentPane.add(answer2RadioButton);
		
		JRadioButton answer3RadioButton = new JRadioButton("Answer 3");
		answersButtonGroup.add(answer3RadioButton);
		answer3RadioButton.setBounds(20, 125, 109, 23);
		contentPane.add(answer3RadioButton);
		
		JRadioButton answer4RadioButton = new JRadioButton("Answer 4");
		answersButtonGroup.add(answer4RadioButton);
		answer4RadioButton.setBounds(20, 151, 109, 23);
		contentPane.add(answer4RadioButton);
		
		JLabel questionLabel = new JLabel("New label");
		questionLabel.setBounds(20, 44, 46, 14);
		contentPane.add(questionLabel);
		
		JButton nextQuestionButton = new JButton("Next Question");
		nextQuestionButton.setBounds(166, 181, 109, 23);
		contentPane.add(nextQuestionButton);
		
		JButton viewResultsButton = new JButton("View Results");
		viewResultsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		viewResultsButton.setBounds(285, 181, 109, 23);
		contentPane.add(viewResultsButton);
	}

}
