package cs301.power_grid;

import org.junit.Test;
import android.graphics.Color;
import static org.junit.Assert.*;

/**
 * Created by guilianluchini on 3/23/17.
 */
public class PowerStateTest {
    @Test
    public void getPlayerId() throws Exception {
        PowerState powerState = new PowerState();

        assertTrue(powerState.getPlayerId() == 0);


    }

    @Test
    public void getGamePhase() throws Exception {
        PowerState powerState = new PowerState();

        assertTrue(powerState.getGamePhase() == 0);
    }

    @Test
    public void setPlayerId() throws Exception {
        PowerState powerState = new PowerState();
        powerState.setPlayerId(20);

        assertTrue(powerState.getPlayerId() == 20);
    }

    @Test
    public void initiateCityScape() throws Exception {
        PowerState powerState = new PowerState();
        assertTrue(powerState.getAvailCities().size() == 20);
    }

    @Test
    public void initiatePowerPlants() throws Exception {
        PowerState powerState = new PowerState();
        assertTrue(powerState.getAvailPowerplant().size() == 42);
    }

    @Test
    public void scramblePlants() throws Exception {
        PowerState powerState = new PowerState();
        int cost = powerState.getAvailPowerplant().get(20).getCost();
        int cost2 = powerState.getAvailPowerplant().get(5).getCost();
        powerState.scramblePlants();

        assertTrue(cost != powerState.getAvailPowerplant().get(20).getCost());

        assertTrue(cost2 == powerState.getAvailPowerplant().get(5).getCost());
    }

}