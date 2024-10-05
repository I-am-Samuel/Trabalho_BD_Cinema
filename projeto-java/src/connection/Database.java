package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import utils.Arquivo;
import utils.MenuFormatter;

public class Database {
    // Classes auxiliares
    private static MenuFormatter menu = new MenuFormatter();
    private static Arquivo arq = new Arquivo();

    // Váriaveis referentes à criação do banco da Aplicação
    private static final String PORTA_SERVICO = "5432";
    private static final String URL_POSTGRES = "jdbc:postgresql://localhost:" + PORTA_SERVICO + "/postgres";
    private static final String USER_POSTGRES = "postgres";
    private static final String PASSWORD_POSTGRES = "postgres";
    private static final String DATABASE_NAME = "ger_cinema_db";
    
    // Váriaveis do Arquivo SQL Inicial
    private static final String NOME_ARQUIVO = "teste.sql";
    private static final String NOME_PASTA_SQL = "sql";
    private static final String CAMINHO_PASTA_SQL = arq.procuraPasta(NOME_PASTA_SQL);
    private static final String CAMINHO_ARQUIVO = CAMINHO_PASTA_SQL + "\\" + NOME_ARQUIVO;

    // Váriaveis referentes ao banco da Aplicação
    private static final String URL_DB = "jdbc:postgresql://localhost:" + PORTA_SERVICO + "/" + DATABASE_NAME;

    // Método para criar o banco informado na variável DATABASE_NAME
    public static void criarDatabase() {
        String createDatabaseSQL = "CREATE DATABASE " + DATABASE_NAME;

        try (Connection conn = DriverManager.getConnection(URL_POSTGRES, USER_POSTGRES, PASSWORD_POSTGRES);
            Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(createDatabaseSQL);
            System.out.println("Banco de dados '" + DATABASE_NAME + "' criado com sucesso.");

        } catch (SQLException e) {
            System.out.println("Erro ao criar o banco de dados: " + e.getMessage());
        }
    }

    // Método para conectar no BANCO
    public static Connection conectar() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL_DB, USER_POSTGRES, PASSWORD_POSTGRES);

        } catch (SQLException e) {
            menu.msgTerminalERROR(e.getMessage());
        }
        return conexao;
    }

    // Método para inserir os dados inciais do BANCO
    public static void inicializarDatabase() {
        try (Connection conexao = conectar();
             Statement stmt = conexao.createStatement()) {

            String createTableSQL = arq.lerSQL(CAMINHO_ARQUIVO);

            stmt.executeUpdate(createTableSQL);
            
            menu.msgTerminalINFO("Tabelas criadas e Dados inseridos no Banco: '" + DATABASE_NAME + "'.");

        } catch (SQLException e) {
            menu.msgTerminalERROR(e.getMessage());
        }
    }
    
    // Método para executar um comando SQL
    private static void executarSQL(String sql) {
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            menu.msgTerminalINFO("SQL executado com sucesso!");

        } catch (SQLException e) {
            menu.msgTerminalERROR(e.getMessage());

        }
    }


    // Main
    public static void main(String[] args) {
        // Teste da classe
    }
}
