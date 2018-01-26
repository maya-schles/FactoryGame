package com.r3dtech.factory.graphics.machines;

import android.graphics.Color;
import android.graphics.Rect;

import com.r3dtech.factory.MyGame;
import com.r3dtech.factory.graphics.GenericDrawer;
import com.r3dtech.factory.graphics.slots.Slot;
import com.r3dtech.factory.graphics.loading_timers.LoadingTimerDrawer;
import com.r3dtech.factory.logic.loading_timers.LoadingTimer;
import com.r3dtech.factory.logic.machines.FueledProcessingMachine;
import com.r3dtech.factory.logic.machines.StoneFurnace;

/**
 * This is the stone furnace screen.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 24/01/2018.
 */

public class StoneFurnaceScreen extends MachineScreen {
    private static final int TIMER_SIDE_DIST = 32;

    private StoneFurnace furnace;
    private Slot smeltable = Slot.getDefaultSlot();
    private Slot fuel = smeltable.adjacentSlot(Slot.AdjacentSlot.BELOW);
    private Slot product = smeltable.adjacentSlot(Slot.AdjacentSlot.RIGHT).adjacentSlot(Slot.AdjacentSlot.RIGHT)
            .adjacentSlot(Slot.AdjacentSlot.RIGHT);

    private Rect timerBounds = new Rect(
            smeltable.getBounds().right + TIMER_SIDE_DIST,
            smeltable.getBounds().top,
            product.getBounds().left - TIMER_SIDE_DIST,
            smeltable.getBounds().bottom);

    private GenericDrawer<Float> fuelLevelDrawer = new BarDrawer(Color.RED);
    private Rect fuelBarBounds = new Rect(
            fuel.getBounds().right + TIMER_SIDE_DIST,
            fuel.getBounds().top,
            product.getBounds().left - TIMER_SIDE_DIST,
            fuel.getBounds().bottom);
    private GenericDrawer<LoadingTimer> timerDrawer = new LoadingTimerDrawer(null);


    public StoneFurnaceScreen(MyGame game, StoneFurnace furnace) {
        super(game);
        this.furnace = furnace;
        timerDrawer.setBounds(timerBounds);
        fuelLevelDrawer.setBounds(fuelBarBounds);
    }

    @Override
    public void paint() {
        super.paint();
        timerDrawer.draw(canvas, furnace.getTimer());
        fuelLevelDrawer.draw(canvas, furnace.getCurrFuel()/(float) FueledProcessingMachine.MAX_FUEL);
    }
    @Override
    protected Slot[] generateMachineSlots() {
        smeltable.setItemStack(furnace.getSmeltable());
        fuel.setItemStack(furnace.getFuel());
        product.setItemStack(furnace.getOutput());
        Slot[] slots = {smeltable, fuel, product};
        return slots;
    }

    @Override
    protected String getMachineName() {
        return "Stone Furnace";
    }
}
