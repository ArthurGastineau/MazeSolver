/**
 * 
 */
package model;

/**
 * @author arthur
 *
 * The potential states for the maze to be in.
 */

public enum MazeState {
	INIT(MazeInstructionConstants.INIT), SOLVED(MazeInstructionConstants.SOLVED);

	public final String instruction;

	MazeState(String instruction) {
		this.instruction = instruction;
	}

	public String getInstruction() {
		return instruction;
	}
}
