package cs3500.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import cs3500.music.util.CompositionBuilder;


/**
 * Class for my music editor.
 */
public class MusicEditorModel implements IMusicEditor {

  private HashSet<Note> musicalNotes;
  private int tempo;

  public MusicEditorModel() {
    this.musicalNotes = new HashSet<>();
  }

  /**
   * Constructor for testing with a default tempo.
   * @param tempo the tempo of the music editor
   */
  public MusicEditorModel(int tempo) {
    this.musicalNotes = new HashSet<>();
    this.tempo = tempo;
  }

  MusicEditorModel(ArrayList<Note> notes, int tempo) {
    this.musicalNotes = new HashSet<>();
    musicalNotes.addAll(notes);
    this.tempo = tempo;
  }

  @Override
  public void addNote(Note note) {
    this.musicalNotes.add(note);
  }

  @Override
  public void removeNote(Note note) {
    this.musicalNotes.remove(note);
  }

  @Override
  public void mergeMusic(IMusicEditor musicToJoin) {
    this.musicalNotes.addAll(musicToJoin.musicalValues());
  }

  @Override
  public void appendMusic(IMusicEditor musicToAppend) {
    int thisLastBeat = this.lengthOfMusic();
    for (Note note : musicToAppend.musicalValues()) {
      note.addToStartTime(thisLastBeat);
    }
    this.mergeMusic(musicToAppend);
  }

  @Override
  public int lengthOfMusic() {
    int max = 0;
    for (Note notes : this.musicalNotes) {
      int temp = notes.endsAtBeat();
      if (temp > max) {
        max = temp;
      }
    }
    return max;
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public int musicRange() {
    Note least = this.getLeastNote();
    Note highest = this.getHighestNote();
    return least.getRange(highest);
  }

  @Override
  public List<Note> musicalValues() {
    List<Note> result = new ArrayList<>();
    result.addAll(this.musicalNotes);
    Collections.sort(result, new Comparator<Note>() {
      @Override
      public int compare(Note o1, Note o2) {
        return o1.getStartBeat() - o2.getStartBeat();
      }
    });
    return result;
  }


  @Override
  public Note getLeastNote() {
    Note min = new Note(Pitch.B, 100000000, 0, 0, 0, 0);
    if (this.musicalNotes.isEmpty()) {
      throw new IllegalArgumentException("Empty");
    } else {
      for (Note note : musicalNotes) {
        if (min.isGreaterThan(note)) {
          min = note;
        }
      }
    }
    return min;
  }

  /**
   * Gets the highest note in this music editor.
   *
   * @return the highest note
   */
  @Override
  public Note getHighestNote() {
    Note max = new Note(Pitch.C, Integer.MIN_VALUE, 0, 0, 0, 0);
    if (this.musicalNotes.isEmpty()) {
      throw new IllegalArgumentException("Empty");
    } else {
      for (Note note : musicalNotes) {
        if (note.isGreaterThan(max)) {
          max = note;
        }
      }
    }
    return max;
  }

  public static final class Builder implements CompositionBuilder<IMusicEditor> {

    private ArrayList<Note> notes = new ArrayList<>();
    private int tempo = 0;

    @Override
    public MusicEditorModel build() {
      return new MusicEditorModel(notes, tempo);
    }

    @Override
    public CompositionBuilder<IMusicEditor> setTempo(int tempo) {
      if (tempo < 1) {
        throw new IllegalArgumentException("Tempo cannot be less than 1");
      } else {
        this.tempo = tempo;
      }
      return this;
    }

    @Override
    public CompositionBuilder<IMusicEditor> addNote(int start, int end, int instrument, int
            pitch, int volume) {
      this.notes.add(new Note(Pitch.intToPitch(pitch), pitch / 12, start, end - start, volume,
              instrument));
      return this;
    }
  }

}

