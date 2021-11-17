package org.digitalcrafting.eregold.api.transfers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/transfers")
public class TransfersController {
    private final TransfersControllerService service;

    // TODO
}
