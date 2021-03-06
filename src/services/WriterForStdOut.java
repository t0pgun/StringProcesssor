package services;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.log4j.Logger;

import exceptions.WriterException;

public class WriterForStdOut extends AbstractWriterForProcessor{

	public static final Logger logger = Logger.getLogger(WriterForStdOut.class);
	
	private BufferedWriter bw;

	public WriterForStdOut() {
		this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
	}

	@Override
	public void close(){
		try {
			logger.info("Closing Writer class... " + getClass().getName());
			bw.flush();
			bw.close();
		} catch (Exception exception) {
			logger.warn("Exception occurred while closing BufferedWriter", exception);
		}
	}
	
	
	@Override
	public void writeListOfFormatData(List<String> output) throws WriterException {
		if(output != null) {
			for(String result : output) {
				writeFormatData(result);
			}
		}
	}
	
	@Override
	public void writeFormatData(String result) throws WriterException {
		try {
			bw.write(result);
			bw.flush();
		} catch (IOException ioe) {
			logger.error("Exception occurred while writing data to BufferedWriter", ioe);
			throw new WriterException(ioe);
		}
	}

}
