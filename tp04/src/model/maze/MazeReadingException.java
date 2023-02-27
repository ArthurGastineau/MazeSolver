package model.maze;

/**
 * 
 * The MazeReadingException class represents an exception that can occur during
 * maze reading. It extends the Exception class and provides additional
 * information about the file name, line number, and error message.
 *
 * @author Arthur Gastineau
 * 
 * @see Exception
 * 
 */

public class MazeReadingException extends Exception {

	/** The file name that caused the exception. */
	private String fileName;

	/** The line number in the file that caused the exception. */
	private int lineNumber;

	/** The error message associated with the exception. */
	private String messageError;

	/**
	 * Constructor for creating a MazeReadingException object with a given file
	 * name, line number, and error message.
	 * 
	 * @param fileName     The file name that caused the exception.
	 * @param lineNumber   The line number in the file that caused the exception.
	 * @param messageError The error message associated with the exception.
	 */

	public MazeReadingException(String fileName, int lineNumber, String messageError) {
		super();
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.messageError = messageError;
	}

	/**
	 * Retrieves the file name that caused the exception.
	 * 
	 * @return The file name that caused the exception.
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Retrieves the line number in the file that caused the exception.
	 * 
	 * @return The line number in the file that caused the exception.
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	/**
	 * Retrieves the error message associated with the exception.
	 * 
	 * @return The error message associated with the exception.
	 */
	public String getMessageError() {
		return messageError;
	}

}
