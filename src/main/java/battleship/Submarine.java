package battleship;

/**
 * {@code Submarine} class describes a ship of length 1.
 *
 * @author Andrei Bogomja
 */
public class Submarine extends Ship
{
    /** This ship length, constant value. */
    private final static int SUBMARINE_LENGTH = 1;

    /**
     * Constructs a {@code Submarine} object. The length value
     * of submarine always equals 1.
     */
    public Submarine()
    {
        super(SUBMARINE_LENGTH);
    }

    /**
     * Overrides abstract method {@link Ship#getShipType()} and
     * returns the description of this type of ship.
     *
     * @return "submarine" string
     */
    @Override
    public String getShipType()
    {
        return "submarine";
    }
}
