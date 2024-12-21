package pt.up.fe.ldts.pacman.viewer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.up.fe.ldts.pacman.gui.GUI;
import pt.up.fe.ldts.pacman.model.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ModelViewerTest {

    private ModelViewer<Object> modelViewer;
    private GUI mockGui;
    private Viewer<Element> mockElementViewer;
    private Element mockElement;
    private Map<Class<?>, Viewer<Element>> viewers;

    @BeforeEach
    void setUp() {
        mockGui = mock(GUI.class);
        mockElement = mock(Element.class);
        mockElementViewer = mock(Viewer.class);

        viewers = new HashMap<>();
        viewers.put(mockElement.getClass(), mockElementViewer);

        modelViewer = new ModelViewer<Object>(viewers) {
            @Override
            public void drawElements(GUI gui, Object model, long frameCount) {
            }
        };
    }

    @Test
    void testDrawElementWithModelFirstTime() throws IOException {
        modelViewer.drawElement(mockGui, new Object(), 0);

        verify(mockGui, times(1)).clear();

        verify(mockGui, times(1)).refresh();
    }

    @Test
    void testDrawElementWithModelSubsequentTimes() throws IOException {
        modelViewer.drawElement(mockGui, new Object(), 0);

        modelViewer.drawElement(mockGui, new Object(), 1);

        verify(mockGui, times(1)).clear();

        verify(mockGui, times(2)).refresh();
    }

    @Test
    void testDrawElementWithDifferentElement() throws IOException {
        Element mockAnotherElement = mock(Element.class);
        Viewer<Element> mockAnotherElementViewer = mock(Viewer.class);
        viewers.put(mockAnotherElement.getClass(), mockAnotherElementViewer);

        modelViewer.drawElement(mockGui, new Object(), 0);

        verify(mockElementViewer, never()).drawElement(mockGui, mockElement, 0);

        verify(mockGui, times(1)).refresh();
    }

    @Test
    void testDrawElementDoesNotClearAfterFirstTime() throws IOException {
        modelViewer.drawElement(mockGui, new Object(), 0);

        modelViewer.drawElement(mockGui, new Object(), 1);

        verify(mockGui, times(1)).clear();

        verify(mockGui, times(2)).refresh();
    }
}
