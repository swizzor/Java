package com.swizzor;

import java.util.*;

public class Vector {
	
	private int firstPick;
	private int secondPick;
	private boolean fullOfBears;
	private boolean fullOfFishes;
	private int iterationAmount = 0;
	private Random r = new Random();
	private boolean useControlePopulacional;
	private boolean controlarPopulacao = false;
	private int lastBearLocation;
	private int lastFishLocation;
	
	public ArrayList<Animal> lista = new ArrayList<Animal>();
	
	public Vector(int p_initialSize, char p_controle){
		
		if(p_controle == 'S' || p_controle == 's'){
			this.useControlePopulacional = true;
		}
		else if(p_controle == 'N' || p_controle == 'n'){
			this.useControlePopulacional = false;
		}
		
		for(int i = 0; i < p_initialSize; i++){
			
			switch(r.nextInt(2)){
				case 0:
					this.lista.add(new Urso());
					break;
				case 1:
					this.lista.add(new Peixe());
					break;
				default:
					break;
			}
			
		}
		
		System.out.println("\n\n- - LISTA RANDOMIZADA DE ENTRADA - -");
		this.PrintaVetor();
		this.ChecaVetor();
		
	}
	
	public void PrintaVetor(){
		for(Animal a : lista){
			System.out.print(" | "+a.abrev+" | ");
		}
	}
	
	public void Xablau(){

		if(!this.fullOfBears && !this.fullOfFishes){
			
			this.iterationAmount++;
			
			Animal firstAnimal;
			Animal secondAnimal;
			
			this.RandomizaPicks();
			
			firstAnimal = this.lista.get(this.firstPick);
			secondAnimal = this.lista.get(this.secondPick);
			
			System.out.println("\n\nINTERA플O ENTRE OS INDICES");
			System.out.println(this.firstPick+"-"+firstAnimal.abrev+" E "+this.secondPick+"-"+secondAnimal.abrev);
			System.out.println("NUMERO DA ITERA플O "+this.iterationAmount);
			
			if(firstAnimal instanceof Urso && secondAnimal instanceof Urso){
				this.lista.add(new Urso());
			}
			
			if(firstAnimal instanceof Urso && secondAnimal instanceof Peixe){
				this.lista.set(this.secondPick, firstAnimal);
				this.lista.remove(this.firstPick);
			}
			
			if(firstAnimal instanceof Peixe && secondAnimal instanceof Peixe){
				this.lista.add(new Peixe());
			}
			
			if(firstAnimal instanceof Peixe && secondAnimal instanceof Urso){
				this.lista.remove(this.firstPick);
			}
			
			System.out.println("RESULTADO");
			this.PrintaVetor();
			this.ChecaVetor();
			
			/*try {
			    Thread.sleep(2000);
			    try{
					Xablau();
				}catch(StackOverflowError e){
					Xablau();
				}
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}*/
			try{
				Xablau();
			}catch(StackOverflowError e){
				Xablau();
			}
		}
		else if(this.fullOfBears){
		
			System.out.println("\n\nONLY BEARS ON THE RUN !");
			System.out.println("DEPOIS DE "+this.iterationAmount+" ITERA합ES");
			this.PrintaVetor();
			
		}
		
		else if(this.fullOfFishes){
			
			System.out.println("\n\nONLY FISHES ON THE RUN !");
			System.out.println("DEPOIS DE "+this.iterationAmount+" ITERA합ES");
			this.PrintaVetor();
			
		}
		
	}
	
	private void RandomizaPicks(){
		
		if(this.controlarPopulacao){
			System.out.println("CONTROLANDO POPULA플O");
			this.firstPick = this.lastBearLocation;
			this.secondPick = this.lastFishLocation;
		}else{
			this.firstPick = this.r.nextInt(lista.size());
			this.secondPick = this.r.nextInt(lista.size());
			
			if ( this.firstPick == this.secondPick ){
				this.RandomizaPicks();
			}
		}		
	}
	
	private void ChecaVetor(){
		
		int bearCount = 0;
		boolean fishLocated = false;
		
		/*for(Animal a : this.lista){
			if(a instanceof Urso){
				bearCount++;
			}
		}*/
		
		for(int i = 0; i < this.lista.size(); i++){
			if(this.lista.get(i) instanceof Urso){
				bearCount++;
				this.lastBearLocation = i;
			}
		}
		for(int i = 0; i < this.lista.size(); i++){
			if(this.lista.get(i) instanceof Peixe && !fishLocated){
				this.lastFishLocation = i;
				fishLocated = true;
			}
		}
		
		if(bearCount == this.lista.size()){
			this.fullOfBears = true;
		}
		
		if(bearCount == 0){
			this.fullOfFishes = true;
		}
		
		if(this.useControlePopulacional){
			if(bearCount > 0 && bearCount <= this.lista.size()*0.15){
				this.controlarPopulacao = true;
			}
			else if(bearCount >= this.lista.size()*0.8 && bearCount <= this.lista.size()-1 ){
				this.controlarPopulacao = true;
			}
		}
		
		System.out.println("BEAR COUNT "+bearCount);
		
	}
	
	private void OrdenaVetor(){
		
		ArrayList<Animal> temp = new ArrayList<Animal>();
		for(Animal a : this.lista){
			if(a != null){
				temp.add(a);
			}
		}
		this.lista.clear();
		for(Animal a : temp){
			this.lista.add(a);
		}	
	}
}
