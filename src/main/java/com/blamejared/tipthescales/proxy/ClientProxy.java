package com.blamejared.tipthescales.proxy;

import com.blamejared.tipthescales.events.ClientEventHandler;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerEvents() {
        super.registerEvents();
        new ClientEventHandler();
    }
}
