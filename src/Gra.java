import java.io.File;

/**glowne parametry gry wygrana,przegrana,liczbaZyc...*/
public class Gra {
	static int liczbaZyc = 3;
	/**nazwa pliku z zapisanymi poziomami gry (mapami)*/
	static String prefix = "plik";
	static int level =1;
	static boolean przegrana = false;
	static boolean wygrana = false;
	public static void ruch(){
		if (liczbaZyc==0){
//			Anim2.thread.stop();//!!!!!!!!!!!!!
			Anim2.aktywny=false;
			zerujPoziom();
			przegrana=true;
			liczbaZyc=3;
		}
		if (Klocek.klocki.size()==0){
//			Anim2.thread.stop();//!!!!!!!!!!!!!!!
			Anim2.aktywny=false;
			level++;
			zerujPoziom();
		}
	}
	public static void zerujPoziom(){
		
		Klocek.klocki.clear();
		Promocja.mapaPromocji.clear();
		Spada.listaSpadajacych.clear();
		if (new File(prefix+level).exists()){
			Klocek.wczytajKlocki(prefix+level);
			Pilka.pilkaStoi=true;
			Paletka.x=Okienko.getSzerokosc()/2;
			Paletka.y=Paletka.domyslnyY;
			Pilka.x=Paletka.x+(Paletka.rozmiarX/2);
			Pilka.y=Paletka.y-Pilka.rozmiar;
			Okienko.ramka.repaint();
		}else{
			wygrana=true;
			Okienko.ramka.repaint();
			level=1;
			zerujPoziom();//rekurencja??
			liczbaZyc=3;
		}
	}
}
