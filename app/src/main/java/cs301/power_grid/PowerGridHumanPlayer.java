package cs301.power_grid;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
//
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

import static android.R.drawable.list_selector_background;
import static cs301.power_grid.R.color.coalColor;
import static cs301.power_grid.R.drawable.abc_btn_check_to_on_mtrl_015;
import static cs301.power_grid.R.drawable.iconnuclear2;
import static cs301.power_grid.R.drawable.iconoil1;
import static cs301.power_grid.R.drawable.icontrash2;

/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */

public class PowerGridHumanPlayer extends GameHumanPlayer {
    //instance variables:
    private TextView phaseTextView;
//    int i = 0;
    private View view;

    //android activity we are running
    private GameMainActivity myActivity;
    //game state
    private PowerState powerState = new PowerState();

    /*Inventory?*/
//    private Inventory inv = powerState.getGameInventories().get(0);//new Inventory();

    private int tempMoney = 0;

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

    private EditText bidEditText;
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
        return (RelativeLayout) myActivity.findViewById(R.id.activity_main);
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        myActivity = activity;
        // Load the layout resource for our GUI
        activity.setContentView(R.layout.activity_main);

        phaseTextView = (TextView) myActivity.findViewById(R.id.phaseView);
        //spinners
        resourcesSpinner = (Spinner) myActivity.findViewById(R.id.UserResourcesSpinner);
        resourcesSpinner.setOnItemSelectedListener(new resourcesSpinListener());
        //resourcesSpinner.setSelection(0);

        powerPlantsSpinner = (Spinner) myActivity.findViewById(R.id.userPowerPlantsSpinner);
        powerPlantsSpinner.setOnItemSelectedListener(new powerPlantsSpinListener());
        //powerPlantsSpinner.setSelection(0);

        //okay and pass buttons
        okayButton = (Button) myActivity.findViewById(R.id.okButton);
        okayButton.setOnClickListener(new okayButListener());
        passButton = (Button) myActivity.findViewById(R.id.passButton);
        passButton.setOnClickListener(new passButListener());

        //select buttons
        selectButton0 = (Button) myActivity.findViewById(R.id.select0);
        selectButton1 = (Button) myActivity.findViewById(R.id.select1);
        selectButton2 = (Button) myActivity.findViewById(R.id.select2);
        selectButton3 = (Button) myActivity.findViewById(R.id.select3);

        selectButton0.setOnClickListener(new select0ButListener());
        selectButton1.setOnClickListener(new select1ButListener());
        selectButton2.setOnClickListener(new select2ButListener());
        selectButton3.setOnClickListener(new select3ButListener());

        //bid views
        previousBid = (TextView) myActivity.findViewById(R.id.previousBidTV);
        bidEditText = (EditText) myActivity.findViewById(R.id.bidEditText);



        //sale power plants
        powerPlant1Cost = (TextView) myActivity.findViewById(R.id.pp1cValue);
        powerPlant1Type = (TextView) myActivity.findViewById(R.id.pp1tValue);
        powerPlant1Number = (TextView) myActivity.findViewById(R.id.pp1nValue);
        powerPlant1House = (TextView) myActivity.findViewById(R.id.pp1hValue);

        powerPlant2Cost = (TextView) myActivity.findViewById(R.id.pp2cValue);
        powerPlant2Type = (TextView) myActivity.findViewById(R.id.pp2tValue);
        powerPlant2Number = (TextView) myActivity.findViewById(R.id.pp2nValue);
        powerPlant2House = (TextView) myActivity.findViewById(R.id.pp2hValue);

        powerPlant3Cost = (TextView) myActivity.findViewById(R.id.pp3cValue);
        powerPlant3Type = (TextView) myActivity.findViewById(R.id.pp3tValue);
        powerPlant3Number = (TextView) myActivity.findViewById(R.id.pp3nValue);
        powerPlant3House = (TextView) myActivity.findViewById(R.id.pp3hValue);

