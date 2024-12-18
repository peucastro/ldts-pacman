package pt.up.fe.ldts.pacman.model.menu;

import pt.up.fe.ldts.pacman.gui.GUI;

public interface MenuOptions {
    boolean ResolutionSelected();
    boolean MasterVolumeSelected();
    void setMasterVolume(float volume);
    void setResolution(GUI.SCREEN_RESOLUTION newResolution);
}
