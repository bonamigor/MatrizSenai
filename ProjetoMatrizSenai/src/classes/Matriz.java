/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.swing.JOptionPane;

/**
 *
 * @author aluno
 */
public class Matriz {
    //Atributos
    private int quantidadeDeLinhas = 0;
    private int quantidadeDeColunas = 0;
    private int[][] objMatriz = null;
    
    //Metodos
    public Matriz(int qLinhas, int qColunas) throws Exception {
        if(qLinhas <= 0 || qColunas <=0) throw new Exception("Quantidade de linhas ou colunas não podem ser <= 0");
        objMatriz = new int[qLinhas][qColunas];
        this.quantidadeDeLinhas = qLinhas;
        this.quantidadeDeColunas = qColunas;
    }
    
    public int getQuantidadeDeLinhas(){
        return quantidadeDeLinhas;
    }
    
    public int getQuantidadeDeColunas(){
        return quantidadeDeColunas;
    }
    
    public int getElemento(int linha, int coluna)throws Exception{
        if( linha < 0 || linha >= quantidadeDeLinhas) throw new Exception("Linha fora do intervalo válido");
        if( coluna < 0 || coluna >= quantidadeDeColunas) throw new Exception("Coluna fora do intervalo válido");
        return objMatriz[linha][coluna];
    }
    
    public void setElemento(int linha, int coluna, int elemento)throws Exception{
        if( linha < 0 || linha >= quantidadeDeLinhas) throw new Exception("Linha fora do intervalo válido");
        if( coluna < 0 || coluna >= quantidadeDeColunas) throw new Exception("Coluna fora do intervalo válido");
        objMatriz[linha][coluna] = elemento;
    }
    
    public String getMatriz(){
        String saida = "";
        for(int l = 0; l < quantidadeDeLinhas; l++){
            for(int c = 0; c < quantidadeDeColunas; c++){
                saida += objMatriz[l][c] + "  ";
            }
            saida += "\n";
        }
        return saida;
    }
    
