package pt.up.fe.ldts.pacman.audio;

import org.junit.jupiter.api.Test;

import javax.sound.sampled.Clip;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AudioPlayerTest {
    @Test
    void basicAudioLoading(){
        assertDoesNotThrow(() -> new AudioPlayer("Audio/ghostEaten.wav"));
    }

    @Test
    void audioLoadingException(){
        assertThrows(AssertionError.class,() -> new AudioPlayer("NeverGonnaGiveYouUp.mp4"));
    }

    @Test
    void playOnceClipDependency() throws NoSuchFieldException, IllegalAccessException {
        Clip mockClip = mock(Clip.class);
        AudioPlayer audioPlayer = new AudioPlayer("Audio/ghostEaten.wav");
        Field privateField = AudioPlayer.class.getDeclaredField("audio");
        privateField.setAccessible(true);

        privateField.set(audioPlayer, mockClip);
        audioPlayer.playOnce();

        verify(mockClip, times(1)).stop();
        verify(mockClip, times(1)).setFramePosition(anyInt());
        verify(mockClip, times(1)).start();
    }

    @Test
    void playInLoopClipDependency() throws NoSuchFieldException, IllegalAccessException {
        Clip mockClip = mock(Clip.class);
        AudioPlayer audioPlayer = new AudioPlayer("Audio/ghostEaten.wav");
        Field privateField = AudioPlayer.class.getDeclaredField("audio");
        privateField.setAccessible(true);

        privateField.set(audioPlayer, mockClip);
        audioPlayer.playInLoop();

        verify(mockClip, times(1)).stop();
        verify(mockClip, times(1)).setFramePosition(anyInt());
        verify(mockClip, times(1)).loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Test
    void setAndGetVolume(){
        AudioPlayer audioPlayer = new AudioPlayer("Audio/ghostEaten.wav");

        audioPlayer.setVolume(0.5f);

        assertEquals(0.5f, audioPlayer.getVolume());
    }

    @Test
    void setAndGetInvalidVolume(){
        AudioPlayer audioPlayer = new AudioPlayer("Audio/ghostEaten.wav");

        audioPlayer.setVolume(1f);

        assertEquals(1f, audioPlayer.getVolume());

        audioPlayer.setVolume(-1f);

        assertEquals(1f, audioPlayer.getVolume());
    }

    @Test
    void isPlaying(){
        AudioPlayer audioPlayer = new AudioPlayer("Audio/ghostEaten.wav");

        assertFalse(audioPlayer.isPlaying());
    }

    @Test
    void getException(){
        Exception exception = assertThrows(Exception.class, () -> new AudioPlayer("Audio/silence.mp4"));

        assertEquals("Could not open audio: Audio/silence.mp4", exception.getMessage());
    }


}
