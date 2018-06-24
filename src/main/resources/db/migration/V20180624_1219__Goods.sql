ALTER TABLE ajax.goods ADD COLUMN brand_id INTEGER DEFAULT NULL;
ALTER TABLE ajax.goods ADD CONSTRAINT brand_id_fk FOREIGN KEY (brand_id) REFERENCES ajax.brand (id);
