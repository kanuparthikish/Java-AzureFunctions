package com.kish.AzureFunctions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.BlobOutput;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;

public class BlobTriggerFunction {
	
	
	
	@FunctionName("BlobTrigger")
	public void run(@BlobTrigger( connection = "AzureWebJobsStorage", name = "blobfile", path = "mydrive/{name}") String  a,
			final ExecutionContext context,
			@BlobOutput(name = "target", path = "backup/{name}-backup",connection = "AzureWebJobsStorage") OutputBinding<String> outputItem
			)
	{
		 context.getLogger().info("Java BlobTrigger trigger processed a request."+a);
	     outputItem.setValue("Back file:::"+a);
	     
	     context.getLogger().info("Java BlobTrigger trigger processed completed.");
	}

}
