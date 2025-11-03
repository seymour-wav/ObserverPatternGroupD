public interface UiObserver {
  // Update pushes data
  public update(UiUpdate update) {

  }

  // onNotified pulls data
  public onNotified (UiSubjectRef ref, Channel ch) {

  }
}
