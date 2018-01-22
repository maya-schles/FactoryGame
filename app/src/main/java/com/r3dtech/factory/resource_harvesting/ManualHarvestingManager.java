package com.r3dtech.factory.resource_harvesting;


import com.r3dtech.factory.inventory.GameItem;
import com.r3dtech.factory.inventory.Inventory;
import com.r3dtech.factory.loading_timers.GameItemTimersManager;
import com.r3dtech.factory.loading_timers.LoadingTimer;
import com.r3dtech.factory.loading_timers.LoadingTimerImplementation;
import com.r3dtech.factory.loading_timers.TimerCallback;
import com.r3dtech.factory.loading_timers.TimersManager;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the manual resource harvesting.
 *
 * Created by Maya Schlesinger(maya.schlesinger@gmail.com) on 20/01/2018.
 */

public class ManualHarvestingManager extends GameItemTimersManager {
    public ManualHarvestingManager(Inventory inventory) {
        super(inventory);
    }
}
