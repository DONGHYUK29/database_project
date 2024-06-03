import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Manager extends JFrame {

    private JTable table;
    private JScrollPane scrollPane;
    private JPanel panel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Manager databaseViewer = new Manager();
                databaseViewer.setVisible(true);
            }
        });
    }

    public Manager() {
        setTitle("Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null); // 프레임을 화면 중앙에 배치

        panel = new JPanel();

        JButton showTableButton = new JButton("전체 테이블 보기");
        showTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTable();
            }
        });

        JButton addMovieButton = new JButton("영화 추가");
        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 addmoviedata();
            	 }
        });

        JButton addScheduleButton = new JButton("상영일정 추가");
        addScheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	addscheduledata();
            	}
        });

        JButton addTheaterButton = new JButton("상영관 추가");
        addTheaterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	addtheaterdata();
            	}
        });

        JButton addSeatsButton = new JButton("좌석 추가");
        addSeatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	addseatsdata();
            	}
        });
        
        panel.add(showTableButton);
        panel.add(addMovieButton);
        panel.add(addScheduleButton);
        panel.add(addTheaterButton);
        panel.add(addSeatsButton);

        add(panel, BorderLayout.NORTH);

        // 테이블 초기화
        table = new JTable();
        scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void showTable() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=UTC", "root", "1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SHOW TABLES");

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Table Name");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1)});
            }

            table.setModel(model);

            rs.close();
            stmt.close();
            conn.close();

            JPanel buttonPanel = new JPanel();
            for (int i = 0; i < model.getRowCount(); i++) {
                String tablename = (String)model.getValueAt(i, 0);
                JButton tableButton = new JButton(tablename + " Table");
                tableButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        showTableMoviesData(tablename);
                    }
                });
                buttonPanel.add(tableButton);
            }

            add(buttonPanel, BorderLayout.SOUTH);
            revalidate();
            repaint();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void addmoviedata() {
    	
    }
    
    private void addscheduledata() {
    	
    }
    
	private void addtheaterdata() {
		
	}
    
    private void addseatsdata() {
    	
    }
    
    private void showTableMoviesData(String tablename) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1?serverTimezone=UTC", "root", "1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tablename);

            // Populate table model with data from ResultSet
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            DefaultTableModel model = new DefaultTableModel();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }

            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    rowData[i] = rs.getObject(i + 1);
                }
                model.addRow(rowData);
            }

            table.setModel(model);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
