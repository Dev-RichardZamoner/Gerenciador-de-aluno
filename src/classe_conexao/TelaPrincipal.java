package classe_conexao;

import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;

@SuppressWarnings("serial")
public class TelaPrincipal extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private DefaultTableModel tableModel;
    private JLabel lblNome;
    private JLabel lblIdade;
    private JTextField textField_1;
    private JLabel lblEmail;
    private JTextField textField_2;
    private JLabel lblGerenciadorMouraLacerda;
    private JButton btnNovoAluno;
    private JButton btnTodosOsAlunos;
    private JButton btnExcluirAluno;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal frame = new TelaPrincipal();
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
    public TelaPrincipal() {
        setTitle("Gerenciador de Conteudo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 524);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        textField = new JTextField();
        textField.setBounds(78, 126, 350, 22);
        contentPane.add(textField);
        textField.setColumns(10);
        
        // Configuração do JTable e DefaultTableModel
        tableModel = new DefaultTableModel(new Object[]{"ID", "Usuário", "Email", "Idade"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(33, 217, 518, 209);
        contentPane.add(scrollPane);
        
        JButton btnPesquisar = new JButton("Pesquisar");
        btnPesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textField.getText();
                String idade = textField_1.getText();
                String email = textField_2.getText();
                
                // Verifica se algum campo está preenchido para realizar a pesquisa
                if(nome.trim().isEmpty() && idade.trim().isEmpty() && email.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um nome, idade ou e-mail para pesquisar.");
                    return;
                }
                
                try {
                    Connection con = Conexao.faz_conexao();
                    // Cria a consulta SQL com base nos campos preenchidos
                    String sql = "SELECT * FROM dados WHERE ";
                    boolean hasPrevious = false;
                    
                    if (!nome.trim().isEmpty()) {
                        sql += "usuario LIKE ?";
                        hasPrevious = true;
                    }
                    
                    if (!idade.trim().isEmpty()) {
                        if (hasPrevious) sql += " AND ";
                        sql += "idade = ?";
                        hasPrevious = true;
                    }
                    
                    if (!email.trim().isEmpty()) {
                        if (hasPrevious) sql += " AND ";
                        sql += "email LIKE ?";
                    }
                    
                    PreparedStatement stmt = con.prepareStatement(sql);
                    
                    int index = 1;
                    if (!nome.trim().isEmpty()) {
                        stmt.setString(index++, "%" + nome + "%");
                    }
                    if (!idade.trim().isEmpty()) {
                        stmt.setInt(index++, Integer.parseInt(idade));
                    }
                    if (!email.trim().isEmpty()) {
                        stmt.setString(index++, "%" + email + "%");
                    }
                    
                    ResultSet rs = stmt.executeQuery();
                    
                    tableModel.setRowCount(0); // Limpa a tabela antes de adicionar novos resultados
                    
                    while(rs.next()){
                        tableModel.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("usuario"),
                            rs.getString("email"),
                            rs.getInt("idade")
                        });
                    }
                    
                    rs.close();
                    stmt.close();
                    con.close();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnPesquisar.setBounds(454, 126, 97, 68);
        contentPane.add(btnPesquisar);
        
        lblNome = new JLabel("Nome:");
        lblNome.setBounds(33, 129, 56, 16);
        contentPane.add(lblNome);
        
        lblIdade = new JLabel("Idade:");
        lblIdade.setBounds(33, 178, 56, 16);
        contentPane.add(lblIdade);
        
        textField_1 = new JTextField();
        textField_1.setBounds(78, 172, 116, 22);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        lblEmail = new JLabel("E-mail");
        lblEmail.setBounds(207, 175, 56, 16);
        contentPane.add(lblEmail);
        
        textField_2 = new JTextField();
        textField_2.setBounds(256, 172, 172, 22);
        contentPane.add(textField_2);
        textField_2.setColumns(10);
        
        lblGerenciadorMouraLacerda = new JLabel("Gerenciador  de Aluno\r\n");
        lblGerenciadorMouraLacerda.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 34));
        lblGerenciadorMouraLacerda.setBounds(102, 13, 411, 93);
        contentPane.add(lblGerenciadorMouraLacerda);
        
     // ... [restante do código acima]

        btnNovoAluno = new JButton("Novo Aluno");
        btnNovoAluno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textField.getText();
                String idade = textField_1.getText();
                String email = textField_2.getText();
                
                // Verifica se todos os campos estão preenchidos
                if(nome.trim().isEmpty() || idade.trim().isEmpty() || email.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos para inserir um novo aluno.");
                    return;
                }
                
                try {
                    Connection con = Conexao.faz_conexao();
                    String sql = "INSERT INTO dados (usuario, email, idade) VALUES (?, ?, ?)";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    
                    stmt.setString(1, nome);
                    stmt.setString(2, email);
                    stmt.setInt(3, Integer.parseInt(idade));
                    
                    int rowsInserted = stmt.executeUpdate();
                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Um novo aluno foi inserido com sucesso!");
                    }
                    
                    stmt.close();
                    con.close();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnNovoAluno.setBounds(285, 439, 116, 25);
        contentPane.add(btnNovoAluno);
        
     // ... [restante do código acima]

        btnTodosOsAlunos = new JButton("Todos os Alunos");
        btnTodosOsAlunos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = Conexao.faz_conexao();
                    String sql = "SELECT * FROM dados";
                    PreparedStatement stmt = con.prepareStatement(sql);
                    ResultSet rs = stmt.executeQuery();
                    
                    tableModel.setRowCount(0); // Limpa a tabela antes de adicionar novos resultados
                    
                    while(rs.next()){
                        tableModel.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("usuario"),
                            rs.getString("email"),
                            rs.getString("idade")// Supondo que você queira mostrar a senha
                            // Se não quiser mostrar a senha, remova esta linha
                        });
                    }
                    
                    rs.close();
                    stmt.close();
                    con.close();
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnTodosOsAlunos.setBounds(117, 439, 156, 25);
        contentPane.add(btnTodosOsAlunos);
        
     // ... [restante do código acima]

        btnExcluirAluno = new JButton("Excluir Aluno");
        btnExcluirAluno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione um aluno para excluir.");
                    return;
                }
                
                // Obtem o ID do aluno selecionado
                int alunoId = (int) tableModel.getValueAt(selectedRow, 0);
                
                int confirm = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o aluno selecionado?", "Excluir Aluno", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    try {
                        Connection con = Conexao.faz_conexao();
                        String sql = "DELETE FROM dados WHERE id = ?";
                        PreparedStatement stmt = con.prepareStatement(sql);
                        
                        stmt.setInt(1, alunoId);
                        
                        int rowsDeleted = stmt.executeUpdate();
                        if (rowsDeleted > 0) {
                            JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");
                            // Remove a linha da tabela após a exclusão bem-sucedida
                            tableModel.removeRow(selectedRow);
                        }
                        
                        stmt.close();
                        con.close();
                        
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        btnExcluirAluno.setBounds(413, 439, 127, 25);
        contentPane.add(btnExcluirAluno);

        // ... [restante do código]

        // ... [restante do código]


    }
}
