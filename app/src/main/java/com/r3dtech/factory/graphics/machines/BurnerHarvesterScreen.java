package com.r3dtech.factory.graphics.machines;

import android.graphics.Color;
import android.graphics.Rect;

import com.r3dtech.factory.MyGame;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.loading_timers.LoadingTimerDrawer;
import com.r3dtech.factory.graphics.slots.Slot;
import com.r3dtech.factory.logic.loading_timers.LoadingTimer;
import com.r3dtech.factory.logic.machines.BurnerHarvester;
import com.r3dtech.factory.logic.machines.FueledProcessingMachine;

/**
 * This is the burner harvester's screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 26/01/2018.
 */

public class BurnerHarvesterScreen extends MachineScreen {
    private static final int TIMER_SIDE_DIST = 32;
    private static final int TIMER_UP_DIST = 32;

    private BurnerHarvester harvester;
    private Slot fuel = Slot.getDefaultSlot();
    private Slot product = fuel.adjacentSlot(Slot.AdjacentSlot.RIGHT).adjacentSlot(Slot.AdjacentSlot.RIGHT)
            .adjacentSlot(Slot.AdjacentSlot.RIGHT);

    private Rect timerBounds = new Rect(
            fuel.getBounds().right + TIMER_SIDE_DIST,
            fuel.getBounds().top,
            product.getBounds().left - TIMER_SIDE_DIST,
            fuel.getBounds().bottom);
    private GenericDrawer<LoadingTimer> timerDrawer = new LoadingTimerDrawer(null);

    private Rect fuelBarBounds = new Rect(timerBounds);
    private GenericDrawer<Float> fuelLevelDrawer = new BarDrawer(Color.RED);


    public BurnerHarvesterScreen(MyGame game, BurnerHarvester harvester) {
        super(game);
        this.harvester = harvester;
        timerDrawer.setBounds(timerBounds);
        fuelBarBounds.offset(0, fuelBarBounds.height()+TIMER_UP_DIST);
        fuelLevelDrawer.setBounds(fuelBarBounds);

    }

    @Override
    public void paint() {
        super.paint();
        fuelLevelDrawer.draw(canvas, harvester.getCurrFuel()/(float) FueledProcessingMachine.MAX_FUEL);
        timerDrawer.draw(canvas, harvester.getTimer());
    }
    @Override
    protected Slot[] generateMachineSlots() {
        fuel.setItemStack(harvester.getFuel());
        product.setItemStack(harvester.getOutput());
        Slot[] slots = {fuel, product};
        return slots;
    }

    @Override
    protected String getMachineName() {
        return "Harvester";
    }
}
