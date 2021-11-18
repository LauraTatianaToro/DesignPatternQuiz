package designpatternquiz;

import java.util.Random;

public class Exam {
	/*
	 * Two options: get tested on all questions or
	 * select from a contiguous subset of certain question to answer
	 */
	private int fromQuestion;
	private int toQuestion;
	private int NUMBER_OF_QUESTIONS_FOR_EXAM = 10;
	private Question[] allQuestions = new Question[NUMBER_OF_QUESTIONS_FOR_EXAM];
	//if the user selects all questions, they all copy into questionsSelected, or if they select
	//from for example question 3 to 6, then only 3-6 will populate questionSelected array.
	private Question[] questionsSelected;
	
	public Exam(int fromQuestion, int toQuestion) {
		this.fromQuestion = fromQuestion;
		this.toQuestion = toQuestion;
		
		//Questions:
		allQuestions[0] = new Question("What is the Composite Pattern?",  "Answer", new String[]{"Wrong1", "Wrong2", "Wrong3"} );
		allQuestions[1] = new Question("What is the Proxy Pattern?",  "Answer", new String[]{"Wrong1", "Wrong2", "Wrong3"} );
		allQuestions[2] = new Question("What is the Decrator Pattern?",  "Answer", new String[]{"Wrong1", "Wrong2", "Wrong3"} );
		allQuestions[3] = new Question("Select the statement that is true.",  
										"Design Patterns describe problems that occur over and over and the core of the solution to that problem.",
										new String[]{"Design Patterns aim to develop the structure and organization of a softwares main components.",
										"Design Patterns define rules for construction, reuse, analysis, evolution and management of software.", 
										"Design patterns allow programmers to install dependencies quicker."} );
		allQuestions[4] = new Question("Which is NOT one of the 3 design patterns categories  specified by the Gang of Four?",
										"Interactive", new String[]{"Creational", "Structural", "Behavioural"} );
		allQuestions[5] = new Question("The prototype pattern is:",  "A Creational Design Pattern", new String[]{"An Interactive Design Pattern", "A Structural Design Pattern", "A Beahvioural Design Pattern"} );
		allQuestions[6] = new Question("What is a Singleton?",  "Answer", new String[]{"Wrong1", "Wrong2", "Wrong3"} );
		allQuestions[7] = new Question("What is the difference between Abstract Factory and Factory Pattern?",  "Answer", new String[]{"Wrong1", "Wrong2", "Wrong3"} );
		allQuestions[8] = new Question("Which statement is true about Structural Design Patterns?",
										"They focus on the instantiation of objects and allow decoupling a system from the way its objects are instantiated."
										, new String[]{"They focus on the composition of classes and objects to design larger structures.", 
										"They focus on the communication between objects and on the assignment of responsibilities between objects", 
										"They focus on hiding data from other objects, to maintain abstraction."} );
		allQuestions[9] = new Question("The Iteratoor Design Pattern is:",  "Behavioural", new String[]{"Structural", "None of these choices", "Interactive"} );

	}
	
	public Question[] getQuestionsForExam() {
		return questionsSelected;
	}
	
	/**
	 * We want to create a random number that is not bigger than the array.
	 * If we have 10 questions, the max should be 10. We want to generate a random
	 * number that is within the amount of questions we have.
	 * @param min
	 * @param max
	 * @return a random number in the range of min and max.
	 */
	public static int randomNumberRange(int min, int max) {
		Random rand = new Random();
		int randomNum;
		randomNum = rand.nextInt((max-min) + 1);
		return randomNum;
	}
	
	//we swap elements in the array to prevent the questions from always showing up
	//in the same order. We swap elements in the questionsSelected array
	//after questions have been selected
	/**
	 * All the questions in questionsSelected array will be swapped around randomly.
	 * @param array
	 */
	public void swapArrayElementsRandomly(Question[] questionsSelected) {
		for (int i =0; i <questionsSelected.length; i++) {
			//generate first random number every iteration
			int randomNum1 = randomNumberRange(0, (questionsSelected.length -1 ));
			//Second random # every iteration
			Random random = new Random();
			int randomNum2 = random.nextInt(questionsSelected.length);
			
			//swap the questions randomly
			Question temp = questionsSelected[randomNum1];
			questionsSelected[randomNum1] = questionsSelected[randomNum2];
			questionsSelected[randomNum2] = temp;
		}
	}
	
	/*add questions from allQuestions to questionsSelected array(the ones the user selected)*/
	public Question[] choseExamQuestions() {
		//initialize Question array with size =to the amount of questions selected
		questionsSelected = new Question[toQuestion - fromQuestion +1];
		
		//copy over all the questions in the range into questionsSelected
		for(int i = (fromQuestion -1), j = 0; i< toQuestion && j < questionsSelected.length; i++, j++) {
			questionsSelected[j] = new Question(allQuestions[i].getQuestion(), allQuestions[i].getRightAnswer(), allQuestions[i].getWrongAnswers());	
		}
		//call our swap method to randomize the questions
		swapArrayElementsRandomly(questionsSelected);
		
		return questionsSelected;	
	}
	
	
}
