package org.digitalcrafting.eregold.api.transfers;

import lombok.RequiredArgsConstructor;
import org.digitalcrafting.eregold.domain.transfers.TransferModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/transfers")
public class TransfersController {
    private final TransfersControllerService service;

    @PostMapping
    public void transfer(@RequestBody TransferModel request) {
        service.transfer(request);
    }
}
