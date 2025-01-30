CREATE TABLE "customer_transactions" (
   id BIGSERIAL PRIMARY KEY,
   customer_id INTEGER not null references customers(id),
   transaction_date TIMESTAMPTZ not null
)

