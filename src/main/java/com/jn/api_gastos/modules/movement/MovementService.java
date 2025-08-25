package com.jn.api_gastos.modules.movement;

import com.jn.api_gastos.auth.AuthenticatedUser;
import com.jn.api_gastos.modules.account.AccountRepository;
import com.jn.api_gastos.modules.category.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovementService implements IMovementService{

    @Autowired
    private MovementRepository movementRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthenticatedUser authenticatedUser;

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
                        movement.getAccount(),
                        movement.getCategory(),
                        movement.getSubCategory()
                ))
                .toList();
    }

    @Override
    public Movement saveMovement(MovementRequestDTO movement) {
        return null;
    }

    @Override
    public Movement getMovementById(Integer idMovement) {
        return null;
    }
}
