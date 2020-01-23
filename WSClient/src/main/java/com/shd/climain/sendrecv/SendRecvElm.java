package com.shd.climain.sendrecv;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;

import java.util.concurrent.CompletableFuture;

public class SendRecvElm {
private Recv recv ;
private Send send ;
private String elmId =  null ;

//private final String  conectUri= "ws://localhost:8080/test1" ;
//private final String  conectUri= "ws://localhost:8080/letschat" ;

private String rootPath="/serverPath" ;
private String host="localhost" ; 
private int port=8080 ; 
private String endPath = "/test4EndPoint"; 				// test4EndPoint test1EndPoint working from manal cfg ONLY /test2" is working

//private final String  conectUri= "ws://localhost:8080/test2" ;

private final String  conectUri= "ws://" + host + ":" + port + rootPath +  endPath ;

public SendRecvElm() {
		recv = new Recv() ;
	}
public void startSendRecv()
{
	
	CompletableFuture<WebSocket> cliCFuturei = HttpClient
            .newHttpClient()
            .newWebSocketBuilder()
            .buildAsync(URI.create(conectUri),recv); // this Async[The Builder] will returns the WebSocket 
	
    WebSocket ws = cliCFuturei.join();				 //Wait till Builder completed and the let Calling thread in wait till the WebSocket from builder Completed
  // ws.sendText("Hello!", true);
    send = new Send() ;
    send.setWs(ws);
	
}
	public Recv getRecv() {
		return recv;
	}

	public void setRecv(Recv recv) {
		this.recv = recv;
	}

	public Send getSend() {
		return send;
	}

	public void setSend(Send send) {
		this.send = send;
	}

	
	public String getElmId() {
		return elmId;
	}
	public void setElmId(String elmId) {
		this.elmId = elmId;
		recv.setElmId(elmId);
		send.setElmId(elmId);
	}
	@Override
	public String toString() {
		return "SendRecvElm [recv=" + recv + ", send=" + send + ", elmId=" + elmId + ", conectUri=" + conectUri + "]";
	}

	
}
