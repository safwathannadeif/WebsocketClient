//See https://e.printstacktrace.blog/java-type-inference-generic-methods-chain-call/
// see https://www.concretepage.com/java/jdk-8/java-8-collectors-reducing-example
package com.shd.climain;
import java.util.concurrent.CountDownLatch;

import com.shd.climain.sendrecv.RoundRobinSendRecvElms;
import com.shd.climain.sendrecv.SendRecvElm;

import test1.testsendrecv.case4reqresp1.TestReqResp1;


public class ClientMain {

	
    public static void main(String[] args) throws Exception {
    
    	RoundRobinSendRecvElms roundRobinSendRecvElms = new RoundRobinSendRecvElms() ; 
    			
    	for ( int i =0 ; i < 20 ; i++ )
    	{
    	SendRecvElm sendRecvElm = new SendRecvElm() ;
    	sendRecvElm.startSendRecv();
    	sendRecvElm.setElmId("Sendrecv-id" + i);
    	roundRobinSendRecvElms.getSendRecvElmLis().add(sendRecvElm) ;
    	}
    	SingleRef.SINGLEINS.setSendRecvElmLis(roundRobinSendRecvElms);
    	///Testing#2
    	for ( int i =0 ; i < 1 ; i++ ) // tested 200
    	{
    	TestReqResp1 testReqResp1 = new TestReqResp1() ;
    	testReqResp1.cliSendTest();
    	}
    	///Testing#2
    	new CountDownLatch(1).await();  //Wait and Keep Client Up
    	
    }
}