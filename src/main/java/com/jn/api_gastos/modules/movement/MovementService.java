package com.jn.api_gastos.modules.movement;

import com.jn.api_gastos.auth.AuthenticatedUser;
import com.jn.api_gastos.config.exception.ResourceNotFoundException;
import com.jn.api_gastos.modules.account.Account;
import com.jn.api_gastos.modules.account.AccountRepository;
import com.jn.api_gastos.modules.category.Category;
import com.jn.api_gastos.modules.category.CategoryRepository;
import com.jn.api_gastos.modules.subcategory.SubCategory;
import com.jn.api_gastos.modules.subcategory.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MovementService implements IMovementService{

    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthenticatedUser authenticatedUser;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public List<MovementDTO> listMovements() {
        String username = authenticatedUser.getUsername();

        return movementRepository.findAllByUser(username)
                .stream()
                .map(movement -> new MovementDTO(
                        movement.getIdMovement(),
                        movement.getDescription(),
                        movement.getType(),
                        movement.getValue(),
                        movement.getDateMovement(),
                        movement.getAccount(),
                        movement.getCategory(),
                        movement.getSubCategory()
                ))
                .toList();
    }

    @Override
    public Movement saveMovement(MovementRequestDTO movementRequest) {
        Movement movement;

        if (movementRequest.getIdMovement() != null) {
            movement = movementRepository.findById(movementRequest.getIdMovement().intValue())
                    .orElseThrow(() -> new ResourceNotFoundException("Movement", "idMovement", movementRequest.getIdMovement()));
        } else {
            movement = new Movement();
        }

        movement.setAccount(accountRepository.findById(movementRequest.getIdAccount())
                .orElseThrow(() -> new ResourceNotFoundException("Account", "idAccount", movementRequest.getIdAccount())));

        movement.setCategory(categoryRepository.findById(movementRequest.getIdCategory())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "idCategory", movementRequest.getIdCategory())));

        if (movementRequest.getIdSubCategory() != null) {
            movement.setSubCategory(subCategoryRepository.findById(movementRequest.getIdSubCategory())
                    .orElseThrow(() -> new ResourceNotFoundException("SubCategory", "idSubCategory", movementRequest.getIdSubCategory())));
        } else {
            movement.setSubCategory(null);
        }

        movement.setType(movementRequest.getType());
        movement.setValue(BigDecimal.valueOf(movementRequest.getValue()));
        movement.setDateMovement(movementRequest.getDateMovement());
        movement.setDescription(movementRequest.getDescription());
        movement.setUser(movementRequest.getUser());
        movement.setTransferReference(movementRequest.getTransferReference());

        return movementRepository.save(movement);
    }

    @Override
    public Movement getMovementById(Integer idMovement) {
        return movementRepository.findById(idMovement).orElse(null);
    }
}
