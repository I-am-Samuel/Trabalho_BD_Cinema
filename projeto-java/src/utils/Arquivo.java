package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Arquivo extends MenuFormatter {
    // Método para procurar o caminho absoluto de uma certa pasta
    public String procuraPasta (String nomePasta) {
        String diretorioAtual = System.getProperty("user.dir");

        String caminhoCompleto = diretorioAtual + File.separator + nomePasta;
        File pasta = new File(caminhoCompleto);

        if (pasta.exists() && pasta.isDirectory()) {
            super.msgTerminalINFO("Pasta encontrada: ");

        } else {
            super.msgTerminalERROR("Pasta não encontrada.");
        }

        return pasta.getAbsolutePath();
    }    


    // Método para transformar um arquivo SQL em uma String
    public String lerSQL(String caminhoArquivo) {
        StringBuilder sqlBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                sqlBuilder.append(linha).append("\n");
            }

        } catch (IOException e) {
            super.msgTerminalERROR(e.getMessage());
            return null;

        }
        return sqlBuilder.toString();
    }
}
