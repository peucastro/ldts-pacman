package pt.up.fe.ldts.pacman.audio;

import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private static AudioManager instance;
    private AudioPlayer mainMusic;
    private final Map<String, AudioPlayer> audios;
    private float masterVolume;

    private AudioManager() {
        this.audios = new HashMap<>();
        this.masterVolume = 0.5f;
    }

    public void addAudio(String key, String audioPath) {
        if (!audios.containsKey(key)) {
            AudioPlayer audio = new AudioPlayer(audioPath);
            audios.put(key, audio);
            audio.setVolume(audio.getVolume() * masterVolume);
        }
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void stopAllAudios() {
        audios.forEach((s, audioPlayer) -> audioPlayer.stopPlaying());
    }

    public void setMainMusic(AudioPlayer mainMusic) {
        mainMusic.setVolume(mainMusic.getVolume() * masterVolume);
        this.mainMusic = mainMusic;
    }

    public AudioPlayer getAudio(String key) {
        return audios.get(key);
    }

    public float getMasterVolume() {
        return masterVolume;
    }

    public void setMasterVolume(float volume) {
        if (volume <= 0 || volume > 1) return;
        if (mainMusic != null) mainMusic.setVolume(mainMusic.getVolume() * volume / masterVolume);
        audios.forEach((s, audio) -> audio.setVolume(audio.getVolume() * volume / masterVolume));
        this.masterVolume = volume;
    }
}
