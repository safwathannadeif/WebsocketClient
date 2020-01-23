package com.shd.climain.buildclireq;

import java.util.Map;
import java.util.concurrent.Callable;
//import java.util.concurrent.ConcurrentHashMap;

import com.shd.climain.SingleRef;

@SuppressWarnings("rawtypes")
public class ReqBuilder {
	private  Map<String, CLIProcessFuncIF>   concurrentHashMapToUse = null ;
	private Callable<Exception> submitCallFunc = null ;

public ReqBuilder() {
		
	} 
 
public ReqBuilder(Map<String, CLIProcessFuncIF> map, Callable<Exception> asynFun)
{
	concurrentHashMapToUse = map ;
	submitCallFunc=asynFun ;
}
public ReqBuilder addCliProcessor(CliProcessIF cliProcessIF)
{
	concurrentHashMapToUse.put(cliProcessIF.getRespClassType().getCanonicalName().trim(),cliProcessIF.getImplementedProcessFunc());
return this ;

}

public void sendAndprocess( )
{
	//cliSendReq.buildReceiver() ;
	SingleRef.SINGLEINS.getExecService().submit(submitCallFunc) ;
}
}