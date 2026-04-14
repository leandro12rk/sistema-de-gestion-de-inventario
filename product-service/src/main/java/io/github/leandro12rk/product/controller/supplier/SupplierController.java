package io.github.leandro12rk.product.controller.supplier;


import io.github.leandro12rk.product.model.supplier.SupplierModel;
import io.github.leandro12rk.product.projection.supplier.SupplierNameProjection;
import io.github.leandro12rk.product.repository.supplier.SupplierRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    SupplierRepository supplierRepository;
    public SupplierController(SupplierRepository supplierRepository){
        this.supplierRepository = supplierRepository;
    }


    @GetMapping
    public List<SupplierModel> findAll(){
        return supplierRepository.findAll();
    }

    @GetMapping("/supplierId")
    public SupplierModel findById(@PathVariable("supplierId") Long supplierId){
        return supplierRepository.findById(supplierId).orElse(null);
    }

    @GetMapping("/{supplierId}/name")
    public ResponseEntity<SupplierNameProjection> getSupplierName(@PathVariable Long supplierId) {
        return supplierRepository.findCompanyNameProjectionById(supplierId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PutMapping
    public SupplierModel UpdateSupplier(@RequestBody SupplierModel supplier){
        return supplierRepository.save(supplier);
    }
    @PostMapping
    public SupplierModel CreateSupplier(@RequestBody SupplierModel supplier){
        return supplierRepository.save(supplier);
    }

}
