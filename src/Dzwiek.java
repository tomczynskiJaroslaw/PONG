import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.*;
import javax.swing.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
/**odtwarzanie dzwiekow w grze*/
public class Dzwiek  {
	/**MUTE Czy dzwiek ma byc odtwarzany?*/
	static boolean dzwiek = false;
	
	/**mapa dostepnych w grze dzwiekow*/
	static HashMap<String, Dzwiek> dzwieki = new HashMap<String, Dzwiek>();
	
	private Clip clip;
	private AudioInputStream audioIn;

	public Dzwiek(String d) {
		
		if (dzwieki.size() < 100) {
			try {
				Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
				Mixer mixer = AudioSystem.getMixer(mixerInfo[4]);
				// Open an audio input stream.
				URL url = this.getClass().getClassLoader().getResource(d);
				audioIn = AudioSystem.getAudioInputStream(url);
				// Get a sound clip resource.
				clip = AudioSystem.getClip();
				// Open audio clip and load samples from the audio input stream.
				
				// clip.start();

				clip.open(audioIn);
			
//				Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
//				Mixer mixer = AudioSystem.getMixer(mixerInfo[4]);
//				AudioInputStream stream = AudioSystem.getAudioInputStream(new File(d));
//				AudioFormat format = stream.getFormat();
//				DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat(), ((int) stream.getFrameLength()*format.getFrameSize()));
//				Clip clip = (Clip) mixer.getLine(info);
//				clip.open(stream);
//				FloatControl panControl = (FloatControl) clip.getControl(FloatControl.Type.PAN);
//				panControl.setValue(-1);
//				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//				double gain = 10D; // number between 0 and 1 (loudest)
//				float dB = (float)(Math.log(gain)/Math.log(10.0)*20.0);
//				gainControl.setValue(dB);
			
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
			dzwieki.put(d, this);
		}
	}
	/**odtwarza dzwiek o podanej nazwie, jesli jest on juz odtwarzany odtwarza dodatkowo "halas"
	 * @param d nazwa_dzwieku.wav
	 * */
	public static void odtwarzaj(String d) {
		if (dzwiek) {
			if (dzwieki.get(d).clip.getFramePosition() == dzwieki.get(d).clip
					.getFrameLength()) {
				Dzwiek.dzwieki.get("Boxing.wav").clip.setFramePosition(0);
				Dzwiek.dzwieki.get(d).clip.setFramePosition(0);
			} else {
				Dzwiek.dzwieki.get("Martian.wav").clip.stop();
			}
			Dzwiek.dzwieki.get(d).clip.start();
		}
	}
}
