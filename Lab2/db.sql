CREATE TABLE invoice ( 
  id INT NOT NULL AUTO_INCREMENT, 
  ver INT NOT NULL DEFAULT 1, 
   num VARCHAR(10) NOT NULL, 
  idate DATE NOT NULL, 
  sname VARCHAR(50) NOT NULL, 
  saddr VARCHAR(90) NOT NULL, 
  total DECIMAL NOT NULL DEFAULT 0, 
  PRIMARY KEY (id) 
); 

CREATE TABLE invoice_line ( 
  id INT NOT NULL, 
  ord INT NOT NULL, 
  prod VARCHAR(100) NOT NULL,
  qty DECIMAL NOT NULL, 
  price DECIMAL NOT NULL, 
  PRIMARY KEY (id, ord) 
); 
ALTER TABLE invoice_line 
  ADD FOREIGN KEY (id) REFERENCES invoice(id 
    ON DELETE CASCADE; 