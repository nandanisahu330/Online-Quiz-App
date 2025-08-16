import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {
	private List<Question> questions;
    private int score;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        initializeQuestions();
    }

    private void initializeQuestions() {
        questions.add(new Question(
            "What is the capital of France?",
            new String[]{"1. Paris", "2. London", "3. Berlin", "4. Madrid"},
            1
        ));
        questions.add(new Question(
            "Which planet is known as the Red Planet?",
            new String[]{"1. Jupiter", "2. Mars", "3. Venus", "4. Mercury"},
            2
        ));
        questions.add(new Question(
            "What is 2 + 2?",
            new String[]{"1. 3", "2. 4", "3. 5", "4. 6"},
            2
        ));
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Quiz App!\n");

        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);

            System.out.println("Question " + (i + 1) + ": " + currentQuestion.getQuestionText());
            for (String option : currentQuestion.getOptions()) {
                System.out.println(option);
            }

            int userAnswer = getValidAnswer(scanner);

            if (currentQuestion.isCorrect(userAnswer)) {
                score++;
                System.out.println("Correct!\n");
            } else {
                System.out.println("Incorrect. The correct answer was option " +
                    currentQuestion.getOptions()[currentQuestion.getOptions().length-1].charAt(0) + "\n");
            }
        }

        displayResults();
        scanner.close();
    }

    private int getValidAnswer(Scanner scanner) {
        while (true) {
            System.out.print("Enter your answer (1-4): ");
            try {
                int answer = Integer.parseInt(scanner.nextLine());
                if (answer >= 1 && answer <= 4) {
                    return answer;
                } else {
                    System.out.println("Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
            }
        }
    }

    private void displayResults() {
        System.out.println("Quiz Completed!");
        System.out.println("Your Score: " + score + "/" + questions.size());
        double percentage = (double) score / questions.size() * 100;
        System.out.printf("Percentage: %.2f%%\n", percentage);

        if (percentage == 100) {
            System.out.println("Excellent! Perfect score!");
        } else if (percentage >= 70) {
            System.out.println("Good job!");
        } else {
            System.out.println("Keep practicing!");
        }
    }


}
