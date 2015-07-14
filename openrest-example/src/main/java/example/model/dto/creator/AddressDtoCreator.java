package example.model.dto.creator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import orest.dto.DtoInformation;
import orest.dto.EntityFromDtoCreator;
import example.model.Address;
import example.model.dto.AddressDto;

@Component
public class AddressDtoCreator implements EntityFromDtoCreator<Address, AddressDto> {

	private static final Pattern ADDRESS_PATTERN = Pattern.compile("^(.*)[ ]+(.*), ([0-9]{2}-[0-9]{3})[ ]+(.*)$");

	@Override
	public Address create(AddressDto from, DtoInformation dtoInfo) {

		Address address = new Address();
		Matcher matcher = ADDRESS_PATTERN.matcher(from.getAddress().trim());
		if (matcher.find()) {
			address.setStreet(matcher.group(1));
			address.setHomeNr(matcher.group(2));
			address.setZip(matcher.group(3));
			address.setCity(matcher.group(4));
			return address;
		}
		return null;
	}

}
