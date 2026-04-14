DROP TABLE IF EXISTS purchase_order_details;
DROP TABLE IF EXISTS purchase_orders;

-- Estructura
CREATE TABLE purchase_orders
(
    id           SERIAL PRIMARY KEY,
    order_code   VARCHAR(20) UNIQUE NOT NULL, -- Ej: 'OC-2026-001'
    supplier_id  INTEGER NOT NULL,          -- Referencia al servicio de proveedores
    order_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status       VARCHAR(20) CHECK (status IN ('PENDING', 'APPROVED', 'RECEIVED', 'CANCELLED')),
    total_amount NUMERIC(12, 2) DEFAULT 0.00
);

CREATE TABLE purchase_order_details
(
    id                SERIAL PRIMARY KEY,
    purchase_order_id INTEGER NOT NULL REFERENCES purchase_orders(id) ON DELETE CASCADE,
    product_id        INTEGER NOT NULL, -- Referencia al servicio de productos
    quantity_ordered  INTEGER NOT NULL CHECK (quantity_ordered > 0),
    unit_price        NUMERIC(10, 2) NOT NULL,
    subtotal          NUMERIC(12, 2) GENERATED ALWAYS AS (quantity_ordered * unit_price) STORED
);

-- Creación de índices para optimizar las consultas de historial que mencionaste
CREATE INDEX idx_po_supplier ON purchase_orders(supplier_id);
CREATE INDEX idx_pod_product ON purchase_order_details(product_id);


--- Inserción de valores de ejemplo
INSERT INTO purchase_orders (order_code, supplier_id, status, total_amount)
VALUES ('OC-2026-001', 1, 'RECEIVED', 5495.00),
       ('OC-2026-002', 3, 'APPROVED', 12500.00),
       ('OC-2026-003', 10, 'PENDING', 550.00),
       ('OC-2026-004', 5, 'RECEIVED', 1250.00);

INSERT INTO purchase_order_details (purchase_order_id, product_id, quantity_ordered, unit_price)
VALUES (1, 1, 5, 1099.00),
       (2, 2, 10, 1250.00),
       (3, 51, 5, 110.00),
       (4, 41, 10, 125.00);