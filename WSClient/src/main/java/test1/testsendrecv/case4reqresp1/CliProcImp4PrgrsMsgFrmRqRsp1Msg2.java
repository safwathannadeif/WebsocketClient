package test1.testsendrecv.case4reqresp1;



import com.shd.climain.SingleRef;
import com.shd.climain.buildclireq.CLIProcessFuncIF;
import com.shd.climain.buildclireq.CliProcessIF;
import com.shd.common.reqresp.resp.ProgresMsgFrmRqRsp1Msg2;



public class CliProcImp4PrgrsMsgFrmRqRsp1Msg2  implements CliProcessIF<ProgresMsgFrmRqRsp1Msg2> {

	//private RespTest respTest ;
	private  CLIProcessFuncIF<ProgresMsgFrmRqRsp1Msg2> cliProc4ReqRespProgrsMsg2Func = (respPOrgrs22 -> {
		SingleRef.SINGLEINS.getRefLogger().info("CliProcImp4PrgrsMsgFrmRqRsp1Msg2 =\n" + respPOrgrs22.toString() ) ;
		
		return(0) ; //We may have to change  return to be generic
	});

//	public RespTest getRespTestFromCli() {
//		return respTest;
//	}
	
	 @Override
	public CLIProcessFuncIF<ProgresMsgFrmRqRsp1Msg2> getImplementedProcessFunc() {
		return cliProc4ReqRespProgrsMsg2Func;
	}
	
	
	@Override
	public 	Class<ProgresMsgFrmRqRsp1Msg2>  getRespClassType() 
	{
		return ProgresMsgFrmRqRsp1Msg2.class  ;
	}

}