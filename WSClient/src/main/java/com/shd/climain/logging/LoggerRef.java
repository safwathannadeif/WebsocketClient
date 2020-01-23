package com.shd.climain.logging ;
import java.util.Objects;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
public class LoggerRef {

	private final static String nameId="ServDisply" ;
	private static  Logger servLogDisply = null ;

	public static synchronized Logger  makeLogRef()
	{
		servLogDisply = Logger.getLogger(nameId); 
		Objects.requireNonNull(servLogDisply, "NULL!! for servLogDisply" ) ;
		//assert loggerToUse != null ;
		servLogDisply.setUseParentHandlers(false) ;//No Console
		Handler[] handlers = servLogDisply.getHandlers();
		for(Handler handler : handlers) {
			servLogDisply.removeHandler(handler);
		}
		Handler  consoleHandler = new ConsoleHandler();
		consoleHandler.setFormatter(new LogFormat());
		//consoleHandler.setLevel(Level.ALL);
		servLogDisply.addHandler(consoleHandler);
		return servLogDisply ;


	}

}