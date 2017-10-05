package cs3500.music.view;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.controller.Controller;
import cs3500.music.model.IMusicEditor;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;

import static org.junit.Assert.assertEquals;

/**
 * Test for the MidiView.
 */
public class MidiViewTest {


  @Test
  public void testMidi() {
    IMusicEditor model = new MusicEditorModel(1);
    model.addNote(new Note(Pitch.C, 0, 10, 15, 5, 2));
    IViewModel viewModel = new ViewModel(model);
    StringBuilder sb = new StringBuilder("");
    IView view = new MidiView(viewModel, sb);
    view.play();
    assertEquals("Time Stamp: 10 Pitch: 0 Volume: 5\n" +
            "Time Stamp: 25 Pitch: 0 Volume: 5\n", MidiView.midiResult.toString());
  }

  @Test
  public void testMidi2() {
    IMusicEditor model = new MusicEditorModel(1);
    model.addNote(new Note(Pitch.C, 0, 10, 15, 5, 2));
    model.addNote(new Note(Pitch.C, 1, 8, 11, 4, 2));
    IViewModel viewModel = new ViewModel(model);
    StringBuilder sb = new StringBuilder("");
    IView view = new MidiView(viewModel, sb);
    view.play();
    assertEquals("Time Stamp: 8 Pitch: 12 Volume: 4\n" +
            "Time Stamp: 19 Pitch: 12 Volume: 4\n" +
            "Time Stamp: 10 Pitch: 0 Volume: 5\n" +
            "Time Stamp: 25 Pitch: 0 Volume: 5\n", MidiView.midiResult.toString());
  }

  @Test
  public void testMidi3() {
    IMusicEditor model = new MusicEditorModel(1);
    model.addNote(new Note(Pitch.C, 0, 10, 15, 5, 2));
    model.addNote(new Note(Pitch.C, 1, 8, 11, 4, 2));
    model.addNote(new Note(Pitch.C, 4, 15, 5, 6, 2));
    IViewModel viewModel = new ViewModel(model);
    StringBuilder sb = new StringBuilder("");
    IView view = new MidiView(viewModel, sb);
    view.play();
    assertEquals("Time Stamp: 8 Pitch: 12 Volume: 4\n" +
            "Time Stamp: 19 Pitch: 12 Volume: 4\n" +
            "Time Stamp: 10 Pitch: 0 Volume: 5\n" +
            "Time Stamp: 25 Pitch: 0 Volume: 5\n" +
            "Time Stamp: 15 Pitch: 48 Volume: 6\n" +
            "Time Stamp: 20 Pitch: 48 Volume: 6\n", MidiView.midiResult.toString());
  }

