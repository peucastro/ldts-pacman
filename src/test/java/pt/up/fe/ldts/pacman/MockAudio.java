package pt.up.fe.ldts.pacman;

import pt.up.fe.ldts.pacman.audio.AudioManager;
import pt.up.fe.ldts.pacman.audio.AudioPlayer;

import static org.mockito.Mockito.*;

public class MockAudio {
    public static AudioManager getMockAudioManager() {
        AudioManager mockAudioManager = mock(AudioManager.class);

        doNothing().when(mockAudioManager).addAudio(any(), any());
        doNothing().when(mockAudioManager).stopAllAudios();
        doReturn(mock(AudioPlayer.class)).when(mockAudioManager).getAudio(any());
        doNothing().when(mockAudioManager).setMasterVolume(anyFloat());
        when(mockAudioManager.getMasterVolume()).thenReturn(1f);

        return mockAudioManager;
    }
}
