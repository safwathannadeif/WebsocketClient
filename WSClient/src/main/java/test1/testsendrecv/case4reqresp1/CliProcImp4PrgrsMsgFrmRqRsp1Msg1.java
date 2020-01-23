package test1.testsendrecv.case4reqresp1;

import com.shd.climain.SingleRef;
import com.shd.climain.buildclireq.CLIProcessFuncIF;
import com.shd.climain.buildclireq.CliProcessIF;
import com.shd.common.reqresp.resp.ProgresMsgFrmRqRsp1Msg1;



public class CliProcImp4PrgrsMsgFrmRqRsp1Msg1  implements CliProcessIF<ProgresMsgFrmRqRsp1Msg1> {

	//private RespTest respTest ;
	private  CLIProcessFuncIF<ProgresMsgFrmRqRsp1Msg1> cliProc4ReqRespProgrsMsg1Func = (respPOrgrs11 -> {
		SingleRef.SINGLEINS.getRefLogger().info("CliProcImp4PrgrsMsgFrmRqRsp1Msg2 =\n" + respPOrgrs11.toString() ) ;
		
		return(0) ; //We may have to change return to be generic
	});

//	public RespTest getRespTestFromCli() {
//		return respTest;
//	}
	
	 @Override
	public CLIProcessFuncIF<ProgresMsgFrmRqRsp1Msg1> getImplementedProcessFunc() {
		return cliProc4ReqRespProgrsMsg1Func;
	}
	
	
	@Override
	public 	Class<ProgresMsgFrmRqRsp1Msg1>  getRespClassType() 
	{
		return ProgresMsgFrmRqRsp1Msg1.class  ;
	}

}