        powerPlant4Cost = (TextView) myActivity.findViewById(R.id.pp4cValue);
        powerPlant4Type = (TextView) myActivity.findViewById(R.id.pp4tValue);
        powerPlant4Number = (TextView) myActivity.findViewById(R.id.pp4nValue);
        powerPlant4House = (TextView) myActivity.findViewById(R.id.pp4hValue);

        //owned resources and cash
        moneyTextView = (TextView) myActivity.findViewById(R.id.moneyTextView);
        coalNumView = (TextView) myActivity.findViewById(R.id.coalNumView);
        oilNumView = (TextView) myActivity.findViewById(R.id.oilNumView);
        trashNumView = (TextView) myActivity.findViewById(R.id.trashNumView);
        nuclearNumView = (TextView) myActivity.findViewById(R.id.nuclearNumView);

        //owned power plant grid
        costTextView1 = (TextView) myActivity.findViewById(R.id.upp1cValue);
        typeTextView1 = (TextView) myActivity.findViewById(R.id.upp1tValue);
        numberTextView1 = (TextView) myActivity.findViewById(R.id.upp1nValue);
        housesTextView1 = (TextView) myActivity.findViewById(R.id.upp1hValue);

        costTextView2 = (TextView) myActivity.findViewById(R.id.upp2cValue);
        typeTextView2 = (TextView) myActivity.findViewById(R.id.upp2tValue);
        numberTextView2 = (TextView) myActivity.findViewById(R.id.upp2nValue);
        housesTextView2 = (TextView) myActivity.findViewById(R.id.upp2hValue);

        costTextView3 = (TextView) myActivity.findViewById(R.id.upp3cValue);
        typeTextView3 = (TextView) myActivity.findViewById(R.id.upp3tValue);
        numberTextView3 = (TextView) myActivity.findViewById(R.id.upp3nValue);
        housesTextView3 = (TextView) myActivity.findViewById(R.id.upp3hValue);

        costTextView4 = (TextView) myActivity.findViewById(R.id.upp4cValue);
        typeTextView4 = (TextView) myActivity.findViewById(R.id.upp4tValue);
        numberTextView4 = (TextView) myActivity.findViewById(R.id.upp4nValue);
        housesTextView4 = (TextView) myActivity.findViewById(R.id.upp4hValue);

        //initialize resource buttons and listeners
        coalButtons[0] = (ImageButton) myActivity.findViewById(R.id.cb1);
        coalButtons[1] = (ImageButton) myActivity.findViewById(R.id.cb2);
        coalButtons[2] = (ImageButton) myActivity.findViewById(R.id.cb3);
        coalButtons[3] = (ImageButton) myActivity.findViewById(R.id.cb4);
        coalButtons[4] = (ImageButton) myActivity.findViewById(R.id.cb5);
        coalButtons[5] = (ImageButton) myActivity.findViewById(R.id.cb6);
        coalButtons[6] = (ImageButton) myActivity.findViewById(R.id.cb7);
        coalButtons[7] = (ImageButton) myActivity.findViewById(R.id.cb8);
        coalButtons[8] = (ImageButton) myActivity.findViewById(R.id.cb9);
        coalButtons[9] = (ImageButton) myActivity.findViewById(R.id.cb10);
        coalButtons[10] = (ImageButton) myActivity.findViewById(R.id.cb11);
        coalButtons[11] = (ImageButton) myActivity.findViewById(R.id.cb12);
        coalButtons[12] = (ImageButton) myActivity.findViewById(R.id.cb13);
        coalButtons[13] = (ImageButton) myActivity.findViewById(R.id.cb14);
        coalButtons[14] = (ImageButton) myActivity.findViewById(R.id.cb15);

        for (int i = 0; i < 15; i++) {
            coalButtons[i].setOnClickListener(new CoalButtonListener());
        }

