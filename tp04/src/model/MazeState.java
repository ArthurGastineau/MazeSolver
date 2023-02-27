/**
 * 
 */
package model;

/**
 *
 * The potential states for the maze to be in.
 * 
 * @author Arthur Gastineau
 */

public enum MazeState {
	INIT(MazeInstructionConstants.INIT), SOLVED(MazeInstructionConstants.SOLVED),
	GENERATED(MazeInstructionConstants.GENERATED), LOADED(MazeInstructionConstants.LOADED),
	RESET(MazeInstructionConstants.RESET);

	/**
	 * The instruction associated with this maze state.
	 */

	public final String instruction;

	/**
	 * Constructs a new {@link MazeState} with the given instruction.
	 * 
	 * @param instruction The instruction associated with this maze state.
	 */

	MazeState(String instruction) {
		this.instruction = instruction;
	}

	/**
	 * Returns the instruction associated with this maze state.
	 * 
	 * @return the instruction associated with this maze state.
	 */

	public String getInstruction() {
		return instruction;
	}
}