    public Matriz adicionar(Matriz objMatriz) throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || 
            this.quantidadeDeColunas != objMatriz.quantidadeDeColunas) 
            throw new Exception("As matrizes não são de mesma ordem!");
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        for(int l = 0; l < this.quantidadeDeLinhas; l++){
            for(int c = 0; c < this.quantidadeDeColunas; c++){
                int valor = this.getElemento(l, c) + objMatriz.getElemento(l, c);
                aux.setElemento(l, c, valor);
            }
        }
        return aux;
    }
    
    public Matriz subtrair(Matriz objMatriz) throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || 
            this.quantidadeDeColunas != objMatriz.quantidadeDeColunas) 
            throw new Exception("As matrizes não são de mesma ordem!");
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        for(int l = 0; l < this.quantidadeDeLinhas; l++){
            for(int c = 0; c < this.quantidadeDeColunas; c++){
                int valor = this.getElemento(l, c) - objMatriz.getElemento(l, c);
                aux.setElemento(l, c, valor);
            }
        }
        return aux;
    }    
    
    public Matriz multiplicar(Matriz objMatriz) throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || 
            this.quantidadeDeColunas != objMatriz.quantidadeDeColunas) 
            throw new Exception("As matrizes não são de mesma ordem!");
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        
        for(int l = 0; l < this.quantidadeDeLinhas; l++){
            for(int c = 0; c < objMatriz.quantidadeDeColunas; c++){
                int soma = 0;
                for (int k = 0; k < this.quantidadeDeColunas; k++){
                    soma += this.objMatriz[l][k]*objMatriz.objMatriz[k][c];
                   
                }
                
                aux.setElemento(l, c, soma);
            }
        }
        return aux;
    }
    
    public boolean eTriangularSuperior(Matriz objMatriz) throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || 
            this.quantidadeDeColunas != objMatriz.quantidadeDeColunas) 
            throw new Exception("As matrizes não são de mesma ordem!");
        for (int l = 0; l < this.getQuantidadeDeLinhas(); l++) {
            for (int c = 0; c < this.getQuantidadeDeColunas(); c++) {
                if(l > c){
                    if(this.getElemento(l, c) != 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean eTriangularInferior(Matriz objMatriz) throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || 
            this.quantidadeDeColunas != objMatriz.quantidadeDeColunas) 
            throw new Exception("As matrizes não são de mesma ordem!");
        for (int l = 0; l < this.getQuantidadeDeLinhas(); l++) {
            for (int c = 0; c < this.getQuantidadeDeColunas(); c++) {
                if(l < c){
                    if(this.getElemento(l, c) != 0){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean eSimetrica(Matriz objMatriz) throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || 
            this.quantidadeDeColunas != objMatriz.quantidadeDeColunas) 
            throw new Exception("As matrizes não são de mesma ordem!");
        
        for (int l = 0; l < this.getQuantidadeDeLinhas(); l++) {
            for (int c = 0; c < this.getQuantidadeDeColunas(); c++) {
                if(this.objMatriz[l][c] != this.objMatriz[c][l]){
                    return false;
                }
            }
        }
        return true;    
        
    }
    
    public boolean eIdentidade(Matriz objMatriz) throws Exception{
        boolean aux = true;
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || 
            this.quantidadeDeColunas != objMatriz.quantidadeDeColunas) 
            throw new Exception("As matrizes não são de mesma ordem!");
                for (int l = 0; l < this.getQuantidadeDeLinhas(); l++) {
            for (int c = 0; c < this.getQuantidadeDeColunas(); c++) {
                if((l == c && this.objMatriz[l][c] != 1) || (l != c && this.objMatriz[l][c] != 0)){
                return false;
            }
                    }
                }
                return true;
    }
    
    public boolean eIgual(Matriz objMatriz) throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || 
            this.quantidadeDeColunas != objMatriz.quantidadeDeColunas){
            return false;
        } 
            //throw new Exception("As matrizes não são de mesma ordem!");
        for (int l= 0; l < this.quantidadeDeLinhas; l++) {
            for (int c = 0; c < this.quantidadeDeColunas; c++) {
                if(this.getElemento(l, c) != objMatriz.getElemento(l, c)){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean eDiferente(Matriz objMatriz) throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || 
            this.quantidadeDeColunas != objMatriz.quantidadeDeColunas){
            return true;
        } 
            //throw new Exception("As matrizes não são de mesma ordem!");
        for (int l= 0; l < this.quantidadeDeLinhas; l++) {
            for (int c = 0; c < this.quantidadeDeColunas; c++) {
                if(this.getElemento(l, c) != objMatriz.getElemento(l, c)){
                    return true;
                }
            }
        }
        return false;
    }
    
    public Matriz calcTransposta() throws Exception{
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        for(int l = 0; l < quantidadeDeLinhas; l++){
            for(int c = 0; c < quantidadeDeColunas; c++){
                aux.objMatriz[l][c] = this.objMatriz[c][l];
            }
        }
        return aux;
    }
    
    public Matriz Potenciacao(int expoente) throws Exception{
        if(expoente < 0){
            throw new Exception("O expoente não pode ser negativo");
        }
        
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        
        for(int l = 0; l < quantidadeDeLinhas; l++){
            for(int c = 0; c < quantidadeDeColunas; c++){
                aux.objMatriz[l][c] = this.objMatriz[l][c];
                }
            }
        
        if(expoente == 1) return aux;
        if(expoente > 1) {
            for (int i = 1; i < expoente; i++) {
                
                aux = aux.multiplicar(this);
                
            }
        }
        else{
            for(int l = 0; l < quantidadeDeLinhas; l++){
                for(int c = 0; c < quantidadeDeColunas; c++){
                    if(l == c){
                        aux.objMatriz[l][c] = 1;
                    }
                    else{
                        aux.objMatriz[l][c] = 0;
                    }
                }
            }
        }
        return aux;
    }
    
    public Matriz multiplicarPorK(Matriz objMatriz, int expoente)throws Exception{
        if(this.quantidadeDeLinhas != objMatriz.quantidadeDeLinhas || 
            this.quantidadeDeColunas != objMatriz.quantidadeDeColunas) 
            throw new Exception("As matrizes não são de mesma ordem!");
        Matriz aux = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        
        for(int l = 0; l < quantidadeDeLinhas; l++){
            for(int c = 0; c < quantidadeDeColunas; c++){
                aux.objMatriz[l][c] = this.objMatriz[l][c] * expoente; 
            }
        }
        return aux;
    }
    
    public boolean eOrtogonal() throws Exception{
        if(this.quantidadeDeLinhas != this.quantidadeDeColunas){
            return false;
        }
        Matriz auxTransposta = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        Matriz auxIdentidade = new Matriz(this.quantidadeDeLinhas, this.quantidadeDeColunas);
        
        auxTransposta = this.calcTransposta();
        auxIdentidade = this.multiplicar(auxTransposta);
        
        return auxIdentidade.eIdentidade(this);
    }
    
    public boolean ePermutacao(){
        if(this.quantidadeDeLinhas != this.quantidadeDeColunas){
            return false;
        }
        
        int somaLinha = 0;
        int somaColuna = 0;
        
        for(int l = 0; l < this.quantidadeDeLinhas; l++){
            somaLinha = 0;
            somaColuna = 0;
            for(int c = 0; c < this.getQuantidadeDeColunas(); c++){
                if(this.objMatriz[l][c] < 0 || this.objMatriz[l][c] > 1){
                    return false;
                }
                
                somaLinha += this.objMatriz[l][c];
                somaColuna += this.objMatriz[c][l];
                
                if(somaLinha > 1){
                    return false;
                }
                
               if(somaColuna > 1){
                   return false;
               }
            }
        }
        return true;
    }
}


