package services;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import exceptions.ProcesssorException;
import exceptions.ReaderException;
import exceptions.ReaderNotConfiguredException;
import exceptions.WriterException;
import exceptions.WriterNotConfiguredException;
import interfaces.IReaderForProcessor;
import interfaces.ITLVProcessor;
import interfaces.IWriterForProcessor;
import models.FormatData;
import models.IOType;

public class AbstractTLVProcessor implements ITLVProcessor{
	
	public static final String TYPE_NOT_VALID = "Type not valid";
	public static final String THIS_STRING = "THIS STRING";
	
	private IReaderForProcessor reader;
	private IWriterForProcessor writer;
	
	
	public AbstractTLVProcessor(IOType readInputType, IOType writeOutputType) throws WriterNotConfiguredException, ReaderNotConfiguredException{
		identifyAndSetReaderForProcessing(readInputType);
		identifyAndSetWriterForProcessing(writeOutputType);
	}
	
	
	public void identifyAndSetReaderForProcessing(IOType readInputType)  throws ReaderNotConfiguredException {
		switch(readInputType) {
			case STDIN:
				this.reader = new ReaderForStdIn();
				break;
			//Add a case here for network file reader
			default:
				throw new ReaderNotConfiguredException();
		}
	}
	
	public void identifyAndSetWriterForProcessing(IOType writeOutputType) throws WriterNotConfiguredException {
		switch(writeOutputType) {
			case STDOUT:
				this.writer = new WriterForStdOut();
				break;
			//Add a case here for network file writer
			default:
				throw new WriterNotConfiguredException();
		}
	}

	
	@Override
	public List<String> processFormats(List<FormatData> listOfFormats) {
		return Collections.EMPTY_LIST;
	}

	@Override
	public String processFormat(FormatData format) {
		return StringUtils.EMPTY;
	}

	@Override
	public void process() throws ProcesssorException, WriterException, ReaderException {
	}

	public void closeStreams() {
		this.reader.close();
		this.writer.close();
	}
	

	public IReaderForProcessor getReader() {
		return reader;
	}
	public void setReader(IReaderForProcessor reader) {
		this.reader = reader;
	}
	public IWriterForProcessor getWriter() {
		return writer;
	}
	public void setWriter(IWriterForProcessor writer) {
		this.writer = writer;
	}
}
