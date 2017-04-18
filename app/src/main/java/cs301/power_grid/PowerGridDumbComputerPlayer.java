package cs301.power_grid;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import game.Game;
import game.GameComputerPlayer;
import game.GameMainActivity;
import game.GamePlayer;
import game.infoMsg.GameInfo;

/**
 * @author Luchini Guilian, Tibbetts Nathan, Douville Luke, Hoang Paul
 */


public class PowerGridDumbComputerPlayer extends GameComputerPlayer {
    private GameMainActivity activity;
    //    PowerState powerState = new PowerState();
    PowerState powerState;// = new PowerState(PowerGridDumbComputerPlayer.this.powerState);
//    PowerGridHumanPlayer instanceVariables = new PowerGridHumanPlayer("DumbAI");

    private int selectNum = -1;
    private int bidValue = -1;
    private ResourceStore localStore = new ResourceStore();
    View v;
    /*Copy over all GUI elements from PowerGridHumanPlayer?*/
    private Button okayButton;
    private Button passButton;
    //    private EditText bidVal;// = (EditText)findViewByIdR.id.bidEditText;
//    bidValue = Integer.parseInt(bidVal.getText().toString());
//    private Button selectButton0;
//    private Button selectButton1;
//    private Button selectButton2;
//    private Button selectButton3;
    private Powerplant tempPlant;// = new Powerplant();
    private Powerplant selectedPowerPlant;// = new Powerplant();
    private int basicGray = Color.rgb(214,215,215);
    private int prettyBlue = Color.rgb(0, 221, 255);

    private boolean isClicked = false;

//    private GamePlayer tempPlayer;

    /**
     * constructor
     *
     * @param name the player's name (e.g., "John")
     */
    public PowerGridDumbComputerPlayer(String name) {
        super(name);
    }

    /*Don't need onCreate() method, since we won't be updating the GUI.*/
    /*Does not recognize methods onCreate(), setContentView(), or findViewById().*/
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        okayButton = (Button)findViewById(R.id.okButton);
//        okayButton.setOnClickListener(new okayButListener());
//        passButton = (Button)findViewById(R.id.passButton);
//        passButton.setOnClickListener(new passButListener());
//
//        selectButton0 = (Button)findViewById(R.id.select0);
//        selectButton1 = (Button)findViewById(R.id.select1);
//        selectButton2 = (Button)findViewById(R.id.select2);
//        selectButton3 = (Button)findViewById(R.id.select3);
//    }

