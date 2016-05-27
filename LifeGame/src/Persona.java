
public class Persona {
	
	public String nome;
	public int value;
	
	public Persona(String p_nome, int p_value){
		
		nome = p_nome;
		value = p_value;
		
	}
	
	public void receveNeighbors(int p_alive){
		
		if(value == 1 && p_alive < 2)
			this.die();
		if(value == 1 && (p_alive >= 2 && p_alive <= 3))
			this.Remain();
		if(value == 1 && p_alive > 3)
			this.die();
		if(value == 0 && p_alive == 3)
			this.live();
	}
	
	public void die(){
		
		value = 0;
		
	}
	
	public void live(){
		
		value = 1;
		
	}
	
	public void Remain(){
		value = value;
	}
	

}
