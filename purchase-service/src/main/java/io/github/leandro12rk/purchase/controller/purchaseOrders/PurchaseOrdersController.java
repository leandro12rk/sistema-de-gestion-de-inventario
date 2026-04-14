package io.github.leandro12rk.purchase.controller.purchaseOrders;



import io.github.leandro12rk.purchase.client.supplier.SupplierClient;
import io.github.leandro12rk.purchase.dto.supplier.SupplierDTO;
import io.github.leandro12rk.purchase.model.purchaseOrderDetails.PurchaseOrderDetailsModel;
import io.github.leandro12rk.purchase.model.purchaseOrders.PurchaseOrdersModel;
import io.github.leandro12rk.purchase.respository.purchaseOrders.PurchaseOrdersRespository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrdersController {
    private final SupplierClient supplierClient;
    PurchaseOrdersRespository purchaseOrdersRespository;

    public PurchaseOrdersController(SupplierClient supplierClient, PurchaseOrdersRespository purchaseOrdersRespository) {
        this.supplierClient = supplierClient;
        this.purchaseOrdersRespository = purchaseOrdersRespository;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> findAll() {

        List<PurchaseOrdersModel> purchaseOrderDetailsList = purchaseOrdersRespository.findAll();

        List<Map<String, Object>> responseList = purchaseOrderDetailsList.stream().map(item -> {
            Map<String, Object> response = new HashMap<>();
            try {
                SupplierDTO supplier = supplierClient.getSupplierCompanyName(item.getSupplier_id());
                response.put("SupplierName", supplier.getCompanyName());
            } catch (Exception e) {
                response.put("SupplierName", "Nombre no disponible");
            }

            response.put("purchaseOrderID", item.getId());
            response.put("purchase_order_supplier_id", item.getSupplier_id());
            response.put("purchase_order_orderDate", item.getOrder_date());
            response.put("purchase_order_status", item.getStatus());
            response.put("purchase_order_total_ammount", item.getTotal_amount());

            return response;
        }).toList();

        return ResponseEntity.ok(responseList);
    }

}
