-- Agregar datos a la BBDD BRANDS
INSERT INTO brands (id, name) VALUES (1, 'Zara');

-- Agregar datos a la BBDD PRICES
INSERT INTO prices
  (brand_id, product_id, start_date, end_date, priority, price, price_list, curr)
  VALUES
  (1, 35455, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 0, 35.50, 1, 'EUR');

INSERT INTO prices
  (brand_id, product_id, start_date, end_date, priority, price, price_list, curr)
  VALUES
  (1, 35455, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 1, 25.45, 2, 'EUR');

INSERT INTO prices
  (brand_id, product_id, start_date, end_date, priority, price, price_list, curr)
  VALUES
  (1, 35455, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 1, 30.50, 3, 'EUR');

INSERT INTO prices
  (brand_id, product_id, start_date, end_date, priority, price, price_list, curr)
  VALUES
  (1, 35455, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 1, 38.95, 4, 'EUR');

