package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that handles key handles.
 */
public class KeyboardHandler implements KeyListener {

  private final Map<Integer, Runnable> typed;
  private final Map<Integer, Runnable> pressed;
  private final Map<Integer, Runnable> released;

  KeyboardHandler(Map<Integer, Runnable> typed, Map<Integer, Runnable> pressed,
                  Map<Integer, Runnable> released) {
    this.typed = typed;
    this.pressed = pressed;
    this.released = released;
  }

  /**
   * Handles a key typed event.
   *
   * @param e a Key event.
   */
  @Override
  public void keyTyped(KeyEvent e) {
    if (this.typed.get(e.getKeyCode()) != null) {
      this.typed.get(e.getKeyCode()).run();
    }
  }

  /**
   * Handles a key pressed event.
   *
   * @param e a Key event.
   */
  @Override
  public void keyPressed(KeyEvent e) {
    if (this.pressed.get(e.getKeyCode()) != null) {
      this.pressed.get(e.getKeyCode()).run();
    }
  }

  /**
   * Handles a key released event.
   *
   * @param e a Key event.
   */
  @Override
  public void keyReleased(KeyEvent e) {
    if (this.released.get(e.getKeyCode()) != null) {
      this.released.get(e.getKeyCode()).run();
    }
  }


  public static final class Builder {
    private final Map<Integer, Runnable> typed = new HashMap<>();
    private final Map<Integer, Runnable> pressed = new HashMap<>();
    private final Map<Integer, Runnable> released = new HashMap<>();

    /**
     * Builds a key handler based on the hash maps.
     *
     * @return a key handler.
     */
    KeyboardHandler build() {
      return new KeyboardHandler(this.typed, this.pressed, this.released);
    }


    /**
     * Adds a runnable to key typed.
     *
     * @param keyCode  the key value for the runnable
     * @param runnable the runnable attached.
     * @return the builder.
     */
    public Builder addKeyTyped(int keyCode, Runnable runnable) {
      this.typed.put(keyCode, runnable);
      return this;
    }

    /**
     * Adds a runnable to key pressed.
     *
     * @param keyCode  the key value for the runnable
     * @param runnable the runnable attached.
     * @return the builder.
     */
    public Builder addKeyPressed(int keyCode, Runnable runnable) {
      this.pressed.put(keyCode, runnable);
      return this;
    }

    /**
     * Adds a runnable to key released.
     *
     * @param keyCode  the key value for the runnable
     * @param runnable the runnable attached.
     * @return the builder.
     */
    public Builder addKeyReleased(int keyCode, Runnable runnable) {
      this.released.put(keyCode, runnable);
      return this;
    }
  }

}
