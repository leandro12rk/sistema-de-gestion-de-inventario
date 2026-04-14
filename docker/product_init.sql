DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS suppliers;
DROP TABLE IF EXISTS categories;

CREATE TABLE suppliers
(
    id           SERIAL PRIMARY KEY,
    company_name VARCHAR(150) NOT NULL,
    contact_name VARCHAR(100),
    email        VARCHAR(100),
    phone        VARCHAR(20),
    address      TEXT,
    status       BOOLEAN   DEFAULT TRUE,
    country      VARCHAR(100),
    city         VARCHAR(100),
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    status      BOOLEAN   DEFAULT TRUE,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products
(
    id          SERIAL PRIMARY KEY,
    sku         VARCHAR(20)    NOT NULL UNIQUE,
    name        VARCHAR(100)   NOT NULL,
    description TEXT,
    price       NUMERIC(10, 2) NOT NULL,
    category_id INTEGER REFERENCES categories (id),
    supplier_id INTEGER REFERENCES suppliers (id),
    status      BOOLEAN   DEFAULT TRUE,
    CONSTRAINT check_positive_price CHECK (price >= 0),
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);

-- Índices para mejorar rendimiento de consultas JOIN
CREATE INDEX idx_products_category ON products (category_id);
CREATE INDEX idx_products_supplier ON products (supplier_id);


--Trigger
-- Crear la función que actualiza el timestamp
CREATE OR REPLACE FUNCTION update_timestamp_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ language 'plpgsql';

-- Aplicar el trigger a tu tabla de productos
CREATE TRIGGER update_products_modtime
    BEFORE UPDATE ON products
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp_column();
-- Aplicar el trigger a tu tabla de productos
CREATE TRIGGER update_products_modtime
    BEFORE UPDATE ON categories
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp_column();

-- Aplicar el trigger a tu tabla de productos
CREATE TRIGGER update_products_modtime
    BEFORE UPDATE ON suppliers
    FOR EACH ROW
    EXECUTE FUNCTION update_timestamp_column();




-- Categorías
INSERT INTO categories (name, description, status)
VALUES ('Laptops', 'Equipos portátiles para oficina y gaming', true),
       ('Monitores', 'Pantallas LED, IPS y 4K', true),
       ('Almacenamiento', 'Discos SSD, HDD y memorias USB', true),
       ('Componentes', 'Procesadores, RAM y tarjetas de video', true),
       ('Periféricos', 'Teclados, mouses y diademas', true),
       ('Redes', 'Routers, switches y cableado', true),
       ('Impresión', 'Impresoras láser y tinta continua', true),
       ('Servidores', 'Equipos de rack y torres de datos', true),
       ('Software', 'Licencias de SO y suites de oficina', true),
       ('Accesorios', 'Cables, mochilas y adaptadores', true);

-- Proveedores (Con datos de ciudad y país)
INSERT INTO suppliers (company_name, contact_name, email, phone, address, city, country, status)
VALUES ('Intcomex Panamá', 'Ricardo Pérez', 'ventas@intcomex.com.pa', '+507 300-1234', 'Costa del Este', 'Panamá',
        'Panamá', true),
       ('Solutek Panamá', 'María Castro', 'soporte@solutek.com.pa', '+507 200-5678', 'Vía España', 'Panamá', 'Panamá',
        true),
       ('Dell Technologies', 'Carlos Ruiz', 'panama_sales@dell.com', '+507 800-DELL', 'Panamá Pacífico', 'Arraiján',
        'Panamá', true),
       ('HP Inc Panamá', 'Elena Gómez', 'contacto@hp.pa', '+507 260-1122', 'Torre de las Américas', 'Panamá', 'Panamá',
        true),
       ('Logitech Latam', 'Luis Méndez', 'distribucion@logitech.com', '+507 6543-2100', 'Zona Libre Colón', 'Colón',
        'Panamá', true),
       ('Kingston Technology', 'Ana Ríos', 'latam_orders@kingston.com', '+507 321-0987', 'Área Bancaria', 'Panamá',
        'Panamá', true),
       ('Cisco Systems PA', 'Jorge Vega', 'partners@cisco.pa', '+507 210-4455', 'Oceania Business Plaza', 'Panamá',
        'Panamá', true),
       ('Microsoft Panamá', 'Sofía Alba', 'licenciamiento@microsoft.pa', '+507 301-9900', 'Calle 50', 'Panamá',
        'Panamá', true),
       ('Samsung Electronics', 'Kevin Chan', 'b2b.pa@samsung.com', '+507 205-1000', 'Multiplaza', 'Panamá', 'Panamá',
        true),
       ('TP-Link Panamá', 'Raúl Díaz', 'sales.pa@tp-link.com', '+507 390-6677', 'Obarrio', 'Panamá', 'Panamá', true);



INSERT INTO products (sku, name, description, price, category_id, supplier_id)
VALUES
-- Laptops (category_id: 1)
('LAP-APPLE-M2', 'MacBook Air M2 13"', '8GB RAM, 256GB SSD, Medianoche', 1099.00, 1, 1),
('LAP-DELL-XPS13', 'Dell XPS 13 9315', 'Intel Core i7, 16GB RAM, 512GB SSD', 1250.00, 1, 3),
('LAP-HP-ENVY13', 'HP Envy x360', 'Ryzen 7, 16GB RAM, 1TB SSD, Touch', 950.00, 1, 4),
('LAP-ASUS-G14', 'ASUS ROG Zephyrus G14', 'Ryzen 9, RTX 4060, 16GB RAM', 1450.00, 1, 1),
('LAP-LEN-X1C', 'Lenovo ThinkPad X1 Carbon', 'Intel i7 12va Gen, 16GB, LTE', 1600.00, 1, 2),
('LAP-MSI-GF63', 'MSI GF63 Thin', 'Intel i5, GTX 1650, 8GB RAM', 750.00, 1, 1),
('LAP-APPLE-M3P', 'MacBook Pro 14" M3', '18GB RAM, 512GB SSD, Negro Espacial', 1999.00, 1, 1),
('LAP-DELL-LAT', 'Dell Latitude 5430', 'Core i5, 8GB RAM, 256GB SSD Pro', 890.00, 1, 3),
('LAP-SURF-L5', 'Microsoft Surface Laptop 5', '13.5", Intel i5, 8GB, 256GB', 999.00, 1, 8),
('LAP-ACER-SW5', 'Acer Swift 5', 'Intel Evo i7, 16GB RAM, 1TB SSD', 1100.00, 1, 2),

-- Monitores (category_id: 2)
('MON-LG-27UL', 'Monitor LG 27" 4K', 'IPS, HDR400, USB-C Charging', 380.00, 2, 9),
('MON-SAMS-OD5', 'Samsung Odyssey G5 27"', 'Curvo, 144Hz, QHD, 1ms', 320.00, 2, 9),
('MON-DELL-U27', 'Dell UltraSharp U2723QE', '4K, IPS Black, Hub USB-C', 580.00, 2, 3),
('MON-ASUS-VG', 'ASUS TUF Gaming 24"', '165Hz, Full HD, FreeSync', 195.00, 2, 1),
('MON-HP-M27', 'HP M27f FHD', 'Diseño ultra delgado, 75Hz', 165.00, 2, 4),
('MON-VIEW-VP', 'ViewSonic VP2756-4K', 'Validado por Pantone, Ergonómico', 450.00, 2, 1),
('MON-GIG-M27Q', 'Gigabyte M27Q', 'QHD, 170Hz, KVM integrado', 340.00, 2, 2),
('MON-MSI-MP', 'MSI Modern MD241P', 'FHD, Pivotable, USB-C', 175.00, 2, 1),
('MON-BENQ-PD', 'BenQ PD2700Q', '2K Designer Monitor, 100% sRGB', 399.00, 2, 1),
('MON-LG-34WL', 'LG UltraWide 34"', '21:9 ratio, Multitarea', 450.00, 2, 9),

-- Almacenamiento (category_id: 3)
('SSD-SAM-980P', 'Samsung 980 Pro 1TB', 'NVMe Gen4, Lectura 7000MB/s', 115.00, 3, 9),
('SSD-KIN-NV2', 'Kingston NV2 2TB', 'NVMe PCIe 4.0, Económico', 130.00, 3, 6),
('SSD-WD-BLACK', 'WD Black SN850X 1TB', 'Heatsink incluido, Gaming', 125.00, 3, 2),
('HDD-SEA-IB4', 'Seagate IronWolf 4TB', 'Para sistemas NAS, 5900 RPM', 95.00, 3, 1),
('SSD-CRU-MX', 'Crucial MX500 1TB', 'SATA III 2.5", Confiable', 75.00, 3, 2),
('EXT-LAC-2TB', 'LaCie Rugged Mini 2TB', 'Resistente a golpes y agua', 110.00, 3, 1),
('USB-KIN-64', 'Kingston DataTraveler 64GB', 'USB 3.2 Gen 1, Metal', 12.00, 3, 6),
('SSD-SAM-T7', 'Samsung T7 Shield 1TB', 'SSD Externo, IP65 Rugged', 105.00, 3, 9),
('SSD-COR-MP6', 'Corsair MP600 Pro 2TB', 'Gen4 PCIe x4 NVMe SSD', 190.00, 3, 2),
('HDD-WD-BLUE', 'WD Blue 2TB HDD', '7200 RPM, Almacenamiento PC', 65.00, 3, 2),

-- Componentes (category_id: 4)
('CPU-AMD-R7', 'AMD Ryzen 7 7800X3D', 'El mejor para gaming, AM5', 420.00, 4, 1),
('CPU-INT-I9', 'Intel Core i9-14900K', '24 Cores, Hasta 6.0GHz', 590.00, 4, 1),
('GPU-ASUS-407', 'ASUS Dual RTX 4070', '12GB GDDR6X, DLSS 3', 650.00, 4, 1),
('RAM-COR-32', 'Corsair Vengeance DDR5 32GB', '5600MHz, C36, Black', 145.00, 4, 2),
('MOBO-ROG-B6', 'ASUS ROG Strix B650-A', 'WiFi 6E, AM5, ATX White', 240.00, 4, 1),
('PSU-EVGA-85', 'EVGA SuperNOVA 850G', '80+ Gold, Fully Modular', 135.00, 4, 2),
('CASE-NZXT-H5', 'NZXT H5 Flow Black', 'Torre media, flujo aire alto', 95.00, 4, 2),
('COOL-NZX-K6', 'NZXT Kraken 240', 'AIO Liquid Cooler con pantalla', 140.00, 4, 2),
('GPU-MSI-306', 'MSI Ventus 2X RTX 3060', '12GB GDDR6, Compacta', 320.00, 4, 1),
('RAM-KIN-16', 'Kingston Fury Renegade 16GB', '6000MHz DDR5 RGB', 85.00, 4, 6),

-- Periféricos (category_id: 5)
('PER-LOG-GPR', 'Logitech G Pro X Superlight', 'Mouse Wireless, 63g', 125.00, 5, 5),
('PER-RAZ-HUN', 'Razer Huntsman V2', 'Teclado Óptico, Latencia Cero', 160.00, 5, 1),
('PER-LOG-MXM', 'Logitech MX Master 3S', 'Ergonómico, Scroll MagSpeed', 99.00, 5, 5),
('PER-COR-HS8', 'Corsair HS80 RGB Wireless', 'Audio Spatial, Hi-Fi', 135.00, 5, 2),
('PER-STE-ARC', 'SteelSeries Arctis Nova 7', 'Multiplataforma Wireless', 170.00, 5, 1),
('PER-LOG-C92', 'Logitech C920s Pro HD', 'Webcam 1080p con obturador', 75.00, 5, 5),
('PER-HYP-QUA', 'HyperX QuadCast S', 'Micrófono USB con iluminación RGB', 150.00, 5, 1),
('PER-KEY-K2', 'Keychron K2 V2', 'Teclado Mecánico Wireless', 95.00, 5, 2),
('PER-LOG-G50', 'Logitech G502 Hero', 'Sensor 25K, Pesas ajustables', 55.00, 5, 5),
('PER-RAZ-DEA', 'Razer DeathAdder V3 Pro', 'Ergonómico Profesional', 130.00, 5, 1),

-- Redes (category_id: 6)
('NET-TP-AX55', 'TP-Link Archer AX55', 'Router WiFi 6 AX3000', 110.00, 6, 10),
('NET-CIS-C92', 'Cisco Catalyst 9200L', 'Switch 24 Puertos Managed', 2400.00, 6, 7),
('NET-UBI-UDM', 'Ubiquiti Dream Machine', 'Router All-in-one Enterprise', 350.00, 6, 1),
('NET-TP-DECO', 'TP-Link Deco X50 (3-Pack)', 'Sistema WiFi Mesh AX3000', 280.00, 6, 10),
('NET-ASU-AX8', 'ASUS RT-AX88U Pro', 'Router Gaming Dual-Band', 299.00, 6, 1),
('NET-NET-NIG', 'Netgear Nighthawk M6', 'Hotspot 5G Pro', 650.00, 6, 2),
('NET-TP-SG10', 'TP-Link 8-Port Gigabit', 'Switch no administrado metal', 35.00, 6, 10),
('NET-CIS-CBS', 'Cisco Business 250', 'Smart Switch 24 Puertos', 450.00, 6, 7),
('NET-LIN-MX5', 'Linksys Velop Pro 6E', 'Tri-Band Mesh System', 320.00, 6, 1),
('NET-UBI-U6P', 'Ubiquiti UniFi U6 Pro', 'Access Point WiFi 6', 185.00, 6, 1),

-- Impresión (category_id: 7)
('PRN-EPS-L32', 'Epson EcoTank L3210', 'Tinta continua, Multifuncional', 195.00, 7, 1),
('PRN-HP-LJ40', 'HP LaserJet Pro M404dn', 'Láser monocromática veloz', 280.00, 7, 4),
('PRN-CAN-G31', 'Canon PIXMA G3160', 'Tanque de tinta con WiFi', 185.00, 7, 1),
('PRN-BRO-T45', 'Brother DCP-T420W', 'Inyección de tinta compacta', 165.00, 7, 2),
('PRN-HP-SMRT', 'HP Smart Tank 515', 'Escaneo móvil, Tanque tinta', 210.00, 7, 4),
('PRN-EPS-L18', 'Epson EcoTank L1800', 'Impresora fotográfica A3', 650.00, 7, 1),
('PRN-ZEB-ZD2', 'Zebra ZD220 Desktop', 'Impresora de etiquetas térmica', 150.00, 7, 1),
('PRN-KYOC-P2', 'Kyocera Ecosys P2040dw', 'Láser Profesional Oficina', 340.00, 7, 2),
('PRN-HP-LJ50', 'HP Color LaserJet Enterprise', 'Láser color alta gama', 780.00, 7, 4),
('PRN-PAN-MB2', 'Panasonic KX-MB2085', 'Multifuncional con Fax', 220.00, 7, 1),

-- Servidores (category_id: 8)
('SRV-DEL-R75', 'Dell PowerEdge R750', 'Dual Xeon, 64GB RAM, Rack 2U', 5200.00, 8, 3),
('SRV-HP-DL38', 'HPE ProLiant DL380 Gen11', 'Intel Scalable 4ta Gen', 4800.00, 8, 4),
('SRV-DEL-T35', 'Dell PowerEdge T350', 'Servidor Torre, Xeon E', 1450.00, 8, 3),
('SRV-LEN-ST5', 'Lenovo ThinkSystem ST50', 'Ideal para PYMES', 950.00, 8, 2),
('SRV-QNAP-TS', 'QNAP TS-464 NAS', '4 Bahías, Intel Quad-Core', 620.00, 8, 1),
('SRV-SYN-DS9', 'Synology DiskStation DS923+', 'NAS para backups corporativos', 650.00, 8, 1),
('SRV-ASU-ESC', 'ASUS ESC4000 G4', 'Servidor optimizado para GPU', 3800.00, 8, 1),
('SRV-CIS-UCS', 'Cisco UCS C220 M6', 'Rack compacto alto rendimiento', 4100.00, 8, 7),
('SRV-SUP-813', 'Supermicro SuperServer', '1U Rackmount, Dual NVMe', 2100.00, 8, 2),
('SRV-HP-ML30', 'HPE ProLiant ML30 Gen10', 'Torre robusta económica', 1100.00, 8, 4),

-- Software (category_id: 9)
('SW-MS-W11P', 'Windows 11 Pro OEM', 'Licencia perpetua 1 PC', 145.00, 9, 8),
('SW-MS-O365', 'Microsoft 365 Business Standard', 'Suscripción anual, 1 usuario', 150.00, 9, 8),
('SW-AD-CC1Y', 'Adobe Creative Cloud 1 Año', 'Suite completa diseño', 599.00, 9, 8),
('SW-VS-2022', 'Visual Studio Professional', 'Licencia anual dev', 499.00, 9, 8),
('SW-KS-INTE', 'Kaspersky Internet Security', '3 Dispositivos, 1 Año', 45.00, 9, 2),
('SW-BIT-TOT', 'Bitdefender Total Security', '5 Dispositivos, Multi-OS', 55.00, 9, 1),
('SW-VMW-WRK', 'VMware Workstation Pro', 'Virtualización avanzada', 199.00, 9, 1),
('SW-AUTO-CAD', 'AutoCAD LT 1 Año', 'Dibujo 2D profesional', 480.00, 9, 2),
('SW-SLK-PRO', 'Slack Pro Anual', 'Comunicación equipo', 96.00, 9, 8),
('SW-ZOO-BUS', 'Zoom Business Plan', 'Hasta 300 participantes', 199.00, 9, 8),

-- Accesorios (category_id: 10)
('ACC-BEL-7OU', 'Belkin 7-Outlet Surge', 'Protector sobretensión', 25.00, 10, 1),
('ACC-UG-HUB', 'Ugreen USB-C Hub 7-in-1', '4K HDMI, PD 100W', 45.00, 10, 2),
('ACC-TAR-BAC', 'Targus Drifter II 17"', 'Mochila reforzada laptop', 85.00, 10, 1),
('ACC-LOG-H39', 'Logitech H390 Headset', 'USB con cancelación ruido', 35.00, 10, 5),
('ACC-SAM-EVO', 'Samsung EVO Select 256GB', 'MicroSDXC con adaptador', 30.00, 10, 9),
('ACC-SON-WH1', 'Sony WH-1000XM5', 'Audífonos Noise Cancelling', 340.00, 10, 1),
('ACC-APP-PCL', 'Apple Pencil 2da Gen', 'Para iPad Pro/Air', 125.00, 10, 1),
('ACC-WD-MYP', 'WD My Passport 5TB', 'Disco externo portable', 140.00, 10, 2),
('ACC-HYP-FURY', 'HyperX Pulsefire Mat L', 'Mousepad tela profesional', 25.00, 10, 1),
('ACC-KIN-A40', 'Kingston Workflow Station', 'Hub para creadores de contenido', 99.00, 10, 6);

