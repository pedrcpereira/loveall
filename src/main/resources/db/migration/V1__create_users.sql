create table users(
    id uuid primary key default uuidv7(),
    email varchar(254) not null,
    password_hash varchar(255) not null,
    name varchar(100) not null,
    created_at timestamptz not null,

    constraint uk_users_email unique (email),
    constraint ck_users_email_lowercase check (email = lower(email))
);
