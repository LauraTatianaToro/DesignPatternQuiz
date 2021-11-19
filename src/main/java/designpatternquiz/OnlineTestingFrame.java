package designpatternquiz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class OnlineTestingFrame extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup answersButtonGroup = new ButtonGroup();

	// our variables- count: how many answers the user gets right,
	// current is the current question
	// radiobutton array is an array of the the answer buttons
	int count = 0, current = 0;
	JRadioButton[] jb = new JRadioButton[4];
	static Exam exam;
	static int randomNumberForUnorderedAnswer01; // right answer

	private JRadioButton answer1RadioButton;
	private JRadioButton answer2RadioButton;
	private JRadioButton answer3RadioButton;
	private JRadioButton answer4RadioButton;
	private JLabel questionLabel;
	private JLabel questionNumberLabel;
	private JLabel lblNewLabel;

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
		setComponents();

		jb[0] = answer1RadioButton;
		jb[1] = answer2RadioButton;
		jb[2] = answer3RadioButton;
		jb[3] = answer4RadioButton;
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(OnlineTestingFrame.class.getResource("/designpatternquiz/pink-romantic-background.jpg")));
		lblNewLabel.setBounds(0, 0, 665, 293);
		contentPane.add(lblNewLabel);

		// make an exam with every question
		exam = new Exam(1, 10);
		set(exam);
	}

	public OnlineTestingFrame(String from, String to) {
		setComponents();
		jb[0] = answer1RadioButton;
		jb[1] = answer2RadioButton;
		jb[2] = answer3RadioButton;
		jb[3] = answer4RadioButton;

		// make an exam with every question
		exam = new Exam(Integer.parseInt(from), Integer.parseInt(to));
		set(exam);
	}

	public void setComponents() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				OnlineTestingFrame.class.getResource("/designpatternquiz/512px-Eo_circle_pink_checkmark.svg.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		questionNumberLabel = new JLabel("Question Number:");
		questionNumberLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		questionNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionNumberLabel.setBounds(84, 11, 478, 33);
		contentPane.add(questionNumberLabel);

		answer1RadioButton = new JRadioButton("Answer 1");
		answer1RadioButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		answersButtonGroup.add(answer1RadioButton);
		answer1RadioButton.setBounds(10, 74, 645, 38);
		contentPane.add(answer1RadioButton);

		answer2RadioButton = new JRadioButton("Answer 2");
		answer2RadioButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		answersButtonGroup.add(answer2RadioButton);
		answer2RadioButton.setBounds(10, 115, 649, 34);
		contentPane.add(answer2RadioButton);

		answer3RadioButton = new JRadioButton("Answer 3");
		answer3RadioButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		answersButtonGroup.add(answer3RadioButton);
		answer3RadioButton.setBounds(10, 152, 649, 40);
		contentPane.add(answer3RadioButton);

		answer4RadioButton = new JRadioButton("Answer 4");
		answer4RadioButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		answersButtonGroup.add(answer4RadioButton);
		answer4RadioButton.setBounds(10, 195, 645, 38);
		contentPane.add(answer4RadioButton);

		questionLabel = new JLabel("New label");
		questionLabel.setBounds(10, 53, 645, 14);
		contentPane.add(questionLabel);
		
		answer1RadioButton.setOpaque(false);
		answer2RadioButton.setOpaque(false);
		answer3RadioButton.setOpaque(false);
		answer4RadioButton.setOpaque(false);

		

		final JButton viewResultsButton = new JButton("View Results");
		viewResultsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == viewResultsButton) {
					if (check(exam)) {
						count = count + 1;
					}
					// why increment the count and current if this is the last question?
					current++;

					int totalQuestions = exam.getQuestionsForExam().length;
					int correctAnswers = count;
					double correctAnswersDouble = correctAnswers;
					int incorrectAnswers = totalQuestions - count;
					double percentageGrade = (correctAnswersDouble / totalQuestions * 100.00);
					int percentageGradeInt = (int) percentageGrade;
					char letterGrade = calculateLetterGrade(percentageGradeInt);

					String message = "Your final score: " + letterGrade;

					JOptionPane.showMessageDialog(null,
							message + "\nTotal Questions: " + totalQuestions + "\nCorrect Answers: " + correctAnswers
									+ "\nWrong Answers " + incorrectAnswers + "\nGrade: " + percentageGradeInt + " %");

					System.exit(0);
				}
			}
		});
		viewResultsButton.setBounds(546, 259, 109, 23);
		viewResultsButton.setEnabled(false);
		contentPane.add(viewResultsButton);

		final JButton nextQuestionButton = new JButton("Next Question");
		nextQuestionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == nextQuestionButton) {
					if (check(exam)) {
						count = count + 1;
					}
					current++;
					set(exam);
					if (current == exam.getQuestionsForExam().length - 1) {
						nextQuestionButton.setEnabled(false);
						viewResultsButton.setEnabled(true);
					}
				}
			}
		});
		nextQuestionButton.setBounds(225, 259, 177, 23);
		contentPane.add(nextQuestionButton);
	}

	// randomize the order of answers
	void set(Exam exam) {

		final int NUMBER_OF_ANSWERS = 4;
		int randomNumberForUnorderedAnswer02;
		int randomNumberForUnorderedAnswer03;
		int randomNumberForUnorderedAnswer04;

		Question[] questionsForExam = exam.getQuestionsForExam();

		// when next button is hit, currernt incremenets
		questionLabel.setText(questionsForExam[current].getQuestion());
		// plus one bcause current is the index of the array
		questionNumberLabel.setText("Question Number " + (current + 1) + " of " + questionsForExam.length + ":");

		// the correct answer
		randomNumberForUnorderedAnswer01 = randomNumberRange(0, NUMBER_OF_ANSWERS - 1);
		jb[randomNumberForUnorderedAnswer01].setText(questionsForExam[current].getRightAnswer());

		randomNumberForUnorderedAnswer02 = randomNumberWithException(0, NUMBER_OF_ANSWERS - 1,
				randomNumberForUnorderedAnswer01);
		jb[randomNumberForUnorderedAnswer02]
				.setText(questionsForExam[current].getWrongAnswers()[randomNumberForUnorderedAnswer02]);

		randomNumberForUnorderedAnswer03 = randomNumberWith2Exception(0, NUMBER_OF_ANSWERS - 1,
				randomNumberForUnorderedAnswer01, randomNumberForUnorderedAnswer02);
		jb[randomNumberForUnorderedAnswer03]
				.setText(questionsForExam[current].getWrongAnswers()[randomNumberForUnorderedAnswer03]);

		randomNumberForUnorderedAnswer04 = randomNumberWith3Exception(0, NUMBER_OF_ANSWERS - 1,
				randomNumberForUnorderedAnswer01, randomNumberForUnorderedAnswer02, randomNumberForUnorderedAnswer03);
		jb[randomNumberForUnorderedAnswer04]
				.setText(questionsForExam[current].getWrongAnswers()[randomNumberForUnorderedAnswer04]);

		answersButtonGroup.clearSelection();

	}

	// check if the answer is correct
	boolean check(Exam exam) {
		for (int i = 0; i < exam.getQuestionsForExam().length; i++) {
			if (i == current) {
				return jb[randomNumberForUnorderedAnswer01].isSelected(); // check if correct answer is selected
			}
		}
		return false;
	}

	// method for grades - return a letter grade
	public char calculateLetterGrade(double grade) {
		if (grade >= 90) {
			return 'A';
		} else if (grade >= 80) {
			return 'B';
		} else if (grade >= 70) {
			return 'C';
		} else if (grade >= 60) {
			return 'D';
		} else {
			return 'F';
		}
	}

	// methods for random numebers
	public static int randomNumberRange(int min, int max) {
		Random rand = new Random();
		int randomNum;
		randomNum = rand.nextInt(max - min) + 1;
		return randomNum;
	}

	// generate a number that is not the exception, ensures all questions are random
	public static int randomNumberWithException(int min, int max, int exception) {
		Random rand = new Random();
		int randomNum;

		do {
			randomNum = rand.nextInt((max - min) + 1) + min;
		} while (randomNum == exception);

		return randomNum;
	}

	public static int randomNumberWith2Exception(int min, int max, int exception1, int exception2) {
		Random rand = new Random();
		int randomNum;

		do {
			randomNum = rand.nextInt((max - min) + 1) + min;
		} while (randomNum == exception1 || randomNum == exception2);

		return randomNum;
	}

	public static int randomNumberWith3Exception(int min, int max, int exception1, int exception2, int exception3) {
		Random rand = new Random();
		int randomNum;

		do {
			randomNum = rand.nextInt((max - min) + 1) + min;
		} while (randomNum == exception1 || randomNum == exception2 || randomNum == exception3);

		return randomNum;
	}

}
