package pt.up.fe.ldts.pacman.audio;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private AudioPlayer mainMusic;
    private final Map<String,AudioPlayer> audios;
    private float masterVolume;

    public AudioManager(){
        this.audios = new HashMap<>();
        this.masterVolume = 0.5f;
    }


    public void addAudio(String key, AudioPlayer audio){
        audios.put(key,audio);
        audio.setVolume(audio.getVolume()*masterVolume);
    }

    public void stopAllAudios(){
        audios.forEach((s, audioPlayer) -> audioPlayer.stopPlaying());
    }

    public AudioPlayer getMainMusic() {
        return mainMusic;
    }

    public void setMainMusic(AudioPlayer mainMusic) {
        mainMusic.setVolume(mainMusic.getVolume()*masterVolume);
        this.mainMusic = mainMusic;
    }

    public boolean audioExists(String key){
        return audios.containsKey(key);
    }

    public AudioPlayer getAudio(String key){
        return audios.get(key);
    }

    public void setMasterVolume(float volume){
        if(volume <= 0 || volume > 1) return;
        if(mainMusic != null) mainMusic.setVolume(mainMusic.getVolume()*volume/masterVolume);
        audios.forEach((s, audio) -> audio.setVolume(audio.getVolume()*volume/masterVolume));
        this.masterVolume = volume;
    }

    public float getMasterVolume() {
        return masterVolume;
    }
}
