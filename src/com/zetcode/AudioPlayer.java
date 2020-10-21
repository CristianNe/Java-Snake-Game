package com.zetcode;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class AudioPlayer {
    
    private final File sound;
    private Clip clip;

    public AudioPlayer(String soundfile) {
	this.sound = new File(soundfile);
	preloadSound();
    }

    public void play() throws Exception {
	clip.start();
    }

    private void reset() {
	clip.close();
	preloadSound();
    }

    private void preloadSound() {
	try {
	    AudioInputStream sourceStream = AudioSystem.getAudioInputStream(sound);
	    AudioFormat sourceFormat = sourceStream.getFormat();
	    AudioFormat targetFormat = new AudioFormat(sourceFormat.getEncoding(), sourceFormat.getSampleRate(), 16,
		    sourceFormat.getChannels(), sourceFormat.getChannels() * 2, sourceFormat.getFrameRate(),
		    sourceFormat.isBigEndian());
	    AudioInputStream convertedStream = AudioSystem.getAudioInputStream(targetFormat, sourceStream);

	    clip = AudioSystem.getClip();
	    clip.open(convertedStream);
	    clip.addLineListener(new LineListener() {
		@Override
		public void update(LineEvent myLineEvent) {
		    if (myLineEvent.getType() == LineEvent.Type.STOP)
			reset();
		}
	    });
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}