    private class okayButtonListener implements View.OnClickListener {
        //        private boolean isClicked = true;
        /*Declare and initialize a new Powerplant object with all the values of the Powerplant that the dumb AI selects.*/
//        private Powerplant selectedPowerPlant;// = new Powerplant(selectedPPlant.getCost(), selectedPplant.getPtp(), selectedPplant.getHp(), selectedPplant.getKind());
//        GameInfo gi;
        @Override
        public void onClick(View view) {
            isClicked = true;

//            if(isClicked == true) {
//
//            }
            int phase = powerState.getGamePhase();
//            if(powerState.getTurn() != powerState.getPlayerId()) {
//            if(powerState.getGamePhase() != powerState.getPlayerId()) {
//            if(phase != powerState.getPlayerId()) {
//                return;
//            }

            if(phase == 0) {
                /*First player chooses a power plant.
                * "OK" or "Pass" updates phase.*/
                /*Set all the variables of a Powerplant object here.*/
                /*Use a random int because this is the dumb AI.*/
//                int rand = ((int)Math.random())*4;
//                selectedPowerPlant.setCost(powerState.getGameInventories().get(0).getMyPlants().get(rand).getCost());
//                selectedPowerPlant.setPtP(powerState.getGameInventories().get(0).getMyPlants().get(rand).getPtP());
//                selectedPowerPlant.setHp(powerState.getGameInventories().get(0).getMyPlants().get(rand).getHp());
//                selectedPowerPlant.setKind(powerState.getGameInventories().get(0).getMyPlants().get(rand).getKind());
//                SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridDumbComputerPlayer.this, selectNum);
//                SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridDumbComputerPlayer.this, selectedPowerPlant);
//                game.sendAction(sppa);
                return;
            }
            else if (phase == 1 ) {
                /*Bidding on power plant(s).
                * Bidding or "pass" updates phase.*/
//                BidAction ba = new BidAction(PowerGridDumbComputerPlayer.this, bidValue);
//                game.sendAction(ba);
            }
            else if (phase == 2 ) {
                /*Previous passer chooses a power plant.
                * "OK" or "Pass" updates phase.*/
//                SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridDumbComputerPlayer.this, selectNum);
//                SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridDumbComputerPlayer.this, selectedPowerPlant);
//                game.sendAction(sppa);
            }
            //if phase ==3 and we haven't gone through the whole resource store, we gotta keep sending actions
            //only one can be sent at a time, hence the returns.
            //work on this.
            else if (phase == 3 ) {
                /*Second player chooses resources.
                * "OK" or "Pass" updates phase.*/
//                for(int i = 0; i < 15; i++){
//                    //if it's not available in the local store, it must have been bought!
//                    if(!localStore.coal[i]){
//                        BuyCoalAction bca = new BuyCoalAction(PowerGridDumbComputerPlayer.this, i);
//                        game.sendAction(bca);
//                        return;
//                    }
//
//                    if(!localStore.trash[i]){
//                        BuyTrashAction bta = new BuyTrashAction(PowerGridDumbComputerPlayer.this, i);
//                        game.sendAction(bta);
//                        return;
//                    }
//
//                    if(i < 5 && !localStore.uranium[i]){
//                        BuyUraniumAction bua = new BuyUraniumAction(PowerGridDumbComputerPlayer.this, i);
//                        game.sendAction(bua);
//                        return;
//                    }
//
//                    if(i < 10 && !localStore.oil[i]){
//                        BuyOilAction boa = new BuyOilAction(PowerGridDumbComputerPlayer.this, i);
//                        game.sendAction(boa);
//                        return;
//                    }
//
//                }
            }
            else if (phase == 4 ) {
                /*First player chooses resources.
                * "OK" or "Pass" updates phase.*/
//                for(int i = 0; i < 15; i++){
//                    //if it's not available in the local store, it must have been bought!
//                    if(!localStore.coal[i]){
//                        BuyCoalAction bca = new BuyCoalAction(PowerGridDumbComputerPlayer.this, i);
//                        game.sendAction(bca);
//                    }
//                    if(!localStore.trash[i]){
//                        BuyTrashAction bta = new BuyTrashAction(PowerGridDumbComputerPlayer.this, i);
//                        game.sendAction(bta);
//                    }
//                    if(i < 5){
//                        if(!localStore.uranium[i]) {
//                            BuyUraniumAction bua = new BuyUraniumAction(PowerGridDumbComputerPlayer.this, i);
//                            game.sendAction(bua);
//                        }
//                    }
//                    if(i < 10){
//                        if(!localStore.oil[i]) {
//                            BuyOilAction boa = new BuyOilAction(PowerGridDumbComputerPlayer.this, i);
//                            game.sendAction(boa);
//                        }
//                    }
//                }
            }
            else if (phase == 5 ) {
                /*Second player chooses cities.
                * "OK" or "Pass" updates phase.*/
            }
            else if (phase == 6 ) {
                /*First player chooses cities.
                * "OK" or "Pass" updates phase.*/
            }
            else {
                /*Other cases go here.*/
            }
        }
    }

    private class passButtonListener implements View.OnClickListener {
        //        private boolean isClicked = true;
        @Override
        public void onClick(View view) {
//            if(isClicked == true) {
//
//            }
            isClicked = true;
        }
    }

