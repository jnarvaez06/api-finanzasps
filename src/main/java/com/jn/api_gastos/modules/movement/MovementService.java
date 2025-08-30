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

        Account account = accountRepository.findById(movementRequest.getIdAccount())
                .orElse(null);
        System.out.println("account "+account);
        if (account == null) {
            throw new ResourceNotFoundException("Account", "idAccount", movementRequest.getIdAccount());
        }

        Category category = categoryRepository.findById(movementRequest.getIdCategory())
                .orElse(null);
        System.out.println("category "+category);
        if (category == null) {
            throw new ResourceNotFoundException("Category", "idCategory", movementRequest.getIdCategory());
        }

        SubCategory subCategory = subCategoryRepository.findById(movementRequest.getIdSubCategory())
                .orElse(null);

        if (subCategory == null) {
            throw new ResourceNotFoundException("SubCategory", "idSubCategory", movementRequest.getIdSubCategory());
        }
        Movement movement = Movement.builder()
                .account(account)
                .type(movementRequest.getType())
                .value(BigDecimal.valueOf(movementRequest.getValue()))
                .dateMovement(movementRequest.getDateMovement())
                .description(movementRequest.getDescription())
                .category(category)
                .subCategory(subCategory)
                .user(movementRequest.getUser())
                .build();

        return movementRepository.save(movement);
    }

    @Override
    public Movement getMovementById(Integer idMovement) {
        return null;
    }
}
