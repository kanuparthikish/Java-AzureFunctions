package com.kish.AzureFunctions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.QueueOutput;
import com.microsoft.azure.functions.annotation.TimerTrigger;

public class TimeTriggerExample {
	
	@FunctionName("Timer")
    public String functionHandler(@TimerTrigger(name = "timerInfo", schedule = "0 * * * * *") String timerInfo, final ExecutionContext executionContext) {
        executionContext.getLogger().info("Timer trigger input: " + timerInfo);
        int mb = 1024*1024;
		//Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();
		executionContext.getLogger().info("##### Heap utilization statistics [MB] #####");
		//Print used memory
		executionContext.getLogger().info("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / mb);
		//Print free memory
		executionContext.getLogger().info("Free Memory:" + runtime.freeMemory() / mb);
		//Print total available memory
		executionContext.getLogger().info("Total Memory:" + runtime.totalMemory() / mb);
		//Print Maximum available memory
		executionContext.getLogger().info("Max Memory:" + runtime.maxMemory() / mb);
        return "From timer: \"" + timerInfo + "\"";
    }

}