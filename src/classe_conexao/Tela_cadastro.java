package classe_conexao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DropMode;
import java.awt.Font;

@SuppressWarnings({ "serial", "unused" })
public class Tela_cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField TFUsuario;
	private JTextField TFSenha;
	private JTextField TFmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_cadastro frame = new Tela_cadastro();
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
	public Tela_cadastro() {
		setResizable(false);
		setTitle("Cadastro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 332, 289);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		TFUsuario = new JTextField();
		TFUsuario.setBounds(66, 35, 217, 22);
		contentPane.add(TFUsuario);
		TFUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Nome:");
		lblUsuario.setBounds(23, 38, 56, 16);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(23, 143, 56, 16);
		contentPane.add(lblSenha);
		
		TFSenha = new JTextField();
		TFSenha.setColumns(10);
		TFSenha.setBounds(66, 140, 217, 22);
		contentPane.add(TFSenha);
		
		JButton btnVoltar = new JButton("Inicio");
		btnVoltar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Tela_de_acesso telaDeAcesso = new Tela_de_acesso();
		        telaDeAcesso.setVisible(true);
		        setVisible(false); // Esconde a Tela_cadastro
		    }
		});
		btnVoltar.setBounds(51, 187, 97, 25);
		contentPane.add(btnVoltar);
		
		JButton btnSalvar = new JButton("Registre-se");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(TFUsuario.getText().equals("") || TFSenha.getText().equals("") || TFmail.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Usuaruio/senha em branco");
				}else{ 
			
				try {
					
					Connection con = Conexao.faz_conexao();
					String sql = "insert into dados(usuario,email, senha) values (?, ?, ?)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, TFUsuario.getText());
					stmt.setString(2, TFmail.getText());
					stmt.setString(3, TFSenha.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
					
					TFUsuario.setText("");
					TFSenha.setText("");
							
				} catch(SQLException e){
					
					e.printStackTrace();
				}
			 	
			}
			}
		});
		btnSalvar.setBounds(159, 187, 128, 25);
		contentPane.add(btnSalvar);
		
		TFmail = new JTextField();
		TFmail.setBounds(66, 88, 217, 22);
		contentPane.add(TFmail);
		TFmail.setColumns(10);
		
		JLabel lblEmail = new JLabel("E:mail:");
		lblEmail.setBounds(23, 91, 56, 16);
		contentPane.add(lblEmail);
	}
}
