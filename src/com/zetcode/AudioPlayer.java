package com.zetcode;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioPlayer {
	
	private final String soundfile;
	
	public AudioPlayer(String soundfile) 
	{
		this.soundfile = soundfile;
	}
	
	public void play() throws Exception
	{
		try {
			Clip clip = AudioSystem.getClip();
			InputStream inputStream = new BufferedInputStream(new FileInputStream(soundfile));
			AudioInputStream audioInput = convertToPCM(AudioSystem.getAudioInputStream(inputStream));
			clip.open(audioInput);
			clip.start();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private AudioInputStream convertToPCM(AudioInputStream audioInputStream)
    {
        AudioFormat m_format = audioInputStream.getFormat();
        AudioFormat targetFormat = new AudioFormat(m_format.getEncoding(),
        		m_format.getSampleRate(), 16,
        		m_format.getChannels(), m_format.getChannels() * 2,
        		m_format.getSampleRate(), m_format.isBigEndian());
        audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
        
        return audioInputStream;
    }
}


