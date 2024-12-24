package pt.up.fe.ldts.pacman.audio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AudioPlayerTest {
    private Clip mockClip;
    private AudioPlayer audioPlayer;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        mockClip = mock(Clip.class);
        audioPlayer = new AudioPlayer("Audio/ghostEaten.wav");
        Field privateField = AudioPlayer.class.getDeclaredField("audio");
        privateField.setAccessible(true);
        privateField.set(audioPlayer, mockClip);
    }

    @Test
    void basicAudioLoading(){
        assertDoesNotThrow(() -> new AudioPlayer("Audio/ghostEaten.wav"));
    }

    @Test
    void audioLoadingException() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> new AudioPlayer("NeverGonnaGiveYouUp.mp4"));
        assertTrue(exception.getMessage().contains("Could not open audio"));
    }


    @Test
    void playOnceClipDependency() throws NoSuchFieldException, IllegalAccessException {
        audioPlayer.playOnce();

        verify(mockClip, times(1)).stop();
        verify(mockClip, times(1)).setFramePosition(anyInt());
        verify(mockClip, times(1)).start();
    }

    @Test
    void playInLoopClipDependency() throws NoSuchFieldException, IllegalAccessException {
        audioPlayer.playInLoop();

        verify(mockClip, times(1)).stop();
        verify(mockClip, times(1)).setFramePosition(anyInt());
        verify(mockClip, times(1)).loop(Clip.LOOP_CONTINUOUSLY);
    }

    @Test
    void stopPlayingClipDependency(){
        audioPlayer.stopPlaying();

        verify(mockClip).stop();
    }

    @Test
    void isPlayingClipDependency(){
        audioPlayer.isPlaying();

        verify(mockClip).isActive();
    }

    @Test
    void setAndGetVolume(){
        AudioPlayer audioPlayer = new AudioPlayer("Audio/ghostEaten.wav");

        audioPlayer.setVolume(0.5f);
        assertEquals(0.5f, audioPlayer.getVolume());

        audioPlayer.setVolume(1f);
        assertEquals(1f, audioPlayer.getVolume());

        audioPlayer.setVolume(0.01f);
        assertEquals(0.01f, audioPlayer.getVolume());
    }

    @Test
    void setAndGetInvalidVolume(){
        AudioPlayer audioPlayer = new AudioPlayer("Audio/ghostEaten.wav");

        audioPlayer.setVolume(1f);
        assertEquals(1f, audioPlayer.getVolume());

        audioPlayer.setVolume(-1f);
        assertEquals(1f, audioPlayer.getVolume());

        audioPlayer.setVolume(0f);
        assertEquals(0f, audioPlayer.getVolume());
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

    @Test
    void stopAudio(){
        AudioPlayer audioPlayer = new AudioPlayer("Audio/silence.wav");
        audioPlayer.playInLoop();

        audioPlayer.stopPlaying();

        assertFalse(audioPlayer.isPlaying());
    }

    @Test
    void setVolumeFloatControl(){
        FloatControl mockFloatControl = mock(FloatControl.class);
        when(mockClip.getControl(any())).thenReturn(mockFloatControl);

        audioPlayer.setVolume(0.5f);

        verify(mockClip).getControl(FloatControl.Type.MASTER_GAIN);
        verify(mockFloatControl).setValue(20f * (float) Math.log10(0.5f));
    }
}
