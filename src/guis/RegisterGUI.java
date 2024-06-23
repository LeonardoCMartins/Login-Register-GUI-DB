package guis;

import db.DB;
import db.MyJDBC;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterGUI extends JFrame {

    public static final Color PRIMARY_COLOR = Color.decode("#3D4C41");
    public static final Color SECONDARY_COLOR = Color.decode("#999999");
    public static final Color TEXT_COLOR = Color.decode("#E6E6E6");

    Border border = BorderFactory.createLineBorder(Color.WHITE, 2);

    JTextField username;
    JPasswordField passsword;
    JPasswordField repasssword;

    public RegisterGUI(){
        super("Register");
        setSize(400,470);
        getContentPane().setBackground(PRIMARY_COLOR);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel label = new JLabel("REGISTER");
        label.setBounds(130,0, 150,100);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setForeground(TEXT_COLOR);
        add(label);

        JLabel labelUserName = new JLabel("Username");
        labelUserName.setBounds(10, 90, 60, 30);
        labelUserName.setFont(new Font("Arial", Font.PLAIN, 12));
        labelUserName.setForeground(TEXT_COLOR);
        add(labelUserName);

        username = new JTextField();
        username.setBounds(10, 120, 365, 40);
        username.setFont(new Font("Arial", Font.PLAIN, 18));
        username.setBackground(SECONDARY_COLOR);
        username.setForeground(TEXT_COLOR);
        username.setEditable(true);
        username.setBorder(border);
        add(username);

        JLabel labelPassword = new JLabel("Password");
        labelPassword.setBounds(10, 160, 60, 30);
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        labelPassword.setForeground(TEXT_COLOR);
        add(labelPassword);

        passsword = new JPasswordField();
        passsword.setBounds(10, 190, 365, 40);
        passsword.setFont(new Font("Arial", Font.PLAIN, 22));
        passsword.setBackground(SECONDARY_COLOR);
        passsword.setForeground(TEXT_COLOR);
        passsword.setEditable(true);
        passsword.setBorder(border);
        add(passsword);

        JLabel labelRePassword = new JLabel("Re-enter Password");
        labelRePassword.setBounds(10, 230, 150, 30);
        labelRePassword.setFont(new Font("Arial", Font.PLAIN, 12));
        labelRePassword.setForeground(TEXT_COLOR);
        add(labelRePassword);

        repasssword = new JPasswordField();
        repasssword.setBounds(10, 260, 365, 40);
        repasssword.setFont(new Font("Arial", Font.PLAIN, 22));
        repasssword.setBackground(SECONDARY_COLOR);
        repasssword.setForeground(TEXT_COLOR);
        repasssword.setEditable(true);
        repasssword.setBorder(border);
        add(repasssword);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(110, 340, 180, 33);
        registerButton.setFont(new Font("Arial", Font.PLAIN, 15));
        registerButton.setBackground(TEXT_COLOR);
        registerButton.setForeground(SECONDARY_COLOR);
        registerButton.addActionListener(this::register);
        add(registerButton);

        JLabel login = new JLabel("Já é um usuário? Entre aqui");
        login.setBounds(130,380,200,30);
        login.setFont(new Font("Arial", Font.PLAIN, 12));
        login.setForeground(TEXT_COLOR);
        login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        login.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                RegisterGUI.this.dispose();
                new LoginGUI().setVisible(true);
            }
        });
        add(login);




        setVisible(true);
    }

    private void register(ActionEvent actionEvent) {
        new MyJDBC(DB.getConnection());
        if(MyJDBC.registerUser(username.getText(),passsword.getText(),repasssword.getText()) == true){
            JOptionPane.showMessageDialog(null,"Usuário Registrado!","Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,"Ocorreu um erro!", "Erro", JOptionPane.ERROR_MESSAGE);
        }


    }
}
