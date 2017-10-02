package battleship;

/**
 * {@code Battleship} class describes a ship of length 4.
 *
 * @author Andrei Bogomja
 */
public class Battleship extends Ship
{
    /** This ship length, constant value. */
    private final static int BATTLESHIP_LENGTH = 4;

    /**
     * Constructs a {@code Battleship} object. The length value
     * of battleship always equals 4.
     */
    public Battleship()
    {
        super(BATTLESHIP_LENGTH);
    }

    /**
     * Overrides abstract method {@link Ship#getShipType()} and
     * returns the description of this type of ship.
     *
     * @return "battleship" string
     */
    @Override
    public String getShipType()
    {
        return "battleship";
    }
}
