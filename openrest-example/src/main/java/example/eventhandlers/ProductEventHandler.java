package example.eventhandlers;

import orest.event.annotation.HandleBeforeSaveWithDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import example.model.Product;
import example.model.dto.ClientProductDto;
import example.repositories.ClientRepository;

@RepositoryEventHandler(Product.class)
@Component
public class ProductEventHandler {

	@Autowired
	private ClientRepository clientRepository;

	@HandleBeforeSaveWithDto(dto = ClientProductDto.class)
	public void addProductToClient(Product product, ClientProductDto dto) {
		dto.getClient().addProduct(product);
		clientRepository.save(dto.getClient());
	}
}
