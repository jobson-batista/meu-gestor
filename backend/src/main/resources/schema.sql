CREATE TABLE users (
  id UUID PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(150) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(20) CHECK (role IN ('user', 'administrator'))
);

CREATE TABLE debts (
  id UUID PRIMARY KEY,
  amount NUMERIC NOT NULL,
  due_date DATE NOT NULL,
  creditor_id UUID REFERENCES users(id),
  debtor_id UUID REFERENCES users(id),
  status VARCHAR(20) CHECK (status IN ('pending', 'paid')),
  interest_rate NUMERIC
);

CREATE TABLE payments (
  id UUID PRIMARY KEY,
  debt_id UUID REFERENCES debts(id),
  amount_paid NUMERIC NOT NULL,
  payment_date DATE NOT NULL
);
