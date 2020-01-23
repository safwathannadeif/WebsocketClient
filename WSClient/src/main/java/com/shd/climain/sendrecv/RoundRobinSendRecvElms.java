package com.shd.climain.sendrecv;
import java.util.ArrayList;
import java.util.List;

public class RoundRobinSendRecvElms {
private List<SendRecvElm>	  sendRecvElmLis = new ArrayList<SendRecvElm>();

private Integer inxToUse = -1 ;

public RoundRobinSendRecvElms()
{
	
}

public List<SendRecvElm> getSendRecvElmLis() {
	return sendRecvElmLis;
}

public void setSendRecvElmLis(List<SendRecvElm> sendRecvElmLis) {
	this.sendRecvElmLis = sendRecvElmLis;
}


public SendRecvElm getNextSendRecv()
{
	 synchronized (this) {		
	//inxToUse = ++inxToUse ; ???
	inxToUse = inxToUse + 1 ;
	if ( inxToUse == sendRecvElmLis.size())inxToUse= 0 ; 
	return sendRecvElmLis.get(inxToUse) ;
}

}
//
}
	 
	    