    /*The bid field is just an edit text.  You have to use the virtual keyboard's "enter" key to lock in a value.*/
//    private class bidEditTextListener implements View.OnKeyListener {
//        @Override
//        public boolean onKey(View view, int keycode, KeyEvent keyEvent) {
//            isClicked = true;
//            return true;
//        }
//    }
    /*Or is the OK button being used to enter in bids??  If this is the case, you would just use the okayButtonListener.*/

//    private class select0ButtonListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            selectNum = 0;
//            /*Don't need GUI elements.*/
////            selectButton0.setBackgroundColor(Color.YELLOW);
////            selectButton1.setBackgroundColor(prettyBlue);
////            selectButton2.setBackgroundColor(prettyBlue);
////            selectButton3.setBackgroundColor(prettyBlue);
//            Powerplant selectedPowerPlant;// = new Powerplant(selectedPowerPlant.getCost(), selectedPowerPlant.getPtp(), selectedPowerPlant.getHp(), selectedPowerPlant.getKind());
//            SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridDumbComputerPlayer.this, selectedPowerPlant);
//        }
//    }
//
//    private class select1ButListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            selectNum = 1;
//            /*Don't need GUI elements.*/
////            selectButton1.setBackgroundColor(Color.YELLOW);
////            selectButton0.setBackgroundColor(prettyBlue);
////            selectButton2.setBackgroundColor(prettyBlue);
////            selectButton3.setBackgroundColor(prettyBlue);
//            Powerplant selectedPowerPlant;// = new Powerplant(selectedPowerPlant.getCost(), selectedPowerPlant.getPtp(), selectedPowerPlant.getHp(), selectedPowerPlant.getKind());
//        }
//    }
//
//    private class select2ButListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            selectNum = 2;
//            /*Don't need GUI elements.*/
////            selectButton2.setBackgroundColor(Color.YELLOW);
////            selectButton0.setBackgroundColor(prettyBlue);
////            selectButton1.setBackgroundColor(prettyBlue);
////            selectButton3.setBackgroundColor(prettyBlue);
//            Powerplant selectedPowerPlant;// = new Powerplant(selectedPowerPlant.getCost(), selectedPowerPlant.getPtp(), selectedPowerPlant.getHp(), selectedPowerPlant.getKind());
//        }
//    }
//
//    private class select3ButListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v){
//            selectNum = 3;
//            /*Don't need GUI elements.*/
////            selectButton3.setBackgroundColor(Color.YELLOW);
////            selectButton2.setBackgroundColor(prettyBlue);
////            selectButton1.setBackgroundColor(prettyBlue);
////            selectButton0.setBackgroundColor(prettyBlue);
//            Powerplant selectedPowerPlant;// = new Powerplant(selectedPowerPlant.getCost(), selectedPowerPlant.getPtp(), selectedPowerPlant.getHp(), selectedPowerPlant.getKind());
//        }
//    }

