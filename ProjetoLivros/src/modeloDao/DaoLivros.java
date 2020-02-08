/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modeloBeans.BeansLivro;
import modeloConnection.ConexaoBD;

/**
 *
 * @author yuril
 */
public class DaoLivros {

    ConexaoBD conex = new ConexaoBD();
    BeansLivro mod = new BeansLivro();

    public void salvar(BeansLivro mod) {
        conex.conexao();

        try {
            PreparedStatement pst = conex.conn.prepareStatement("insert into livros(titulo_livro, autor_livro, editora_livro, paginas_livro, formato_livro, lido_livro, sequencia_livro, numseq_livro, box_livro) values(?,?,?,?,?,?,?,?,?)");
            pst.setString(1, mod.getTitulo());
            pst.setString(2, mod.getAutor());
            pst.setString(3, mod.getEditora());
            pst.setInt(4, mod.getPaginas());
            pst.setString(5, mod.getFormato());
            pst.setString(6, mod.getLido());
            pst.setString(7, mod.getSequencia());
            pst.setInt(8, mod.getNumerosequencia());
            pst.setString(9, mod.getBox());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados inseridos no BD com Sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir dados no BD" + ex.getMessage());
        }

        conex.desconecta();
    }

    public void excluir(BeansLivro mod) {
        conex.conexao();

        try {
            PreparedStatement pst = conex.conn.prepareStatement("delete from livros where cod_livro=?");
            pst.setInt(1, mod.getCod());
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Dados excluidos com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir dados." + ex.getMessage());
        }

        conex.desconecta();
    }

    public void editar(BeansLivro mod) {
        conex.conexao();

        try {
            PreparedStatement pst = conex.conn.prepareStatement("update livros set titulo_livro=?, autor_livro=?, editora_livro=?, paginas_livro=?, formato_livro=?, lido_livro=?, sequencia_livro=?, numseq_livro=?, box_livro=? where cod_livro=?");
            pst.setString(1, mod.getTitulo());
            pst.setString(2, mod.getAutor());
            pst.setString(3, mod.getEditora());
            pst.setInt(4, mod.getPaginas());
            pst.setString(5, mod.getFormato());
            pst.setString(6, mod.getLido());
            pst.setString(7, mod.getSequencia());
            pst.setInt(8, mod.getNumerosequencia());
            pst.setString(9, mod.getBox());
            pst.setInt(10, mod.getCod());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro dados não alterados.\nErro: " + ex.getMessage());
        }

        conex.desconecta();
    }

    public BeansLivro buscaLivro(BeansLivro mod) {
        conex.conexao();

        conex.executaSql("select *from livros where titulo_livro like'%" + mod.getPesquisa() + "%'");
        try {
            if (conex.rs.next()) {
                mod.setCod(conex.rs.getInt("cod_livro"));
                mod.setTitulo(conex.rs.getString("titulo_livro"));
                mod.setAutor(conex.rs.getString("autor_livro"));
                mod.setEditora(conex.rs.getString("editora_livro"));
                mod.setPaginas(conex.rs.getInt("paginas_livro"));
                mod.setFormato(conex.rs.getString("formato_livro"));
                mod.setBox(conex.rs.getString("box_livro"));
                mod.setLido(conex.rs.getString("lido_livro"));
                mod.setSequencia(conex.rs.getString("sequencia_livro"));
                mod.setNumerosequencia(conex.rs.getInt("numseq_livro"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Livro não encontrado! " + ex);
        }

        conex.desconecta();
        return mod;
    }
}
