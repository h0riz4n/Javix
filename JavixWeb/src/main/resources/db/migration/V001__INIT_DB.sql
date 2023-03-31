drop table if exists tic_tac_toe_game;
drop table if exists numbers_game;
drop table if exists player;

drop sequence if exists hibernate_sequence;
drop sequence if exists tic_tac_toe_sequence;
drop sequence if exists numbers_sequence;

create sequence hibernate_sequence start 1 increment 20;
create sequence tic_tac_toe_sequence start 1 increment 20;
create sequence numbers_sequence start 1 increment 20;

create table if not exists player (
    id integer not null,
    login varchar(25),
    password bytea not null,
    tg_id bigint not null,
    constraint player_pkey primary key (id),
    constraint player_tg_id unique (tg_id)
);

comment on table player is 'Предназначена для хранения пользователей.';

create table if not exists tic_tac_toe_game (
    id integer not null,
    score integer not null,
    tg_id bigint not null,
    constraint tic_tac_toe_pkey primary key (id),
    foreign key (tg_id) references player (tg_id) on delete cascade
);

comment on table tic_tac_toe_game is 'Предназначена для хранения результато в крестики-нолики.';

create table if not exists numbers_game (
    id integer not null,
    score integer not null,
    tg_id bigint not null,
    constraint numbers_pkey primary key (id),
    foreign key (tg_id) references player (tg_id) on delete cascade
);

comment on table numbers_game is 'Предназначена для хранения результатов в 2048.';
