import java.util.*;

  /**
   * Registers an observer from one or more channel.
   */
public interface UiSubject {
  void register(UiObserver o, Channel ch);

  /**
   * Unregisters an observer from one or more channel.
   */
  void unregister(UiObserver o, Channel ch);

/**
 * Notify observer subscribed to a given channel.
 */
  void notify(Channel ch);

  boolean modeToggle();
}
