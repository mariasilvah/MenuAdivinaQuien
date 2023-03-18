import java.util.Random;
import java.util.Scanner;

import java.util.ArrayList;
import java.io.File;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class NiamAdivina {
	private static final String ListPerju = "Perfiles\n";
	
	/////////////////////////////////////////////////////////////
	Random rondo = new Random();
	static String[][]Mat=new String[10][5];//static porque no cambia hasta que empeize otra ronda //CAMBIAR A MATRIZ 11*5
	static String[]fragen = new String[] {"1) ¿Tiene cerebro?","2) ¿Le gustan las pasas?", "3) Canta opera?", "4) Baila flamenco?"};
	static int rowPers;
	static String PersonaElegida;
	
	public static void main(String[]args) {
		
		//////////////M E N U ///////////////
		Scanner sca =new Scanner (System.in);
		Perju pla = null;
		boolean adios = false;
		
		while (!adios) {
			System.out.println("Menu\n"
					+ "Si eres nuevo, teclea tecla, nuevo\n"
					+ "Si ya has jugado, teclea, existente \n"
					+ "Si quieres ver estadistica de algun"
					+ "jugador, teclea, estadistica\n"
					+ "Para jugar, teclea, jugar\n"
					+ "Para salir, teclea, salir");
			String sel = sca.nextLine();
			
			switch(sel) {
			case "nuevo":
				System.out.print("Bienvenido, ingresa tu nombre");
				String Player = sca.nextLine();
				pla = new Perju(Player);
				neuePer(pla);
			break;
				
			case "existente":
				System.out.print("Hola de nuevo, ingresa tu nombre");
				Player=sca.nextLine();
				pla = cargPer(Player);
			break;
				
			case"estadistica":
				System.out.print("Ingresa el nombre para ver su estadistica");
				Player=sca.nextLine();
				pla = cargPer(Player);
				if(pla!=null) {
					System.out.println("Veras los numeros de"+pla);
				}
			break;

			case "jugar":
				if(pla!=null) {
					ConstruirPe();
				}else {
					System.out.println("Error, teclea de nuevo");

				}
			break;
			
			case "salir":
				adios=true;
			break;
			
			default:
				System.out.println("Opcion no valida");
				
			break;
			}
		}
//}
		
		/////////////////////////////////////////////////////////////

			
		//char respu; //jugar o no
		//int respu;
		String respu;
		//int prelec; //pregunta selecc
		String prelec;
		int s;//coc
		int p; // pre
		String elegido; // Personaje elegifo
		////////////////////////////////////////
		System.out.println("Ingresa tu nombre");
		System.out.println("Si quieres jugar, teclea con minusculas si, si no quieres jugar, teclea no ");
		Scanner scan = new Scanner(System.in);
		//respu=(scan.next().charAt(0));
		respu=(scan.nextLine());
		
		while(respu.equals("si")) {
			//ShowPers();
			
			ConstruirPe();
				
				rowPers=(int)Math.floor(Math.random()*10);
				PersonaElegida=Mat[rowPers][0];
				
				s=0;
					while (s<3) {
						ShowPers();
						ShowQues();
						
						System.out.println("\n\nEscoge la pregunta");
						prelec=scan.nextLine();
						p=Integer.parseInt(prelec);
						/////////////////////
						System.out.println(Mat[rowPers][p]);
						Quitarpe(p);
						s++;
					}
					System.out.println("Quién es mi personaje?");
					elegido = (scan.nextLine());
					
					System.out.println(elegido);
					//System.out.println(PersonaElegida);
					
					if(elegido.equalsIgnoreCase(PersonaElegida.replace("=", ""))) {
						System.out.println("Gnaste!");
					}
					//if(!(elegido.equals(PersonaElegida))) {
						//System.out.println("Perdiste :(");
					//}
					else {
						System.out.println("Perdiste");
					}
			}
	sca.close();
	
	
	}
	
	//////////////////////////////////////////Imprimir matriy con los personajes
	public static void ShowPers() {
		for(int x=0; x<Mat.length; x++) {
			for(int b=0; b<Mat[x].length; b++) {
				System.out.print(Mat[x][b]+"");
			}
		System.out.print("\n"); //////////////////////////////////////////
	}
		System.out.print("\n\n *********\n");
	}
	
	/////////////////////////////////////////Preguntas
	public static void ShowQues() {
		for (int x=0; x<fragen.length; x++) {
			System.out.println(fragen[x]);
		}
	}
	
	///////////////////////////// DESCARTAR PERSONAKES
	public static void Quitarpe(int z) {
		for (int r=0; r<Mat.length; r++) {
			if(Mat[rowPers][z].equals(Mat[r][z])) {
				continue;
			}
			else {
				for(int co=1; co<Mat[r].length; co++) {
					Mat[r][co]="";
				}
			}
		}
	}
	
	
	public static void ConstruirPe() {
		for(int m=0; m<Mat.length; m++) {//En el ciclo, se están imprimiendo las 5 columanas por los 10 reglones
			SubClaPer q= new SubClaPer();
			Mat[m][0]=q.getNombr()+ "=";
			Mat[m][1]=q.getCer()+"/";
			Mat[m][2]=q.getPas()+"/";
			Mat[m][3]=q.getOpe()+"/";
			Mat[m][4]=q.getFla();
			if(m !=0) {
				//System.out.println("repetido??");
				while (Repetido(Mat,m)) {
					q.setCer();
					q.setPas();
					q.setOpe();
					q.setFla();
					////////////
					Mat[m][0]=q.getNombr()+"=";
					Mat[m][1]=q.getCer()+"/";
					Mat[m][2]=q.getPas()+"/";
					Mat[m][3]=q.getOpe()+"/";
					Mat[m][4]=q.getFla();
				}
				Mat[m][1]=q.getCer()+"/";
				Mat[m][2]=q.getPas()+"/";
				Mat[m][3]=q.getOpe()+"/";
				Mat[m][4]=q.getFla();
			}
			else {
				Mat[m][1]=q.getCer()+"/";
				Mat[m][2]=q.getPas()+"/";
				Mat[m][3]=q.getOpe()+"/";
				Mat[m][4]=q.getFla();
			}

		}
	}
	public static boolean Repetido(String[][]reps, int t) {
		int s;
		for(int d=0; d<t; d++) {
			s=4;
			for(int v=1; v<5; v++) {
				if(reps[t][v]==reps[d][v]) {
					s=s-1;
					if(s==0) {
						return true;
					}
				}
			}
		}
		return false;
	}
//}
	
////////////////////////////////////////////M E N U /////////////////////////////////////////////////////
	
	//Nuevo jugador
	private static void neuePer(Perju pla) {
		try {
			File file=new File(ListPerju+pla.getPlayer()+".txt"); 
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			ObjectOutputStream ObOuSt=new ObjectOutputStream(new FileOutputStream(file));
			ObOuSt.writeObject(pla);
			ObOuSt.close();
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	//Perfil de jugador ya existente
	private static Perju cargPer (String Player) {
		try {
			File file=new File(ListPerju + Player +".txt"); 
			if(file.exists()) {
				ObjectInputStream ObInSt = new ObjectInputStream(new FileInputStream(file));
				Perju pla = (Perju) ObInSt.readObject();
				ObInSt.close();
				return pla;
			}
			else {
				System.out.println("El perfil no existe, crear uno nuevo");	
			}
		}
		catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	
	
}
