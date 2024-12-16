package pt.up.fe.ldts.pacman.audio;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private AudioPlayer mainMusic;
    private Map<String,AudioPlayer> audios;

    public AudioManager(){
        this.audios = new HashMap<>();
    }


    public void addAudio(String key, AudioPlayer audio){
        audios.put(key,audio);
    }

    public void stopAllAudios(){
        audios.forEach((s, audioPlayer) -> audioPlayer.stopPlaying());
    }

    public AudioPlayer getMainMusic() {
        return mainMusic;
    }

    public void setMainMusic(AudioPlayer mainMusic) {
        this.mainMusic = mainMusic;
    }

    public boolean audioExists(String key){
        return audios.containsKey(key);
    }

    public AudioPlayer getAudio(String key){
        return audios.get(key);
    }
}
