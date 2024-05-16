ALTER TABLE ordr
    ADD COLUMN count integer check ( count >= 1 ) not null default 1;