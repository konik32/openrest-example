package example.eventhandlers;

import orest.event.annotation.HandleAfterCreateWithDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import example.model.ContactPerson;
import example.model.dto.CountyContactPersonDto;
import example.repositories.CountyRepository;

@RepositoryEventHandler(ContactPerson.class)
@Component
public class ContactPersonEventHandler {

	@Autowired
	private CountyRepository countyRepository;

	@HandleAfterCreateWithDto(dto = CountyContactPersonDto.class)
	public void addContactPersonToCounty(ContactPerson cp, CountyContactPersonDto dto) {
		dto.getCounty().addContactPerson(cp);
		countyRepository.save(dto.getCounty());
	}

}
