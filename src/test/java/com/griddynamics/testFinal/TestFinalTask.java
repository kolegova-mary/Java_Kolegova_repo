package com.griddynamics.testFinal;

import com.griddynamics.finalTask.actions.ActionParser;
import org.testng.annotations.Test;

public class TestFinalTask {
    @Test(expectedExceptions = IllegalStateException.class)
    public void testParser(){
        ActionParser actionParser = new ActionParser();
        actionParser.parse(10);
    }

}
