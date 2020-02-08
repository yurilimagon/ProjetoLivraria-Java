/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloConnection;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author yuril
 */
public class ConexaoBD {

    //Atributos
    public Statement stm; //Responsável por realizar pesquisa no BD
    public ResultSet rs;       //Armazena o resultado da pesquisa
    private String driver = "org.postgresql.Driver";     //Identifica o serviço no BSD
    private String caminho = "jdbc:postgresql://localhost:5432/projetolivros";    //Qual o caminho do BD
    private String usuario = "postgres";    //Usuario BD
    private String senha = "Herinhas2";      //Senha BD
    public Connection conn;     //Conexão com o BD

    public void conexao() { //Método que realiza a conexão com o BD
        try {
            System.setProperty("jdbc.Drivers", driver);
            conn = DriverManager.getConnection(caminho, usuario, senha);
            //JOptionPane.showMessageDialog(null, "Conexão com BD efetuada com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao se conectar com o BD: \n" + ex.getMessage());
        }
    }

    public void executaSql(String sql) {
        try {
            stm = conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ExecutaSql: \n" + ex.getMessage());
        }

    }

    public void desconecta() { //Método que desconecta do BD
        try {
            conn.close();
            //JOptionPane.showMessageDialog(null, "Desconectado com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão com o BD: \n" + ex.getMessage());
        }
    }
}