        oilButtons[0] = (ImageButton) myActivity.findViewById(R.id.ob1);
        oilButtons[1] = (ImageButton) myActivity.findViewById(R.id.ob2);
        oilButtons[2] = (ImageButton) myActivity.findViewById(R.id.ob3);
        oilButtons[3] = (ImageButton) myActivity.findViewById(R.id.ob4);
        oilButtons[4] = (ImageButton) myActivity.findViewById(R.id.ob5);
        oilButtons[5] = (ImageButton) myActivity.findViewById(R.id.ob6);
        oilButtons[6] = (ImageButton) myActivity.findViewById(R.id.ob7);
        oilButtons[7] = (ImageButton) myActivity.findViewById(R.id.ob8);
        oilButtons[8] = (ImageButton) myActivity.findViewById(R.id.ob9);
        oilButtons[9] = (ImageButton) myActivity.findViewById(R.id.ob10);

        for (int i = 0; i < 10; i++) {
            oilButtons[i].setOnClickListener(new OilButtonListener());
        }

        uraniumButtons[0] = (ImageButton) myActivity.findViewById(R.id.ub1);
        uraniumButtons[1] = (ImageButton) myActivity.findViewById(R.id.ub2);
        uraniumButtons[2] = (ImageButton) myActivity.findViewById(R.id.ub3);
        uraniumButtons[3] = (ImageButton) myActivity.findViewById(R.id.ub4);
        uraniumButtons[4] = (ImageButton) myActivity.findViewById(R.id.ub5);

        for (int i = 0; i < 5; i++) {
            uraniumButtons[i].setOnClickListener(new NuclearButtonListener());
        }

        trashButtons[0] = (ImageButton) myActivity.findViewById(R.id.tb1);
        trashButtons[1] = (ImageButton) myActivity.findViewById(R.id.tb2);
        trashButtons[2] = (ImageButton) myActivity.findViewById(R.id.tb3);
        trashButtons[3] = (ImageButton) myActivity.findViewById(R.id.tb4);
        trashButtons[4] = (ImageButton) myActivity.findViewById(R.id.tb5);
        trashButtons[5] = (ImageButton) myActivity.findViewById(R.id.tb6);
        trashButtons[6] = (ImageButton) myActivity.findViewById(R.id.tb7);
        trashButtons[7] = (ImageButton) myActivity.findViewById(R.id.tb8);
        trashButtons[8] = (ImageButton) myActivity.findViewById(R.id.tb9);
        trashButtons[9] = (ImageButton) myActivity.findViewById(R.id.tb10);
        trashButtons[10] = (ImageButton) myActivity.findViewById(R.id.tb11);
        trashButtons[11] = (ImageButton) myActivity.findViewById(R.id.tb12);
        trashButtons[12] = (ImageButton) myActivity.findViewById(R.id.tb13);
        trashButtons[13] = (ImageButton) myActivity.findViewById(R.id.tb14);
        trashButtons[14] = (ImageButton) myActivity.findViewById(R.id.tb15);

        for (int i = 0; i < 15; i++) {
            trashButtons[i].setOnClickListener(new TrashButtonListener());
        }

        //city buttons
        cityButtons[0] = (Button) myActivity.findViewById(R.id.button4);
        cityButtons[1] = (Button) myActivity.findViewById(R.id.button);
        cityButtons[2] = (Button) myActivity.findViewById(R.id.button2);
        cityButtons[3] = (Button) myActivity.findViewById(R.id.button3);
        cityButtons[4] = (Button) myActivity.findViewById(R.id.button21);
        cityButtons[5] = (Button) myActivity.findViewById(R.id.button20);
        cityButtons[6] = (Button) myActivity.findViewById(R.id.button7);
        cityButtons[7] = (Button) myActivity.findViewById(R.id.button5);
        cityButtons[8] = (Button) myActivity.findViewById(R.id.button19);
        cityButtons[9] = (Button) myActivity.findViewById(R.id.button9);
        cityButtons[10] = (Button) myActivity.findViewById(R.id.button8);
        cityButtons[11] = (Button) myActivity.findViewById(R.id.button10);
        cityButtons[12] = (Button) myActivity.findViewById(R.id.button6);
        cityButtons[13] = (Button) myActivity.findViewById(R.id.button12);
        cityButtons[14] = (Button) myActivity.findViewById(R.id.button11);
        cityButtons[15] = (Button) myActivity.findViewById(R.id.button17);
        cityButtons[16] = (Button) myActivity.findViewById(R.id.button14);
        cityButtons[17] = (Button) myActivity.findViewById(R.id.button15);
        cityButtons[18] = (Button) myActivity.findViewById(R.id.button16);
        cityButtons[19] = (Button) myActivity.findViewById(R.id.button18);

