
package com.shd.climain.sendrecv;

import java.net.http.WebSocket;
import java.net.http.WebSocket.Listener;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.shd.climain.SingleRef;
import com.shd.climain.buildclireq.CLIProcessFuncIF;


@SuppressWarnings("rawtypes")
public class Recv implements WebSocket.Listener {
	private  String elmId =  null ;
	private String servSessionId = null ;
	private  AtomicInteger atomIntg = new AtomicInteger(1);
	List<CharSequence> parts = new ArrayList<>();
	CompletableFuture<?> accumulatedMessage = new CompletableFuture<>();
	private   Map<String, Map<String, CLIProcessFuncIF>>   cliRecvConcurHashMap = new ConcurrentHashMap<String,  Map<String, CLIProcessFuncIF> >() ;
	@Override
	public void onOpen(WebSocket webSocket) {
		 webSocket.getSubprotocol() ;             /* Keep this line */
		SingleRef.SINGLEINS.getRefLogger().info("onOpen using Subprotocol Done to Complte the connection");
		WebSocket.Listener.super.onOpen(webSocket);
	}
	//SizeOfStr=16384*200  tested OK   3276800 approx 350K Chars
	// but is can up   be  is 2^31 - 1 (or around 2 billion.)
	// [25224442] 25 M length is OK from Window not from Eclipse
	@Override
	public CompletionStage<?> onText(WebSocket webSocket, CharSequence message, boolean last) {

		//String strToAdd= 	String.valueOf(message) ;

		parts.add(message);
		webSocket.request(1);

		if (last) {
			// processWholeText(parts);
			ProcessCliResponse processCliResponse = new ProcessCliResponse() ;
			processCliResponse.setCliRecvConcurHashMap(cliRecvConcurHashMap);
			processCliResponse.setInComingWrapMsgNew(parts);
			SingleRef.SINGLEINS.getExecService().submit(processCliResponse.getAsyncSubmitToServer()) ;	
			//processWholeText(parts);
			parts = new ArrayList<>();
			accumulatedMessage.complete(null);
			CompletionStage<?> cf = accumulatedMessage;
			accumulatedMessage = new CompletableFuture<>();
			return cf;
		}
		return accumulatedMessage;
	}
	@Override
	public CompletionStage<?> onClose(WebSocket webSocket, int statusCode,  String reason) {
		SingleRef.SINGLEINS.getRefLogger().warning("onClose: " + statusCode + " " + reason);
		return Listener.super.onClose(webSocket, statusCode, reason);
	}

	@Override
	public void onError(WebSocket webSocket, Throwable error) {
		SingleRef.SINGLEINS.getRefLogger().warning("Something Not Right Happend! " + webSocket.toString());
		WebSocket.Listener.super.onError(webSocket, error);
	}
	@Override
	public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {

		String pongRecvd= new String(message.array()) ; 
		SingleRef.SINGLEINS.getRefLogger().info("pongRecvd-onPong: " + pongRecvd);
		servSessionId=pongRecvd.substring(pongRecvd.indexOf(":") +1) ;
		SingleRef.SINGLEINS.getRefLogger().info("Registred servSessionId =[" + servSessionId + "]") ;
		return Listener.super.onPong(webSocket, message);
	}
	//
	@Override
	public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {

		SingleRef.SINGLEINS.getRefLogger().info("onPing: " + new String(message.array()));

		return Listener.super.onPong(webSocket, message);
	}
	//
	public String getElmId() {
		return elmId;
	}

	public void setElmId(String elmId) {
		this.elmId = elmId;
	}

	private  String getServSessionId() {
		return servSessionId;
	}

	public String getNextReqRespId() {
		return(atomIntg.getAndIncrement() +getServSessionId()) ;
	}
	@Override
	public String toString() {
		return "Recv [elmId=" + elmId + "]";
	}

	public Map<String, Map<String, CLIProcessFuncIF>> getCliRecvConcurHashMap() {
		return cliRecvConcurHashMap;
	}

	public void setCliRecvConcurHashMap(Map<String, Map<String, CLIProcessFuncIF>> cliRecvConcurHashMap) {
		this.cliRecvConcurHashMap = cliRecvConcurHashMap;
	}

}