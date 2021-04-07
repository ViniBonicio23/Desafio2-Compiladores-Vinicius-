/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio2.compiladores;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author arthu
 */
public class Desafio2Compiladores {

    private static final char abre_parentese = '(';
    private static final char fecha_parentese = ')';
    private static final char abre_colchete = '[';
    private static final char fecha_colchete = ']';
    private static final char abre_chave = '{';
    private static final char fecha_chave = '}';
    public static void main(String[] args) throws IOException {
        Scanner ler = new Scanner(System.in);
        System.out.printf("Digite o nome do arquivo texto para ler: ");
        String texto = ler.nextLine();
        Scanner in = new Scanner(new FileReader("Arquivo/" + texto.trim() + ".txt"));
        ArrayList<String> lista = new ArrayList();
        Stack<Character> pilha = new Stack<Character>();
        FileWriter arq = new FileWriter("Arquivo/" + texto.trim() + "-check.txt");
        PrintWriter gravaArq = new PrintWriter(arq);
        while (in.hasNextLine()){
            lista.add(in.nextLine());
        }
        
        for(int i = 0; i < lista.size(); i++){
            String linha = lista.get(i);
            Boolean valido = true;
            for (int y = 0; y < linha.length(); y++){
                if (linha.charAt(y) == abre_parentese){
                    pilha.push(abre_parentese);
                } else if (linha.charAt(y) == abre_colchete) {
                    pilha.push(abre_colchete);
                } else if (linha.charAt(y) == abre_chave){
                    pilha.push(abre_chave);
                } else if (linha.charAt(y) == fecha_parentese){
                    if (pilha.isEmpty()){
                        valido = false;
                        break;
                    }
                    if (pilha.pop() != abre_parentese){
                        valido = false;
                        break;
                    }
                } else if (linha.charAt(y) == fecha_colchete){
                    if (pilha.isEmpty()){
                        valido = false;
                        break;
                    }
                    if (pilha.pop() != abre_colchete){
                        valido = false;
                        break;
                    }
                } else if (linha.charAt(y) == fecha_chave){
                    if (pilha.isEmpty()){
                        valido = false;
                        break;
                    }
                    if (pilha.pop() != abre_chave){
                        valido = false;
                        break;
                    }
                }
            }
            if (valido == false)
                linha = linha + " - Inválido";
            else
                linha = linha + " - OK";
            gravaArq.println(linha);
            pilha.clear();
        }
        
        arq.close();
        System.out.println("Tudo deu certo!");
    }
    
}
