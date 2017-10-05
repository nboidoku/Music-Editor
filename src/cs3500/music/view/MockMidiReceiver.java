package cs3500.music.view;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * A mock Midi receiver.
 */
class MockMidiReceiver implements Receiver {
  @Override
  public void send(MidiMessage message, long timeStamp) {
    ShortMessage shortMessage = (ShortMessage) message;

    MidiView.midiResult
            .append("Time Stamp: ")
            .append(timeStamp)
            .append(" ")
            .append("Pitch: ")
            .append(shortMessage.getData1())
            .append(" ")
            .append("Volume: ")
            .append(shortMessage.getData2())
            .append("\n");
  }

  @Override
  public void close() {
    MidiView.midiResult.append("Result closed");
  }
}
