import java.util.*;

public class Matriz {
	
	public Persona[][] grid;
	public Persona[][] grid_temp;
	public int linhas;
	public int colunas;
	
	private int aliveN = 0;
	private int deadN = 0;
	
	private int actualGeneration = 0;
	
	public Matriz(int p_linhas, int p_colunas, int[][] p_personas){
		
		linhas = p_linhas;
		colunas = p_colunas;
		grid = new Persona[linhas][colunas];
		grid_temp = new Persona[linhas][colunas];
		
		for(int i = 0; i < linhas; i++){
			for(int j = 0; j < colunas; j++){
				grid[i][j] = new Persona(("Persona"+i+"-"+j), p_personas[i][j]);
				grid_temp[i][j] = new Persona(("Persona"+i+"-"+j), p_personas[i][j]);
			}
		}		
	}
	
	public void RodaGeracao(){
		
		actualGeneration++;
		
		//Primeira linha
				for(int j = 0; j <= colunas-1; j++){
					
					if(j-1 < 0){
						if(grid_temp[0][j+1].value == 1)
							aliveN++;
						if(grid_temp[1][j+1].value == 1)
							aliveN++;
						if(grid_temp[1][j].value == 1)
							aliveN++;
					}
					
					if(j-1 >= 0 && j+1 <= colunas-1){
						if(grid_temp[0][j+1].value == 1)
							aliveN++;
						if(grid_temp[1][j+1].value == 1)
							aliveN++;
						if(grid_temp[1][j].value == 1)
							aliveN++;
						if(grid_temp[1][j-1].value == 1)
							aliveN++;
						if(grid_temp[0][j-1].value == 1)
							aliveN++;
					}
					
					if(j == colunas-1){
						if(grid_temp[1][j].value == 1)
							aliveN++;
						if(grid_temp[1][j-1].value == 1)
							aliveN++;
						if(grid_temp[0][j-1].value == 1)
							aliveN++;
					}
					
					grid[0][j].receveNeighbors(aliveN);
					PernoasStateReset();
					
				}
				
				//Primeira coluna
				for(int i = 1; i < linhas-1; i++){
					
					if(grid_temp[i-1][0].value == 1)
						aliveN++;
					if(grid_temp[i-1][1].value == 1)
						aliveN++;
					if(grid_temp[i][1].value == 1)
						aliveN++;
					if(grid_temp[i+1][1].value == 1)
						aliveN++;
					if(grid_temp[i+1][0].value == 1)
						aliveN++;
					
					grid[i][0].receveNeighbors(aliveN);
					PernoasStateReset();
							
				}
				
				//Ultima linha
				for(int j = 0; j < colunas-1; j++){
					if(j-1 < 0){
						if(grid_temp[(linhas-1)-1][j].value == 1)
							aliveN++;
						if(grid_temp[(linhas-1)-1][j+1].value == 1)
							aliveN++;
						if(grid_temp[linhas-1][j+1].value == 1)
							aliveN++;
					}
					
					if(j-1 >= 0 && j+1 < colunas-1){
						if(grid_temp[linhas-1][j-1].value == 1)
							aliveN++;
						if(grid_temp[(linhas-1)-1][j-1].value == 1)
							aliveN++;
						if(grid_temp[(linhas-1)-1][j].value == 1)
							aliveN++;
						if(grid_temp[(linhas-1)-1][j+1].value == 1)
							aliveN++;
						if(grid_temp[linhas-1][j+1].value == 1)
							aliveN++;
					}
					
					grid[linhas-1][j].receveNeighbors(aliveN);
					PernoasStateReset();
					
				}
				
				//Ultima coluna
				for(int i = 1; i <= linhas-1; i++){
					
					if(i >= 1 && i < linhas-1){
						if(grid_temp[i+1][colunas-1].value == 1)
							aliveN++;
						if(grid_temp[i+1][(colunas-1)-1].value == 1)
							aliveN++;
						if(grid_temp[i][(colunas-1)-1].value == 1)
							aliveN++;
						if(grid_temp[i-1][(colunas-1)-1].value == 1)
							aliveN++;
						if(grid_temp[i-1][colunas-1].value == 1)
							aliveN++;
					}
					
					if(i == linhas-1){
						if(grid_temp[i][(colunas-1)-1].value == 1)
							aliveN++;
						if(grid_temp[i-1][(colunas-1)-1].value == 1)
							aliveN++;
						if(grid_temp[i-1][colunas-1].value == 1)
							aliveN++;
					}
					
					grid[i][colunas-1].receveNeighbors(aliveN);
					PernoasStateReset();
					
				}
				
				//Geral
				for(int i = 1; i < linhas-1; i++){
					for(int j = 1; j < colunas-1; j++){				
						
						if(grid_temp[i-1][j].value == 1)
							aliveN++;
						if(grid_temp[i-1][j+1].value == 1)
							aliveN++;
						if(grid_temp[i][j+1].value == 1)
							aliveN++;
						if(grid_temp[i+1][j+1].value == 1)
							aliveN++;
						if(grid_temp[i+1][j].value == 1)
							aliveN++;
						if(grid_temp[i+1][j-1].value == 1)
							aliveN++;
						if(grid_temp[i][j-1].value == 1)
							aliveN++;
						if(grid_temp[i-1][j-1].value == 1)
							aliveN++;
						
						grid[i][j].receveNeighbors(aliveN);
						PernoasStateReset();
						
					}
				}
				
		replicaMatriz();
		PrintMatriz();
		
	}
	
	public void PernoasStateReset(){
		aliveN = 0;
		deadN = 0;
	}
	
	public void PrintMatriz(){
		
		System.out.println(actualGeneration+" GERACAO");
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid.length; j++){
				System.out.print(grid[i][j].value);
			}
			System.out.println();
		}
		System.out.println();	
	}
	
	public void replicaMatriz(){
		
		for(int i = 0; i <= linhas-1; i++){
			for(int j = 0; j <= colunas-1; j++){
				grid_temp[i][j] = new Persona(("Persona"+i+"-"+j), grid[i][j].value);
			}
		}
		
	}
	
	
}
