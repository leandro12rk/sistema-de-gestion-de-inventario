DROP TABLE IF EXISTS stock_movements;
DROP TABLE IF EXISTS goods_receipts;
DROP TABLE IF EXISTS inventory;

-- Tabla maestra de inventario (Stock actual)
CREATE TABLE inventory
(
    id           SERIAL PRIMARY KEY,
    product_id   INTEGER NOT NULL UNIQUE,
    quantity     INTEGER NOT NULL DEFAULT 0 CHECK (quantity >= 0),
    last_updated TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de recepción de mercancía (Encabezado)
CREATE TABLE goods_receipts
(
    id                SERIAL PRIMARY KEY,
    purchase_order_id INTEGER      NOT NULL,
    received_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    received_by       VARCHAR(100) NOT NULL
);

/*
El campo type (La lógica de movimiento)
IN (Entrada): Representa mercancía que ingresa a tu bodega. Aumenta la cantidad total en tu tabla inventory.

OUT (Salida): Representa mercancía que sale de tu bodega (ventas, despachos a sucursales, devoluciones a proveedores). Disminuye la cantidad total.

ADJUSTMENT (Ajuste): Se usa para corregir discrepancias. Si el sistema dice que tienes 10, pero físicamente cuentas 9, haces un ajuste de -1.
 */


-- Movimientos detallados (Histórico/Kardex)
CREATE TABLE stock_movements
(
    id               SERIAL PRIMARY KEY,
    product_id       INTEGER NOT NULL,
    goods_receipt_id INTEGER REFERENCES goods_receipts (id), -- Conexión directa a recepción
    type             VARCHAR(10) CHECK (type IN ('IN', 'OUT', 'ADJUSTMENT')),
    amount           INTEGER NOT NULL,
    reason           TEXT,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO inventory (product_id, quantity)
VALUES (1, 15),  -- MacBook Air M2
       (2, 8),   -- Dell XPS 13
       (11, 20), -- Monitores LG 4K
       (21, 50), -- SSD Samsung 980 Pro
       (31, 12), -- Procesadores Ryzen 7
       (41, 30), -- Mouse Logitech G Pro
       (51, 25), -- Routers TP-Link
       (61, 10), -- Impresoras Epson L3210
       (71, 3),  -- Servidor Dell R750
       (81, 100);
-- Licencias Windows 11


-- Registramos las recepciones primero
INSERT INTO goods_receipts (id, purchase_order_id, received_by)
VALUES (1, 1, 'Leandro R.'),
       (2, 4, 'Carlos M.'),
       (3, 4, 'Carlos M.');


INSERT INTO stock_movements (product_id, goods_receipt_id, type, amount, reason)
VALUES
    -- Entradas asociadas a recepciones
    (1, 1, 'IN', 15, 'Carga inicial de inventario'),
    (41, 2, 'IN', 30, 'Abastecimiento mensual Logitech'),
    -- Movimientos sin recepción previa (ajustes o salidas)
    (2, 3, 'IN', 10, 'Entrada por Orden de Compra #2'),
    (11, NULL, 'OUT', 2, 'Venta directa a cliente corporativo'),
    (21, NULL, 'ADJUSTMENT', -1, 'Unidad dañada en bodega'),
    (51, NULL, 'OUT', 5, 'Despacho a sucursal vía España');


INSERT INTO stock_movements (product_id, type, amount, reason)
VALUES (1, 'IN', 15, 'Carga inicial de inventario'),
       (2, 'IN', 10, 'Entrada por Orden de Compra #2'),
       (11, 'OUT', 2, 'Venta directa a cliente corporativo'),
       (21, 'ADJUSTMENT', -1, 'Unidad dañada en bodega'),
       (41, 'IN', 30, 'Abastecimiento mensual Logitech'),
       (51, 'OUT', 5, 'Despacho a sucursal vía España');