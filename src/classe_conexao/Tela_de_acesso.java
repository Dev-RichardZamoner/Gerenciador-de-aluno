package classe_conexao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JSlider;

@SuppressWarnings("serial")
public class Tela_de_acesso extends JFrame {

    private JPanel contentPane;
    private JTextField tfUsuario;
    private JPasswordField PFSenha;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tela_de_acesso frame = new Tela_de_acesso();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Tela_de_acesso() {
        setTitle("Gerenciador de Aluno");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 356, 334);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.BLACK);
        lblUsuario.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
        lblUsuario.setBounds(47, 64, 92, 34);
        contentPane.add(lblUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setForeground(Color.BLACK);
        lblSenha.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
        lblSenha.setBounds(47, 134, 77, 34);
        contentPane.add(lblSenha);

        tfUsuario = new JTextField();
        tfUsuario.setBounds(47, 99, 262, 22);
        contentPane.add(tfUsuario);
        tfUsuario.setColumns(10);

        PFSenha = new JPasswordField();
        PFSenha.setBounds(47, 181, 262, 22);
        contentPane.add(PFSenha);

     // ... [restante do código acima]

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String usuario = tfUsuario.getText();
                String senha = new String(PFSenha.getPassword());
                
                if(usuario.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira o usuário e a senha.");
                    return; // Impede a continuação do método se usuário ou senha estiverem vazios
                }
                
                try {
                    Connection con = Conexao.faz_conexao();
                    String sql = "SELECT * FROM dados WHERE usuario = ? AND senha = ?";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    stmt.setString(1, usuario);
                    stmt.setString(2, senha);
                    ResultSet rs = stmt.executeQuery();

                    if(rs.next()){
                        TelaPrincipal telaPrincipal = new TelaPrincipal();
                        telaPrincipal.setVisible(true);
                        setVisible(false); // Esconde a Tela_de_acesso
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario/Senha Incorreto");
                    }

                    stmt.close();
                    con.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        btnEntrar.setBounds(191, 232, 120, 25);
        contentPane.add(btnEntrar);

        // Botão Registre-se
        JButton btnRegistreSe = new JButton("Registre-se");
        btnRegistreSe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Tela_cadastro telaCadastro = new Tela_cadastro();
                telaCadastro.setVisible(true);
                setVisible(false); // Esconde a Tela_de_acesso
            }
        });
        btnRegistreSe.setBounds(47, 232, 120, 25);
        contentPane.add(btnRegistreSe);
        
        JLabel lblMouraLacerda = new JLabel("Moura Lacerda");
        lblMouraLacerda.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
        lblMouraLacerda.setBounds(78, 13, 169, 47);
        contentPane.add(lblMouraLacerda);


    }
}
