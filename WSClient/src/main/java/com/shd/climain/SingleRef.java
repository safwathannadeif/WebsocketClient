package com.shd.climain;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import com.shd.climain.logging.LoggerRef;
import com.shd.climain.sendrecv.RoundRobinSendRecvElms;


public enum SingleRef {
	SINGLEINS ;
	private RoundRobinSendRecvElms   roundRobinSendRecvElms  = null ;
	private   Logger servLogDisply = null ;
	public  Logger getRefLogger() {
		if ( servLogDisply == null ) servLogDisply = LoggerRef.makeLogRef() ;
		Objects.requireNonNull(servLogDisply, "NULL!! for displog" ) ;
		//assert displog != null : "here is null >>" ;
		return servLogDisply;
	}
//
	public String expToStr(Exception ex)
	{
	StringWriter sw = new StringWriter();
	PrintWriter pw = new PrintWriter(sw);
	ex.printStackTrace(pw);
	return pw.toString() ;
	}
//
	private   ExecutorService execService = Executors.newFixedThreadPool(27); //pool for thread
	public ExecutorService  getExecService()
	{
		return execService ;
	}
//
	public RoundRobinSendRecvElms getRoundRobinSendRecvElms() {
		return roundRobinSendRecvElms;
	}
	public void setSendRecvElmLis(RoundRobinSendRecvElms roundRobinSendRecvElms) {
		this.roundRobinSendRecvElms = roundRobinSendRecvElms;
	}
}