package com.shd.climain.sendrecv;

import java.net.http.WebSocket;


public class Send {
private  String elmId =  null ;
private WebSocket ws  ; //Sender ;
	public Send() {
		
	}
//	public WebSocket getWs() {
//		return ws;
//	}
	public void setWs(WebSocket ws) {
		this.ws = ws;
		//ws.sendPing(ByteBuffer.wrap("Ping from NewConnection".getBytes())) ;
		//ws.sendPong(ByteBuffer.wrap("Ping from NewConnection".getBytes())) ;
	}
public void sendMsg( String msg)
{

    synchronized (this) {					//One Send at a time
       ws.sendText(msg, true) ;
    }
}
public String getElmId() {
	return elmId;
}
public void setElmId(String elmId) {
	this.elmId = elmId;
}
@Override
public String toString() {
	return "Send [elmId=" + elmId + "]";
}

}
