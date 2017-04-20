package cs301.power_grid;

import android.graphics.Color;
import android.view.View;

import game.GameHumanPlayer;
import game.GameMainActivity;
import game.infoMsg.GameInfo;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */

public class PowerGridHumanPlayer extends GameHumanPlayer {
    //instance variables:

    //android activity we are running
    private GameMainActivity myActivity;
    //game state
    private PowerState powerState;

    private int selectNum = -1;
    private int bidValue = -1;
    private ResourceStore localStore = new ResourceStore();

    ArrayList<BuyCityAction> cityActionList = new ArrayList<BuyCityAction>();
    ArrayList<BuyResourceAction> actionList = new ArrayList<BuyResourceAction>();

    //GUI features
    private RelativeLayout activity;
    private Spinner resourcesSpinner;
    private Spinner powerPlantsSpinner;

    private TextView powerPlant1Cost;
    private TextView powerPlant1Type;
    private TextView powerPlant1Number;
    private TextView powerPlant1House;

    private TextView powerPlant2Cost;
    private TextView powerPlant2Type;
    private TextView powerPlant2Number;
    private TextView powerPlant2House;

    private TextView powerPlant3Cost;
    private TextView powerPlant3Type;
    private TextView powerPlant3Number;
    private TextView powerPlant3House;

    private TextView powerPlant4Cost;
    private TextView powerPlant4Type;
    private TextView powerPlant4Number;
    private TextView powerPlant4House;

    private TextView moneyTextView;
    private TextView coalNumView;
    private TextView oilNumView;
    private TextView trashNumView;
    private TextView nuclearNumView;

    private TextView costTextView1;
    private TextView typeTextView1;
    private TextView numberTextView1;
    private TextView housesTextView1;

    private TextView costTextView2;
    private TextView typeTextView2;
    private TextView numberTextView2;
    private TextView housesTextView2;

    private TextView costTextView3;
    private TextView typeTextView3;
    private TextView numberTextView3;
    private TextView housesTextView3;

    private TextView costTextView4;
    private TextView typeTextView4;
    private TextView numberTextView4;
    private TextView housesTextView4;

    private EditText bidVal;
    private TextView previousBid;

    private int basicGray = Color.rgb(214, 215, 215);
    private int prettyBlue = Color.rgb(0, 221, 255);
    private int opponentRed = Color.rgb(255,68,68);

    private Button okayButton;
    private Button passButton;
    private boolean guiIsReady = false;

    private boolean[] localCities = new boolean[20];
    //denver, seattle, sf, la, missoula, boise, phx, omaha, okc, dallas, hou, stlou, chi, mem, new orl, det, atl, miami, bos, nyc

    private Button[] cityButtons = new Button[20];

    //resource buttons
    private ImageButton[] coalButtons = new ImageButton[15];
    private ImageButton[] oilButtons = new ImageButton[10];
    private ImageButton[] uraniumButtons = new ImageButton[5];
    private ImageButton[] trashButtons = new ImageButton[15];

    private Button selectButton0;
    private Button selectButton1;
    private Button selectButton2;
    private Button selectButton3;

    //Constructor
    public PowerGridHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return (RelativeLayout) findViewById(R.id.activity_main);
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        myActivity = activity;
        // Load the layout resource for our GUI
        activity.setContentView(R.layout.activity_main);

        //spinners
        resourcesSpinner = (Spinner) findViewById(R.id.UserResourcesSpinner);
        resourcesSpinner.setOnItemSelectedListener(new resourcesSpinListener());
        //resourcesSpinner.setSelection(0);

        powerPlantsSpinner = (Spinner) findViewById(R.id.userPowerPlantsSpinner);
        powerPlantsSpinner.setOnItemSelectedListener(new powerPlantsSpinListener());
        //powerPlantsSpinner.setSelection(0);

        //okay and pass buttons
        okayButton = (Button) findViewById(R.id.okButton);
        okayButton.setOnClickListener(new okayButListener());
        passButton = (Button) findViewById(R.id.passButton);
        passButton.setOnClickListener(new passButListener());

