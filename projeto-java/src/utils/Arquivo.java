package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Arquivo {
    // Método para procurar o caminho absoluto de uma certa pasta
    public static String procuraPasta (String nomePasta) {
        String diretorioAtual = System.getProperty("user.dir");

        String caminhoCompleto = diretorioAtual + File.separator + nomePasta;
        File pasta = new File(caminhoCompleto);

        if (pasta.exists() && pasta.isDirectory()) {
            MenuFormatter.msgTerminalINFO("Pasta encontrada. ");

        } else {
            MenuFormatter.msgTerminalERROR("Pasta não encontrada.");
        }

        return pasta.getAbsolutePath();
    }    


    // Método para transformar um arquivo SQL em uma String
    public static String lerSQL(String caminhoArquivo) {
        StringBuilder sqlBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                sqlBuilder.append(linha).append("\n");
            }

        } catch (IOException e) {
            MenuFormatter.msgTerminalERROR(e.getMessage());
            return null;

        }
        return sqlBuilder.toString();
    }
}
