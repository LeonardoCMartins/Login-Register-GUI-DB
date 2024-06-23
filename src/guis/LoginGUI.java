package guis;

import db.DB;
import db.MyJDBC;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGUI extends JFrame {

    JLabel label;
    JTextField username;
    JPasswordField passsword;
    JButton loginButton;

    public static final Color PRIMARY_COLOR = Color.decode("#3D4C41");
    public static final Color SECONDARY_COLOR = Color.decode("#999999");
    public static final Color TEXT_COLOR = Color.decode("#E6E6E6");

    Border border = BorderFactory.createLineBorder(Color.WHITE, 2);

    public LoginGUI(){
        super("Login");
        getContentPane().setBackground(PRIMARY_COLOR);
        setSize(400,470);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setForeground(TEXT_COLOR);
        setLocationRelativeTo(null);
        setLayout(null);

        label = new JLabel("LOGIN");
        label.setBounds(140,0, 150,100);
        label.setFont(new Font("Arial", Font.BOLD, 30));
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
        labelPassword.setBounds(10, 190, 60, 30);
        labelPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        labelPassword.setForeground(TEXT_COLOR);
        add(labelPassword);

        passsword = new JPasswordField();
        passsword.setBounds(10, 220, 365, 40);
        passsword.setFont(new Font("Arial", Font.PLAIN, 22));
        passsword.setBackground(SECONDARY_COLOR);
        passsword.setForeground(TEXT_COLOR);
        passsword.setEditable(true);
        passsword.setBorder(border);
        add(passsword);

        loginButton = new JButton("Login");
        loginButton.setBounds(110, 320, 180, 33);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 15));
        loginButton.setBackground(TEXT_COLOR);
        loginButton.setForeground(SECONDARY_COLOR);
        loginButton.addActionListener(this::login);
        add(loginButton);


        JLabel register = new JLabel("Não é um usuário? Registre-se aqui");
        register.setBounds(105,380,200,30);
        register.setFont(new Font("Arial", Font.PLAIN, 12));
        register.setForeground(TEXT_COLOR);
        register.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        register.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                LoginGUI.this.dispose();
                new RegisterGUI().setVisible(true);
            }
        });
        add(register);


        setVisible(true);



    }

    private void login(ActionEvent actionEvent) {
        new MyJDBC(DB.getConnection());

        if (MyJDBC.checkUser(username.getText())){

            if (MyJDBC.validateLogin(username.getText(),passsword.getText())){
                JOptionPane.showMessageDialog(null,"Login realizado com sucesso!","Mensagem",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"Dados inválidos!","Erro",JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null,"Usuário não existe!","Erro",JOptionPane.ERROR_MESSAGE);
        }

    }


}
