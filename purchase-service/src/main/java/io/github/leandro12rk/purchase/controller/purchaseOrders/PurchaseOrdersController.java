package io.github.leandro12rk.purchase.controller.purchaseOrders;


import io.github.leandro12rk.purchase.respository.purchaseOrders.PurchaseOrdersRespository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrdersController {
    PurchaseOrdersRespository purchaseOrdersRespository;

    public PurchaseOrdersController(PurchaseOrdersRespository purchaseOrdersRespository){
        this.purchaseOrdersRespository=purchaseOrdersRespository;
    }
}
