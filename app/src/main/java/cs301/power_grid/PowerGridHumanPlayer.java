package cs301.power_grid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import game.Game;
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

    //instance of android activity we are running
    private GameMainActivity myActivity;

    private PowerState powerState = new PowerState();

    private int selectNum = -1;
    private int bidValue = -1;
    private ResourceStore localStore = new ResourceStore();

    //GUI features
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

    private int basicGray = Color.rgb(214, 215, 215);
    private int prettyBlue = Color.rgb(0, 221, 255);

    private Button okayButton;
    private Button passButton;

    private boolean[] localCities = new boolean[20];
    //denver, seattle, sf, la, missoula, boise, phx, omaha, okc, dallas, hou, stlou, chi, mem, new orl, det, atl, miami, bos, nyc

    private Button[] cityButtons = new Button[20];



    private Button selectButton0;
    private Button selectButton1;
    private Button selectButton2;
    private Button selectButton3;

    private ImageButton coalButton1;
    private ImageButton coalButton2;
    private ImageButton coalButton3;
    private ImageButton coalButton4;
    private ImageButton coalButton5;
    private ImageButton coalButton6;
    private ImageButton coalButton7;
    private ImageButton coalButton8;
    private ImageButton coalButton9;
    private ImageButton coalButton10;
    private ImageButton coalButton11;
    private ImageButton coalButton12;
    private ImageButton coalButton13;
    private ImageButton coalButton14;
    private ImageButton coalButton15;

    private ImageButton oilButton1;
    private ImageButton oilButton2;
    private ImageButton oilButton3;
    private ImageButton oilButton4;
    private ImageButton oilButton5;
    private ImageButton oilButton6;
    private ImageButton oilButton7;
    private ImageButton oilButton8;
    private ImageButton oilButton9;
    private ImageButton oilButton10;

    private ImageButton uraniumButton1;
    private ImageButton uraniumButton2;
    private ImageButton uraniumButton3;
    private ImageButton uraniumButton4;
    private ImageButton uraniumButton5;

    private ImageButton trashButton1;
    private ImageButton trashButton2;
    private ImageButton trashButton3;
    private ImageButton trashButton4;
    private ImageButton trashButton5;
    private ImageButton trashButton6;
    private ImageButton trashButton7;
    private ImageButton trashButton8;
    private ImageButton trashButton9;
    private ImageButton trashButton10;
    private ImageButton trashButton11;
    private ImageButton trashButton12;
    private ImageButton trashButton13;
    private ImageButton trashButton14;
    private ImageButton trashButton15;

    //Constructor
    public PowerGridHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return (RelativeLayout) findViewById(R.id.activity_main);
    }

    @Override
    public void receiveInfo(GameInfo info) {
        //if (powerState == null){return;}

        //update GUI - //update user resources and power plants 
        // setResources(((PowerState) info).getGameInventories().get(0)); 
        // setPowerPlants(((PowerState) info).getGameInventories().get(0)); 
        // setPowerPlants(((PowerState) info).getGameInventories().get(1));

        //check if info is a gameState
        if (info instanceof PowerState) {

            PowerState powerState = (PowerState) info;

            //update GUI -
            //look at what phase game is in then update accordingly
            int phase = powerState.getGamePhase();
            if (phase == 0) {
                /*First player chooses a power plant.
                * "OK" or "Pass" updates phase.*/
            } else if (phase == 1) {
                /*Bidding on power plant(s).
                * Bidding or "pass" updates phase.*/
            } else if (phase == 2) {
                /*Previous passer chooses a power plant.
                * "OK" or "Pass" updates phase.*/
            } else if (phase == 3) {
                /*Second player chooses resources.
                * "OK" or "Pass" updates phase.*/
            } else if (phase == 4) {
                /*First player chooses resources.
                * "OK" or "Pass" updates phase.*/
            } else if (phase == 5) {
                /*Second player chooses cities.
                * "OK" or "Pass" updates phase.*/
            } else if (phase == 6) {
                /*First player chooses cities.
                * "OK" or "Pass" updates phase.*/
            } else {
                /*Other cases go here*/
            }
        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.activity_main);
    }

    /**************************
     * onCreate
     * <p>
     * initializes buttons and registers them to listeners
     *
     * @param savedInstanceState
     *************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //spinners
        resourcesSpinner = (Spinner) findViewById(R.id.UserResourcesSpinner);
        resourcesSpinner.setOnItemSelectedListener(new resourcesSpinListener());

        powerPlantsSpinner = (Spinner) findViewById(R.id.userPowerPlantsSpinner);
        powerPlantsSpinner.setOnItemSelectedListener(new powerPlantsSpinListener());

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

        //bid edit text
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

        //resource buttons
        coalButton1 = (ImageButton) findViewById(R.id.cb1);
        coalButton2 = (ImageButton) findViewById(R.id.cb2);
        coalButton3 = (ImageButton) findViewById(R.id.cb3);
        coalButton4 = (ImageButton) findViewById(R.id.cb4);
        coalButton5 = (ImageButton) findViewById(R.id.cb5);
        coalButton6 = (ImageButton) findViewById(R.id.cb6);
        coalButton7 = (ImageButton) findViewById(R.id.cb7);
        coalButton8 = (ImageButton) findViewById(R.id.cb8);
        coalButton9 = (ImageButton) findViewById(R.id.cb9);
        coalButton10 = (ImageButton) findViewById(R.id.cb10);
        coalButton11 = (ImageButton) findViewById(R.id.cb11);
        coalButton12 = (ImageButton) findViewById(R.id.cb12);
        coalButton13 = (ImageButton) findViewById(R.id.cb13);
        coalButton14 = (ImageButton) findViewById(R.id.cb14);
        coalButton15 = (ImageButton) findViewById(R.id.cb15);

        coalButton1.setOnClickListener(new CoalButtonListener());
        coalButton2.setOnClickListener(new CoalButtonListener());
        coalButton3.setOnClickListener(new CoalButtonListener());
        coalButton4.setOnClickListener(new CoalButtonListener());
        coalButton5.setOnClickListener(new CoalButtonListener());
        coalButton6.setOnClickListener(new CoalButtonListener());
        coalButton7.setOnClickListener(new CoalButtonListener());
        coalButton8.setOnClickListener(new CoalButtonListener());
        coalButton9.setOnClickListener(new CoalButtonListener());
        coalButton10.setOnClickListener(new CoalButtonListener());
        coalButton11.setOnClickListener(new CoalButtonListener());
        coalButton12.setOnClickListener(new CoalButtonListener());
        coalButton13.setOnClickListener(new CoalButtonListener());
        coalButton14.setOnClickListener(new CoalButtonListener());
        coalButton15.setOnClickListener(new CoalButtonListener());

        oilButton1 = (ImageButton) findViewById(R.id.ob1);
        oilButton2 = (ImageButton) findViewById(R.id.ob2);
        oilButton3 = (ImageButton) findViewById(R.id.ob3);
        oilButton4 = (ImageButton) findViewById(R.id.ob4);
        oilButton5 = (ImageButton) findViewById(R.id.ob5);
        oilButton6 = (ImageButton) findViewById(R.id.ob6);
        oilButton7 = (ImageButton) findViewById(R.id.ob7);
        oilButton8 = (ImageButton) findViewById(R.id.ob8);
        oilButton9 = (ImageButton) findViewById(R.id.ob9);
        oilButton10 = (ImageButton) findViewById(R.id.ob10);

        oilButton1.setOnClickListener(new OilButtonListener());
        oilButton2.setOnClickListener(new OilButtonListener());
        oilButton3.setOnClickListener(new OilButtonListener());
        oilButton4.setOnClickListener(new OilButtonListener());
        oilButton5.setOnClickListener(new OilButtonListener());
        oilButton6.setOnClickListener(new OilButtonListener());
        oilButton7.setOnClickListener(new OilButtonListener());
        oilButton8.setOnClickListener(new OilButtonListener());
        oilButton9.setOnClickListener(new OilButtonListener());
        oilButton10.setOnClickListener(new OilButtonListener());

        uraniumButton1 = (ImageButton) findViewById(R.id.ub1);
        uraniumButton2 = (ImageButton) findViewById(R.id.ub2);
        uraniumButton3 = (ImageButton) findViewById(R.id.ub3);
        uraniumButton4 = (ImageButton) findViewById(R.id.ub4);
        uraniumButton5 = (ImageButton) findViewById(R.id.ub5);

        uraniumButton1.setOnClickListener(new NuclearButtonListener());
        uraniumButton2.setOnClickListener(new NuclearButtonListener());
        uraniumButton3.setOnClickListener(new NuclearButtonListener());
        uraniumButton4.setOnClickListener(new NuclearButtonListener());
        uraniumButton5.setOnClickListener(new NuclearButtonListener());

        trashButton1 = (ImageButton) findViewById(R.id.tb1);
        trashButton2 = (ImageButton) findViewById(R.id.tb2);
        trashButton3 = (ImageButton) findViewById(R.id.tb3);
        trashButton4 = (ImageButton) findViewById(R.id.tb4);
        trashButton5 = (ImageButton) findViewById(R.id.tb5);
        trashButton6 = (ImageButton) findViewById(R.id.tb6);
        trashButton7 = (ImageButton) findViewById(R.id.tb7);
        trashButton8 = (ImageButton) findViewById(R.id.tb8);
        trashButton9 = (ImageButton) findViewById(R.id.tb9);
        trashButton10 = (ImageButton) findViewById(R.id.tb10);
        trashButton11 = (ImageButton) findViewById(R.id.tb11);
        trashButton12 = (ImageButton) findViewById(R.id.tb12);
        trashButton13 = (ImageButton) findViewById(R.id.tb13);
        trashButton14 = (ImageButton) findViewById(R.id.tb14);
        trashButton15 = (ImageButton) findViewById(R.id.tb15);

        trashButton1.setOnClickListener(new TrashButtonListener());
        trashButton2.setOnClickListener(new TrashButtonListener());
        trashButton3.setOnClickListener(new TrashButtonListener());
        trashButton4.setOnClickListener(new TrashButtonListener());
        trashButton5.setOnClickListener(new TrashButtonListener());
        trashButton6.setOnClickListener(new TrashButtonListener());
        trashButton7.setOnClickListener(new TrashButtonListener());
        trashButton8.setOnClickListener(new TrashButtonListener());
        trashButton9.setOnClickListener(new TrashButtonListener());
        trashButton10.setOnClickListener(new TrashButtonListener());
        trashButton11.setOnClickListener(new TrashButtonListener());
        trashButton12.setOnClickListener(new TrashButtonListener());
        trashButton13.setOnClickListener(new TrashButtonListener());
        trashButton14.setOnClickListener(new TrashButtonListener());
        trashButton15.setOnClickListener(new TrashButtonListener());

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

    }

    /**
     * setResources
     * <p>
     * to be used in the spinners methods, sets resources text on GUI
     *
     * @param rsc //resource
     */
    private void setResources(Inventory rsc) {
        moneyTextView.setText("" + rsc.getMoney());
        coalNumView.setText("" + rsc.getCoal());
        oilNumView.setText("" + rsc.getOil());
        trashNumView.setText("" + rsc.getTrash());
        nuclearNumView.setText("" + rsc.getUranium());
    }

    /**
     * setPowerPlants
     * <p>
     * to be used in the spinners methods, sets power plants text on GUI
     *
     * @param upp //userPowerPlant
     */
    private void setPowerPlants(Inventory upp) {
        costTextView1.setText("");
        typeTextView1.setText("");
        numberTextView1.setText("");
        housesTextView1.setText("");

        costTextView2.setText("");
        typeTextView2.setText("");
        numberTextView2.setText("");
        housesTextView2.setText("");

        costTextView3.setText("");
        typeTextView3.setText("");
        numberTextView3.setText("");
        housesTextView3.setText("");

        costTextView4.setText("");
        typeTextView4.setText("");
        numberTextView4.setText("");
        housesTextView4.setText("");

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
           if(powerState.getTurn() != powerState.getPlayerId()) return;


            int phase = powerState.getGamePhase();
            if (phase == 0) {

               SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridHumanPlayer.this, selectNum);
                game.sendAction(sppa);

            } else if (phase == 1) {

                BidAction ba = new BidAction(PowerGridHumanPlayer.this, bidValue);
                game.sendAction(ba);

            } else if (phase == 2) {

                SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridHumanPlayer.this, selectNum);
                game.sendAction(sppa);

            }
            //if phase ==3 and we haven't gone through the whole resource store, we gotta keep sending actions
            //only one can be sent at a time, hence the returns.
            //work on this.
            else if (phase == 3 || phase == 4) {
                for (int i = 0; i < 15; i++) {
                    //if it's not available in the local store, it must have been bought!
                    if (!localStore.coal[i]) {
                        localStore.coal[i] = true;
                        BuyCoalAction bca = new BuyCoalAction(PowerGridHumanPlayer.this, i);
                        game.sendAction(bca);
                        return;
                    }

                    if (!localStore.trash[i]) {
                        localStore.coal[i] = true;
                        BuyTrashAction bta = new BuyTrashAction(PowerGridHumanPlayer.this, i);
                        game.sendAction(bta);
                        return;
                    }

                    if (i < 5 && !localStore.uranium[i]) {
                        localStore.uranium[i] = true;
                        BuyUraniumAction bua = new BuyUraniumAction(PowerGridHumanPlayer.this, i);
                        game.sendAction(bua);
                        return;
                    }

                    if (i < 10 && !localStore.oil[i]) {
                        localStore.oil[i] = true;
                        BuyOilAction boa = new BuyOilAction(PowerGridHumanPlayer.this, i);
                        game.sendAction(boa);
                        return;
                    }

                }
                //if we get to this code then it has gone through the whole store, bought what was needed to be bought, and now needs to update the phase
                //a pass action will do just fine
                PassAction pA = new PassAction(PowerGridHumanPlayer.this);
                game.sendAction(pA);


            } else if (phase == 5 || phase == 6) {
                for (int i = 0; i < 20; i++) {
                    if(localCities[i]){
                        localCities[i] = false;
                        BuyCityAction bca = new BuyCityAction(PowerGridHumanPlayer.this, powerState.getAvailCities().get(i), i);
                        game.sendAction(bca);
                        return;
                    }
                }
                PassAction pA = new PassAction(PowerGridHumanPlayer.this);
                game.sendAction(pA);

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
            if(powerState.getTurn() != powerState.getPlayerId()) return; //it's not your turn!
            for (int i = 0; i < 20; i++) {

                if (v.getId() != cityButtons[i].getId()) continue; //if they didn't click on it, don't worry about it
                if(powerState.getBoughtCities()[i]) continue; //if they did click on it, but it's already been purchased, don't worry about it

                //if they don't own any cities, they can select anything
                //if they own cities, they can only buy neighboring cities
                //checks to see if the one they clicked is a legitimate neighbor
                if(powerState.getGameInventories().get(powerState.getPlayerId()).getMyCities().size() == 0 || powerState.getAvailCities().get(i).containsNeighbor(powerState.getGameInventories().get(powerState.getPlayerId()).getMyCities())) {
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
                            if(powerState.getAvailCities().get(i).isNeighbor(powerState.getAvailCities().get(trueIndexes.get(j)))){
                                if (localCities[i]) {
                                    cityButtons[i].setBackgroundColor(basicGray);
                                    localCities[i] = false;
                                }
                                else {
                                    cityButtons[i].setBackgroundColor(prettyBlue);
                                    localCities[i] = true;

                                }
                            }
                        }
                    }
                }

            }
        }
    }


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
                                coalButton1.setBackgroundColor(Color.TRANSPARENT);
                                localStore.coal[i] = false;
                            } else {

                                coalButton1.setBackgroundColor(Color.BLACK);
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
                                oilButton1.setBackgroundColor(Color.TRANSPARENT);
                                localStore.oil[i] = false;
                            } else {

                                oilButton1.setBackgroundColor(Color.BLACK);
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
                                trashButton1.setBackgroundColor(Color.TRANSPARENT);
                                localStore.trash[i] = false;
                            } else {

                                trashButton1.setBackgroundColor(Color.BLACK);
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
                                uraniumButton1.setBackgroundColor(Color.TRANSPARENT);
                                localStore.uranium[i] = false;
                            } else {

                                uraniumButton1.setBackgroundColor(Color.BLACK);
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

