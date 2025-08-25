package com.jn.api_gastos.modules.movement;

import java.util.List;

public interface IMovementService {

    public List<MovementDTO> listMovements();

    public Movement saveMovement(MovementRequestDTO movement);

    public Movement getMovementById(Integer idMovement);
}
