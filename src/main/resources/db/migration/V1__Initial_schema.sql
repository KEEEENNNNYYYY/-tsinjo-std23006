-- Tables de base
CREATE TABLE donor (
                       id VARCHAR(255) PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE beneficiary (
                             id VARCHAR(255) PRIMARY KEY,
                             full_name VARCHAR(255) NOT NULL,
                             email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE payment (
                         id VARCHAR(255) PRIMARY KEY,
                         amount DECIMAL(15,2) NOT NULL,
                         method VARCHAR(50) NOT NULL,
                         payment_date TIMESTAMP NOT NULL,
                         status VARCHAR(20) NOT NULL DEFAULT 'VERIFYING'
);

CREATE TABLE donation (
                          id VARCHAR(255) PRIMARY KEY,
                          donor_id VARCHAR(255) NOT NULL REFERENCES donor(id),
                          payment_id VARCHAR(255) NOT NULL REFERENCES payment(id),
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE help (
                      id VARCHAR(255) PRIMARY KEY,
                      beneficiary_id VARCHAR(255) NOT NULL REFERENCES beneficiary(id),
                      payment_id VARCHAR(255) NOT NULL REFERENCES payment(id),
                      accident_description TEXT NOT NULL,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Index pour les requêtes fréquentes
CREATE INDEX idx_donation_created_at ON donation(created_at);
CREATE INDEX idx_help_created_at ON help(created_at);