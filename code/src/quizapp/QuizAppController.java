package quizapp;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class QuizAppController implements Initializable {

    @FXML
    private Button answer1;
    @FXML
    private Button answer2;
    @FXML
    private Button answer3;
    @FXML
    private Text questionNumber;
    @FXML
    private Text questionBody;
    @FXML
    private Text youWin;
    @FXML
    private Text youLose;

    //level 1 questions
    private Question ques1lvl1 = new Question("JAVA est un langage", "Compilé", "Interprété", "Compilé et interpreté", "Compilé");
    private Question ques2lvl1 = new Question("Toutes les classes héritent de la classe", "Main", "Object", "AWT", "Main");
    private Question ques3lvl1 = new Question("Par convention une classe ", "est en minuscule", "commence par une majuscule", "est en majuscules", "commence par une majuscule");
    private Question ques4lvl1 = new Question("Est-ce que on peut avoir plusieurs constructeurs pour la même classe", "oui", "non", "oui");
    private Question ques5lvl1 = new Question("Dans la ligne \"public class A implements B\", B est :", "Interface", "Classe parent", "Package", "Interface");
    //level2 questions
    private Question ques1lvl2 = new Question("Après la compilation, un programme écrit en\n"
            + "JAVA, il se transforme en programme en bytecode\n"
            + "Quelle est l’extension du programme en\n"
            + "bytecode ?", "aucun des choix", "Bonjour Nous sommes en 2018 !", "Nous sommes en 2018 !", "aucun des choix");
    private Question ques2lvl2 = new Question("Class Test{\n"
            + "Public Test () {\n"
            + "System.out.println(”Bonjour”);}\n"
            + "public Test (int i) {\n"
            + "this();\n"
            + "System.out.println(”Nous sommes en ”+i+ ” !”);};\n"
            + "}\n"
            + "qu’affichera l’instruction suivante?\n"
            + "Test t1=new Test (2018);", "Main", "Object", "AWT", "Main");
    private Question ques3lvl2 = new Question("Voici un constructeur de la classe Employee, y-at'il un problème ?\n"
            + "Public void Employe(String n){\n"
            + "Nom=n;}", "vrai", "faux", "faux");
    private Question ques4lvl2 = new Question("Pour spécifier que la variable ne pourra plus être\n"
            + "modifiée et doit être initialisée dès sa déclaration,\n"
            + "on la déclare comme une constante avec le mot\n"
            + "réservé", "aucun des choix", "final", "const", "const");
    private Question ques5lvl2 = new Question("Dans une classe, on accède à ses variables grâce au\n"
            + "mot clé", "aucun des choix", "this", "super", "Interface");
    //level3 questions
    private Question ques1lvl3 = new Question("calculerSalaire(int)\n"
            + "calculerSalaire(int, double)\n"
            + "La méthode calculerSalaire est :", "aucun des choix", "surchargée", "redéfinie", "surchargée");
    private Question ques2lvl3 = new Question("Une classe qui contient au moins une méthode\n"
            + "abstraite doit être déclarée abstraite.", "vrai", "faux", "vrai");
    private Question ques3lvl3 = new Question("Est-ce qu’une classe peut implémenter plusieurs\n"
            + "interfaces ?", "vrai", "faux", "vrai");
    private Question ques4lvl3 = new Question("La déclaration d'une méthode suivante :\n"
            + "public void traitement() throws IOException\n"
            + "précise que la méthode propage une exception\n"
            + "contrôlée", "vrai", "faux", "vrai");
    private Question ques5lvl3 = new Question("class Test{\n"
            + "public static void main (String[] args) {\n"
            + "try {\n"
            + "int a =10;\n"
            + "System.out.println (\"a = \" + a );\n"
            + "int b = 0 / a;\n"
            + "System.out.println (\"b = \" + b);\n"
            + "}\n"
            + "catch(ArithmeticException e)\n"
            + "{System.out.println (\"diviser par 0!\"); }\n"
            + "finally\n"
            + "{System.out.p", "aucun des choix", "a=10 b=0 Je suis à l’intérieur de finally", "a=10 b=0 diviser par 0! je suis à l’intérieur de finally", "a=10 b=0 Je suis à l’intérieur de finally");

    private Level level1 = new Level();
    private Level level2 = new Level();
    private Level level3 = new Level();

    private Level level;

    private int globalCounter = 0;
    private int counter;

    private int lvl1counter = 0;
    private int lvl2counter = 0;
    private int lvl3counter = 0;

    private int secondsRemaining = 300;

    Timer quizTimer = new Timer();

    TimerTask task = new TimerTask() {
        public void run() {
                secondsRemaining--;
                timer.setText(""+ secondsRemaining +"");
                
                if (secondsRemaining <= 0) {
                quizTimer.cancel();
                quizTimer.purge(); 
                youLose.setVisible(true);
                answer1.setVisible(false);
                answer2.setVisible(false);
                answer3.setVisible(false);
                questionNumber.setVisible(false);
                questionBody.setVisible(false);
                return;
                
            }
        }
    };
    @FXML
    private Text timer;
    @FXML
    private Text timerTitle;

    public void start() {
        quizTimer.scheduleAtFixedRate(task, 1000, 1000);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start();
        youLose.setVisible(false);
        youWin.setVisible(false);

        //adding level1 questions
        level1.addQuestion(ques1lvl1);
        level1.addQuestion(ques2lvl1);
        level1.addQuestion(ques3lvl1);
        level1.addQuestion(ques4lvl1);
        level1.addQuestion(ques5lvl1);
        //adding level2 questions
        level2.addQuestion(ques1lvl2);
        level2.addQuestion(ques2lvl2);
        level2.addQuestion(ques3lvl2);
        level2.addQuestion(ques4lvl2);
        level2.addQuestion(ques5lvl2);
        //adding level3 questions
        level3.addQuestion(ques1lvl3);
        level3.addQuestion(ques2lvl3);
        level3.addQuestion(ques3lvl3);
        level3.addQuestion(ques4lvl3);
        level3.addQuestion(ques5lvl3);

        questionBody.setText(level1.getQuestions().get(0).getQuestionBody());
        answer1.setText(level1.getQuestions().get(0).getAnswer1());
        answer2.setText(level1.getQuestions().get(0).getAnswer2());
        answer3.setText(level1.getQuestions().get(0).getAnswer3());
    }

    @FXML
    private void answerController(ActionEvent event) {
        if (globalCounter == 0) {
            level = level1;

        } else if (globalCounter == 5) {
            level = level2;

        } else if (globalCounter == 10) {
            level = level3;
        }

        answer3.setVisible(true);

        String text = ((Button) event.getTarget()).getText();
        if (text.equals(level.getQuestions().get(counter).getCorrectAnswer())) {
            level.setScore(level.getScore() + 20);
            System.out.println("Correct");
        } else {
            System.out.println("Wrong");
        }

        counter++;
        globalCounter++;
        if (globalCounter == 0) {
            level = level1;
            counter = 0;
        } else if (globalCounter == 5) {

            if (level1.getScore() >= 40) {
                level = level2;
                counter = 0;
            } else {
                youLose.setVisible(true);
                answer1.setVisible(false);
                answer2.setVisible(false);
                answer3.setVisible(false);
                questionNumber.setVisible(false);
                questionBody.setVisible(false);
                timer.setVisible(false);
                timerTitle.setVisible(false);
            }

        } else if (globalCounter == 10) {
            if (level2.getScore() >= 60) {
                level = level3;
                counter = 0;
            } else {
                youLose.setVisible(true);
                answer1.setVisible(false);
                answer2.setVisible(false);
                answer3.setVisible(false);
                questionNumber.setVisible(false);
                questionBody.setVisible(false);
                timer.setVisible(false);
                timerTitle.setVisible(false);
            }

        } else if (globalCounter == 15) {
            if (level3.getScore() >= 80) {
                youWin.setVisible(false);
                answer1.setVisible(false);
                answer2.setVisible(false);
                answer3.setVisible(false);
                questionNumber.setVisible(false);
                questionBody.setVisible(false);
                timer.setVisible(false);
                timerTitle.setVisible(false);
            } else {
                youLose.setVisible(true);
                answer1.setVisible(false);
                answer2.setVisible(false);
                answer3.setVisible(false);
                questionNumber.setVisible(false);
                questionBody.setVisible(false);
                timer.setVisible(false);
                timerTitle.setVisible(false);
            }
        }

        if (counter < 5) {
            questionNumber.setText("Question " + (counter + 1));
            questionBody.setText(level.getQuestions().get(counter).getQuestionBody());
            answer1.setText(level.getQuestions().get(counter).getAnswer1());
            answer2.setText(level.getQuestions().get(counter).getAnswer2());
            answer3.setText(level.getQuestions().get(counter).getAnswer3());
            if (level.getQuestions().get(counter).getAnswer3() == null) {
                answer3.setVisible(false);
            }
        }

        System.out.println(counter);
        System.out.println(level.getScore());

    }

}