        //select buttons
        selectButton0 = (Button) findViewById(R.id.select0);
        selectButton1 = (Button) findViewById(R.id.select1);
        selectButton2 = (Button) findViewById(R.id.select2);
        selectButton3 = (Button) findViewById(R.id.select3);

        //bid views
        previousBid = (TextView) findViewById(R.id.previousBidTV);
        bidVal = (EditText) findViewById(R.id.bidEditText);
        bidValue = Integer.parseInt(bidVal.getText().toString());

        //sale power plants
        powerPlant1Cost = (TextView) findViewById(R.id.pp1cValue);
        powerPlant1Type = (TextView) findViewById(R.id.pp1tValue);
        powerPlant1Number = (TextView) findViewById(R.id.pp1nValue);
        powerPlant1House = (TextView) findViewById(R.id.pp1hValue);

        powerPlant2Cost = (TextView) findViewById(R.id.pp2cValue);
        powerPlant2Type = (TextView) findViewById(R.id.pp2tValue);
        powerPlant2Number = (TextView) findViewById(R.id.pp2nValue);
        powerPlant2House = (TextView) findViewById(R.id.pp2hValue);

        powerPlant3Cost = (TextView) findViewById(R.id.pp3cValue);
        powerPlant3Type = (TextView) findViewById(R.id.pp3tValue);
        powerPlant3Number = (TextView) findViewById(R.id.pp3nValue);
        powerPlant3House = (TextView) findViewById(R.id.pp3hValue);

        powerPlant4Cost = (TextView) findViewById(R.id.pp4cValue);
        powerPlant4Type = (TextView) findViewById(R.id.pp4tValue);
        powerPlant4Number = (TextView) findViewById(R.id.pp4nValue);
        powerPlant4House = (TextView) findViewById(R.id.pp4hValue);

        //owned resources and cash
        moneyTextView = (TextView) findViewById(R.id.moneyTextView);
        coalNumView = (TextView) findViewById(R.id.coalNumView);
        oilNumView = (TextView) findViewById(R.id.oilNumView);
        trashNumView = (TextView) findViewById(R.id.trashNumView);
        nuclearNumView = (TextView) findViewById(R.id.nuclearTextView);

        //owned power plant grid
        costTextView1 = (TextView) findViewById(R.id.upp1cValue);
        typeTextView1 = (TextView) findViewById(R.id.upp1tValue);
        numberTextView1 = (TextView) findViewById(R.id.upp1nValue);
        housesTextView1 = (TextView) findViewById(R.id.upp1hValue);

        costTextView2 = (TextView) findViewById(R.id.upp2cValue);
        typeTextView2 = (TextView) findViewById(R.id.upp2tValue);
        numberTextView2 = (TextView) findViewById(R.id.upp2nValue);
        housesTextView2 = (TextView) findViewById(R.id.upp2hValue);

        costTextView3 = (TextView) findViewById(R.id.upp3cValue);
        typeTextView3 = (TextView) findViewById(R.id.upp3tValue);
        numberTextView3 = (TextView) findViewById(R.id.upp3nValue);
        housesTextView3 = (TextView) findViewById(R.id.upp3hValue);

        costTextView4 = (TextView) findViewById(R.id.upp4cValue);
        typeTextView4 = (TextView) findViewById(R.id.upp4tValue);
        numberTextView4 = (TextView) findViewById(R.id.upp4nValue);
        housesTextView4 = (TextView) findViewById(R.id.upp4hValue);

        //initialize resource buttons and listers
        coalButtons[0] = (ImageButton) findViewById(R.id.cb1);
        coalButtons[1] = (ImageButton) findViewById(R.id.cb2);
        coalButtons[2] = (ImageButton) findViewById(R.id.cb3);
        coalButtons[3] = (ImageButton) findViewById(R.id.cb4);
        coalButtons[4] = (ImageButton) findViewById(R.id.cb5);
        coalButtons[5] = (ImageButton) findViewById(R.id.cb6);
        coalButtons[6] = (ImageButton) findViewById(R.id.cb7);
        coalButtons[7] = (ImageButton) findViewById(R.id.cb8);
        coalButtons[8] = (ImageButton) findViewById(R.id.cb9);
        coalButtons[9] = (ImageButton) findViewById(R.id.cb10);
        coalButtons[10] = (ImageButton) findViewById(R.id.cb11);
        coalButtons[11] = (ImageButton) findViewById(R.id.cb12);
        coalButtons[12] = (ImageButton) findViewById(R.id.cb13);
        coalButtons[13] = (ImageButton) findViewById(R.id.cb14);
        coalButtons[14] = (ImageButton) findViewById(R.id.cb15);

