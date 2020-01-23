package com.shd.climain.sendrecv;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.shd.climain.SingleRef;
import com.shd.climain.buildclireq.CLIProcessFuncIF;
import com.shd.common.msgelms.ResponseMsgTypes;
import com.shd.common.msgelms.Wrap;
@SuppressWarnings("rawtypes")
public class ProcessCliResponse {

	private String inComingWrapMsg = null ;
	private Map<String, Map<String, CLIProcessFuncIF>>   cliRecvConcurHashMap  = null ;
	public ProcessCliResponse() {
		
	}
		
	private Callable<Exception> asyncProcCliResp = () -> {
		Exception exp = null;
		try
		{
		SingleRef.SINGLEINS.getRefLogger().info("Warp msg in asyncProcCliResp\n[" + inComingWrapMsg + "]") ;
		Gson gson = new Gson();
		Wrap wrap = gson.fromJson(getInComingWrapMsg(),Wrap.class) ;
		ResponseMsgTypes responseMsgTypes = wrap.getHeader().getRespType() ;
		
		
		String reqrespId = wrap.getHeader().getReqResId() ;
		Map<String, CLIProcessFuncIF> mapFromCli = cliRecvConcurHashMap.get(reqrespId) ; //???Watch for Memory 
		//
		if ( responseMsgTypes.isEndOfResp(responseMsgTypes) ) {
			cliRecvConcurHashMap.remove(reqrespId) ;
		}
		String  strInClassName = wrap.getHeader().getClassType4jsonStrmsg().trim() ;
		Class <?> inClass = Class.forName(strInClassName) ;
		//Object objForFunc = Class.forName(claz.getCanonicalName()).getConstructor().newInstance() ;
		String respMsgFromWrap = wrap.getJsonStrmsg() ;
		Object objForFunc = gson.fromJson(respMsgFromWrap, inClass);
		CLIProcessFuncIF cliProces = mapFromCli.get(strInClassName) ;
		if (null == cliProces ) {
			SingleRef.SINGLEINS.getRefLogger().warning("***********************Map  ProcessCliResponse getting NULl Here   !!!! " ) ;
			String err= "Incoming Response type Class= [" + strInClassName + "] is not matching any CLIProcessFuncIF Configuration"  ;
			throw new RuntimeException (err) ;
		}
		@SuppressWarnings("unchecked")
		Object obj  = cliProces.processIt(objForFunc);	
		SingleRef.SINGLEINS.getRefLogger().info(" asyncProcCliResp return value processIt  cliProcFunc......................\n[" + obj.toString() +"]");
		SingleRef.SINGLEINS.getRefLogger().info(" asyncProcCliResp return msg Length respMsgFromWrap = ["+ respMsgFromWrap.length() +"]");
		//respMsgFromWrap
		}
		catch (Exception e )
		{
			SingleRef.SINGLEINS.getRefLogger().warning(" AsyncProcCliResp Callable Exception ");
			e.printStackTrace();
			exp=e ;
			throw new RuntimeException (e) ;
		}
	return exp ;	
};
	public String getInComingWrapMsg() {
		return inComingWrapMsg;
	}
	public void setInComingWrapMsg(String inComingWrapMsg) {
		this.inComingWrapMsg = inComingWrapMsg;
		
	}
	//List<CharSequence> parts
	public void setInComingWrapMsgNew(List<CharSequence> parts) {
		//this.inComingWrapMsg = inComingWrapMsg;
		this.inComingWrapMsg = parts.stream().collect(Collectors.joining()); 
		System.out.println("inComingWrapMsg after joining:\n" + inComingWrapMsg) ;
	}
	public void setCliRecvConcurHashMap(Map<String, Map<String, CLIProcessFuncIF>> cliRecvConcurHashMap) {
		this.cliRecvConcurHashMap = cliRecvConcurHashMap;
	}
	public Callable<Exception> getAsyncSubmitToServer() {
		return asyncProcCliResp;
	}


}