  @Test
  public void testMary() {

    CompositionBuilder<IMusicEditor> builder = new MusicEditorModel.Builder();
    File file = new File("mary-little-lamb.txt");
    Readable read = null;

    try {
      read = new FileReader(file);
    } catch (
            FileNotFoundException e) {
      e.printStackTrace();
    }

    IMusicEditor model = MusicReader.parseFile(read, builder);
    IViewModel viewModel = new ViewModel(model);
    StringBuilder string = new StringBuilder("");
    IView view = new MidiView(viewModel, string);
    Controller controller = new Controller(view);
    controller.execute();
    assertEquals("Time Stamp: 0 Pitch: 55 Volume: 70\n" +
                    "Time Stamp: 1400000 Pitch: 55 Volume: 70\n" +
                    "Time Stamp: 0 Pitch: 64 Volume: 72\n" +
                    "Time Stamp: 400000 Pitch: 64 Volume: 72\n" +
                    "Time Stamp: 400000 Pitch: 62 Volume: 72\n" +
                    "Time Stamp: 800000 Pitch: 62 Volume: 72\n" +
                    "Time Stamp: 800000 Pitch: 60 Volume: 71\n" +
                    "Time Stamp: 1200000 Pitch: 60 Volume: 71\n" +
                    "Time Stamp: 1200000 Pitch: 62 Volume: 79\n" +
                    "Time Stamp: 1600000 Pitch: 62 Volume: 79\n" +
                    "Time Stamp: 1600000 Pitch: 55 Volume: 79\n" +
                    "Time Stamp: 3000000 Pitch: 55 Volume: 79\n" +
                    "Time Stamp: 1600000 Pitch: 64 Volume: 85\n" +
                    "Time Stamp: 2000000 Pitch: 64 Volume: 85\n" +
                    "Time Stamp: 2000000 Pitch: 64 Volume: 78\n" +
                    "Time Stamp: 2400000 Pitch: 64 Volume: 78\n" +
                    "Time Stamp: 2400000 Pitch: 64 Volume: 74\n" +
                    "Time Stamp: 3000000 Pitch: 64 Volume: 74\n" +
                    "Time Stamp: 3200000 Pitch: 62 Volume: 75\n" +
                    "Time Stamp: 3600000 Pitch: 62 Volume: 75\n" +
                    "Time Stamp: 3200000 Pitch: 55 Volume: 77\n" +
                    "Time Stamp: 4800000 Pitch: 55 Volume: 77\n" +
                    "Time Stamp: 3600000 Pitch: 62 Volume: 77\n" +
                    "Time Stamp: 4000000 Pitch: 62 Volume: 77\n" +
                    "Time Stamp: 4000000 Pitch: 62 Volume: 75\n" +
                    "Time Stamp: 4800000 Pitch: 62 Volume: 75\n" +
                    "Time Stamp: 4800000 Pitch: 55 Volume: 79\n" +
                    "Time Stamp: 5200000 Pitch: 55 Volume: 79\n" +
                    "Time Stamp: 4800000 Pitch: 64 Volume: 82\n" +
                    "Time Stamp: 5200000 Pitch: 64 Volume: 82\n" +
                    "Time Stamp: 5200000 Pitch: 67 Volume: 84\n" +
                    "Time Stamp: 5600000 Pitch: 67 Volume: 84\n" +
                    "Time Stamp: 5600000 Pitch: 67 Volume: 75\n" +
                    "Time Stamp: 6400000 Pitch: 67 Volume: 75\n" +
                    "Time Stamp: 6400000 Pitch: 64 Volume: 73\n" +
                    "Time Stamp: 6800000 Pitch: 64 Volume: 73\n" +
                    "Time Stamp: 6400000 Pitch: 55 Volume: 78\n" +
                    "Time Stamp: 8000000 Pitch: 55 Volume: 78\n" +
                    "Time Stamp: 6800000 Pitch: 62 Volume: 69\n" +
                    "Time Stamp: 7200000 Pitch: 62 Volume: 69\n" +
                    "Time Stamp: 7200000 Pitch: 60 Volume: 71\n" +
                    "Time Stamp: 7600000 Pitch: 60 Volume: 71\n" +
                    "Time Stamp: 7600000 Pitch: 62 Volume: 80\n" +
                    "Time Stamp: 8000000 Pitch: 62 Volume: 80\n" +
                    "Time Stamp: 8000000 Pitch: 55 Volume: 79\n" +
                    "Time Stamp: 9600000 Pitch: 55 Volume: 79\n" +
                    "Time Stamp: 8000000 Pitch: 64 Volume: 84\n" +
                    "Time Stamp: 8400000 Pitch: 64 Volume: 84\n" +
                    "Time Stamp: 8400000 Pitch: 64 Volume: 76\n" +
                    "Time Stamp: 8800000 Pitch: 64 Volume: 76\n" +
                    "Time Stamp: 8800000 Pitch: 64 Volume: 74\n" +
                    "Time Stamp: 9200000 Pitch: 64 Volume: 74\n" +
                    "Time Stamp: 9200000 Pitch: 64 Volume: 77\n" +
                    "Time Stamp: 9600000 Pitch: 64 Volume: 77\n" +
                    "Time Stamp: 9600000 Pitch: 62 Volume: 75\n" +
                    "Time Stamp: 10000000 Pitch: 62 Volume: 75\n" +
                    "Time Stamp: 9600000 Pitch: 55 Volume: 78\n" +
                    "Time Stamp: 11200000 Pitch: 55 Volume: 78\n" +
                    "Time Stamp: 10000000 Pitch: 62 Volume: 74\n" +
                    "Time Stamp: 10400000 Pitch: 62 Volume: 74\n" +
                    "Time Stamp: 10400000 Pitch: 64 Volume: 81\n" +
                    "Time Stamp: 10800000 Pitch: 64 Volume: 81\n" +
                    "Time Stamp: 10800000 Pitch: 62 Volume: 70\n" +
                    "Time Stamp: 11200000 Pitch: 62 Volume: 70\n" +
                    "Time Stamp: 11200000 Pitch: 52 Volume: 72\n" +
                    "Time Stamp: 12800000 Pitch: 52 Volume: 72\n" +
                    "Time Stamp: 11200000 Pitch: 60 Volume: 73\n" +
                    "Time Stamp: 12800000 Pitch: 60 Volume: 73\n"
            , MidiView.midiResult.toString());
  }

}