        for (int i = 0; i < 20; i++) {
            cityButtons[i].setOnClickListener(new CityButtonListener());
        }

        //let receiveInfo method know that the GUI has been initialized
        guiIsReady = true;
    }

    /* *receiveInfo
     *
     * recieves a gamestate and updates the GUI based
     * on what phase the game is currently in
     * @param info
     */
    @Override
    public void receiveInfo(GameInfo info) {
        int opponentNum = -1;
        if (playerNum == 0){opponentNum = 1;}
        else if (playerNum == 1){opponentNum = 0;}
        //check if guiIsReady - if setAsGui has been called
        if (guiIsReady){
            //check if info is a gameState
            if (info instanceof PowerState){
                powerState = (PowerState) info;
                //update GUI - //update user resources and power plants  every phase
                //look at which user the human player has displayed on their spinners
                tempMoney = powerState.getGameInventories().get(playerNum).getMoney();
                int resourcePos = resourcesSpinner.getSelectedItemPosition();
                int powerPlantPos = powerPlantsSpinner.getSelectedItemPosition();
                boolean hadItems = false;

                setResources((powerState.getGameInventories().get(resourcePos)));
                setPowerPlants((powerState.getGameInventories().get(powerPlantPos)));

                checkResourceButtons();

                //display the power plants up for auction each update

                //update GUI by phase
                int phase = powerState.getGamePhase();

                bidEditText.setFocusable(false);

                if (phase == 0) {
                    /*First player chooses a power plant.
                    * "OK" or "Pass" updates phase.*/
                    //GUI updates handled by button listener
                    //color cities other player has bought

                    int opponentNumCities = powerState.getGameInventories().get(opponentNum).getMyCities().size();
                    if (opponentNumCities > 0) {
                        for (int i = 0; i < opponentNumCities; i++) {
                            //find the city index that the opponent bought
                            int cityIndex = powerState.getGameInventories().get(opponentNum).getMyCities().get(i).getIndex();
                            //color the buttons that the opponent owns
                            cityButtons[cityIndex].setBackgroundColor(opponentRed);
                            //use opponentRed Color
                        }
                    }
                    resetResourceButtons();
                }
                /**asdf
                 External Citation
                 Date:    21 April 2017
                 Problem:  making editText editable under a certain condition
                 Resource:
                 http://stackoverflow.com/questions/6555455/how-to-set-editable
                 -true-false-edittext-in-android-programmatically
                 Solution: used the example code from this post.
                 */
                else if (phase == 1 ) {
                    /*Bidding on power plant(s).
                    *"Pass" updates phase.*/
                    int bid = powerState.getCurrentBid();
                    if (bid == 0){
                        previousBid.setText("-");
                    }
                    else {
                        previousBid.setText("" + bid);
                    }
                    bidEditText.setText("");
                    bidEditText.setFocusableInTouchMode(true);
                }
                else if (phase == 2 ) {
                    /*Previous passer chooses a power plant.
                    * "OK" or "Pass" updates phase.*/
                    //GUI updates handled by button listeners
                    //update bid bar
                    previousBid.setText("-");
                    bidEditText.setText("");

                }
                else if (phase == 3) {
                    /*Second player chooses resources.
                    * "OK" or "Pass" updates phase.*/
                    //check if all of the users resources have been purchased, if not send that action
                    if (actionList.size() > 0) {
                        //send action according to what resource user bought, remove it from the list
                        game.sendAction(actionList.remove(0));
                        hadItems = true;
                    } else if (hadItems) {
                        //if the list is empty, change the phase with a pass action
                        game.sendAction(new PassAction(PowerGridHumanPlayer.this));
                    }
                    resetSelectButtons();
                }
                else if(phase == 4){
                        if(actionList.size() > 0) {
                            //send action according to what resource user bought, remove it from the list
                            game.sendAction(actionList.remove(0));
                            hadItems = true;
                        }
                        else if (hadItems){
                            //if the list is empty, change the phase with a pass action
                            game.sendAction(new PassAction(PowerGridHumanPlayer.this));
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
                    /*Reset the resource buttons.*/

                }

                else {
                    /*Other cases go here ?*/
                }
            }
        }
    }

    private void resetSelectButtons() {
        selectButton0.setBackgroundColor(0xFF00DDFF);
        selectButton1.setBackgroundColor(0xFF33B5E5);
        selectButton2.setBackgroundColor(0xFF0099CC);
        selectButton3.setBackgroundColor(0xFF00DDFF);
    }

    private void resetResourceButtons()
    {
        for(int i = 0; i < 15; i++)
        {
            coalButtons[i].setVisibility(View.VISIBLE);

        }
        for(int j = 0; j < 10; j++) {

            oilButtons[j].setVisibility(View.VISIBLE);
        }
        for(int k = 0; k < 5; k++)
        {

            uraniumButtons[k].setVisibility(View.VISIBLE);
        }
        for(int l = 0; l < 15; l++)
        {

            trashButtons[l].setVisibility(View.VISIBLE);
        }
    }

    /**
     * showPlants
     *
     * displays plants on the powerPlants bar that is at the top of the screen
     */
    public void showPlants(){

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

            powerPlant4Cost.setText("" + powerState.getAvailPowerplant().get(3).getCost());
            powerPlant4Type.setText(powerState.getAvailPowerplant().get(3).getKind());
            powerPlant4Number.setText("" + powerState.getAvailPowerplant().get(3).getPtP());
            powerPlant4House.setText("" + powerState.getAvailPowerplant().get(3).getHp());
    }

    /**
     * setResources
     * <p>
     * to be used in the spinners methods, sets resources text on GUI
     *
     * @param rsc //resource
     */
    public void setResources(Inventory rsc) {
        if (rsc.getMoney() == 0) {
            moneyTextView.setText("-");
        }
        else{
            moneyTextView.setText("$" + rsc.getMoney());
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
        showPlants();
        phaseTextView.setText("" + powerState.getGamePhase());


    }

    /**
     * setPowerPlants
     * <p>
     * to be used in the spinners methods, sets power plants text on GUI
     *
     * @param upp //userPowerPlant
     */
    public void setPowerPlants(Inventory upp) {
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

        if (upp.getMyPlants().size() > 1 ) {
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
                if (selectNum == -1){
                    game.sendAction(new PassAction(PowerGridHumanPlayer.this));
                }
                else {
                    SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridHumanPlayer.this, selectNum);
                    game.sendAction(sppa);
                }
            }
            else if (phase == 1) {
                bidValue = Integer.parseInt(bidEditText.getText().toString());
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
            else if (phase == 3){

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
            else if(phase == 4){
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
                selectNum = -1;
            }

        }
    }

    private class passButListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if(playerNum != powerState.getTurn()) return;
            PassAction pA = new PassAction(PowerGridHumanPlayer.this);
            game.sendAction(pA);
        }
    }

    private class select0ButListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int cost = powerState.getAvailPowerplant().get(0).getCost();
            int myMoney = powerState.getGameInventories().get(playerNum).getMoney();
            if(powerState.getTurn() != playerNum) return;
            if (powerState.getGamePhase() == 0 || powerState.getGamePhase() == 2 && myMoney >= cost ) {
                selectNum = 0;
                selectButton0.setBackgroundColor(Color.YELLOW);
                selectButton1.setBackgroundColor(0xFF33B5E5);
                selectButton2.setBackgroundColor(0xFF0099CC);
                selectButton3.setBackgroundColor(prettyBlue);
            }
        }
    }

    private class select1ButListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int cost = powerState.getAvailPowerplant().get(1).getCost();
            int myMoney = powerState.getGameInventories().get(playerNum).getMoney();
            if(powerState.getTurn() != playerNum) return;
            if (powerState.getGamePhase() == 0 || powerState.getGamePhase() == 2 && myMoney >= cost ) {
                selectNum = 1;
                selectButton1.setBackgroundColor(Color.YELLOW);
                selectButton0.setBackgroundColor(prettyBlue);
                selectButton2.setBackgroundColor(0xFF0099CC);
                selectButton3.setBackgroundColor(prettyBlue);
            }
        }
    }

    private class select2ButListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int cost = powerState.getAvailPowerplant().get(2).getCost();
            int myMoney = powerState.getGameInventories().get(playerNum).getMoney();
            if(powerState.getTurn() != playerNum) return;
            if (powerState.getGamePhase() == 0 || powerState.getGamePhase() == 2 && myMoney >= cost ) {
                selectNum = 2;
                selectButton2.setBackgroundColor(Color.YELLOW);
                selectButton0.setBackgroundColor(prettyBlue);
                selectButton1.setBackgroundColor(0xFF33B5E5);
                selectButton3.setBackgroundColor(prettyBlue);
            }
        }
    }

    private class select3ButListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int cost = powerState.getAvailPowerplant().get(3).getCost();
            int myMoney = powerState.getGameInventories().get(playerNum).getMoney();
            if(powerState.getTurn() != playerNum) return;
            if (powerState.getGamePhase() == 0 || powerState.getGamePhase() == 2 && myMoney >= cost ) {
                selectNum = 3;
                selectButton3.setBackgroundColor(Color.YELLOW);
                selectButton2.setBackgroundColor(0xFF0099CC);
                selectButton1.setBackgroundColor(0xFF33B5E5);
                selectButton0.setBackgroundColor(prettyBlue);
            }
        }
    }

    private class CityButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ArrayList<Integer> trueIndexes = new ArrayList<Integer>();
            if(powerState.getGamePhase() != 5 && powerState.getGamePhase() != 6) return; //we don't want to mess with cities unless it's the right phase
            if(powerState.getTurn() != playerNum) return; //it's not your turn!
            for (int i = 0; i < 20; i++) {

                String cityName = (String) cityButtons[i].getText();


                if (v.getId() != cityButtons[i].getId()) {continue;} //if they didn't click on it, don't worry about it
                if(powerState.getBoughtCities()[i]) {return;} //if they did click on it, but it's already been purchased, don't worry about it

                //if they don't own any cities, they can select anything
                //if they own cities, they can only buy neighboring cities
                //checks to see if the one they clicked is a legitimate neighbor
                if(powerState.getGameInventories().get(playerNum).getMyCities().size() == 0) {
                    //if they haven't previously selected anything, all options are open
                    if (isArrayFalse(localCities)) {
                        cityButtons[i].setBackgroundColor(prettyBlue);
                        localCities[i] = true;
                    }
                    //if they have previously selected something, we must check to see if they are neighbors with cities they've tried to buy this round
                    else {
                        for (int k = 0; k<trueIndexes(localCities).size(); k++) {
                            if (powerState.getAvailCities().get(i).isNeighbor(trueIndexes(localCities).get(k))) {
                                if(canBuy(i)) {
                                    cityButtons[i].setBackgroundColor(prettyBlue);
                                    localCities[i] = true;
                                }
                            }
                        }//end for loop k
                    }
                }
                else{
                    //if they haven't previously selected anything during this round, they can only buy cities that are neighboring their previously bought cities
                    for(int j = 0; j<powerState.getGameInventories().get(playerNum).getMyCities().size(); j++) {
                        if (isArrayFalse(localCities) && powerState.getGameInventories().get(playerNum).getMyCities().get(j).containsNeighbor(trueIndexes(localCities))) {
                            if(canBuy(i)) {
                                cityButtons[i].setBackgroundColor(prettyBlue);
                                localCities[i] = true;
                            }
                        }
                        //if they have previously selected something, we must check to see if they are neighbors with cities they've tried to buy this round
                        else {
                            for (int l = 0; l < trueIndexes(localCities).size(); l++) {
                                if (powerState.getAvailCities().get(i).isNeighbor(trueIndexes(localCities).get(l))) {
                                    if(canBuy(i)) {
                                        cityButtons[i].setBackgroundColor(prettyBlue);
                                        localCities[i] = true;
                                    }
                                }
                            }//end for loop l
                        }
                    }//end for loop j
                }
            }//end for loop i
       }
    }//cityButtonListener

    //implements the coal buttons
    public class CoalButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            if (powerState.getGamePhase() == 3 || powerState.getGamePhase() == 4) {// || powerState.getGamePhase() == 5 || powerState.getGamePhase() == 6) {
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
                            if (localStore.coal[i] && (tempMoney >= i/3 + 1)) {
                                tempMoney -= i/3 +1;
                                coalButtons[i].setVisibility(View.INVISIBLE);
                                localStore.coal[i] = false;
                            }
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
            if (powerState.getGamePhase() == 3 || powerState.getGamePhase() == 4) {
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
                            if (localStore.oil[i]  && (tempMoney >= i/2 + 1)) {
                                tempMoney -= i/2 +1;
                                oilButtons[i].setVisibility(View.INVISIBLE);
                                localStore.oil[i] = false;
                            }

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
            if (powerState.getGamePhase() == 3 || powerState.getGamePhase() == 4) {
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
                            if (localStore.trash[i] && (tempMoney >= i/3 + 1)) {
                                tempMoney -= i/3 +1;
                                trashButtons[i].setVisibility(View.INVISIBLE);
                                localStore.trash[i] = false;
                            }
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
            if (powerState.getGamePhase() == 3 || powerState.getGamePhase() == 4) {
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
                            if (localStore.uranium[i] && (tempMoney >= i + 1)) {
                                tempMoney -= i +1;
                                uraniumButtons[i].setVisibility(View.INVISIBLE);
                                localStore.uranium[i] = false;
                            }
                        }
                    }
                }
            }
        }
    }

    //helper method to check to see if the entire array contains false elements
    public boolean isArrayFalse(boolean[] array){
        for(int i = 0; i < array.length; i++ ){
            if(array[i] == true) return false;
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

   public boolean canBuy(int city){

       int tempCost = 999999;
       ArrayList neighborhood = powerState.getAvailCities().get(city).getNeighborhood();
       ArrayList<City> myCities = powerState.getGameInventories().get(playerNum).getMyCities();
       ArrayList<Integer> index = new ArrayList<Integer>();

        if (myCities.size() == 0 && tempMoney >= 10){
           return true;
        }

        for(int i = 0; i< powerState.getAvailCities().get(city).returnNeighbors(myCities).size(); i++){
            index.add(powerState.getAvailCities().get(city).returnNeighbors(myCities).get(i).returnNeighborByIndex(city));

        }

       for(int i = 0; i < index.size(); i++){
           if(powerState.getAvailCities().get(city).getCosts().get(index.get(i)) < tempCost){
               tempCost = powerState.getAvailCities().get(city).getCosts().get(index.get(i));
           }
       }

       if(tempCost <= tempMoney){
           tempMoney -= tempCost;
           return true;
       }
       else{
           return false;
       }


   }
    public void checkResourceButtons(){
        for(int i = 0; i < 15; i++) {
            if (localStore.coal[i])
                coalButtons[i].setVisibility(View.VISIBLE);
            else{
                coalButtons[i].setVisibility(View.INVISIBLE);
            }

            if (localStore.trash[i])
                trashButtons[i].setVisibility(View.VISIBLE);
            else{
                trashButtons[i].setVisibility(View.INVISIBLE);
            }

            if (i < 10 && localStore.oil[i]){
                oilButtons[i].setVisibility(View.VISIBLE);
            }
            else if (i < 10 && !localStore.oil[i]){
                oilButtons[i].setVisibility(View.INVISIBLE);
            }

            if (i < 5 && localStore.uranium[i]){
                uraniumButtons[i].setVisibility(View.VISIBLE);
            }
            else if (i < 10 && !localStore.uranium[i]){
                uraniumButtons[i].setVisibility(View.INVISIBLE);
            }
        }
    }
}

