package algorithms;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class InputGenerator {

    public static void generateGraphInput(String filename, int vertices, int edges) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println(vertices); // Número de vértices
            
            Random random = new Random();
            for (int i = 0; i < edges; i++) {
                int src = random.nextInt(vertices); // Gera um vértice de origem
                int dest = random.nextInt(vertices); // Gera um vértice de destino
                
                // Garantir que src != dest para evitar loops
                while (dest == src) {
                    dest = random.nextInt(vertices);
                }

                int weight = random.nextInt(50) + 1; // Gera pesos entre 1 e 50
                writer.printf("%d %d %d%n", src, dest, weight); // Escreve no arquivo
            }

            System.out.println("Arquivo 'input.txt' gerado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Configurar o número de vértices e arestas desejado
        int vertices = 10; // Exemplo: 10 vértices
        int edges = 20; // Exemplo: 20 arestas
        generateGraphInput("input.txt", vertices, edges);
    }
}