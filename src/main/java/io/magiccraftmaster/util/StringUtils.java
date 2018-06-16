package io.magiccraftmaster.util;

import javax.annotation.Nonnull;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings(value={"unused","WeakerAccess"})
public final class StringUtils {
	/**
	 * Clip operations for {@link #clip(String[], ClipType, int)}
	 */
	public enum ClipType {
		/**
		 * Removes all items to the left of the array (first indexes)
		 */
		LEFT,
		/**
		 * Removes all items to the right of the array (last indexes)
		 */
		RIGHT,
		/**
		 * Removes all items to the left and right of the array (first and last indexes)
		 */
		LEFT_RIGHT
	}

	/**
	 * Date format operations for {@link #formatDate(OffsetDateTime, DateFormatType, boolean, boolean)}
	 */
	public enum DateFormatType {
		/**
		 * Shows the date and time
		 */
		DATE_TIME,
		/**
		 * Shows the date
		 */
		DATE,
		/**
		 * Shows the time
		 */
		TIME
	}

	/**
	 * Converts a string array to a string
	 * @param strings the array of strings to combine
	 * @param separator the character(s) to separate with
	 * @return a new string from the array
	 */
	@Nonnull
	public static String toString(String[] strings, String separator) {
		if (strings == null || strings.length == 0) return "";
		StringBuilder stringBuilder = new StringBuilder();
		for (String string : strings) stringBuilder.append(string).append(separator);
		return stringBuilder.substring(0, stringBuilder.length() - separator.length());
	}

	/**
	 * Returns a string of the item in the format 'one', 'two' and 'three'
	 * @param strings the strings to combine
	 * @return a string of the given array
	 */
	@Nonnull
	public static String toListString(String[] strings) {
		if (strings.length == 1) return "'"+strings[0]+"'";

		StringBuilder stringBuilder = new StringBuilder();
		for (int i=0, len=strings.length; i<len; i++) {
			if (i < len-2)
				stringBuilder.append("'").append(strings[i]).append("', ");
			else if (i < len-1)
				stringBuilder.append("'").append(strings[i]).append("' ");
			else
				stringBuilder.append("and '").append(strings[i]).append("'");
		}
		return stringBuilder.toString();
	}

	/**
	 * Converts an {@link OffsetDateTime} to a string using 24-hour time
	 * @param offsetDateTime the date to convert
	 * @return a string date
	 * @deprecated in favor of {@link #formatDate(OffsetDateTime, DateFormatType, boolean, boolean)}
	 */
	@Nonnull
	@Deprecated
	public static String toString(OffsetDateTime offsetDateTime) {
		return formatDate(offsetDateTime, DateFormatType.DATE_TIME,true,false);
	}

	/**
	 * Ensures that a string starts with an uppercase letter<br>
	 *     Eg: <code>"all lower"</code> becomes <code>"All lower"</code>
	 * @param string the string to adjust
	 * @return the fixed string
	 */
	@Nonnull
	public static String fixCase(String string) {
		if (string.length() == 1) return string.toUpperCase();
		return string.substring(0,1).toUpperCase() + string.substring(1);
	}

	/**
	 * Removes ends of an array as specified
	 * @param array the array to "clip"
	 * @param clipType a mode
	 * @param distance how many elements to remove from the end(s)
	 * @return the clipped array
	 */
	@Nonnull
	public static String[] clip(String[] array, ClipType clipType, int distance) {
		String[] array2 = null;
		switch (clipType) {
			case LEFT:
				array2 = new String[array.length - distance];
				System.arraycopy(array, distance, array2, 0,array.length - distance);
				break;

			case RIGHT:
				array2 = new String[array.length - distance];
				System.arraycopy(array, 0, array, 0,array.length - distance);
				break;

			case LEFT_RIGHT:
				array2 = new String[array.length - distance * 2];
				System.arraycopy(array, distance, array, 0,array.length - distance * 2);
				break;
		}
		return array2;
	}

	/**
	 * Creates a text box around elements of the given array
	 * @param lines an array of strings for the box content
	 * @return a box string
	 */
	@Nonnull
	public static String box(String[] lines) {
		StringBuilder out = new StringBuilder();
		List<String> newLines = new ArrayList<>();

		for (String line : lines) {
			String[] splitLine = line.replace("\t", "   ").split("\n");
			Collections.addAll(newLines, splitLine);
		}

		int length = 0;
		for (String line : newLines) if (line.length() > length) length = line.length();

		out.append("+");
		for (int i=0; i<length; i++) out.append("-");
		out.append("+\n");

		for (String line : newLines) {
			out.append("|");
			out.append(line);
			for (int i=line.length(); i<length; i++) out.append(" ");
			out.append("|\n");
		}

		out.append("+");
		for (int i=0; i<length; i++) out.append("-");
		out.append("+");

		return out.toString();
	}

	/**
	 * Formats an {@link OffsetDateTime}
	 * @param offsetDateTime the date/time to format
	 * @param formatType the format type
	 * @param military if 24-hour time is to be used
	 * @param clean if number-only rep should be used (AM/PM is shown if <b>military</b> if false)
	 * @return a formatted string
	 */
	@Nonnull
	public static String formatDate(OffsetDateTime offsetDateTime, DateFormatType formatType, boolean military, boolean clean) {
		if (offsetDateTime == null || formatType == null) throw new NullPointerException("Neither 'offsetDateTime' nor 'formatType' may be null");
		String format = "";
		if (formatType == DateFormatType.DATE_TIME || formatType == DateFormatType.DATE)
			format = format + (clean ? "yyyy/MM/dd " : "MMM dd, yyyy ");
		if (formatType == DateFormatType.DATE_TIME || formatType == DateFormatType.TIME)
			format = format + (military ? "HH:mm O" : "hh:mma O");
		return offsetDateTime.format(DateTimeFormatter.ofPattern(format));
	}

	/**
	 * Formats a {@link LocalDate}
	 * @param localDate the date to format
	 * @param clean if number-only rep should be used
	 * @return a formatted string
	 */
	@Nonnull
	public static String formatDate(LocalDate localDate, boolean clean) {
		if (localDate == null) throw new NullPointerException("Neither 'localDate' may not be null");
		String format = clean ? "yyyy/MM/dd" : "MMM dd, yyyy";
		return localDate.format(DateTimeFormatter.ofPattern(format));
	}

	private static final DecimalFormat numberFormat = new DecimalFormat("#,###.##");
	/**
	 * Formats a number to be human readable
	 * @param d the number as a double
	 * @return the formatted number
	 */
	@Nonnull
	public static String formatNumber(double d) {
		return numberFormat.format(d);
	}
	
	
}