    public void receiveInfo(GameInfo info) {
//        Powerplant selectedPowerPlant;// = new Powerplant(selectedPowerPlant.getCost(), selectedPowerPlant.getPtp(), selectedPowerPlant.getHp(), selectedPowerPlant.getKind());
        //check if info is a gameState
        if (info instanceof PowerState) {
            powerState = (PowerState)info;
            //look at what phase game is in then make a move
            int phase = powerState.getGamePhase();
            if (phase == 0) {
                /*First player chooses a power plant.
                * "OK" or "Pass" updates phase.*/

                /*Use a random integer because this is the dumb AI.*/
                int randOKPass = (int)(Math.random()*2);
                int randPPlantToBidOnIndex = ((int)Math.random())*42;
//                int rand1 = ((int)Math.random())*4;

                powerState.initiatePowerPlants();
                selectedPowerPlant = powerState.getAvailPowerplant().get(randPPlantToBidOnIndex);

//                okayButtonListener obl = new okayButtonListener();
//                okayButton.setOnClickListener(obl);
//                passButtonListener pbl = new passButtonListener();
//                passButton.setOnClickListener(pbl);
                switch(randOKPass) {
                    case 1: randOKPass = 1;
                        okayButton.setOnClickListener(new okayButtonListener());
                    case 2: randOKPass = 2;
                        passButton.setOnClickListener(new passButtonListener());
                }
//                okayButton.setOnClickListener(new okayButtonListener());
//                passButton.setOnClickListener(new passButtonListener());

//                selectedPowerPlant.setCost(powerState.getGameInventories().get(0).getMyPlants().get(rand).getCost());
//                selectedPowerPlant.setCost(powerState.getGameInventories().get(1).getMyPlants().get(rand).getCost());
//                selectedPowerPlant.setCost(powerState.getAvailPowerplant().get(rand).getCost());
                /*Proper setter callback*/
//                selectedPowerPlant.setCost(powerState.getAvailPowerplant().get(randPPlantToBidOn).getCost());


//                selectedPowerPlant.setPtP(powerState.getGameInventories().get(0).getMyPlants().get(rand).getPtP());
//                selectedPowerPlant.setPtP(powerState.getGameInventories().get(1).getMyPlants().get(rand).getPtP());
                /*Proper setter callback*/
//                selectedPowerPlant.setPtP(powerState.getAvailPowerplant().get(randPPlantToBidOn).getPtP());


//                selectedPowerPlant.setHp(powerState.getGameInventories().get(0).getMyPlants().get(rand).getHp());
//                selectedPowerPlant.setHp(powerState.getGameInventories().get(1).getMyPlants().get(rand).getHp());
                /*Proper setter callback*/
//                selectedPowerPlant.setHp(powerState.getAvailPowerplant().get(randPPlantToBidOn).getHp());


//                selectedPowerPlant.setKind(powerState.getGameInventories().get(0).getMyPlants().get(rand).getKind());
//                selectedPowerPlant.setKind(powerState.getGameInventories().get(1).getMyPlants().get(rand).getKind());
                /*Proper setter callback*/
//                selectedPowerPlant.setKind(powerState.getAvailPowerplant().get(randPPlantToBidOn).getKind());



                /*Assigning instance variables of selectedPowerPlant using the Powerplant's constructor.*/
                /*Don't assign instance variables using the constructor*/
//                selectedPowerPlant = new Powerplant(selectedPowerPlant.setCost(powerState.getAvailPowerplant().get(randPPlantToBidOn).getCost()), selectedPowerPlant.setPtP(powerState.getAvailPowerplant().get(randPPlantToBidOn).getPtP()), selectedPowerPlant.setHp(powerState.getAvailPowerplant().get(randPPlantToBidOn).getHp()), selectedPowerPlant.setKind(powerState.getAvailPowerplant().get(randPPlantToBidOn).getKind()));

                if(isClicked == true) {
                    phase = 1;
                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
                    game.sendAction(upa);
//                    return;
                }
                if(randOKPass == 1) {
//                    phase = 1;

//                    selectedPowerPlant.setCost(powerState.getAvailPowerplant().get(randPPlantToBidOnIndex).getCost());
//                    selectedPowerPlant.setPtP(powerState.getAvailPowerplant().get(randPPlantToBidOnIndex).getPtP());
//                    selectedPowerPlant.setHp(powerState.getAvailPowerplant().get(randPPlantToBidOnIndex).getHp());
//                    selectedPowerPlant.setKind(powerState.getAvailPowerplant().get(randPPlantToBidOnIndex).getKind());

//                    selectedPowerPlant = powerState.getAvailPowerplant().get(randPPlantToBidOnIndex);

                    /*Send the updated phase and the game action.*/
                    SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridDumbComputerPlayer.this, selectedPowerPlant);
                    game.sendAction(sppa);
//                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
//                    game.sendAction(upa);
                    return;
                }

            } else if(phase == 1) {
                /*Bidding on power plant(s).
                * Bidding or "pass" updates phase.*/
                BidAction bid;
//                BidAction bid = new BidAction(this, (int)Math.random()*20);
//                bid.getBid();
//                bidVal.setOnKeyListener(new bidEditTextListener());
                int randOKPass = (int)(Math.random()*2);
                switch(randOKPass) {
                    case 1: randOKPass = 1;
                        okayButton.setOnClickListener(new okayButtonListener());
                    case 2: randOKPass = 2;
                        passButton.setOnClickListener(new passButtonListener());
                }
//                okayButton.setOnClickListener(new okayButtonListener());
//                passButton.setOnClickListener(new passButtonListener());

                /*You could just put this in an else statement after evaluating randOKPass below.*/
                if(isClicked == true) {
                    phase = 2;
                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
                    game.sendAction(upa);
//                    return;
                }
                if(randOKPass == 1) {
//                    phase = 2;
                    bid = new BidAction(this, (int)Math.random()*20);
                    bid.getBid();
                    game.sendAction(bid);
//                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
//                    game.sendAction(upa);
                    return;
                }
//                if(okayButListener.onClick(v)) {
//                    phase = 2;
//                    return;
//                }
////                private class okayButListener implements View.OnClickListener {
//                class okayButListener implements View.OnClickListener {
//                    private boolean isClicked = true;
//                    private int tempPhase = 1;
//                    @Override
//                    public void onClick(View view) {
//                        if(isClicked == true) {
//                            tempPhase = 2;
//                            return;
//                        }
//                    }
//                    phase = tempPhase;
//                }
//                class passButListener implements View.OnClickListener {
//                    private boolean isClicked = true;
//                    @Override
//                    public void onClick(View view) {
//                        if(isClicked == true) {
//                            phase = 2;
//                            return;
//                        }
//                    }
//                }

            } else if(phase == 2) {
                /*Previous passer chooses a power plant.
                * "OK" or "Pass" updates phase.*
                * Same as when phase is 0 b/c same logic.
                * We don't determine who the previous passer was in this if statement.*/
                int randOKPass = (int)(Math.random()*2);
                int randPPlantToBidOn = (int)(Math.random()*42);
                selectedPowerPlant.setCost(powerState.getAvailPowerplant().get(randPPlantToBidOn).getCost());
                selectedPowerPlant.setPtP(powerState.getAvailPowerplant().get(randPPlantToBidOn).getPtP());
                selectedPowerPlant.setHp(powerState.getAvailPowerplant().get(randPPlantToBidOn).getHp());
                selectedPowerPlant.setKind(powerState.getAvailPowerplant().get(randPPlantToBidOn).getKind());
//                okayButton.setOnClickListener(new okayButtonListener());
//                passButton.setOnClickListener(new passButtonListener());
                switch(randOKPass) {
                    case 1: randOKPass = 1;
                        okayButton.setOnClickListener(new okayButtonListener());
                    case 2: randOKPass = 2;
                        passButton.setOnClickListener(new okayButtonListener());
                }
                /*You could just put this in an else statement after evaluating randOKPass below.*/
                if(isClicked == true) {
                    phase = 3;
                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
                    game.sendAction(upa);
//                    return;
                }
                if(randOKPass == 1) {
//                    phase = 3;
                    SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridDumbComputerPlayer.this, selectedPowerPlant);
                    game.sendAction(sppa);
//                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
//                    game.sendAction(upa);
                    return;
                }
            } else if(phase == 3) {
                /*Second player chooses resources.
                * "OK" or "Pass" updates phase.*/
                int randOKPass = (int)(Math.random() * 2);
                switch (randOKPass) {
                    case 1:
                        randOKPass = 1;
                        okayButton.setOnClickListener(new okayButtonListener());
                    case 2:
                        randOKPass = 2;
                        passButton.setOnClickListener(new okayButtonListener());
                }
                /*You could just put this in an else statement after evaluating randOKPass below.*/
                if (isClicked == true) {
                    phase = 4;
                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
                    game.sendAction(upa);
//                    return;
                }
                if (randOKPass == 1) {
//                    phase = 4;
//                    SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridDumbComputerPlayer.this, selectedPowerPlant);
//                    game.sendAction(sppa);
//                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
//                    game.sendAction(upa);
//                    return;
//                }

                    for (int i = 0; i < 15; i++) {
                        //if it's not available in the local store, it must have been bought!
                        if (!localStore.coal[i]) {
                            BuyCoalAction bca = new BuyCoalAction(PowerGridDumbComputerPlayer.this, i);
                            game.sendAction(bca);
                            return;
                        }

                        if (!localStore.trash[i]) {
                            BuyTrashAction bta = new BuyTrashAction(PowerGridDumbComputerPlayer.this, i);
                            game.sendAction(bta);
                            return;
                        }

                        if (i < 5 && !localStore.uranium[i]) {
                            BuyUraniumAction bua = new BuyUraniumAction(PowerGridDumbComputerPlayer.this, i);
                            game.sendAction(bua);
                            return;
                        }

                        if (i < 10 && !localStore.oil[i]) {
                            BuyOilAction boa = new BuyOilAction(PowerGridDumbComputerPlayer.this, i);
                            game.sendAction(boa);
                            return;
                        }
                    }
                }

            } else if(phase == 4) {
                /*First player chooses resources.
                * "OK" or "Pass" updates phase.*/
                int randOKPass = (int)(Math.random() * 2);
                switch (randOKPass) {
                    case 1:
                        randOKPass = 1;
                        okayButton.setOnClickListener(new okayButtonListener());
                    case 2:
                        randOKPass = 2;
                        passButton.setOnClickListener(new okayButtonListener());
                }
                /*You could just put this in an else statement after evaluating randOKPass below.*/
                if (isClicked == true) {
                    phase = 4;
                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
                    game.sendAction(upa);
//                    return;
                }
                if (randOKPass == 1) {
//                    phase = 4;
//                    SelectPowerPlantAction sppa = new SelectPowerPlantAction(PowerGridDumbComputerPlayer.this, selectedPowerPlant);
//                    game.sendAction(sppa);
//                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
//                    game.sendAction(upa);
//                    return;

                    for(int i = 0; i < 15; i++) {
                        //if it's not available in the local store, it must have been bought!
                        if (!localStore.coal[i]) {
                            BuyCoalAction bca = new BuyCoalAction(PowerGridDumbComputerPlayer.this, i);
                            game.sendAction(bca);
                        }
                        if (!localStore.trash[i]) {
                            BuyTrashAction bta = new BuyTrashAction(PowerGridDumbComputerPlayer.this, i);
                            game.sendAction(bta);
                        }
                        if (i < 5) {
                            if (!localStore.uranium[i]) {
                                BuyUraniumAction bua = new BuyUraniumAction(PowerGridDumbComputerPlayer.this, i);
                                game.sendAction(bua);
                            }
                        }
                        if (i < 10) {
                            if (!localStore.oil[i]) {
                                BuyOilAction boa = new BuyOilAction(PowerGridDumbComputerPlayer.this, i);
                                game.sendAction(boa);
                            }
                        }
                    }
                }
            } else if(phase == 5) {
                /*Second player chooses cities.
                * "OK" or "Pass" updates phase.*/
                City selectedCity;
                int randCityToBidOnIndex = (int)(Math.random() * 20);
                selectedCity = powerState.getAvailCities().get(randCityToBidOnIndex);
                int randOKPass = (int)(Math.random() * 2);
                switch(randOKPass) {
                    case 1: randOKPass = 1;
                        okayButton.setOnClickListener(new okayButtonListener());
                    case 2: randOKPass = 2;
                        passButton.setOnClickListener(new passButtonListener());
                }
                if(isClicked == true) {
                    phase = 6;
                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
                    game.sendAction(upa);
                }
                if(randOKPass == 1) {
                    BuyCityAction bca = new BuyCityAction(PowerGridDumbComputerPlayer.this, selectedCity);
                    game.sendAction(bca);
                    return;
                }

            } else if(phase == 6) {
                /*First player chooses cities.
                * "OK" or "Pass" updates phase.*/
                City selectedCity;
                int randCityToBidOnIndex = (int)(Math.random() * 20);
                selectedCity = powerState.getAvailCities().get(randCityToBidOnIndex);
                int randOKPass = (int)(Math.random() * 2);
                switch(randOKPass) {
                    case 1: randOKPass = 1;
                        okayButton.setOnClickListener(new okayButtonListener());
                    case 2: randOKPass = 2;
                        passButton.setOnClickListener(new passButtonListener());
                }
                if(isClicked == true) {
                    phase = 6;
                    UpdatePhaseAction upa = new UpdatePhaseAction(PowerGridDumbComputerPlayer.this, phase);
                    game.sendAction(upa);
                }
                if(randOKPass == 1) {
                    BuyCityAction bca = new BuyCityAction(PowerGridDumbComputerPlayer.this, selectedCity);
                    game.sendAction(bca);
                    return;
                }
            } else {
                /*Other cases go here.*/
            }
        }
    }
}

