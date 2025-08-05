-- Données pour preprod
INSERT INTO donor (id, full_name, email) VALUES
    ('d1', 'Test User', 'test@hei.school');

INSERT INTO payment (id, amount, method, payment_date, status) VALUES
    ('p1', 1000.00, 'Orange Money', '2025-07-01 10:00:00', 'SUCCEEDED');

INSERT INTO donation (id, donor_id, payment_id, created_at) VALUES
    ('don1', 'd1', 'p1', '2025-07-01 10:05:00');

-- Pour prod, vous auriez un fichier différent avec des données réelles