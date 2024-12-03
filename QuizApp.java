import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApp {
    private JFrame frame;
    private JLabel questionLabel;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionsGroup;
    private JButton nextButton;
    private int currentQuestion = 0;
    private int score = 0;

    // Questions, options, and answers
    private String[][] questions = {
        {"What is the capital of France?", "Berlin", "Madrid", "Paris", "Lisbon", "Paris"},
        {"Which language is used to build Android apps?", "Python", "Java", "C++", "Ruby", "Java"},
        {"What is 9 + 10?", "18", "19", "20", "21", "19"},
        {"Which planet is known as the Red Planet?", "Earth", "Mars", "Venus", "Jupiter", "Mars"},
        {"Who wrote 'Hamlet'?", "Shakespeare", "Dickens", "Hemingway", "Austen", "Shakespeare"},
        {"What is the largest ocean on Earth?", "Atlantic", "Indian", "Arctic", "Pacific", "Pacific"},
        {"In what year did the Titanic sink?", "1900", "1912", "1920", "1930", "1912"},
        {"What is the chemical symbol for gold?", "Au", "Ag", "Pb", "Fe", "Au"},
        {"Who painted the Mona Lisa?", "Van Gogh", "Picasso", "Da Vinci", "Rembrandt", "Da Vinci"},
        {"What is the square root of 64?", "6", "7", "8", "9", "8"}
    };

    public QuizApp() {
        frame = new JFrame("Java Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);  // Increased the size a bit for more space
        frame.setLocationRelativeTo(null); // Centers the window on the screen

        // Set layout for frame
        frame.setLayout(new BorderLayout(10, 10));

        // Question label
        questionLabel = new JLabel("Question will appear here");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the text
        frame.add(questionLabel, BorderLayout.NORTH);

        // Options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(4, 1, 10, 10));

        option1 = new JRadioButton();
        option2 = new JRadioButton();
        option3 = new JRadioButton();
        option4 = new JRadioButton();

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        frame.add(optionsPanel, BorderLayout.CENTER);

        // Next button
        nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.PLAIN, 14));
        nextButton.setBackground(new Color(50, 150, 250));
        nextButton.setForeground(Color.WHITE);
        nextButton.setFocusPainted(false);
        nextButton.setBorderPainted(false);

        // Add hover effect on the button
        nextButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nextButton.setBackground(new Color(70, 170, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextButton.setBackground(new Color(50, 150, 250));
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processAnswer();
            }
        });

        frame.add(nextButton, BorderLayout.SOUTH);

        loadQuestion();
        frame.setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            String[] q = questions[currentQuestion];
            questionLabel.setText(q[0]);
            option1.setText(q[1]);
            option2.setText(q[2]);
            option3.setText(q[3]);
            option4.setText(q[4]);
            optionsGroup.clearSelection();
        } else {
            showResults();
        }
    }

    private void processAnswer() {
        String selectedOption = null;
        if (option1.isSelected()) selectedOption = option1.getText();
        if (option2.isSelected()) selectedOption = option2.getText();
        if (option3.isSelected()) selectedOption = option3.getText();
        if (option4.isSelected()) selectedOption = option4.getText();

        if (selectedOption != null) {
            String correctAnswer = questions[currentQuestion][5];
            if (selectedOption.equals(correctAnswer)) {
                score++;
            }
            currentQuestion++;
            loadQuestion();
        } else {
            JOptionPane.showMessageDialog(frame, "Please select an answer!");
        }
    }

    private void showResults() {
        JOptionPane.showMessageDialog(frame, "Quiz Over! Your score is: " + score + "/" + questions.length);
        frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizApp::new);
    }
}
