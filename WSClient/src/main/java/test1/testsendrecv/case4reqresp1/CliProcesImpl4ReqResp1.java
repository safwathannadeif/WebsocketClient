package test1.testsendrecv.case4reqresp1;

import com.shd.climain.SingleRef;
import com.shd.climain.buildclireq.CLIProcessFuncIF;
import com.shd.climain.buildclireq.CliProcessIF;
import com.shd.common.reqresp.resp.Resp1;

//
// CliProcessImpl4ProgressMsgfromRqRsp1Msg

public class CliProcesImpl4ReqResp1 implements CliProcessIF<Resp1> {

	private  CLIProcessFuncIF<Resp1> cliProcReqResp1Func = (respTest -> {
		SingleRef.SINGLEINS.getRefLogger().info("CliResp cliProcFunc CliProcesImpl4ReqResp1  return respTest.getList()=\n" + respTest.getList().toString() ) ;
		return(respTest.getList()) ; 
	});
	 @Override
	public CLIProcessFuncIF<Resp1> getImplementedProcessFunc() {
		return cliProcReqResp1Func;
	}
	
	
	@Override
	public 	Class<Resp1>  getRespClassType() 
	{
		return Resp1.class  ;
	}

}