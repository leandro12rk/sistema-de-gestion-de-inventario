package io.github.leandro12rk.purchase.controller.purchaseOrderDetails;


import io.github.leandro12rk.purchase.model.purchaseOrderDetails.PurchaseOrderDetailsModel;
import io.github.leandro12rk.purchase.model.purchaseOrders.PurchaseOrdersModel;
import io.github.leandro12rk.purchase.respository.purchaseOrders.PurchaseOrdersRespository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/purchase-order-details")
public class PurchaseOrderDetailsController {

    PurchaseOrdersRespository purchaseOrdersRespository;

    public PurchaseOrderDetailsController(PurchaseOrdersRespository purchaseOrdersRespository){
        this.purchaseOrdersRespository=purchaseOrdersRespository;
    }


}
