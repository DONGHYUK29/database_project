import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class test {
    private static JFrame frame;

    public static void main(String[] args) {
    	frame = new JFrame("Movie reservation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("Choose your account");

        JButton startButton1 = new JButton("root");
        startButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();//Close
                start(1);
            }
        });
        
        JButton startButton2 = new JButton("user");
        startButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                start(2);
            }
        });
        
        panel.add(titleLabel);
        panel.add(startButton1);
        panel.add(startButton2);

        frame.getContentPane().add(panel);
        frame.setSize(300, 100);
        // 프레임을 화면 중앙에 배치
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    private static void start(int k) {
        //private static JFrame frame;
        String root_id=null;
        String root_password=null;
        String user_id=null;
        String user_password=null;
        String InputID = JOptionPane.showInputDialog(null, "id " + ": input your ID");
        String InputPW = JOptionPane.showInputDialog(null, "password " + ": input your PASSWORD");

    	if(k==1) {
    		root_id = InputID;
    		root_password = InputPW;
    	}
    	else if(k==2) {
    		user_id = InputID;
    		user_password = InputPW;
    	}
        
        
    }
    
}