        for (int i = 0; i < 15; i++) {
            coalButtons[i].setOnClickListener(new CoalButtonListener());
        }

        oilButtons[0] = (ImageButton) findViewById(R.id.ob1);
        oilButtons[1] = (ImageButton) findViewById(R.id.ob2);
        oilButtons[2] = (ImageButton) findViewById(R.id.ob3);
        oilButtons[3] = (ImageButton) findViewById(R.id.ob4);
        oilButtons[4] = (ImageButton) findViewById(R.id.ob5);
        oilButtons[5] = (ImageButton) findViewById(R.id.ob6);
        oilButtons[6] = (ImageButton) findViewById(R.id.ob7);
        oilButtons[7] = (ImageButton) findViewById(R.id.ob8);
        oilButtons[8] = (ImageButton) findViewById(R.id.ob9);
        oilButtons[9] = (ImageButton) findViewById(R.id.ob10);

        for (int i = 0; i < 10; i++) {
            oilButtons[i].setOnClickListener(new OilButtonListener());
        }

        uraniumButtons[0] = (ImageButton) findViewById(R.id.ub1);
        uraniumButtons[1] = (ImageButton) findViewById(R.id.ub2);
        uraniumButtons[2] = (ImageButton) findViewById(R.id.ub3);
        uraniumButtons[3] = (ImageButton) findViewById(R.id.ub4);
        uraniumButtons[4] = (ImageButton) findViewById(R.id.ub5);

        for (int i = 0; i < 5; i++) {
            uraniumButtons[i].setOnClickListener(new NuclearButtonListener());
        }

        trashButtons[0] = (ImageButton) findViewById(R.id.tb1);
        trashButtons[1] = (ImageButton) findViewById(R.id.tb2);
        trashButtons[2] = (ImageButton) findViewById(R.id.tb3);
        trashButtons[3] = (ImageButton) findViewById(R.id.tb4);
        trashButtons[4] = (ImageButton) findViewById(R.id.tb5);
        trashButtons[5] = (ImageButton) findViewById(R.id.tb6);
        trashButtons[6] = (ImageButton) findViewById(R.id.tb7);
        trashButtons[7] = (ImageButton) findViewById(R.id.tb8);
        trashButtons[8] = (ImageButton) findViewById(R.id.tb9);
        trashButtons[9] = (ImageButton) findViewById(R.id.tb10);
        trashButtons[10] = (ImageButton) findViewById(R.id.tb11);
        trashButtons[11] = (ImageButton) findViewById(R.id.tb12);
        trashButtons[12] = (ImageButton) findViewById(R.id.tb13);
        trashButtons[13] = (ImageButton) findViewById(R.id.tb14);
        trashButtons[14] = (ImageButton) findViewById(R.id.tb15);

        for (int i = 0; i < 15; i++) {
            trashButtons[i].setOnClickListener(new TrashButtonListener());
        }

