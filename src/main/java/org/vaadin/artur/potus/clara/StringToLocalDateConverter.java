package org.vaadin.artur.potus.clara;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convertToModel(String value,
			Class<? extends LocalDate> targetType, Locale locale)
					throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (value == null || value.isEmpty()) {
			return null;
		}

		return LocalDate.parse(value, getFormatter(locale));
	}

	private DateTimeFormatter getFormatter(Locale locale) {
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(
				locale);
	}

	@Override
	public String convertToPresentation(LocalDate value,
			Class<? extends String> targetType, Locale locale)
					throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (value == null) {
			return "";
		}

		return value.format(getFormatter(locale));
	}

	@Override
	public Class<LocalDate> getModelType() {
		return LocalDate.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

}
