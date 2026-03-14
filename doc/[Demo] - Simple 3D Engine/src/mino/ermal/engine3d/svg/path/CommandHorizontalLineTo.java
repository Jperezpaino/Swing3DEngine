package mino.ermal.engine3d.svg.path;

import java.util.Objects;

import mino.ermal.engine3d.svg.Position;
import mino.ermal.engine3d.svg.path.interfaces.PathCommand;

  /**
   *
   */
  public class CommandHorizontalLineTo
      implements PathCommand {

    /**
     *
     */
    public static final String TAG = "H";

    /**
     *
     */
    private Position position;

    /**
     *
     */
    public CommandHorizontalLineTo() {
      this(
        new Position()
      );
    }

    /**
     *
     * @param _position {@code Position}
     */
    public CommandHorizontalLineTo(
        final CommandHorizontalLineTo _command) {
      this( 
        _command.position()
      );
    }

    /**
     *
     * @param _position {@code Position}
     */
    public CommandHorizontalLineTo(
        final Position _position) {
      super();
      this.position = _position;
    }

    /**
     *
     * @return {@code Position}
     */
    public final Position position() {
      return this.position;
    }

    /**
     *
     * @param _position {@code Position}
     */
    public final void position(
        final Position _position) {
      this.position = _position;
    }

    /**
     *
     * @return {@code CommandMoveTo}
     */
    public final CommandHorizontalLineTo command() {
      return this;
    }

    /**
     *
     * @param _command {@code CommandMoveTo}
     */
    public final void command(
        final CommandHorizontalLineTo _command) {
      this.command(
        _command.position()
      );
    }

    /**
     *
     * @param _position {@code Position}
     */
    public final void command(
        final Position _position) {
      this.position = _position;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
      return Objects.hash(
        this.position
      );
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(
        final Object _command) {
      if (this == _command) {
        return true;
      }
      if ((_command == null)
       || (this.getClass() != _command.getClass())) {
        return false;
      }
      final CommandHorizontalLineTo command
        = (CommandHorizontalLineTo) _command;
      return
        command.position().equals(this.position);
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
      return String.format(
        "Command [position=%s]",
        this.position().toString()
      );
    }

  }