        //city buttons
        cityButtons[0] = (Button) findViewById(R.id.button4);
        cityButtons[1] = (Button) findViewById(R.id.button);
        cityButtons[2] = (Button) findViewById(R.id.button2);
        cityButtons[3] = (Button) findViewById(R.id.button3);
        cityButtons[4] = (Button) findViewById(R.id.button21);
        cityButtons[5] = (Button) findViewById(R.id.button20);
        cityButtons[6] = (Button) findViewById(R.id.button7);
        cityButtons[7] = (Button) findViewById(R.id.button5);
        cityButtons[8] = (Button) findViewById(R.id.button19);
        cityButtons[9] = (Button) findViewById(R.id.button9);
        cityButtons[10] = (Button) findViewById(R.id.button8);
        cityButtons[11] = (Button) findViewById(R.id.button10);
        cityButtons[12] = (Button) findViewById(R.id.button6);
        cityButtons[13] = (Button) findViewById(R.id.button12);
        cityButtons[14] = (Button) findViewById(R.id.button11);
        cityButtons[15] = (Button) findViewById(R.id.button17);
        cityButtons[16] = (Button) findViewById(R.id.button14);
        cityButtons[17] = (Button) findViewById(R.id.button15);
        cityButtons[18] = (Button) findViewById(R.id.button16);
        cityButtons[19] = (Button) findViewById(R.id.button18);

        for (int i = 0; i < 20; i++) {
            cityButtons[i].setOnClickListener(new CityButtonListener());
        }

