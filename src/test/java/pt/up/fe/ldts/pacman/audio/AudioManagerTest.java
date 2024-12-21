package pt.up.fe.ldts.pacman.audio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AudioManagerTest {
    private AudioManager audioManager;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        audioManager = AudioManager.getInstance();
        Field privateField = AudioManager.class.getDeclaredField("audios");
        privateField.setAccessible(true);

        privateField.set(audioManager, new HashMap<>()); //clear the audio manager
        audioManager.setMasterVolume(1f);
    }

    @Test
    void basicAudioManagerCreation(){
        assertDoesNotThrow(AudioManager::getInstance);
    }

    @Test
    void setMainMusic() throws NoSuchFieldException, IllegalAccessException {
        AudioPlayer mockAudioPlayer = mock(AudioPlayer.class);
        Field privateField = AudioManager.class.getDeclaredField("mainMusic");
        privateField.setAccessible(true);

        audioManager.setMainMusic(mockAudioPlayer);

        assertEquals(mockAudioPlayer, privateField.get(audioManager));
    }

    @Test
    void addAudio() throws NoSuchFieldException, IllegalAccessException {
        Field privateField = AudioManager.class.getDeclaredField("audios");
        privateField.setAccessible(true);

        audioManager.addAudio("audio1", "Audio/ghostEaten.wav"); //this audio is added: size = 1
        audioManager.addAudio("audio2", "Audio/ghostEaten.wav"); //this audio is also added (different key): size = 2
        audioManager.addAudio("audio2", "Audio/ghostEaten.wav"); //this audio is not added (key already exists): size = 2

        assertEquals(2, ((Map<String, AudioPlayer>)privateField.get(audioManager)).size());
    }

    @Test
    void getAudio(){
        audioManager.addAudio("audio1", "Audio/ghostEaten.wav");
        audioManager.addAudio("audio2", "Audio/ghostEaten.wav");

        assertNotNull(audioManager.getAudio("audio1")); //audio with this key exists
        assertNotNull(audioManager.getAudio("audio2")); //audio with this key exists
        assertNull(audioManager.getAudio("audio3")); //audio with this key does not exist
    }

    @Test
    void setAndGetMasterVolume(){
        audioManager.setMasterVolume(0.5f);

        assertEquals(0.5f, audioManager.getMasterVolume());
    }

    @Test
    void setMasterVolumeOnAllAudios(){
        audioManager.setMasterVolume(0.5f);

        audioManager.addAudio("audio1", "Audio/ghostEaten.wav");
        audioManager.addAudio("audio2", "Audio/ghostEaten.wav");
        AudioPlayer audio1 = audioManager.getAudio("audio1");
        AudioPlayer audio2 = audioManager.getAudio("audio2");


        assertEquals(0.5f, audio1.getVolume());
        assertEquals(0.5f, audio2.getVolume());
    }

    @Test
    void setInvalidMasterVolumeOnAllAudios() {
        audioManager.addAudio("audio1", "Audio/ghostEaten.wav");
        audioManager.addAudio("audio2", "Audio/ghostEaten.wav");
        AudioPlayer audio1 = audioManager.getAudio("audio1");
        AudioPlayer audio2 = audioManager.getAudio("audio2");
        audio2.setVolume(0.5f);

        audioManager.setMasterVolume(2f);

        assertEquals(1f,audioManager.getMasterVolume());
        assertEquals(1f, audio1.getVolume());
        assertEquals(0.5f, audio2.getVolume());
    }
}
