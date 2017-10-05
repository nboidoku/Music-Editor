package cs3500.music.view;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;

import cs3500.music.model.Note;

/**
 * A MIDI representation of our Music Editor.
 */
class MidiView implements IView {
  private int maxBeat = 0;
  private final Synthesizer synth;
  private Sequencer seq;
  private Sequence sec;
  private IViewModel viewModel;
  private final MidiChannel[] midiChannels;
  private long savedTickPosition = 0;
  static StringBuilder midiResult = new StringBuilder("");

  MidiView(IViewModel viewModel) {
    Synthesizer synthTemp = null;

    Sequencer seqTemp = null;
    this.viewModel = viewModel;
    try {
      synthTemp = MidiSystem.getSynthesizer();
      seqTemp = MidiSystem.getSequencer();

      synthTemp.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

    this.synth = synthTemp;
    this.seq = seqTemp;
    try {
      this.sec = new Sequence(Sequence.PPQ, 20);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }

    this.midiChannels = synth.getChannels();
    midiResult = new StringBuilder("");
    for (Note note : viewModel.getMusicalValues()) {
      if (note.endsAtBeat() > maxBeat) {
        maxBeat = note.endsAtBeat();
      }
    }

  }

  MidiView(IViewModel viewModel, long tickPosition) {
    this(viewModel);
    this.savedTickPosition = tickPosition;
  }


  /*
   * This constructor is used for testing the Midi View with a mock implementation.
   *
   * @param viewModel    the view Model used.
   * @param stringResult the string result.
    */
  MidiView(IViewModel viewModel, StringBuilder stringResult) {
    MidiView.midiResult = stringResult;
    Synthesizer synthTemp = null;

    Sequencer seqTemp = null;
    this.viewModel = viewModel;
    try {
      synthTemp = new MockMidiDevice();
      seqTemp = MidiSystem.getSequencer();


      synthTemp.open();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    try {
      this.sec = new Sequence(Sequence.PPQ, 20);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    this.synth = synthTemp;
    this.seq = seqTemp;
    this.midiChannels = synth.getChannels();


    for (Note note : viewModel.getMusicalValues()) {
      if (note.endsAtBeat() > maxBeat) {
        maxBeat = note.endsAtBeat();
      }
    }
  }


  /**
   * Initializes the Instruments to the appropriate midi channel.
   */
  private void initializeInstruments() {
    for (int x = 0; x < 16; x = x + 1) {
      midiChannels[x].programChange(x + 1);
    }
  }

  /**
   * Plays the notes in the songs and initializes the sleep thread to complement the piece.
   */
  public void play() {

    try {
      playNotes();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }


  }


  /**
   * Iterates through the notes in the model and plays each note.
   */
  private void playNotes() throws InvalidMidiDataException {
    this.initializeInstruments();

    Track track = this.sec.createTrack();
    try {
      this.seq.open();

    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    for (Note note : viewModel.getMusicalValues()) {
      ShortMessage start = new ShortMessage(ShortMessage.NOTE_ON, note.instrument - 1,
              note.getPitchOctave(), note.volume);
      ShortMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, note.instrument - 1,
              note.getPitchOctave(), note.volume);
      MidiEvent event = new MidiEvent(start, 20 * note.getStartBeat());
      MidiEvent event1 = new MidiEvent(stop, 20 * note.endsAtBeat());
      track.add(event);
      track.add(event1);
    }

    seq.setSequence(sec);
    seq.setLoopCount(0);
    seq.setTickPosition(savedTickPosition);
    seq.setTempoInMPQ(viewModel.getTempo());
    seq.start();

  }

  /**
   * Gets whether the seq is running.
   *
   * @Return a boolean representing state.
   */
  public boolean getSeqState() {
    return this.seq.isRunning();
  }

  /**
   * Stops the sequencer.
   */
  public void stopSeq(long savedTickPosition) {
    this.savedTickPosition = savedTickPosition;
    this.seq.stop();


  }

  /**
   * Starts the sequencer.
   */
  public void startSeq() {
    try {
      this.playNotes();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  /**
   * Restarts the sequencer.
   */
  public void restartSeq() {
    savedTickPosition = 0;

  }

  /**
   * Gets the tick position.
   *
   * @return a long representing tick position.
   */
  public long getTickPosition() {
    return seq.getTickPosition();
  }

  /**
   * Sets the tick position.
   * @param position: the position to be set.
   *
   */
  public void setTickPosition(long position) {
    this.seq.setTickPosition(position);
    seq.setTempoInMPQ(viewModel.getTempo());

  }


}

