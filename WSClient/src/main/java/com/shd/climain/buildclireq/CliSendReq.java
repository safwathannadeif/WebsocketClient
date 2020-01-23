package com.shd.climain.buildclireq;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.Gson ;
import com.shd.climain.SingleRef;
import com.shd.climain.sendrecv.SendRecvElm;
import com.shd.common.msgelms.Header;
import com.shd.common.msgelms.MesgTypes;
import com.shd.common.msgelms.Wrap;

public class  CliSendReq<Req> {
	private Req req = null ;
	private String uri = null ;
	private ReqBuilder builder = null ;
	@SuppressWarnings("rawtypes")
	private  Map<String, CLIProcessFuncIF>   concurrentHashMap = new ConcurrentHashMap<String, CLIProcessFuncIF >() ;
	private Callable<Exception> asyncSubmitToServer = () -> {
		Exception ex = null;

		Class<?> clz = req.getClass() ;
		Wrap wrap = new Wrap() ;
		Header header = new Header() ;
		SendRecvElm sendRecvElm = SingleRef.SINGLEINS.getRoundRobinSendRecvElms().getNextSendRecv() ;
		String reqRespId = sendRecvElm.getRecv().getNextReqRespId() ;
		sendRecvElm.getRecv().getCliRecvConcurHashMap().put(reqRespId, concurrentHashMap);			
		header.setReqResId(reqRespId);
		header.setMsgType(MesgTypes.requestMsgType);
		header.setUriRequest(uri);
		header.setClassType4jsonStrmsg(req.getClass().getCanonicalName().trim()) ;
		wrap.setHeader(header);
		Gson gson = new Gson();
		String msgToSendJsonStr = gson.toJson(req,clz);
		wrap.setJsonStrmsg(msgToSendJsonStr);
		String wrapStrToSend = gson.toJson(wrap,Wrap.class);
		sendRecvElm.getSend().sendMsg(wrapStrToSend);
		return ex ;

	} ;

	public CliSendReq() {

	}
	public ReqBuilder setReqAndUri(Req reqi, String urii) {
		req = reqi ;
		uri=urii.trim() ;
		return buildCliSendReq() ;
	}
	private ReqBuilder buildCliSendReq() {

		ReqBuilder builderi = new ReqBuilder(concurrentHashMap,asyncSubmitToServer) ;
		builder=builderi ;
		return builder ;
	}


}

