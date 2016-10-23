package com.stockbone.utils.valid;

public final class Preconditions {

	private Preconditions() {

	}

	public static void checkArgument(boolean expression) {
		if (!expression) {
			throw new IllegalArgumentException();
		}
	}

	public static void checkArgument(boolean expression, Object errorMessage) {
		if (!expression) {
			throw new IllegalArgumentException(String.valueOf(errorMessage));
		}
	}

	public static void checkArgument(boolean expression, String errorMessageTemplate, Object[] errorMessageArgs) {
		if (!expression) {
			throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
		}
	}

	public static void checkState(boolean expression) {
		if (!expression) {
			throw new IllegalStateException();
		}
	}

	public static void checkState(boolean expression, Object errorMessage) {
		if (!expression) {
			throw new IllegalStateException(String.valueOf(errorMessage));
		}
	}

	public static void checkState(boolean expression, String errorMessageTemplate, Object[] errorMessageArgs) {
		if (!expression) {
			throw new IllegalStateException(format(errorMessageTemplate, errorMessageArgs));
		}
	}

	public static Object checkNotNull(Object reference) {
		if (reference == null || (reference.getClass() == String.class && "".equals(reference))) {
			throw new NullPointerException();
		}
		return reference;
	}

	public static Object checkNotNull(Object reference, Object errorMessage) {
		if (reference == null || (reference.getClass() == String.class && "".equals(reference))) {
			throw new NullPointerException(String.valueOf(errorMessage));
		}
		return reference;
	}

	public static Object checkNotNull(Object reference, String errorMessageTemplate, Object[] errorMessageArgs) {
		if (reference == null || (reference.getClass() == String.class && "".equals(reference))) {
			throw new NullPointerException(format(errorMessageTemplate, errorMessageArgs));
		}
		return reference;
	}

	static String format(String template, Object[] args) {
		template = String.valueOf(template); // null -> "null"
		// start substituting the arguments into the '{}' placeholders
		StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
		int templateStart = 0;
		int i = 0;
		while (i < args.length) {
			int placeholderStart = template.indexOf("{}", templateStart);
			if (placeholderStart == -1) {
				break;
			}
			builder.append(template.substring(templateStart, placeholderStart));
			builder.append(args[i++]);
			templateStart = placeholderStart + 2;
		}
		builder.append(template.substring(templateStart));
		// if we run out of placeholders, append the extra args in square braces
		if (i < args.length) {
			builder.append(" [");
			builder.append(args[i++]);
			while (i < args.length) {
				builder.append(", ");
				builder.append(args[i++]);
			}
			builder.append(']');
		}
		return builder.toString();
	}
}
