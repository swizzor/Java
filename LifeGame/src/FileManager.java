import java.io.*;
import java.util.*;

public class FileManager {
	
	FileReader fileReader;
	BufferedReader bufferedReader;
	
	List<String> fileContent = new ArrayList<String>();
	
	public void LoadFile(String url){
		
		String line = null;

	    try {
	        fileReader = new FileReader(url);

	        bufferedReader = new BufferedReader(fileReader);
	        
	        while((line = bufferedReader.readLine()) != null) {
	        	
	        	if(line.length() > 0)
	        		fileContent.add(line);
	        }   
	        
	        bufferedReader.close();         
	    }
	    catch(FileNotFoundException ex) {
	        System.out.println(
	            "Nao foi possivel abrir o arquivo '" + url + "'");                
	    }
	    catch(IOException ex) {
	        System.out.println(
	            "Erro na leitura do arquivo '" + url + "'");               
	    }
	}
	
	public int[] GetSize(){
		
		int[] temp = new int[2];
		temp[0] = Integer.parseInt(fileContent.get(0));
		temp[1] = Integer.parseInt(fileContent.get(1));
		
		return temp;
		
	}
	
	public int GetGeracaoTimes(){
		
		return Integer.parseInt(fileContent.get(2));
		
	}
	
	public int[][] GetPersonas(){
		
		int size = fileContent.size();
		
		int[][] temp = new int[size][size];
		
		for(int i = 3; i < fileContent.size(); i++){
			for(int j = 0; j < fileContent.get(i).length(); j++){
				temp[i-3][j] = Character.getNumericValue(((temp[i][j] = fileContent.get(i).charAt(j))));
			}
		}
		
		return temp;
		
	}
	
}
