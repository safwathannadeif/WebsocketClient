package test1.testsendrecv.case4reqresp1;

import com.shd.climain.buildclireq.CliSendReq;
import com.shd.common.reqresp.req.Req1;

public class TestReqResp1 {


public void cliSendTest()
{
Req1 req1 = new Req1();
req1.setStart(1); 
req1.setEnd(10);
String uriReq1 = "/testRR11-1" ;  //testRR11-1   ///TestNo1-UnitTest-A-1 Just Resp
//CliSendReq<Req1> cli = new CliSendReq<Req1>(req1Tosend,uriReq1) ;
CliSendReq<Req1> cli1 = new CliSendReq<Req1>() ;
//cli1.setReqAndUri(req1Tosend,uriReq1).addCliProcessor(new CliProces_1_Imlp()).sendAndprocess();
//cli.sendToServer();
					//
					//cli1.setReqAndUri(req1,uriReq1)
					//	.addCliProcessor(new CliProcesImpl4ReqResp1())
					//	.addCliProcessor(new CliProcImp4PrgrsMsgFrmRqRsp1Msg1())
					//	.addCliProcessor(new CliProcImp4PrgrsMsgFrmRqRsp1Msg2())
					//	.sendAndprocess() ;

//
req1 = new Req1();
req1.setStart(1); 
req1.setEnd(450); // 11000 Ok from WindowCosole msg length for 450 record= 132,160 bytes
 uriReq1 = "/Employee-Report-1" ;  //testRR11-1   ///TestNo1-UnitTest-A-1 Just Resp
//CliSendReq<Req1> cli = new CliSendReq<Req1>(req1Tosend,uriReq1) ;
 cli1 = new CliSendReq<Req1>() ;
//cli1.setReqAndUri(req1Tosend,uriReq1).addCliProcessor(new CliProces_1_Imlp()).sendAndprocess();
//cli.sendToServer();

cli1.setReqAndUri(req1,uriReq1)
	.addCliProcessor(new CliProcesImpl4ReqResp1())
	.addCliProcessor(new CliProcImp4PrgrsMsgFrmRqRsp1Msg1())
	.addCliProcessor(new CliProcImp4PrgrsMsgFrmRqRsp1Msg2())
	.sendAndprocess() ;



}
}