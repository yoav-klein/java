package org.example;

import io.micrometer.observation.Observation;
import io.micrometer.observation.Observation.Context;
import io.micrometer.observation.ObservationHandler;

public class SimpleObservationHandler implements ObservationHandler<Observation.Context> {

    @Override
    public boolean supportsContext(Context context) {
        return true;
    }

    @Override
    public void onEvent(Observation.Event event, Context context) {
        System.out.println("!!! EVENT");
    }
    
}
