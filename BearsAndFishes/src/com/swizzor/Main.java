package com.swizzor;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		int tamanhoLista;
		char controle;
		
		Scanner r = new Scanner(System.in);
		System.out.print("ENTRE COM O TAMANHO INICIAL PARA A LISTA: ");
		tamanhoLista = r.nextInt();
		System.out.print("CONTROLE POPULACIONAL ? (S/N): ");
		controle = r.next().charAt(0);
		Vector vetor = new Vector(tamanhoLista, controle);
		vetor.Xablau();

	}

}
