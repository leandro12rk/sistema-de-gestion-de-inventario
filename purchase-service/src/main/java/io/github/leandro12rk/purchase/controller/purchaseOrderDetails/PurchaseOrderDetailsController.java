package io.github.leandro12rk.purchase.controller.purchaseOrderDetails;


import io.github.leandro12rk.purchase.client.product.ProductClient;
import io.github.leandro12rk.purchase.dto.product.ProductDTO;
import io.github.leandro12rk.purchase.model.purchaseOrderDetails.PurchaseOrderDetailsModel;
import io.github.leandro12rk.purchase.model.purchaseOrders.PurchaseOrdersModel;
import io.github.leandro12rk.purchase.respository.purchaseOrderDetails.PurchaseOrderDetailsRespository;
import io.github.leandro12rk.purchase.respository.purchaseOrders.PurchaseOrdersRespository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/purchase-order-details")
public class PurchaseOrderDetailsController {

    PurchaseOrderDetailsRespository purchaseOrderDetailsRespository;
    private final ProductClient  productClient;

    public PurchaseOrderDetailsController(ProductClient productClient,PurchaseOrderDetailsRespository purchaseOrdersDetailsRespository) {
        this.productClient = productClient;
        this.purchaseOrderDetailsRespository=purchaseOrdersDetailsRespository;
    }

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> findAll(){

        List<PurchaseOrderDetailsModel> purchaseOrderDetailsList = purchaseOrderDetailsRespository.findAll();

        List<Map<String, Object>> responseList = purchaseOrderDetailsList.stream().map(item -> {
            Map<String, Object> response = new HashMap<>();
            try {
                ProductDTO product = productClient.getProductName(item.getProduct_id());
                response.put("productName", product.getName());
            } catch (Exception e) {
                response.put("productName", "Nombre no disponible");
            }

            response.put("purchaseOrderDetailsId", item.getId());
            response.put("purchase_order_id", item.getPurchase_order_id());
            response.put("product_id", item.getProduct_id());
            response.put("quantity_ordered", item.getQuantity_ordered());
            response.put("unit_price", item.getUnit_price());

            return response;
        }).toList();

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{purchaseOrderDetailsId}")
    public ResponseEntity<List<Map<String, Object>>> findAll(@PathVariable Long purchaseOrderDetailsId){

        Optional<PurchaseOrderDetailsModel> purchaseOrderDetailsList = purchaseOrderDetailsRespository.findById(purchaseOrderDetailsId);

        List<Map<String, Object>> responseList = purchaseOrderDetailsList.stream().map(item -> {
            Map<String, Object> response = new HashMap<>();
            try {
                ProductDTO product = productClient.getProductName(item.getProduct_id());
                response.put("productName", product.getName());
            } catch (Exception e) {
                response.put("productName", "Nombre no disponible");
            }

            response.put("purchaseOrderDetailsId", item.getId());
            response.put("purchase_order_id", item.getPurchase_order_id());
            response.put("product_id", item.getProduct_id());
            response.put("quantity_ordered", item.getQuantity_ordered());
            response.put("unit_price", item.getUnit_price());

            return response;
        }).toList();

        return ResponseEntity.ok(responseList);
    }
}
