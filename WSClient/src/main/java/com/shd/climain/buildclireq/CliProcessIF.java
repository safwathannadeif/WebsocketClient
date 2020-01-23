package com.shd.climain.buildclireq;

public interface CliProcessIF<Resp> {
	
//void  setResp(Resp resp) ;
public CLIProcessFuncIF<Resp> getImplementedProcessFunc();  //Implemented
public Class<Resp> getRespClassType() ;
}
