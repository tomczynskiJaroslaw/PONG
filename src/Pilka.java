import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


public class Pilka {
	//dodac mozliwosc wielu pilek w grze?
	static int x = 20;
	static int y = 20;
	static int kierunekX =-1;
	static int kierunekY =-1;
	static int mocX=1;
	static int mocY=1;
	static int realizacjaKierunkuX=0;
	static int realizacjaKierunkuY=0;
	static int rozmiar=10;
	/**przestaje zmieniac x i y na stale utrzymujac wspolrzedne Paletki
	 * @see Paletka */
	static boolean pilkaStoi=false;
	
	static ArrayList<Integer[]> tmpLista = new ArrayList<Integer[]>();//tnp
	
	public static void ruch() {
		Integer i[] = {x,y};//tmp
		tmpLista.add(i);//tmp
		
		int okienkoX = Okienko.plutno.getWidth();
		int okienkoY = Okienko.plutno.getHeight();
		
		//prawo
		if (okienkoX==x+rozmiar){
			kierunekX=-kierunekX;
			x--;//!
			Dzwiek.odtwarzaj("Realistic.wav");
		}
		//lewo
		if (x==0){
			kierunekX=-kierunekX;
			x++;//!
			Dzwiek.odtwarzaj("Realistic.wav");
		}
		//gora
		if ( y==0){
			kierunekY=-kierunekY;
			y++;//!
			Dzwiek.odtwarzaj("Realistic.wav");
		}
		//zaklejonaPrzepasc
		if (okienkoY-20==y+rozmiar && Promocja.mapaPromocji.containsKey("zaklejonaPrzepasc")){
			
			kierunekY=-kierunekY;
			y--;//!
			Dzwiek.odtwarzaj("Realistic.wav");
		}
		//dol
		if (okienkoY==y){
			Gra.liczbaZyc--;
			Pilka.pilkaStoi=true;
			
		}
		if (!pilkaStoi){
			if (realizacjaKierunkuX<=mocX){
				realizacjaKierunkuX++;
				x+=kierunekX;
			}else if(realizacjaKierunkuY<=mocY){
				realizacjaKierunkuY++;
				y+=kierunekY;
				
			}else{
				realizacjaKierunkuX=0;
				realizacjaKierunkuY=0;
				x+=kierunekX;
				y+=kierunekY;
			}
//			x+=kierunekX;
//			y+=kierunekY;
		}else{
			x=Paletka.x+(Paletka.rozmiarX/2);
			y=Paletka.y-rozmiar;
		}
	}
}
