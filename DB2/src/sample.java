import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class sample {

    private static int currentStep = 1;
    private static JFrame frame;

    public static void main(String[] args) {
    	frame = new JFrame("Find the Most Frequent");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("Find the Most Frequent");

        JButton startButton1 = new JButton("Start Game_number version");
        startButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();//Close
                startGame(1);
            }
        });
        
        JButton startButton2 = new JButton("Start Game_picture version");
        startButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                startGame(2);
            }
        });
        
        panel.add(titleLabel);
        panel.add(startButton1);
        panel.add(startButton2);

        frame.getContentPane().add(panel);
        frame.setSize(300, 150);
        frame.setVisible(true);
    }
    
    private static void startGame(int k) {
        boolean gameContinues = true;

        while (gameContinues) {
        	int userGuess = 0;
        	if(k==1) userGuess = getUserGuess(currentStep);
        	else if (k==2) userGuess = getUserGuess_pic(currentStep);
        	
            if (userGuess == 1) {
                currentStep++;
            } else {
                gameContinues = false;
                JOptionPane.showMessageDialog(null, "Game Over. Steps completed: " + (currentStep - 1));
            }
        }
    }

    private static int randomNumber() {
        Random random = new Random();
        return random.nextInt(3) + 1;//range 1 or 2 or 3
    }

    private static int getUserGuess(int step) {
        int userGuess = 0;
        int cor = 0;

        int a=0;//mean num 1
        int b=0;
        int c=0;
        
        for(int i=0;i<2*step-1;i++) {
        	int k = randomNumber();
            JOptionPane.showMessageDialog(null, "                                     "
        	+k,"",JOptionPane.PLAIN_MESSAGE);
            
            if(k == 1)a++;
            else if(k==2)b++;
            else if(k==3)c++;
            cor = ((a > b && a > c) ? 1 : b > c ? 2 : 3);
            
            if(i==2*step-2) {
            	if((cor==1 && (a==b||a==c)))i--;
            	else if((cor==2 && (b==c||b==a)))i--;
            	else if((cor==3 && (c==a||c==b)))i--;
            
            }
        }        
        //System.out.println(a+ " " + b + " "+ c + " "+ cor);
        try {
            String userInput = JOptionPane.showInputDialog(null, "Step " + step + ": Guess the most frequent number (1 or 2 or 3)");
            if (userInput != null) {
                userGuess = Integer.parseInt(userInput);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            getUserGuess(step);
        }
        
        if(userGuess == cor)return 1;
        else return 0;

    }
    
    private static int getUserGuess_pic(int step) {
        String userGuess=null;
        int cor = 0;

        int a=0;//means rabbit
        int b=0;//means dog
        int c=0;//means cat
        
        for(int i=0;i<2*step-1;i++) {
        	int k = randomNumber();
            ImageIcon icon=null;
            
            if(k == 1) {
                icon = new ImageIcon("C:\\Users\\brant\\Desktop\\2-2\\자바\\KakaoTalk_20231211_014256782.png");
            	a++;
            }
            else if(k==2) {
                icon = new ImageIcon("C:\\Users\\brant\\Desktop\\2-2\\자바\\KakaoTalk_20231211_014420832.png");
            	b++;
            }
            else if(k==3) {
                icon = new ImageIcon("C:\\Users\\brant\\Desktop\\2-2\\자바\\KakaoTalk_20231211_015639871.png");
            	c++;
            }
            
            cor = ((a > b && a > c) ? 1 : b > c ? 2 : 3);
            
            if(i==2*step-2) {
            	if((cor==1 && (a==b||a==c)))i--;
            	else if((cor==2 && (b==c||b==a)))i--;
            	else if((cor==3 && (c==a||c==b)))i--;
            
            }
            JLabel label = new JLabel(icon);
            JOptionPane.showMessageDialog(null,label,"",JOptionPane.PLAIN_MESSAGE);
        }
        
        //System.out.println(a+ " " + b + " "+ c + " "+ cor);

        String userInput = JOptionPane.showInputDialog(null, "Step " + step + ": Guess the most frequent number (1 or 2 or 3)");

        if (!userInput.equals("")) {
            userGuess = userInput;
        }
        else {            
           	userGuess="default";
        }
        if(userGuess.equals("default")) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter an animal name.");
            getUserGuess_pic(step);
        }

        if(userGuess.equals("rabbit") && cor == 1)return 1;
        else if(userGuess.equals("dog") && cor == 2)return 1;
        else if(userGuess.equals("cat") && cor == 3)return 1;
        else return 0;

    }
}
