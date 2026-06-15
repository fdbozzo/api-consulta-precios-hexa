CREATE TABLE IF NOT EXISTS brands (
  id INT NOT NULL AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS prices (
  brand_id INT NOT NULL,
  product_id INT NOT NULL,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  priority INT NOT NULL,
  price DECIMAL(10,2) NOT NULL,
  price_list INT NOT NULL,
  curr CHAR(3) NOT NULL,
  FOREIGN KEY(brand_id) REFERENCES brands(id)
);

CREATE INDEX IF NOT EXISTS Prices_Composite_Key ON prices(brand_id, product_id, start_date, end_date, priority DESC)
