package maze;

@SuppressWarnings("serial")
public class MazeReadingException extends Exception{

	String fileName;
	int lineNumber;
	String messageError;

	public MazeReadingException(String fileName, int lineNumber, String messageError) {
		super();
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.messageError = messageError;
	}

	public String getFileName() {
		return fileName;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getMessageError() {
		return messageError;
	}

}
