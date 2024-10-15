package controllers;

import conexion.Database;
import models.Endereco;
import utils.MenuFormatter;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.util.ReaderInputStream;

public class EnderecoController {
    public static boolean inserirEndereco (Endereco endereco) {
        String sql = "INSERT INTO endereco (id_endereco, numero, rua, bairro, cidade, uf) VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int idEndereco = EnderecoController.getMaiorId();
            if (idEndereco == -500) {
                idEndereco = 0;

            } else if (idEndereco == -999) {
                return false;
            }
            
            pstmt.setInt(1, idEndereco+1);
            pstmt.setInt(2, endereco.getNumero());
            pstmt.setString(3, endereco.getRua());
            pstmt.setString(4, endereco.getBairro());
            pstmt.setString(5, endereco.getCidade());
            pstmt.setString(6, endereco.getUf());

            pstmt.executeUpdate();

            return true;
            
        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }
    
    public static boolean excluirEndereco (int idEndereco) {
        String sql = "DELETE FROM endereco WHERE id_endereco = ?;";

        if (EnderecoController.existeEndereco(idEndereco)) {
            try (Connection conn = Database.conectar();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, idEndereco);
                pstmt.executeUpdate();

                return true;

            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
            
        } else {
            MenuFormatter.msgTerminalERROR("Endereço não encontrado no Banco de Dados.");
            return false;
        }
    }

    public static boolean excluirTodosRegistros () {
        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {

                String sql = "DELETE FROM endereco;";
                stmt.executeUpdate(sql);

                return true;
                

            } catch (SQLException e) {
                MenuFormatter.msgTerminalERROR(e.getMessage());
                return false;
            }
    }
/*
    public static boolean atualizarEndereco () {

    }
    
    public static Endereco buscarEnderecoPorId (int idEndereco) {
        
    }

    public static LinkedList<Endereco> listarTodosEnderecos () {

    }
    
*/
    public static int contarRegistros () {
        try (Connection conn = Database.conectar();
            Statement stmt = conn.createStatement()) {
            
            int qtdEndereco = 0;
            ResultSet rs = stmt.executeQuery("SELECT COUNT(id_endereco) AS resultado FROM endereco;");
            
            while (rs.next()) {
                qtdEndereco = rs.getInt("resultado");
            }

            return qtdEndereco;

        } catch (SQLException e) {
            return -999;
        }
    }

    public static boolean existeEndereco (int idEndereco) {
        String sql = "SELECT COUNT(id_endereco) AS resultado FROM endereco WHERE id_endereco = ?;";
        int qtdEndereco = 0;

        try (Connection conn = Database.conectar();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idEndereco);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                qtdEndereco = rs.getInt("resultado");
            }

            if (qtdEndereco == 0) {
                return false;
                
            } else {
                return true;
            }

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return false;
        }
    }

    private static int getMaiorId() {
        String sql = "SELECT id_endereco AS resultado FROM endereco ORDER BY id_endereco DESC LIMIT 1;";
        int ultimoId = -500;

        try (Connection conn = Database.conectar();
            Statement pstmt = conn.createStatement()) {

            ResultSet rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                ultimoId = rs.getInt("resultado");
            }

            return ultimoId;

        } catch (SQLException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return -999;
        }
    }

    // Teste da Classe
    public static void main(String[] args) {
        EnderecoController.inserirEndereco(new Endereco(55, "AquelaRua", "AqueleBairro", "AquelaCidade", "UF"));
        EnderecoController.inserirEndereco(new Endereco(55, "AquelaRua", "AqueleBairro", "AquelaCidade", "UF"));
        // EnderecoController.excluirEndereco(2);

        // System.out.println(EnderecoController.excluirTodosRegistros());
    }

}
