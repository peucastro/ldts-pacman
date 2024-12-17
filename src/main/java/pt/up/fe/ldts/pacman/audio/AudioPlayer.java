package pt.up.fe.ldts.pacman.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

public class AudioPlayer {
    private final Clip audio;
    private float volume;

    public AudioPlayer(Clip audio){
        this.audio = audio;
        this.volume = 1f;
    }

    public AudioPlayer(String audioFilepath) {
        this.audio = loadAudioFile(audioFilepath);
        this.volume = 1f;
    }

    private Clip loadAudioFile(String audioFilepath){
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(audioFilepath);
            assert inputStream != null;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(inputStream);
            Clip audio = AudioSystem.getClip();
            audio.open(audioInputStream);
            inputStream.close();
            audioInputStream.close();
            return audio;
        } catch (Exception e) {
            throw new RuntimeException("Could not open audio: " + audioFilepath);
        }
    }

    public void playOnce(){
        audio.stop();
        audio.setFramePosition(0);
        audio.start();
    }

    public void stopPlaying(){
        audio.stop();
    }

    public void playInLoop(){
        audio.stop();
        audio.setFramePosition(0);
        audio.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void setVolume(float volume){
        if(volume < 0 || volume > 1) return;
        this.volume = volume;
        FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    public boolean isPlaying() {
        return audio.isActive();
    }

    public float getVolume() {
        return volume;
    }
}
