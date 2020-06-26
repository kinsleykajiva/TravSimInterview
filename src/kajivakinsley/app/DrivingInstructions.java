package kajivakinsley.app;

import java.util.LinkedList;

public class DrivingInstructions extends LinkedList <Character> {


		private static final int MINIMUM_ALLOWED_NUMBER_OF_MOVEMENTS = 1;

		/**
		 * Constructor
		 *
		 * @param instructionsStr  The driving instructions as a string
		 */
    public DrivingInstructions(String instructionsStr) {
			char[] instructionsArray = instructionsStr.toCharArray();

			for (int index = 0; index < instructionsArray.length; index++) {
				add(instructionsArray[index]);
			}
		}

	class SystaxChecker  {


		public boolean process(Character direction) {
			return checkDirectionSyntax(direction);
		}

		/**
		 * Checks the syntax of a single direction. Only N, S, E and W are allowed.
		 *
		 * @param  direction  The direction provided
		 * @return boolean    true if correct syntax, false otherwise
		 */
		private boolean checkDirectionSyntax(Character direction) {
			boolean isValid = true;

			switch (direction) {
				case 'N':
				case 'S':
				case 'E':
				case 'W':
					// We're good
					break;
				default:
					isValid = false;
			}
			return isValid;
		}}
}
