package cs3500.music.mocks;

import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;



/**
 * A mock of a reciever, for testing purposes.
 */
public class MockReciever implements Receiver {
  StringBuilder stringBuilder;



  public MockReciever(StringBuilder stringBuilder) {
    this.stringBuilder = stringBuilder;
  }
  /**
   * Sends a MIDI message and time-stamp to this receiver.
   * If time-stamping is not supported by this receiver, the time-stamp
   * value should be -1.
   *
   * @param message   the MIDI message to send
   * @param timeStamp the time-stamp for the message, in microseconds.
   * @throws IllegalStateException if the receiver is closed
   */
  @Override
  public void send(MidiMessage message, long timeStamp) {
    this.stringBuilder.append(message.toString() + ":timestamp:" + timeStamp);
  }

  /**
   * Indicates that the application has finished using the receiver, and
   * that limited resources it requires may be released or made available.
   *
   * <p>If the creation of this <code>Receiver</code> resulted in
   * implicitly opening the underlying device, the device is
   * implicitly closed by this method. This is true unless the device is
   * kept open by other <code>Receiver</code> or <code>Transmitter</code>
   * instances that opened the device implicitly, and unless the device
   * has been opened explicitly. If the device this
   * <code>Receiver</code> is retrieved from is closed explicitly by
   * calling {@link MidiDevice#close MidiDevice.close}, the
   * <code>Receiver</code> is closed, too.  For a detailed
   * description of open/close behaviour see the class description
   * of {@link MidiDevice MidiDevice}.
   *
   * @see MidiSystem#getReceiver
   */
  @Override
  public void close() {
    //TODO
  }
}
