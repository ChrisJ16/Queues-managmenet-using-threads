package GUI;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class View extends JFrame{
    private JPanel mainPanel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JPanel panel5 = new JPanel();
    private JPanel panel6 = new JPanel();
    private JPanel panel7 = new JPanel();

    private JButton btn1 = new JButton("Start");
    private JButton btn2 = new JButton("Exemplu 1");
    private JButton btn3 = new JButton("Exemplu 2");
    private JButton btn4 = new JButton("Exemplu 3");

    private JLabel label1 = new JLabel("N clients:");
    private JLabel label2 = new JLabel("Q queues:");
    private JLabel label3 = new JLabel("Simulation time:");
    private JLabel label4 = new JLabel("Arrival time(min-max):");
    private JLabel label5 = new JLabel("Service time(min-max):");

    private JTextField text1 = new JTextField(10);
    private JTextField text2 = new JTextField(10);
    private JTextField text3 = new JTextField(10);
    private JTextField text4 = new JTextField(10);
    private JTextField text5 = new JTextField(10);

    private JTextArea area1 = new JTextArea("Aici va avea loc simuarea",10,30);
    private JScrollPane scroll = new JScrollPane(area1);

    private Controller controller = new Controller(this);

    public View(String name)
    {
        super(name);
        this.initGUI();
    }

    public void initGUI(){
        this.setSize(500, 400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.setContentPane(this.mainPanel);
        this.initComoponents();
        this.setCommands();

    }

    public void initComoponents()
    {
        panel1.setLayout(new FlowLayout());
        panel1.add(label1);
        panel1.add(text1);

        panel2.setLayout(new FlowLayout());
        panel2.add(label2);
        panel2.add(text2);

        panel3.setLayout(new FlowLayout());
        panel3.add(label3);
        panel3.add(text3);

        panel4.setLayout(new FlowLayout());
        panel4.add(label4);
        panel4.add(text4);

        panel5.setLayout(new FlowLayout());
        panel5.add(label5);
        panel5.add(text5);

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel6.add(scroll);

        panel7.setLayout(new FlowLayout());
        panel7.add(btn1);
        panel7.add(btn2);
        panel7.add(btn3);
        panel7.add(btn4);

        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);
        mainPanel.add(panel4);
        mainPanel.add(panel5);
        mainPanel.add(panel7);
        mainPanel.add(panel6);
    }

    public void setCommands(){
        btn1.setActionCommand("Start");
        btn1.addActionListener(this.controller);

        btn2.setActionCommand("EX1");
        btn2.addActionListener(this.controller);

        btn3.setActionCommand("EX2");
        btn3.addActionListener(this.controller);

        btn4.setActionCommand("EX3");
        btn4.addActionListener(this.controller);
    }

    public JTextField getNCLients()
    {
        return text1;
    }

    public JTextField getQThreads()
    {
        return text2;
    }

    public JTextField getSimTime()
    {
        return text3;
    }

    public JTextField getArrivalTime()
    {
        return text4;
    }

    public JTextField getServiceTime()
    {
        return text5;
    }

    public void clearAreaText()
    {
        area1.setText("");
    }

    public void setAreaText(String s){
        area1.setText(s);
    }
}
