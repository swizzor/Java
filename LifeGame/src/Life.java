import java.util.concurrent.TimeUnit;

public class Life {

	public static void main(String[] args) {
		
		FileManager fm = new FileManager();
		fm.LoadFile(args[0]);
		
		Matriz matrix = new Matriz(fm.GetSize()[0], fm.GetSize()[1], fm.GetPersonas());
		
		if(matrix != null)
			System.out.println("MATRIZ INSTANCIADA COM SUCESSO !");	
		
		System.out.println("GERACOES A SEREM RODADAS "+fm.GetGeracaoTimes());
		
		System.out.println();
		
		System.out.println("MATRIZ DE ENTRADA");
		matrix.PrintMatriz();
		
		for(int i = 1; i <= fm.GetGeracaoTimes(); i++){
			try {
			    Thread.sleep(1000);
			    matrix.RodaGeracao();
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		
	}
}