        //let receiveInfo method know that the GUI has been initialized
        guiIsReady = false;
    }

    /* *receiveInfo
     *
     * recieves a gamestate and updates the GUI based
     * on what phase the game is currently in
     * @param info
     */
    @Override
    public void receiveInfo(GameInfo info) {
        //check if guiIsReady - if setAsGui has been called
        if (guiIsReady){
            //check if info is a gameState
            if (info instanceof PowerState){
                powerState = (PowerState) info;
                //update GUI - //update user resources and power plants  every phase
                //look at which user the human player has displayed on their spinners
                int resourcePos = resourcesSpinner.getSelectedItemPosition();
                int powerPlantPos = powerPlantsSpinner.getSelectedItemPosition();
                setResources((powerState.getGameInventories().get(resourcePos)));
                setPowerPlants((powerState.getGameInventories().get(powerPlantPos)));

                //display the power plants up for auction each update
                showPlants();

                //update GUI by phase
                int phase = powerState.getGamePhase();
                if (phase == 0) {
                    /*First player chooses a power plant.
                    * "OK" or "Pass" updates phase.*/
                    //GUI updates handled by button listener
                    //color cities other player has bought
                    int opponentNumCities = powerState.getGameInventories().get(1).getMyCities().size();

                    for (int i = 0; i <= opponentNumCities; i++){
                        //find the city index that the opponent bought
                        int cityIndex = powerState.getGameInventories().get(1).getMyCities().get(i).getIndex();
                        //color the buttons that the opponent owns
                        cityButtons[cityIndex].setBackgroundColor(opponentRed);
                        //use opponentRed Color
                    }
                }
                else if (phase == 1 ) {
                    /*Bidding on power plant(s).
                    *"Pass" updates phase.*/
                    int bid = powerState.getCurrentBid();
                    previousBid.setText("" + bid);

                }
                else if (phase == 2 ) {
                    /*Previous passer chooses a power plant.
                    * "OK" or "Pass" updates phase.*/
                    //GUI updates handled by button listeners
                }
                else if (phase == 3 || phase == 4) {
                    /*Second player chooses resources.
                    * "OK" or "Pass" updates phase.*/
                    //check if all of the users resources have been purchased, if not send that action
                    if(actionList.size() > 0) {
                        //send action according to what resource user bought, remove it from the list
                        game.sendAction(actionList.remove(0));
                    }
                    else if (actionList.size() == 0){
                        //if the list is empty, change the phase with a pass action
                        game.sendAction(new PassAction(this));
                    }
                }
                else if (phase == 5 || phase == 6) {
                    /*Second player chooses cities.
                    * "OK" or "Pass" updates phase.*/
                    //check if all of users cities have been purchased, if not send that action
                    if(cityActionList.size() > 0) {
                        game.sendAction(cityActionList.remove(0));
                    }
                    //look and see which resources or cities user bought, change views accordingly
                }

                else {
                    /*Other cases go here ?*/
                }
            }
        }
    }

    /**
     * showPlants
     *
     * displays plants on the powerPlants bar that is at the top of the screen
     */
    private void showPlants(){

            powerPlant1Cost.setText("" + powerState.getAvailPowerplant().get(0).getCost());
            powerPlant1Type.setText(powerState.getAvailPowerplant().get(0).getKind());
            powerPlant1Number.setText("" + powerState.getAvailPowerplant().get(0).getPtP());
            powerPlant1House.setText("" + powerState.getAvailPowerplant().get(0).getHp());

            powerPlant2Cost.setText("" + powerState.getAvailPowerplant().get(1).getCost());
            powerPlant2Type.setText(powerState.getAvailPowerplant().get(1).getKind());
            powerPlant2Number.setText("" + powerState.getAvailPowerplant().get(1).getPtP());
            powerPlant2House.setText("" + powerState.getAvailPowerplant().get(1).getHp());

            powerPlant3Cost.setText("" + powerState.getAvailPowerplant().get(2).getCost());
            powerPlant3Type.setText(powerState.getAvailPowerplant().get(2).getKind());
            powerPlant3Number.setText("" + powerState.getAvailPowerplant().get(2).getPtP());
            powerPlant3House.setText("" + powerState.getAvailPowerplant().get(2).getHp());

            powerPlant4Cost.setText("" + powerState.getAvailPowerplant().get(4).getCost());
            powerPlant4Type.setText(powerState.getAvailPowerplant().get(4).getKind());
            powerPlant4Number.setText("" + powerState.getAvailPowerplant().get(4).getPtP());
            powerPlant4House.setText("" + powerState.getAvailPowerplant().get(4).getHp());
    }

    /**
     * setResources
     * <p>
     * to be used in the spinners methods, sets resources text on GUI
     *
     * @param rsc //resource
     */
    private void setResources(Inventory rsc) {
        if (rsc.getMoney() == 0) {
            moneyTextView.setText("-");
        }
        else{
            moneyTextView.setText("" + rsc.getMoney());
        }
        if (rsc.getCoal() == 0) {
            coalNumView.setText("-");
        }
        else{
            coalNumView.setText("" + rsc.getCoal());
        }
        if (rsc.getOil() == 0) {
            oilNumView.setText("-");
        }
        else{
            oilNumView.setText("" + rsc.getOil());
        }
        if (rsc.getTrash() == 0) {
            trashNumView.setText("-");
        }
        else{
            trashNumView.setText("" + rsc.getTrash());
        }
        if (rsc.getUranium() == 0) {
            nuclearNumView.setText("-");
        }
        else{
            nuclearNumView.setText("" + rsc.getUranium());
        }

    }

    /**
     * setPowerPlants
     * <p>
     * to be used in the spinners methods, sets power plants text on GUI
     *
     * @param upp //userPowerPlant
     */
    private void setPowerPlants(Inventory upp) {
        costTextView1.setText("-");
        typeTextView1.setText("-");
        numberTextView1.setText("-");
        housesTextView1.setText("-");

        costTextView2.setText("-");
        typeTextView2.setText("-");
        numberTextView2.setText("-");
        housesTextView2.setText("-");

        costTextView3.setText("-");
        typeTextView3.setText("-");
        numberTextView3.setText("-");
        housesTextView3.setText("-");

        costTextView4.setText("-");
        typeTextView4.setText("-");
        numberTextView4.setText("-");
        housesTextView4.setText("-");

        if (upp.getMyPlants().size() > 0) {
            costTextView1.setText("" + upp.getMyPlants().get(0).getCost());
            typeTextView1.setText("" + upp.getMyPlants().get(0).getKind());
            numberTextView1.setText("" + upp.getMyPlants().get(0).getPtP());
            housesTextView1.setText("" + upp.getMyPlants().get(0).getHp());
        }
        if (upp.getMyPlants().size() > 1) {
            costTextView2.setText("" + upp.getMyPlants().get(1).getCost());
            typeTextView2.setText("" + upp.getMyPlants().get(1).getKind());
            numberTextView2.setText("" + upp.getMyPlants().get(1).getPtP());
            housesTextView2.setText("" + upp.getMyPlants().get(1).getHp());
        }
        if (upp.getMyPlants().size() > 2) {
            costTextView3.setText("" + upp.getMyPlants().get(2).getCost());
            typeTextView3.setText("" + upp.getMyPlants().get(2).getKind());
            numberTextView3.setText("" + upp.getMyPlants().get(2).getPtP());
            housesTextView3.setText("" + upp.getMyPlants().get(2).getHp());
        }
        if (upp.getMyPlants().size() > 3) {
            costTextView4.setText("" + upp.getMyPlants().get(3).getCost());
            typeTextView4.setText("" + upp.getMyPlants().get(3).getKind());
            numberTextView4.setText("" + upp.getMyPlants().get(3).getPtP());
            housesTextView4.setText("" + upp.getMyPlants().get(3).getHp());
        }
    }

    /**
     * resourceSpinListener
     * <p>
     * configures the spinner in the upper left of the GUI to display
     * either Humanplayers resources or opponent
     */
    private class resourcesSpinListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //String currVal = resourcesSpinner.getSelectedItem().toString();
            switch (position) {
                case 0:
                    setResources(powerState.getGameInventories().get(0));
                    break;
                case 1:
                    setResources(powerState.getGameInventories().get(1));
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            //do nothing
        }
    }

    /**
     * powerPlantsSpinListener
     * <p>
     * configures the spinner on the map to display
     * either Human Opponent's powerplants
     */
    private class powerPlantsSpinListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //String currVal = resourcesSpinner.getSelectedItem().toString();
            switch (position) {
                case 0:
                    setPowerPlants(powerState.getGameInventories().get(0));
                    break;
                case 1:
                    setPowerPlants(powerState.getGameInventories().get(1));
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parentView) {
            //do nothing
        }
    }

    private class okayButListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
           if(powerState.getTurn() != playerNum) {return;}

            int phase = powerState.getGamePhase();
            if (phase == 0) {

               SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridHumanPlayer.this, selectNum);
                game.sendAction(sppa);

            }
            else if (phase == 1) {
                //only send the action if they made an appropriate bid
                if(bidValue > powerState.getCurrentBid()) {
                    BidAction ba = new BidAction(PowerGridHumanPlayer.this, bidValue);
                    game.sendAction(ba);
                }
            }
            else if (phase == 2) {

                SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridHumanPlayer.this, selectNum);
                game.sendAction(sppa);
            }
            else if (phase == 3 || phase == 4) {

                for (int i = 0; i < 15; i++) {
                    //if it's not available in the local store, it must have been bought!
                    if (!localStore.coal[i]) {
                        localStore.coal[i] = true;
                        actionList.add(new BuyResourceAction(PowerGridHumanPlayer.this, i, "coal"));
                    }

                    if (!localStore.trash[i]) {
                        localStore.coal[i] = true;
                        actionList.add(new BuyResourceAction(PowerGridHumanPlayer.this, i, "trash"));
                    }

                    if (i < 5 && !localStore.uranium[i]) {
                        localStore.uranium[i] = true;
                        actionList.add(new BuyResourceAction(PowerGridHumanPlayer.this, i, "uranium"));
                    }

                    if (i < 10 && !localStore.oil[i]) {
                        localStore.oil[i] = true;
                        actionList.add(new BuyResourceAction(PowerGridHumanPlayer.this, i, "oil"));
                    }
                }

                if(actionList.size() > 0) {
                    game.sendAction(actionList.remove(0));
                }
                else {
                    game.sendAction(new PassAction(PowerGridHumanPlayer.this));
                }

            }
            else if (phase == 5 || phase == 6) {

                for (int i = 0; i < 20; i++) {
                    if(localCities[i]){
                        localCities[i] = false;
                        cityActionList.add(new BuyCityAction(PowerGridHumanPlayer.this, powerState.getAvailCities().get(i), i));
                    }
                }
                if(cityActionList.size() > 0) {
                    game.sendAction(cityActionList.remove(0));
                }
                else {
                    game.sendAction(new PassAction(PowerGridHumanPlayer.this));
                }

            }
        }
    }

    private class passButListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            PassAction pA = new PassAction(PowerGridHumanPlayer.this);
            game.sendAction(pA);
        }
    }

    private class select0ButListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            selectNum = 0;
            selectButton0.setBackgroundColor(Color.YELLOW);
            selectButton1.setBackgroundColor(prettyBlue);
            selectButton2.setBackgroundColor(prettyBlue);
            selectButton3.setBackgroundColor(prettyBlue);
        }
    }

    private class select1ButListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            selectNum = 1;
            selectButton1.setBackgroundColor(Color.YELLOW);
            selectButton0.setBackgroundColor(prettyBlue);
            selectButton2.setBackgroundColor(prettyBlue);
            selectButton3.setBackgroundColor(prettyBlue);
        }
    }

    private class select2ButListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            selectNum = 2;
            selectButton2.setBackgroundColor(Color.YELLOW);
            selectButton0.setBackgroundColor(prettyBlue);
            selectButton1.setBackgroundColor(prettyBlue);
            selectButton3.setBackgroundColor(prettyBlue);
        }
    }

    private class select3ButListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            selectNum = 3;
            selectButton3.setBackgroundColor(Color.YELLOW);
            selectButton2.setBackgroundColor(prettyBlue);
            selectButton1.setBackgroundColor(prettyBlue);
            selectButton0.setBackgroundColor(prettyBlue);
        }
    }

    private class CityButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ArrayList<Integer> trueIndexes = new ArrayList<Integer>();
            if(powerState.getGamePhase() != 5 || powerState.getGamePhase() != 6) return; //we don't want to mess with cities unless it's the right phase
            if(powerState.getTurn() != playerNum) return; //it's not your turn!
            for (int i = 0; i < 20; i++) {

                if (v.getId() != cityButtons[i].getId()) {continue;} //if they didn't click on it, don't worry about it
                if(powerState.getBoughtCities()[i]) {continue;} //if they did click on it, but it's already been purchased, don't worry about it

                //if they don't own any cities, they can select anything
                //if they own cities, they can only buy neighboring cities
                //checks to see if the one they clicked is a legitimate neighbor
                if(powerState.getGameInventories().get(playerNum).getMyCities().size() == 0 || powerState.getAvailCities().get(i).containsNeighbor(powerState.getGameInventories().get(playerNum).getIndexArray(powerState.getGameInventories().get(playerNum).getMyCities()))) {
                    //if they haven't previously selected anything, all options are open
                    if(isArrayFalse(localCities)) {
                        if (localCities[i]) {
                            cityButtons[i].setBackgroundColor(basicGray);
                            localCities[i] = false;
                        }
                        else {
                            cityButtons[i].setBackgroundColor(prettyBlue);
                            localCities[i] = true;

                        }
                    }
                    //if they have previously selected something, we must check to see if they are neighbors
                    else{
                        trueIndexes = trueIndexes(localCities);
                        for(int j = 0; j < trueIndexes.size(); j++){
                            if(powerState.getAvailCities().get(i).isNeighbor((trueIndexes.get(j)))){
                                if (localCities[i]) {
                                    cityButtons[i].setBackgroundColor(basicGray);
                                    localCities[i] = false;
                                }
                                else {
                                    cityButtons[i].setBackgroundColor(prettyBlue);
                                    localCities[i] = true;

                                }
                            }
                        }//end for loop
                    }
                }
            }//end for loop
        }
    }//citybuttonlistener

    //implements the coal buttons
    public class CoalButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            int viewId = view.getId();
            int[] idNum = new int[15];
            idNum[0] = R.id.cb1;
            idNum[1] = R.id.cb2;
            idNum[2] = R.id.cb3;
            idNum[3] = R.id.cb4;
            idNum[4] = R.id.cb5;
            idNum[5] = R.id.cb6;
            idNum[6] = R.id.cb7;
            idNum[7] = R.id.cb8;
            idNum[8] = R.id.cb9;
            idNum[9] = R.id.cb10;
            idNum[10] = R.id.cb11;
            idNum[11] = R.id.cb12;
            idNum[12] = R.id.cb13;
            idNum[13] = R.id.cb14;
            idNum[14] = R.id.cb15;


            for (int i = 0; i < 15; i++) {
                if (viewId == idNum[i]) {
                    if (powerState.getAvailableResources().coal[i]) {
                        if (localStore.coal[i]) {
                            coalButtons[i].setBackgroundColor(Color.TRANSPARENT);
                            localStore.coal[i] = false;
                        }
                        else {

                            coalButtons[i].setBackgroundColor(Color.BLACK);
                            localStore.coal[i] = true;
                        }
                    }
                }
            }
        }
    }

    //implements the oil buttons
    public class OilButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            int[] idNum = new int[15];
            idNum[0] = R.id.ob1;
            idNum[1] = R.id.ob2;
            idNum[2] = R.id.ob3;
            idNum[3] = R.id.ob4;
            idNum[4] = R.id.ob5;
            idNum[5] = R.id.ob6;
            idNum[6] = R.id.ob7;
            idNum[7] = R.id.ob8;
            idNum[8] = R.id.ob9;
            idNum[9] = R.id.ob10;

            for (int i = 0; i < 10; i++) {
                if (viewId == idNum[i]) {
                    if (powerState.getAvailableResources().oil[i]) {
                        if (localStore.oil[i]) {
                            oilButtons[i].setBackgroundColor(Color.TRANSPARENT);
                            localStore.oil[i] = false;
                        }
                        else {

                            oilButtons[i].setBackgroundColor(Color.BLACK);
                            localStore.oil[i] = true;
                        }
                    }
                }
            }
        }
    }

    //implements the trash buttons
    public class TrashButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            int[] idNum = new int[15];
            idNum[0] = R.id.tb1;
            idNum[1] = R.id.tb2;
            idNum[2] = R.id.tb3;
            idNum[3] = R.id.tb4;
            idNum[4] = R.id.tb5;
            idNum[5] = R.id.tb6;
            idNum[6] = R.id.tb7;
            idNum[7] = R.id.tb8;
            idNum[8] = R.id.tb9;
            idNum[9] = R.id.tb10;
            idNum[10] = R.id.tb11;
            idNum[11] = R.id.tb12;
            idNum[12] = R.id.tb13;
            idNum[13] = R.id.tb14;
            idNum[14] = R.id.tb15;


            for (int i = 0; i < 15; i++) {
                if (viewId == idNum[i]) {
                    if (powerState.getAvailableResources().trash[i]) {
                        if (localStore.trash[i]) {
                            trashButtons[i].setBackgroundColor(Color.TRANSPARENT);
                            localStore.trash[i] = false;
                        }
                        else {

                            trashButtons[i].setBackgroundColor(Color.BLACK);
                            localStore.trash[i] = true;
                        }
                    }
                }
            }

        }
    }

    //implements the nuclear buttons
    public class NuclearButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int viewId = view.getId();
            int[] idNum = new int[15];

            idNum[0] = R.id.ub1;
            idNum[1] = R.id.ub2;
            idNum[2] = R.id.ub3;
            idNum[3] = R.id.ub4;
            idNum[4] = R.id.ub5;

            for (int i = 0; i < 5; i++) {
                if (viewId == idNum[i]) {
                    if (powerState.getAvailableResources().uranium[i]) {
                        if (localStore.uranium[i]) {
                            uraniumButtons[i].setBackgroundColor(Color.TRANSPARENT);
                            localStore.uranium[i] = false;
                        }
                        else {

                            uraniumButtons[i].setBackgroundColor(Color.BLACK);
                            localStore.uranium[i] = true;
                        }
                    }
                }
            }
        }
    }

    //helper method to check to see if the entire array contains false elements
    public boolean isArrayFalse(boolean[] array){
        for(int i = 0; i < array.length; i++ ){
            if(array[i] = true) return false;
        }
        return true;
    }

    //helper method that returns every index in an array that is true
    public ArrayList<Integer> trueIndexes(boolean[] array){
        ArrayList<Integer> indexArray = new ArrayList<Integer>();
        for(int i = 0; i< array.length; i++){
            if(array[i]) indexArray.add(i);
        }
        return indexArray;
    }
}

